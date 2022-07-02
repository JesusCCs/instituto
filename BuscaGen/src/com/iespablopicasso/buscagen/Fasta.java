package com.iespablopicasso.buscagen;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Clase principal del modelo, es la encaraga de leer el archivo en formato FASTA, encontrar las coincidencias y escribirlas
 * en un archivo destino*/
public class Fasta {
    /**El archivo FASTA donde hay que leer*/
    private File archivoOrigen;
    /**El fichero donde escribir el resultado*/
    private File archivoDestino;
    /**El gen que estamos buscando*/
    private String genCadena;
    /**Los errores permitidos*/
    private int numErrores;

    /**Constructor
     * @param archivoDestino El archivo de lectura, donde está la información
     * @param archivoOrigen El archivo donde vamos a depositar los resultados obtenidos
     * @param genCadena El gen que se va a buscar
     * @param numErrores La cantidad de mutaciones máximas permitidas*/
    public Fasta(File archivoOrigen, File archivoDestino,String genCadena,int numErrores) {
        this.archivoDestino = archivoDestino;
        this.archivoOrigen = archivoOrigen;
        this.genCadena = genCadena;
        this.numErrores = numErrores;
    }

    /**Método que se encarga de abrir tanto el archivo origen como el destino, leer el primero y, mediante otros métodos
     * privados como ayuda ({@link #comparar(Queue)}, {@link #escribirLinea(BufferedWriter, String[], String)}), comparar y escribir los resultados*/
    public void escribirCoincidencias(){
        try {
            //abrimos tanto el archivo de entrada como el de salida
            BufferedInputStream lector = new BufferedInputStream(new FileInputStream(archivoOrigen)); //vamos a leer byte a byte
            BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoDestino, true));

            //lo primero que hacemos es escribir en el archivo de salida lo que siempre es igual
            escritor.write(genCadena + ";" + numErrores + "\n");
            StringBuilder primeraLinea = new StringBuilder();
            char letra;
            while ((letra = (char) lector.read()) != '\n'){
                primeraLinea.append(letra);
            }
            escritor.write(primeraLinea + "\n");


            int numLinea = 1;

            //primera iteracion, donde debemos llenar la cola hasta el tamaño del gen a encontrar
            Queue<Character> cola = new LinkedList<>();
            while(cola.size() != genCadena.length()){
                letra = (char) lector.read();
                if (letra == '\n') numLinea++;
                else cola.add(letra);
            }
            escribirLinea(escritor,comparar(cola), String.valueOf(numLinea));

            //resto de iteraciones
            while(lector.available() != 0){
                letra = (char) lector.read();
                if (letra == '\r' || letra == '\n') {
                    numLinea++;
                    while (letra == '\n' || letra == '\r')
                        letra = (char) lector.read();//volvemos a leer hasta que dejamos de encontrar espacios
                }                                   // ya que se han observado casos de un \r y \n continuos, aunque el texto no lo aparentaba
                cola.remove();
                cola.add(letra);
                escribirLinea(escritor,comparar(cola), String.valueOf(numLinea));
            }

            lector.close();
            escritor.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**Método soporte que comprueba si ha habido un resultado válido y lo escribe en el fichero destino y con el formato adecuado
     * @param escritor El {@link BufferedWriter} encargado de escribir en el fichero destino
     * @param resultado El array resultado del método {@link Fasta#comparar(Queue)}
     * @param numLinea La línea en la que actualmente nos encontramos dentro del fichero FASTA
     * @exception IOException En caso de que haya habido algún problema con {@link Fasta#archivoDestino} durante la ejecución*/
    private void escribirLinea (BufferedWriter escritor,String[] resultado,String numLinea) throws IOException {
        if (resultado != null) escritor.write(resultado[0] + ";" + numLinea + ";" + resultado[1] + "\n");
    }

    /**Se encarga de comparar una cola de caracteres con el genCadena almacenado
     * @param cola Los caracteres que se han encontrado en el fichero FASTA
     * @return Un array, donde en la primera posición se encuentra la cola y en la segunda el número de errores. Si se ha superado el
     * número de errores, se devuelve null*/
    private String[] comparar (Queue<Character> cola) {
        boolean coincidencia = true;
        int errores = 0;
        int i = 0;
        StringBuilder colaString = new StringBuilder();
        for (Character elemento : cola) {
            if (genCadena.charAt(i) != elemento){
                errores++;
                if (errores > numErrores) {
                    coincidencia = false;
                    break;
                }
            }
            i++;
            colaString.append(elemento);
        }
        String[] resultado = {colaString.toString(), String.valueOf(errores)};
        if (coincidencia) return resultado;
        else return null;
    }

    /**Método igual a {@link #comparar(Queue)} creado únicamente para realizar comodamente pruebas
     * @deprecated  -Se indica esta anotación únicamente para evitar que termine en la documentacion*/
    public static String[] comparar (Queue<Character> cola,String genCadena, int erroresMaximosPermitidos) {
        boolean coincidencia = true;
        int errores = 0;
        int i = 0;
        StringBuilder colaString = new StringBuilder();
        for (Character elemento : cola) {
            if (genCadena.charAt(i) != elemento){
                errores++;
                if (errores > erroresMaximosPermitidos) {
                    coincidencia = false;
                    break;
                }
            }
            i++;
            colaString.append(elemento);
        }
        String[] resultado = {colaString.toString(), String.valueOf(errores)};
        if (coincidencia) return resultado;
        else return null;
    }
}