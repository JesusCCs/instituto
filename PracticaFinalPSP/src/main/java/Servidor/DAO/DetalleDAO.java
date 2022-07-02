package Servidor.DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DetalleDAO extends AbstractDAO {

    public DetalleDAO() throws SQLException {
        super();
    }


    public void create(int compraId, int productoId, int cantidad) {

        try (PreparedStatement pst =
                     conexion.prepareStatement(
                             "INSERT INTO detalles VALUES (0,?,?,?)"
                     ))
        {
            pst.setInt(1,compraId);
            pst.setInt(2,productoId);
            pst.setInt(3,cantidad);
            pst.executeUpdate();

        } catch (Exception throwables) {
            throwables.printStackTrace();
        }

    }
}
