package DTO;

import java.sql.Date;
import java.util.Collection;

public class EmpleadosDTO {
    private int id;
    private String apellidos;
    private String nombre;
    private String cargo;
    private String tratamiento;
    private Date fechaNacimiento;
    private Date fechaContratacion;
    private String direccion;
    private String ciudad;
    private String region;
    private String cp;
    private String pais;
    private String telefonoDomicilio;
    private String extension;
    private String notas;
    private EmpleadosDTO empleado;
    private Collection<EmpleadosDTO> empleados;
    private Collection<PedidosDTO> pedidos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(Date fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getTelefonoDomicilio() {
        return telefonoDomicilio;
    }

    public void setTelefonoDomicilio(String telefonoDomicilio) {
        this.telefonoDomicilio = telefonoDomicilio;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmpleadosDTO that = (EmpleadosDTO) o;

        if (id != that.id) return false;
        if (apellidos != null ? !apellidos.equals(that.apellidos) : that.apellidos != null) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (cargo != null ? !cargo.equals(that.cargo) : that.cargo != null) return false;
        if (tratamiento != null ? !tratamiento.equals(that.tratamiento) : that.tratamiento != null) return false;
        if (fechaNacimiento != null ? !fechaNacimiento.equals(that.fechaNacimiento) : that.fechaNacimiento != null)
            return false;
        if (fechaContratacion != null ? !fechaContratacion.equals(that.fechaContratacion) : that.fechaContratacion != null)
            return false;
        if (direccion != null ? !direccion.equals(that.direccion) : that.direccion != null) return false;
        if (ciudad != null ? !ciudad.equals(that.ciudad) : that.ciudad != null) return false;
        if (region != null ? !region.equals(that.region) : that.region != null) return false;
        if (cp != null ? !cp.equals(that.cp) : that.cp != null) return false;
        if (pais != null ? !pais.equals(that.pais) : that.pais != null) return false;
        if (telefonoDomicilio != null ? !telefonoDomicilio.equals(that.telefonoDomicilio) : that.telefonoDomicilio != null)
            return false;
        if (extension != null ? !extension.equals(that.extension) : that.extension != null) return false;
        if (notas != null ? !notas.equals(that.notas) : that.notas != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (apellidos != null ? apellidos.hashCode() : 0);
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (cargo != null ? cargo.hashCode() : 0);
        result = 31 * result + (tratamiento != null ? tratamiento.hashCode() : 0);
        result = 31 * result + (fechaNacimiento != null ? fechaNacimiento.hashCode() : 0);
        result = 31 * result + (fechaContratacion != null ? fechaContratacion.hashCode() : 0);
        result = 31 * result + (direccion != null ? direccion.hashCode() : 0);
        result = 31 * result + (ciudad != null ? ciudad.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (cp != null ? cp.hashCode() : 0);
        result = 31 * result + (pais != null ? pais.hashCode() : 0);
        result = 31 * result + (telefonoDomicilio != null ? telefonoDomicilio.hashCode() : 0);
        result = 31 * result + (extension != null ? extension.hashCode() : 0);
        result = 31 * result + (notas != null ? notas.hashCode() : 0);
        return result;
    }

    public EmpleadosDTO getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmpleadosDTO empleado) {
        this.empleado = empleado;
    }

    public Collection<EmpleadosDTO> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(Collection<EmpleadosDTO> empleados) {
        this.empleados = empleados;
    }

    public Collection<PedidosDTO> getPedidos() {
        return pedidos;
    }

    public void setPedidos(Collection<PedidosDTO> pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public String toString() {
        return "EmpleadosDTO{" +
                "id=" + id +
                ", apellidos='" + apellidos + '\'' +
                ", nombre='" + nombre + '\'' +
                ", cargo='" + cargo + '\'' +
                ", tratamiento='" + tratamiento + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", fechaContratacion=" + fechaContratacion +
                ", direccion='" + direccion + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", region='" + region + '\'' +
                ", cp='" + cp + '\'' +
                ", pais='" + pais + '\'' +
                ", telefonoDomicilio='" + telefonoDomicilio + '\'' +
                ", extension='" + extension + '\'' +
                ", notas='" + notas + '\'' +
                ", empleado=" + empleado +
                ", empleados=" + empleados +
                ", pedidos=" + pedidos +
                '}';
    }
}
