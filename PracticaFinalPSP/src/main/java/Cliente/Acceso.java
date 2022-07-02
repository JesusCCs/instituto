package Cliente;

import java.io.IOException;

public class Acceso {

    public static final String HOST = "localhost";
    public static final int PUERTO = 6000;

    public static void main(String[] args) {

        Caja caja;

        try {
            caja = new Caja();
        } catch (IOException e) {
            System.out.println("Hubo un problema al conectar al servidor");
            return;
        }


        try {
            caja.iniciar();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Hubo un problema al enviar/recibir la información con el servidor");
            return;
        }

        caja.cerrar();
    }
}
