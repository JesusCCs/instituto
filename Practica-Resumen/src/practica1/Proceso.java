package practica1;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Interfaz implementada por las clases Wrapper que contendrán
 * los comandos a ejecutar en la consola
 */
public interface Proceso {

    /**
     * @param ruta La ruta donde se quiere crear el fichero
     * @param fichero El nombre del fichero (extensión incluida)
     * @throws IOException
     */
    void crearFichero(String ruta, String fichero) throws IOException;

    /**
     * @param ruta La ruta donde se quiere crear la carpeta
     * @param carpeta El nombre de la carpeta
     * @throws IOException
     */
    void crearCarpeta(String ruta, String carpeta) throws IOException;

    /**
     * Se ejecuta un comando y se procesa de forma que se pueda construir una lista
     * @return Lista de la clase InterfazRed
     * @throws IOException
     * @throws InterruptedException
     */
    ArrayList<InterfazRed> obtenerInterfaces() throws IOException, InterruptedException;

    /**
     * Ejecuta un comando para poder conocer si hay conectividad a Internet
     * @return True si hay conexión false lo contrario
     * @throws IOException
     * @throws InterruptedException
     */
    boolean comprobarConexion() throws IOException, InterruptedException;
}
