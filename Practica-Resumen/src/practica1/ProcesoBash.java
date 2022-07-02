package practica1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Clase Wrapper de comandos de terminal para Linux/Mac
 */
public class ProcesoBash  implements Proceso {

    public void crearFichero(String ruta, String fichero) throws IOException {
        new ProcessBuilder("bash", "-c", "cd", ruta, "; touch", fichero).start() ;
    }

    public void crearCarpeta(String ruta, String carpeta) throws IOException {
        new ProcessBuilder("bash", "-c", "cd", ruta, "; mkdir", carpeta).start();
    }

    public ArrayList<InterfazRed> obtenerInterfaces() throws IOException, InterruptedException {
        ArrayList<InterfazRed> interfaces = new ArrayList<>();

        // arp -a --> devuelve líneas de la forma: Dispositivo (IP) at MAC [Tipo] on InterfazNombre
        // Si la consola está en otros idiomas este método no funciona ya que usamos el 'on' para poder identificar el nombre
        // No usamos que sea la última palabra, porque en Mac he visto capturas donde la línea tiene los componentes organizados de otra manera
        Process process = new ProcessBuilder("bash", "-c", "arp -a").start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        reader.lines().forEach(linea -> {
            String[] resultado = linea.split(" ");

            String nombre = null, ip = null, mac = null;

            for (int i = 0; i < resultado.length; i++) {
                if (resultado[i].contains("(")) ip = resultado[i].substring(1,resultado[i].length()-1);
                else if (resultado[i].equals(":")) mac = resultado[i+1];
                else if (resultado[i].equals("on")) nombre = resultado[i+1];
            }

            interfaces.add(new InterfazRed(nombre, ip, mac));
        });

        process.waitFor();

        return interfaces;
    }

    public boolean comprobarConexion() throws IOException, InterruptedException {
        Process process = new ProcessBuilder("bash", "-c", "ping -c 2 8.8.8.8 | grep %").start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        process.waitFor();

        return !reader.readLine().contains("100%");
    }
}
