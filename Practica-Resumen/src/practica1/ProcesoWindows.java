package practica1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Clase Wrapper de comandos de terminal para Windows
 */
public class ProcesoWindows implements Proceso {

    public void crearFichero(String ruta, String fichero) throws IOException {
        new ProcessBuilder("powershell", "cd", ruta, "; ni -i file", fichero).start();
    }

    public void crearCarpeta(String ruta, String carpeta) throws IOException {
        new ProcessBuilder("powershell", "cd", ruta, "; mkdir", carpeta).start();
    }

    public ArrayList<InterfazRed> obtenerInterfaces() throws IOException, InterruptedException {
        ArrayList<InterfazRed> interfaces = new ArrayList<>();

        // Todo este comando hace que pondamos tener un listado cuyas lineas son de la forma: nombre;ip;mac
        Process process = new ProcessBuilder("powershell",
                "Get-WmiObject Win32_NetworkAdapterConfiguration | " +
                        "Select-Object -Property Description, IPAddress, MacAddress | " +
                        "Where IPAddress -NE $null | " +
                        "% { '{0};{1};{2}' -F $_.Description,$_.IPAddress[0],$_.MacAddress }").start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        reader.lines().forEach(linea -> {
            String[] resultado = linea.split(";");
            interfaces.add(new InterfazRed(resultado[0], resultado[1], resultado[2]));
        });

        process.waitFor();

        return interfaces;
    }

    public boolean comprobarConexion() throws IOException, InterruptedException {
        Process process = new ProcessBuilder("powershell",
                        "Test-NetConnection | select PingSucceeded -ExpandProperty PingSucceeded").start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        process.waitFor();

        return reader.readLine().equals("True");
    }
}
