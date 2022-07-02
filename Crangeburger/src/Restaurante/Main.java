package Restaurante;

import Hilo.Cliente;
import Hilo.Cocinero;
import SeccionCritica.Dispensador;

/**
 * Clase principal donde se crean todos los objetos encesarios y los hilos que van a participar en la ejecución del
 * programa
 */
public class Main {

    private static final int NUM_CLIENTES = 5;
    private static final int NUM_COCINEROS = 3;

    public static void main(String[] args) {

        Dispensador dispensador = new Dispensador();

        Cocinero[] cocineros = new Cocinero[NUM_COCINEROS];
        Cliente[] clientes = new Cliente[NUM_CLIENTES];

        for (int i = 0; i < NUM_COCINEROS; i++) {
            cocineros[i] = new Cocinero(dispensador,i);
        }

        for (int i = 0; i < NUM_CLIENTES; i++) {
            clientes[i] = new Cliente(dispensador,i);
        }

        for (Cocinero cocinero : cocineros) {
            cocinero.start();
        }

        for (Cliente cliente : clientes) {
            cliente.start();
        }
    }
}
