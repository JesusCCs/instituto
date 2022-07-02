package Models;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class ProductoDTO implements Serializable {

    private final int id;
    private final String nombre;
    private final int precioVenta;     // céntimos
    private final int precioProveedor; // céntimos
    private final int cantidadStock;

    public ProductoDTO(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.nombre = rs.getString("nombre");
        this.precioVenta = rs.getInt("precio_venta");
        this.precioProveedor = rs.getInt("precio_proveedor");
        this.cantidadStock = rs.getInt("cantidad_stock");
    }

    public ProductoDTO(ResultSet rs, String alias) throws SQLException {
        this.id = rs.getInt(alias + ".id");
        this.nombre = rs.getString(alias + ".nombre");
        this.precioVenta = rs.getInt(alias + ".precio_venta");
        this.precioProveedor = rs.getInt(alias + ".precio_proveedor");
        this.cantidadStock = rs.getInt(alias + ".cantidad_stock");
    }

    public void mostrarLineaMenu(int index) {
        String inicio = index + ". " + nombre;
        float precio = precioVenta/100f;
        String cantidad = "| " + cantidadStock + " unidad/es disponibles\n";

        System.out.printf("%-15s  -- %6.2f € %s",inicio,precio,cantidad);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductoDTO that = (ProductoDTO) o;
        return id == that.id;
    }

    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public int getPrecioVenta() {
        return precioVenta;
    }
    public int getPrecioProveedor() {
        return precioProveedor;
    }
    public int getCantidadStock() {
        return cantidadStock;
    }
}
