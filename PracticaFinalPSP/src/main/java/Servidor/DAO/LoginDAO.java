package Servidor.DAO;

import Models.EmpleadoDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class LoginDAO extends AbstractDAO {

    public LoginDAO() throws SQLException {
        super();
    }

    public EmpleadoDTO login(int id) {
        EmpleadoDTO empleado = null;

        try (PreparedStatement pst = conexion.prepareStatement("SELECT * FROM empleados WHERE id = ?")) {

            pst.setInt(1,id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) empleado = new EmpleadoDTO(rs);

        } catch (Exception throwables) {
            throwables.printStackTrace();
        }

        return empleado;
    }

    public void actualizarFechaSesion(int id) {

        try (PreparedStatement pst = conexion.prepareStatement("UPDATE empleados SET ultima_sesion = ? WHERE id = ?")) {

            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            c.add(Calendar.HOUR, 1);

            pst.setTimestamp(1,new Timestamp(c.getTime().getTime()));
            pst.setInt(2,id);

            pst.executeUpdate();

        } catch (Exception throwables) {
            throwables.printStackTrace();
        }

    }
}
