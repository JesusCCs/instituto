package Cliente;

import Models.ProductoDTO;

import java.util.HashMap;

public class Carrito {

    HashMap<ProductoDTO, Integer> carrito;

    public Carrito() {
        this.carrito = new HashMap<>();
    }

    public void add(ProductoDTO producto, int cantidad) {
        if (carrito.containsKey(producto)) {
            carrito.put(producto,carrito.get(producto) + cantidad);
            return;
        }
        carrito.put(producto,cantidad);
    }

    public void mostrar() {
        System.out.println("Compra actual:");

        carrito.forEach((producto, cantidad) -> {
            String unidades = "x" + cantidad;
            String precio = ((producto.getPrecioVenta() * cantidad) / 100) + " €";
            System.out.printf("%-5s -- %-15s %s\n",unidades,producto.getNombre(),precio);
        });
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Cobro;");

        carrito.forEach((producto, cantidad) -> {
            sb.append(producto.getId()).append(";").append(cantidad).append(";");
        });

        return sb.toString();
    }
}
