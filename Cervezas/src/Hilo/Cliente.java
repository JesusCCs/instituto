package Hilo;

import SeccionCritica.Camarero;

public class Cliente extends Thread {

    private final int id;
    private final Camarero camarero;

    public Cliente(int id, Camarero camarero) {
        this.id = id;
        this.camarero = camarero;
    }

    @Override
    public void run() {

        while (true) {
            try {
                Thread.sleep((int) (Math.random() * 10000));
                camarero.consumir(id);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
