package Models;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

public class CompraDTO implements Serializable {

    private final int id;
    private final int empleadoId;
    private final LocalDateTime fecha;

    private Set<DetallesDTO> detalles;

    public CompraDTO(ResultSet rs, String alias) throws SQLException {
        this.id = rs.getInt(alias + ".id");
        this.empleadoId = rs.getInt(alias + ".empleado_id");
        this.fecha = rs.getTimestamp(alias + ".fecha").toLocalDateTime().minusHours(1);
    }

    public void mostrarDetallesCompra() {
        System.out.println("Compra realizada a las " + fecha.format(DateTimeFormatter.ofPattern("HH:mm")));

        AtomicReference<Float> precioTotal = new AtomicReference<>(0f);
        detalles.forEach(detalle -> {
            ProductoDTO producto = detalle.getProducto();

            String inicio = "  -> " + producto.getNombre();

            String unidades = " x" + detalle.getCantidad() + " unidad/es |";

            float precio = detalle.getCantidad() * producto.getPrecioVenta() / 100f;

            System.out.printf("%-15s %-15s %.2f €\n",inicio,unidades,precio);
            precioTotal.updateAndGet(v -> v + precio);
        });
        System.out.printf(" %-30s %.2f €\n"," ** Precio total:",precioTotal.get());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompraDTO compraDTO = (CompraDTO) o;

        return id == compraDTO.id;
    }

    public int getId() {
        return id;
    }
    public int getEmpleadoId() {
        return empleadoId;
    }
    public LocalDateTime getFecha() {
        return fecha;
    }
    public void setDetalles(Set<DetallesDTO> detalles) {
        this.detalles = detalles;
    }
}
