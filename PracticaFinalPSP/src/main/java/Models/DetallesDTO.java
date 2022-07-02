package Models;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DetallesDTO implements Serializable {

    private final int id;
    private final int cantidad;
    private final ProductoDTO producto;
    private CompraDTO compra;

    public DetallesDTO(ResultSet rs, String alias) throws SQLException {
        this.id = rs.getInt(alias + ".id");
        this.cantidad = rs.getInt(alias + ".cantidad");;
        this.producto = new ProductoDTO(rs,"P");
    }

    public int getId() {
        return id;
    }
    public int getCantidad() {
        return cantidad;
    }
    public ProductoDTO getProducto() {
        return producto;
    }
    public CompraDTO getCompra() {
        return compra;
    }
}
