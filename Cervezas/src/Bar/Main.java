package Bar;

import Hilo.Cliente;
import SeccionCritica.Camarero;

public class Main {

    private static final int NUM_CLIENTES = 3;

    public static void main(String[] args) {
        Camarero camarero = new Camarero(NUM_CLIENTES);
        Cliente[] clientes = new Cliente[NUM_CLIENTES];

        for (int i = 0; i < clientes.length; i++) {
            clientes[i] = new Cliente(i, camarero);
        }

        for (Cliente cliente : clientes) {
            cliente.start();
        }
    }
}
