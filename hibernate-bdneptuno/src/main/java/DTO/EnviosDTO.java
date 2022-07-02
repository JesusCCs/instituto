package DTO;

import java.util.Collection;

public class EnviosDTO {
    private int id;
    private String empresa;
    private String telefono;
    private Collection<PedidosDTO> pedidos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EnviosDTO enviosDTO = (EnviosDTO) o;

        if (id != enviosDTO.id) return false;
        if (empresa != null ? !empresa.equals(enviosDTO.empresa) : enviosDTO.empresa != null) return false;
        if (telefono != null ? !telefono.equals(enviosDTO.telefono) : enviosDTO.telefono != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (empresa != null ? empresa.hashCode() : 0);
        result = 31 * result + (telefono != null ? telefono.hashCode() : 0);
        return result;
    }

    public Collection<PedidosDTO> getPedidos() {
        return pedidos;
    }

    public void setPedidos(Collection<PedidosDTO> pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public String toString() {
        return "EnviosDTO{" +
                "id=" + id +
                ", empresa='" + empresa + '\'' +
                ", telefono='" + telefono + '\'' +
                ", pedidos=" + pedidos +
                '}';
    }
}
