package Servidor;

import Servidor.DAO.CompraDAO;
import Servidor.DAO.DetalleDAO;
import Servidor.DAO.LoginDAO;
import Servidor.DAO.ProductoDAO;
import Models.CompraDTO;
import Models.EmpleadoDTO;
import Models.ProductoDTO;
import Utils.Mail;

import javax.mail.MessagingException;
import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class HiloServidor extends Thread {

    private final Socket cliente;
    private final OutputStream flujoSalida;
    private final InputStream flujoEntrada;
    private int empleadoId;

    private final String LOGIN = "Login";
    private final String COBRO = "Cobro";
    private final String CAJA = "Caja";
    private final String DETALLES = "Detalles";
    private final String PRODUCTOS = "Productos";

    public HiloServidor(Socket cliente) throws IOException {
        this.cliente = cliente;
        flujoEntrada = cliente.getInputStream();
        flujoSalida = cliente.getOutputStream();
    }

    @Override
    public void run() {
        try {

            while(true) {
                // Se debe leer primero lo que quiere hacer el usuario
                String[] entrada = new DataInputStream(flujoEntrada).readUTF().split(";");
                String accion = entrada[0];

                // Una vez leido, aplicamos la acción que desea
                switch (accion) {
                    case LOGIN -> this.login(entrada);
                    case COBRO -> this.cobrar(entrada);
                    case DETALLES -> this.obtenerDetalles();
                    case CAJA -> this.obtenerCaja();
                    case PRODUCTOS -> this.obtenerProductos();
                }
            }

        } catch (SocketException | EOFException e) { // Se pierde conexión con el cliente... se cierra
            this.cerrar();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void login(String[] entrada) throws IOException {
        empleadoId = Integer.parseInt(entrada[1]);

        try (LoginDAO dao = new LoginDAO()) {
            EmpleadoDTO empleado = dao.login(empleadoId);

            if (empleado != null) dao.actualizarFechaSesion(empleadoId);

            new ObjectOutputStream(flujoSalida).writeObject(empleado);

        } catch (SQLException throwables) {
            System.out.println("Error al intentar comprobar el login");
        }
    }

    private void cobrar(String[] entrada) throws IOException {
        try (DetalleDAO detalleDao = new DetalleDAO();
             CompraDAO compraDao = new CompraDAO();
             ProductoDAO productoDao = new ProductoDAO()) {

            // Creamos la compra
            int compraId = compraDao.create(empleadoId);

            if (compraId == 0) throw new SQLException();

            // Generamos la lista que nos indicará qué productos han pasado a tener stock negativo
            List<Integer> productosNegativos = new ArrayList<>();

            for (int i = 1; i < entrada.length; i+=2) {
                int productoId = Integer.parseInt(entrada[i]);
                int cantidad = Integer.parseInt(entrada[i + 1]);

                detalleDao.create(compraId,productoId,cantidad);

                boolean esNegativo = productoDao.restarCantidad(productoId,cantidad);

                if (esNegativo) productosNegativos.add(productoId);
            }

            if (!productosNegativos.isEmpty()) {
                mandarEmail(compraId,productosNegativos);
                regenerarStock(productosNegativos);
            }

            new DataOutputStream(flujoSalida).writeUTF("Compra realizada con éxito");

        } catch (SQLException | IOException throwables) {
            new DataOutputStream(flujoSalida).writeUTF("Error al realizar la compra");
        }
    }

    private void regenerarStock(List<Integer> productosNegativos) {

        try (ProductoDAO productoDao = new ProductoDAO()) {
            productosNegativos.forEach(productoDao::regenerarStock);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    private void mandarEmail(int compraId, List<Integer> productosNegativos) {
        try (CompraDAO compraDao = new CompraDAO();
             ProductoDAO productoDao = new ProductoDAO()) {
            StringBuilder sb = new StringBuilder();

            CompraDTO compra = compraDao.get(compraId);
            String horaCompra = compra.getFecha().format(DateTimeFormatter.ofPattern("HH:mm"));
            sb.append("La compra efectuada a las ").append(horaCompra).
                    append(" ha ocasionado que los siguientes productos agoten su stock: \n");

            productosNegativos.forEach(productoId -> {
                ProductoDTO producto = productoDao.get(productoId);
                int precioProveedor = producto.getPrecioProveedor()/100;

                sb.append(producto.getNombre()).append(" con coste de ").append(precioProveedor).append(" €\n");
            });

            Mail.enviarMail("Nos hemos quedado sin Stock",sb.toString());

        } catch (SQLException | MessagingException throwables) {
            throwables.printStackTrace();
        }
    }

    private void obtenerDetalles() throws IOException {
        try (CompraDAO compraDao = new CompraDAO()) {

            Set<CompraDTO> compras = compraDao.obtenerPorFecha(new Date(),empleadoId);
            new ObjectOutputStream(flujoSalida).writeObject(compras);

        } catch (SQLException | IOException throwables) {
            new DataOutputStream(flujoSalida).writeUTF("Error al conseguir los detalles de la compra");
        }
    }

    private void obtenerCaja() throws IOException {
        try (CompraDAO compraDao = new CompraDAO()) {

            int total = compraDao.obtenerCajaPorFecha(new Date(),empleadoId);
            new DataOutputStream(flujoSalida).writeInt(total);

        } catch (SQLException | IOException throwables) {
            new DataOutputStream(flujoSalida).writeUTF("Error al conseguir la caja");
        }
    }

    private void obtenerProductos() throws IOException {
        try (ProductoDAO dao = new ProductoDAO()) {

            List<ProductoDTO> productos = dao.get();
            new ObjectOutputStream(flujoSalida).writeObject(productos);

        } catch (SQLException throwables) {
            System.out.println("Error al intentar obtener los productos");
        }
    }

    private void cerrar() {
        try {
            flujoEntrada.close();
            flujoSalida.close();
            cliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
