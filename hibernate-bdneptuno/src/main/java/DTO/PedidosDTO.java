package DTO;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Collection;

public class PedidosDTO {
    private int id;
    private Date fechaPedido;
    private Date fechaEntrega;
    private Date fechaEnvio;
    private BigDecimal cargo;
    private String destinatario;
    private String direccionDestinatario;
    private String ciudadDestinatario;
    private String regionDestinatario;
    private String cpDestinatario;
    private String paisDestinatario;
    private Collection<DetallesDTO> detalles;
    private ClientesDTO cliente;
    private EmpleadosDTO empleado;
    private EnviosDTO envio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public BigDecimal getCargo() {
        return cargo;
    }

    public void setCargo(BigDecimal cargo) {
        this.cargo = cargo;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getDireccionDestinatario() {
        return direccionDestinatario;
    }

    public void setDireccionDestinatario(String direccionDestinatario) {
        this.direccionDestinatario = direccionDestinatario;
    }

    public String getCiudadDestinatario() {
        return ciudadDestinatario;
    }

    public void setCiudadDestinatario(String ciudadDestinatario) {
        this.ciudadDestinatario = ciudadDestinatario;
    }

    public String getRegionDestinatario() {
        return regionDestinatario;
    }

    public void setRegionDestinatario(String regionDestinatario) {
        this.regionDestinatario = regionDestinatario;
    }

    public String getCpDestinatario() {
        return cpDestinatario;
    }

    public void setCpDestinatario(String cpDestinatario) {
        this.cpDestinatario = cpDestinatario;
    }

    public String getPaisDestinatario() {
        return paisDestinatario;
    }

    public void setPaisDestinatario(String paisDestinatario) {
        this.paisDestinatario = paisDestinatario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PedidosDTO that = (PedidosDTO) o;

        if (id != that.id) return false;
        if (fechaPedido != null ? !fechaPedido.equals(that.fechaPedido) : that.fechaPedido != null) return false;
        if (fechaEntrega != null ? !fechaEntrega.equals(that.fechaEntrega) : that.fechaEntrega != null) return false;
        if (fechaEnvio != null ? !fechaEnvio.equals(that.fechaEnvio) : that.fechaEnvio != null) return false;
        if (cargo != null ? !cargo.equals(that.cargo) : that.cargo != null) return false;
        if (destinatario != null ? !destinatario.equals(that.destinatario) : that.destinatario != null) return false;
        if (direccionDestinatario != null ? !direccionDestinatario.equals(that.direccionDestinatario) : that.direccionDestinatario != null)
            return false;
        if (ciudadDestinatario != null ? !ciudadDestinatario.equals(that.ciudadDestinatario) : that.ciudadDestinatario != null)
            return false;
        if (regionDestinatario != null ? !regionDestinatario.equals(that.regionDestinatario) : that.regionDestinatario != null)
            return false;
        if (cpDestinatario != null ? !cpDestinatario.equals(that.cpDestinatario) : that.cpDestinatario != null)
            return false;
        if (paisDestinatario != null ? !paisDestinatario.equals(that.paisDestinatario) : that.paisDestinatario != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (fechaPedido != null ? fechaPedido.hashCode() : 0);
        result = 31 * result + (fechaEntrega != null ? fechaEntrega.hashCode() : 0);
        result = 31 * result + (fechaEnvio != null ? fechaEnvio.hashCode() : 0);
        result = 31 * result + (cargo != null ? cargo.hashCode() : 0);
        result = 31 * result + (destinatario != null ? destinatario.hashCode() : 0);
        result = 31 * result + (direccionDestinatario != null ? direccionDestinatario.hashCode() : 0);
        result = 31 * result + (ciudadDestinatario != null ? ciudadDestinatario.hashCode() : 0);
        result = 31 * result + (regionDestinatario != null ? regionDestinatario.hashCode() : 0);
        result = 31 * result + (cpDestinatario != null ? cpDestinatario.hashCode() : 0);
        result = 31 * result + (paisDestinatario != null ? paisDestinatario.hashCode() : 0);
        return result;
    }

    public Collection<DetallesDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(Collection<DetallesDTO> detalles) {
        this.detalles = detalles;
    }

    public ClientesDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClientesDTO cliente) {
        this.cliente = cliente;
    }

    public EmpleadosDTO getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmpleadosDTO empleado) {
        this.empleado = empleado;
    }

    public EnviosDTO getEnvio() {
        return envio;
    }

    public void setEnvio(EnviosDTO envio) {
        this.envio = envio;
    }
}
