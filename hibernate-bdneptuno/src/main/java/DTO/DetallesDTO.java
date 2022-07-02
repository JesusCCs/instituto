package DTO;

import java.math.BigDecimal;

public class DetallesDTO {
    private BigDecimal precioUnidad;
    private Short cantidad;
    private Double descuento;
    private PedidosDTO pedido;
    private ProductosDTO producto;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getPrecioUnidad() {
        return precioUnidad;
    }

    public void setPrecioUnidad(BigDecimal precioUnidad) {
        this.precioUnidad = precioUnidad;
    }

    public Short getCantidad() {
        return cantidad;
    }

    public void setCantidad(Short cantidad) {
        this.cantidad = cantidad;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DetallesDTO that = (DetallesDTO) o;

        if (precioUnidad != null ? !precioUnidad.equals(that.precioUnidad) : that.precioUnidad != null) return false;
        if (cantidad != null ? !cantidad.equals(that.cantidad) : that.cantidad != null) return false;
        if (descuento != null ? !descuento.equals(that.descuento) : that.descuento != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = precioUnidad != null ? precioUnidad.hashCode() : 0;
        result = 31 * result + (cantidad != null ? cantidad.hashCode() : 0);
        result = 31 * result + (descuento != null ? descuento.hashCode() : 0);
        return result;
    }

    public PedidosDTO getPedido() {
        return pedido;
    }

    public void setPedido(PedidosDTO pedido) {
        this.pedido = pedido;
    }

    public ProductosDTO getProducto() {
        return producto;
    }

    public void setProducto(ProductosDTO producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        return "DetallesDTO{" +
                "precioUnidad=" + precioUnidad +
                ", cantidad=" + cantidad +
                ", descuento=" + descuento +
                ", pedido=" + pedido +
                ", producto=" + producto +
                ", id=" + id +
                '}';
    }
}
