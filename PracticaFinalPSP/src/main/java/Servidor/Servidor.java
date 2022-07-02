package Servidor;

import Cliente.Acceso;
import Config.Configuracion;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) throws IOException {

        ServerSocket servidor = new ServerSocket(Acceso.PUERTO);

        System.out.println("SERVIDOR INICIADO");

        try {
            Configuracion.iniciar();
        } catch (Exception e) {
            System.out.println("CONFIGURACIÓN MALFORMADA. TERMINANDO...");
            e.printStackTrace();
            System.exit(0);
        }

        while (true) {
            Socket cliente = servidor.accept();//esperando cliente
            System.out.println("CLIENTE ACEPTADO");
            HiloServidor hilo = new HiloServidor(cliente);
            hilo.start(); // Se atiende al cliente
        }
    }
}