package DTO;

import java.math.BigDecimal;
import java.util.Collection;

public class ProductosDTO {
    private int id;
    private String producto;
    private String cantidadPorUnidad;
    private BigDecimal precioUnidad;
    private Short unidadesExistencia;
    private Short unidadesPedido;
    private Integer nivelNuevoPedido;
    private byte suspendido;
    private String notas;
    private BigDecimal iva;
    private Collection<DetallesDTO> detalles;
    private ProveedoresDTO proveedor;
    private CategoriasDTO categoria;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getCantidadPorUnidad() {
        return cantidadPorUnidad;
    }

    public void setCantidadPorUnidad(String cantidadPorUnidad) {
        this.cantidadPorUnidad = cantidadPorUnidad;
    }

    public BigDecimal getPrecioUnidad() {
        return precioUnidad;
    }

    public void setPrecioUnidad(BigDecimal precioUnidad) {
        this.precioUnidad = precioUnidad;
    }

    public Short getUnidadesExistencia() {
        return unidadesExistencia;
    }

    public void setUnidadesExistencia(Short unidadesExistencia) {
        this.unidadesExistencia = unidadesExistencia;
    }

    public Short getUnidadesPedido() {
        return unidadesPedido;
    }

    public void setUnidadesPedido(Short unidadesPedido) {
        this.unidadesPedido = unidadesPedido;
    }

    public Integer getNivelNuevoPedido() {
        return nivelNuevoPedido;
    }

    public void setNivelNuevoPedido(Integer nivelNuevoPedido) {
        this.nivelNuevoPedido = nivelNuevoPedido;
    }

    public byte getSuspendido() {
        return suspendido;
    }

    public void setSuspendido(byte suspendido) {
        this.suspendido = suspendido;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductosDTO that = (ProductosDTO) o;

        if (id != that.id) return false;
        if (suspendido != that.suspendido) return false;
        if (producto != null ? !producto.equals(that.producto) : that.producto != null) return false;
        if (cantidadPorUnidad != null ? !cantidadPorUnidad.equals(that.cantidadPorUnidad) : that.cantidadPorUnidad != null)
            return false;
        if (precioUnidad != null ? !precioUnidad.equals(that.precioUnidad) : that.precioUnidad != null) return false;
        if (unidadesExistencia != null ? !unidadesExistencia.equals(that.unidadesExistencia) : that.unidadesExistencia != null)
            return false;
        if (unidadesPedido != null ? !unidadesPedido.equals(that.unidadesPedido) : that.unidadesPedido != null)
            return false;
        if (nivelNuevoPedido != null ? !nivelNuevoPedido.equals(that.nivelNuevoPedido) : that.nivelNuevoPedido != null)
            return false;
        if (notas != null ? !notas.equals(that.notas) : that.notas != null) return false;
        if (iva != null ? !iva.equals(that.iva) : that.iva != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (producto != null ? producto.hashCode() : 0);
        result = 31 * result + (cantidadPorUnidad != null ? cantidadPorUnidad.hashCode() : 0);
        result = 31 * result + (precioUnidad != null ? precioUnidad.hashCode() : 0);
        result = 31 * result + (unidadesExistencia != null ? unidadesExistencia.hashCode() : 0);
        result = 31 * result + (unidadesPedido != null ? unidadesPedido.hashCode() : 0);
        result = 31 * result + (nivelNuevoPedido != null ? nivelNuevoPedido.hashCode() : 0);
        result = 31 * result + (int) suspendido;
        result = 31 * result + (notas != null ? notas.hashCode() : 0);
        result = 31 * result + (iva != null ? iva.hashCode() : 0);
        return result;
    }

    public Collection<DetallesDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(Collection<DetallesDTO> detalles) {
        this.detalles = detalles;
    }

    public ProveedoresDTO getProveedor() {
        return proveedor;
    }

    public void setProveedor(ProveedoresDTO proveedor) {
        this.proveedor = proveedor;
    }

    public CategoriasDTO getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriasDTO categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "ProductosDTO{" +
                "id=" + id +
                ", producto='" + producto + '\'' +
                ", cantidadPorUnidad='" + cantidadPorUnidad + '\'' +
                ", precioUnidad=" + precioUnidad +
                ", unidadesExistencia=" + unidadesExistencia +
                ", unidadesPedido=" + unidadesPedido +
                ", nivelNuevoPedido=" + nivelNuevoPedido +
                ", suspendido=" + suspendido +
                ", notas='" + notas + '\'' +
                ", iva=" + iva +
                ", detalles=" + detalles +
                ", proveedor=" + proveedor +
                ", categoria=" + categoria +
                '}';
    }
}
