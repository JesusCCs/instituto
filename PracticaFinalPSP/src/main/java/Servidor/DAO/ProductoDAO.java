package Servidor.DAO;

import Models.ProductoDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO extends AbstractDAO {

    public ProductoDAO() throws SQLException {
        super();
    }

    public List<ProductoDTO> get() {
        List<ProductoDTO> productos = null;

        try (PreparedStatement pst = conexion.prepareStatement("SELECT * FROM productos")) {

            ResultSet rs = pst.executeQuery();
            productos = new ArrayList<>();

            while (rs.next()) {
                productos.add(new ProductoDTO(rs));
            }

        } catch (Exception throwables) {
            throwables.printStackTrace();
        }

        return productos;
    }

    public ProductoDTO get(int id) {
        ProductoDTO producto = null;

        try (PreparedStatement pst = conexion.prepareStatement("SELECT * FROM productos WHERE id = ?")) {

            pst.setInt(1,id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) producto = new ProductoDTO(rs);

        } catch (Exception throwables) {
            throwables.printStackTrace();
        }

        return producto;
    }

    public boolean restarCantidad(int productoId, int cantidad) {

        try (PreparedStatement pst = conexion.prepareStatement("UPDATE productos SET cantidad_stock = cantidad_stock - ? WHERE id = ?")) {

            pst.setInt(1,cantidad);
            pst.setInt(2,productoId);
            pst.executeUpdate();

            return this.get(productoId).getCantidadStock() <= 0;

        } catch (Exception throwables) {
            throwables.printStackTrace();
        }


        return false;
    }

    public void regenerarStock(Integer productoId) {

        try (PreparedStatement pst = conexion.prepareStatement("UPDATE productos SET cantidad_stock = 50 WHERE id = ?")) {

            pst.setInt(1,productoId);
            pst.executeUpdate();

        } catch (Exception throwables) {
            throwables.printStackTrace();
        }

    }
}
