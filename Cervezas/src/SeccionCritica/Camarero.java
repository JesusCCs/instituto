package SeccionCritica;

import java.util.ArrayList;
import java.util.List;

public class Camarero {

    List<Integer> cervezas;

    public Camarero(int numClientes) {
        cervezas = new ArrayList<>();

        for (int i = 0; i < numClientes; i++) {
            cervezas.add(0);
        }
    }

    public synchronized void consumir(int id) throws InterruptedException {
        while (!permitirConsumicion(id)) {
            System.out.println("Cliente " + (id+1) + " en espera");
            wait();
        }

        int numCervezas = cervezas.get(id);
        cervezas.set(id,++numCervezas);

        System.out.println("Consumiciones: " + cervezas.toString());
        notify();
    }

    private boolean permitirConsumicion(int id) {
        for (int i = 0; i < cervezas.size(); i++) {
            if (id == i) continue;
            if (cervezas.get(i) <= cervezas.get(id) - 1) return false;
        }
        return true;
    }
}
