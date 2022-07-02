package Cliente;

import Models.CompraDTO;
import Models.EmpleadoDTO;
import Models.ProductoDTO;
import Utils.Commons;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.IntStream;

public class Caja {

    private final Socket cliente;
    private final OutputStream flujoSalida;
    private final InputStream flujoEntrada;
    private final Scanner scanner;

    private EmpleadoDTO empleado;

    public Caja() throws IOException {
        System.out.println("CAJA INICIADA....");
        cliente = new Socket(Acceso.HOST, Acceso.PUERTO);

        // CREO FLUJO DE SALIDA AL SERVIDOR
        flujoSalida = cliente.getOutputStream();

        // CREO FLUJO DE ENTRADA DEL SERVIDOR
        flujoEntrada = cliente.getInputStream();

        // SE CREA SCANNER
        scanner = new Scanner(System.in);
    }

    public void iniciar() throws IOException, ClassNotFoundException {
        System.out.println("Bienvenido");
        System.out.println("Indique su identificador personal");

        int id = Commons.leerNumero(scanner);
        new DataOutputStream(flujoSalida).writeUTF("Login;" + id);

        Object recibido = new ObjectInputStream(flujoEntrada).readObject();

        if (recibido == null) {
            System.out.println("No se ha encontrado ningún empleado con dicho identificador");
            System.out.println("Terminando programa ...");
            return;
        }

        empleado = (EmpleadoDTO) recibido;

        System.out.println("Bienvenido/a " + empleado.getNombre());

        System.out.println("¿Qué quieres hacer?");
        menu();
    }

    public void menu() throws IOException, ClassNotFoundException {
        System.out.println("1.- Cobrar compra");
        System.out.println("2.- Obtener caja del día");
        System.out.println("3.- Salir");

        int opcion = Commons.leerNumero(scanner, 3);

        switch (opcion) {
            case 1 -> cobrar();
            case 2 -> obtenerCaja();
            case 3 -> cerrar();
        }
    }

    private void cobrar() throws IOException, ClassNotFoundException {
        // Lo primero que se hace es pedir la lista de productos al servidor
        new DataOutputStream(flujoSalida).writeUTF("Productos");

        // Recibiremos dicha lista
        List<ProductoDTO> productos = (List<ProductoDTO>) new ObjectInputStream(flujoEntrada).readObject();

        // Se puede imprimir en pantalla el menú de compra
        System.out.println("ARTÍCULOS DE LOS BUENOS:");
        IntStream.range(0, productos.size()).forEachOrdered(i -> productos.get(i).mostrarLineaMenu(i + 1));
        System.out.println((productos.size() + 1) + ". Cancelar compra");

        Carrito carrito = new Carrito();

        boolean continuar;
        do {
            // Se pide lo que el usuario quiere hacer
            System.out.println("Seleccione el artículo que desea:");
            int respuesta = Commons.leerNumero(scanner, productos.size() + 1);

            // Si selecciona cancelar, se vuelve al menú principal
            if (respuesta == productos.size() + 1) menu();

            // En otro caso, se puede obtener el producto seleccionado
            ProductoDTO producto = productos.get(respuesta - 1);

            // Finalmente, se pregunta por la cantidad
            System.out.println("¿Cuántas unidades?");
            int cantidad = Commons.leerNumero(scanner);

            // Y se añade al carrito
            carrito.add(producto,cantidad);

            // Se muestra el carrito actual
            carrito.mostrar();

            // Se pide lo que el usuario quiere hacer
            System.out.println("¿Quiere seguir añadiendo productos?");
            System.out.println("1. Sí");
            System.out.println("2. No");
            continuar = Commons.leerNumero(scanner, 2) == 1;
        } while (continuar);


        new DataOutputStream(flujoSalida).writeUTF(carrito.toString());
        String respuestaServidor = new DataInputStream(flujoEntrada).readUTF();
        System.out.println(respuestaServidor);

        menu();
    }

    private void obtenerCaja() throws IOException, ClassNotFoundException {

        new DataOutputStream(flujoSalida).writeUTF("Detalles");
        Set<CompraDTO> compras = (Set<CompraDTO>) new ObjectInputStream(flujoEntrada).readObject();

        new DataOutputStream(flujoSalida).writeUTF("Caja");
        int caja = new DataInputStream(flujoEntrada).readInt();

        // Imprimimos los detalles de la compra
        System.out.println();
        System.out.println("Caja del " + Commons.fechaHoy());
        System.out.println("-------------------------------");

        if (compras.size() > 0) {
            compras.forEach(CompraDTO::mostrarDetallesCompra);
        } else {
            System.out.println("No hay compras por el momento");
        }

        System.out.println("-------------------------------");
        System.out.println();

        // Imprimimos la caja del día
        String mensaje = "*  La caja del día ha sido: " + caja + " €  *";
        System.out.println("*".repeat(mensaje.length()));
        System.out.println(mensaje);
        System.out.println("*".repeat(mensaje.length()));
        System.out.println();

        menu();
    }

    public void cerrar() {
        try {

            flujoEntrada.close();
            flujoSalida.close();
            cliente.close();

            System.out.println("Cerrando caja...");
            System.exit(0);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
