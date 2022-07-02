package Models;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class EmpleadoDTO implements Serializable {

    private final int id;
    private final String nombre;
    private final String apellidos;
    private final LocalDateTime ultimaSesion;
    private final LocalDateTime fechaContratacion;

    public EmpleadoDTO(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.nombre = rs.getString("nombre");
        this.apellidos = rs.getString("apellidos");

        Timestamp ultimaSesion = rs.getTimestamp("ultima_sesion");
        this.ultimaSesion = ultimaSesion == null ? null : ultimaSesion.toLocalDateTime();

        Timestamp fechaContratacion = rs.getTimestamp("fecha_contratacion");
        this.fechaContratacion = fechaContratacion == null ? null : fechaContratacion.toLocalDateTime();
    }

    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public String getApellidos() {
        return apellidos;
    }
    public LocalDateTime getUltimaSesion() {
        return ultimaSesion;
    }
    public LocalDateTime getFechaContratacion() {
        return fechaContratacion;
    }
    public String getNombreCompleto() {
        return nombre + " " + apellidos;
    }
}
