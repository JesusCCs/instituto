package Servidor.DAO;

import Config.Configuracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AbstractDAO implements AutoCloseable {

    protected Connection conexion;

    public AbstractDAO() throws SQLException {
        conexion = DriverManager.getConnection(Configuracion.URL, Configuracion.DatabaseUser, Configuracion.DatabasePass);
        System.out.println("Se abre la conexión con la DB");
    }

    @Override
    public void close() {
        try {
            conexion.close();
            System.out.println("Se cierra la conexión con la DB");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
