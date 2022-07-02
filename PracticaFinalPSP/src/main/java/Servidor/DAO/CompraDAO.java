package Servidor.DAO;

import Models.CompraDTO;
import Models.DetallesDTO;
import Utils.Commons;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CompraDAO extends AbstractDAO {

    public CompraDAO() throws SQLException {
        super();
    }

    public int create(int empleadoId) {

        int compraId = 0;

        try (PreparedStatement pst =
                     conexion.prepareStatement(
                             "INSERT INTO compras (empleado_id) VALUES (?)", PreparedStatement.RETURN_GENERATED_KEYS
                     ))
        {
            pst.setInt(1,empleadoId);
            pst.executeUpdate();
            ResultSet rs = pst.getGeneratedKeys();
            rs.next();
            compraId = rs.getInt(1);

        } catch (Exception throwables) {
            throwables.printStackTrace();
        }

        return compraId;
    }

    public Set<CompraDTO> obtenerPorFecha(Date date,int empleadoId) {

        Set<CompraDTO> compras = new HashSet<>();

        try (PreparedStatement pst = conexion.prepareStatement(
                "SELECT C.*, D.*, P.* FROM compras C " +
                        "LEFT JOIN detalles D ON D.compra_id = C.id " +
                        "LEFT JOIN productos P ON D.producto_id = P.id " +
                        "WHERE DATE(C.fecha) = ? AND C.empleado_id = ? ORDER BY C.fecha"))
        {
            pst.setString(1, Commons.dateToDB(date));
            pst.setInt(2,empleadoId);
            ResultSet rs = pst.executeQuery();

            CompraDTO compra = rs.next() ? new CompraDTO(rs,"C") : null;

            if (compra == null) return compras;

            Set<DetallesDTO> detallesCompra = new HashSet<>();
            detallesCompra.add(new DetallesDTO(rs,"D"));

            while (rs.next()) {
                CompraDTO compraNueva = new CompraDTO(rs,"C");

                if (!compraNueva.equals(compra)) {
                    compra.setDetalles(detallesCompra);
                    compras.add(compra);
                    detallesCompra = new HashSet<>();
                }

                detallesCompra.add(new DetallesDTO(rs,"D"));
                compra = compraNueva;
            }

            compra.setDetalles(detallesCompra);
            compras.add(compra);


        } catch (Exception throwables) {
            throwables.printStackTrace();
        }

        return compras;
    }

    public int obtenerCajaPorFecha(Date date,int empleadoId) {

        int total = 0;

        try (PreparedStatement pst = conexion.prepareStatement(
                "SELECT SUM((P.precio_venta - P.precio_proveedor) * D.cantidad) AS total " +
                        "FROM compras C " +
                "LEFT JOIN detalles D ON D.compra_id = C.id " +
                "LEFT JOIN productos P ON D.producto_id = P.id " +
                "WHERE DATE(C.fecha) = ? AND C.empleado_id = ?"))
        {
            pst.setString(1, Commons.dateToDB(date));
            pst.setInt(2,empleadoId);
            ResultSet rs = pst.executeQuery();

            rs.next();

            total = (rs.getInt("total") / 100);

        } catch (Exception throwables) {
            throwables.printStackTrace();
        }

        return total;
    }

    public CompraDTO get(int id) {
        CompraDTO compra = null;

        try (PreparedStatement pst = conexion.prepareStatement("SELECT C.* FROM compras C WHERE id = ?")) {

            pst.setInt(1,id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) compra = new CompraDTO(rs,"C");

        } catch (Exception throwables) {
            throwables.printStackTrace();
        }

        return compra;
    }
}
