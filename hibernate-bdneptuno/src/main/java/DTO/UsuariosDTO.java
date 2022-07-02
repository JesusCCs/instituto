package DTO;

public class UsuariosDTO {
    private String nombre;
    private String clave;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsuariosDTO that = (UsuariosDTO) o;

        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (clave != null ? !clave.equals(that.clave) : that.clave != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nombre != null ? nombre.hashCode() : 0;
        result = 31 * result + (clave != null ? clave.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UsuariosDTO{" +
                "nombre='" + nombre + '\'' +
                ", clave='" + clave + '\'' +
                '}';
    }
}
