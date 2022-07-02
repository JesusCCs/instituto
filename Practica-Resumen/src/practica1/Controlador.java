package practica1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controlador {

    private static final Controlador controlador = new Controlador();
    private static Proceso proceso;

    private Controlador() {
        boolean esWindows = System.getProperty("os.name").contains("Windows");
        proceso = esWindows ? new ProcesoWindows() : new ProcesoBash();
    }

    public static Controlador getControlador() {
        return controlador;
    }

    /**
     * Método principal del Controlador. A partir de la opción que el usuario escoja se realiza la acción correspondiente
     * apoyándose en los métodos del modelo
     * @param opcion
     * @throws IOException
     * @throws InterruptedException
     */
    public void ejecutarAccion(int opcion) throws IOException, InterruptedException {
        String ruta, nombre;
        List<InterfazRed> interfaces;
        InterfazRed interfazEscogida;

        switch (opcion) {
            case 1 -> {
                ruta = Consola.pedirRuta();
                nombre = Consola.pedirNombre(true);
                proceso.crearFichero(ruta, nombre);
            }
            case 2 -> {
                ruta = Consola.pedirRuta();
                nombre = Consola.pedirNombre(false);
                proceso.crearCarpeta(ruta, nombre);
            }
            case 3 -> {
                List<String> nombres = new ArrayList<>();
                proceso.obtenerInterfaces().forEach(interfaz -> nombres.add(interfaz.getNombre()));

                Consola.mostrarInterfaces(nombres);
            }
            case 4 -> {
                interfaces = proceso.obtenerInterfaces();
                interfazEscogida = Consola.pedirInterfaz(interfaces);

                Consola.mostrarIP(interfazEscogida.getIp());
            }
            case 5 -> {
                interfaces = proceso.obtenerInterfaces();
                interfazEscogida = Consola.pedirInterfaz(interfaces);

                Consola.mostrarMAC(interfazEscogida.getMac());
            }
            case 6 -> Consola.mostrarConectividad(proceso.comprobarConexion());
            case 7 -> Consola.finalizarPrograma();
            default -> System.out.println("No existe dicha opción");
        }
    }
}