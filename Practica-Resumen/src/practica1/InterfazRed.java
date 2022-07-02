package practica1;

/**
 * Clase de apoyo que contiene la información que identifica
 * a la interfaz de red
 */
public class InterfazRed {

    private final String nombre;
    private final String ip;
    private final String mac;

    public InterfazRed(String nombre, String ip, String mac) {
        this.nombre = nombre;
        this.ip = ip;
        this.mac = mac;
    }

    public String getNombre() {
        if (nombre == null) return "No hay un nombre de interfaz";
        return nombre;
    }

    public String getIp() {
        if (ip == null) return "No hay una IP asociada";
        return ip;
    }

    public String getMac() {
        if (mac == null) return "No hay una dirección MAC asociada";
        return mac;
    }
}
