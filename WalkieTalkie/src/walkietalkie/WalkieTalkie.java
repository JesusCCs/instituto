package walkietalkie;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Clase principal del programa
 */
public class WalkieTalkie {

    private static final int LIMITE_MENSAJE = 1024;
    private static final int TIMEOUT = 60; // segundos

    private final Scanner lector;
    private InetAddress destino;
    private final DatagramSocket socketLocal;

    private int puertoDestino;

    public WalkieTalkie(int puerto) throws UnknownHostException, SocketException {
        this.socketLocal = new DatagramSocket(puerto);
        socketLocal.setSoTimeout(TIMEOUT * 1000);
        this.lector = new Scanner(System.in);
    }

    /**
     * Punto de entrada del programa
     *
     * @throws IOException
     */
    public void iniciar() throws IOException {
        System.out.println("Indique la IP");
        destino = InetAddress.getByName(Utils.leerIP(lector));

        System.out.println("Indique el puerto");
        puertoDestino = Utils.leerNumero(lector);

        menu();

        socketLocal.close();
        System.out.println("Finalizando WalkieTalkie ...");
    }

    /**
     * Wrapper para las opciones disponibles del programa
     */
    private void menu() throws IOException {
        System.out.println("Escoja que acción quiere realizar:");
        System.out.println("1.- Hablar");
        System.out.println("2.- Esperar a recibir mensaje");

        int opcion = Utils.leerNumero(lector, new Integer[]{1, 2});

        switch (opcion) {
            case 1 -> comunicacion();
            case 2 -> esperarMensaje();
        }
    }

    /**
     * La lógica principal a la hora de escribir los mensajes o recibirlos.
     * Es el método encargado de recoger lo que devuelven {@link #pedirYEnviarMensaje()} y {@link #recibirMensaje()}
     * y finalizar o continuar la comunicación con el otro WalkieTalkie
     *
     * @throws IOException
     */
    private void comunicacion() throws IOException {
        System.out.println("Escriba su mensaje y dele al intro.");
        System.out.println("Se continuará guardando hasta que escriba 'Cambio'.");
        System.out.println("También puede escribir 'Cambio y corto' para finalizar la escucha.");

        try {

            boolean cortarComunicacion;
            while (true) {
                cortarComunicacion = pedirYEnviarMensaje();
                if (cortarComunicacion) break;

                cortarComunicacion = recibirMensaje();
                if (cortarComunicacion) break;
            }

        } catch (SocketTimeoutException e) {
            System.out.println("Ya ha pasado un tiempo y aún no se ha recibido un mensaje.");
            menu();
        }
    }

    /**
     * Método que captura si se corta o no la comunicación al recibir el mensaje. Si no se corta vamos hacia el método
     * de {@link #comunicacion()}
     *
     * @throws IOException
     */
    private void esperarMensaje() throws IOException {
        try {
            boolean cortarComunicacion = recibirMensaje();
            if (!cortarComunicacion) comunicacion();
        } catch (SocketTimeoutException e) {
            System.out.println("Ya ha pasado un tiempo y aún no se ha recibido un mensaje.");
            menu();
        }
    }


    /**
     * Método para escribir y enviar el mensaje que se quiere
     *
     * @return Devuelve  si se ha escrito cortar o no en el mensaje a enviar. Para finalizar la comunicación
     * @throws IOException
     */
    private boolean pedirYEnviarMensaje() throws IOException {

        List<byte[]> mensajes = new ArrayList<>();
        boolean continuar = true, cortar = false;

        System.out.println("Puede escribir su mensaje");

        // Mientras no se escriba cambio o corto y cambio, seguimos guardando el mensaje
        do {
            String mensaje = lector.nextLine();
            mensajes.addAll(dividirMensaje(mensaje + System.getProperty("line.separator")));

            if (mensaje.trim().equalsIgnoreCase("cambio y corto")) {
                continuar = false;
                cortar = true;
            } else if (mensaje.trim().equalsIgnoreCase("cambio")) {
                continuar = false;
            }

        } while (continuar);

        // Enviamos los paquetes
        for (byte[] mensaje : mensajes) {
            DatagramPacket envio = new DatagramPacket(mensaje, mensaje.length, destino, puertoDestino);
            socketLocal.send(envio);
        }

        return cortar;
    }

    /**
     * Si el mensaje que ha escrito el usuario es demasiado grande, se deberá de dividir en varios trozos
     * para poder enviarlos.
     *
     * @param mensaje El mensaje que ha escrito el usuario
     * @return Un array que contiene los trozos que se han generado a partir del mensaje inicial
     */
    private List<byte[]> dividirMensaje(String mensaje) {
        List<byte[]> mensajes = new ArrayList<>();

        byte[] bytes = mensaje.getBytes(StandardCharsets.UTF_8);

        if (bytes.length < LIMITE_MENSAJE) {
            mensajes.add(bytes);
            return mensajes;
        }

        ByteBuffer bb = ByteBuffer.wrap(bytes);

        final int numDivisiones = (int) Math.ceil(bytes.length / (double) LIMITE_MENSAJE);
        final int limiteUltimotrozo = LIMITE_MENSAJE - (numDivisiones * LIMITE_MENSAJE - bytes.length);

        for (int i = 0; i < numDivisiones; i++) {
            byte[] trozo = new byte[LIMITE_MENSAJE];

            if (i + 1 == numDivisiones) bb.get(trozo, 0, limiteUltimotrozo);
            else bb.get(trozo, 0, LIMITE_MENSAJE);

            mensajes.add(trozo);
        }

        return mensajes;
    }


    /**
     * Esperamos recibir un mensaje de otro WalkieTalkie
     *
     * @return Si cortamos la comunicación o no. Esta comunicación se corta si lo último que se reciba es un corto || cambio y corto
     * @throws IOException
     */
    private boolean recibirMensaje() throws IOException {
        byte[] datos = new byte[LIMITE_MENSAJE];
        boolean continuar = true, cortar = false;

        System.out.println("\nEsperando a recibir mensaje...\n");

        do {
            DatagramPacket recibo = new DatagramPacket(datos, datos.length);

            socketLocal.receive(recibo);

            String mensaje = new String(recibo.getData(), 0, recibo.getLength());
            System.out.print(mensaje);

            if (mensaje.trim().equalsIgnoreCase("cambio y corto")) {
                continuar = false;
                cortar = true;
            } else if (mensaje.trim().equalsIgnoreCase("cambio")) {
                continuar = false;
            }

        } while (continuar);

        System.out.println("\nMensaje finalizado\n");

        return cortar;
    }
}
