package filosofos.seccioncritica;

import filosofos.hilo.Filosofo;
import java.util.Random;

public class Mesa {

    private static final int MIN = 2000;
    private static final int MAX = 4000;

    public Mesa() { }

    public synchronized void comer(Filosofo filosofo) throws InterruptedException {

        System.out.println(filosofo.getNombre() + " quiere comer.");

        while (!filosofo.tenedoresDisponibles()) {
            filosofo.noPuedeComer();
            wait();
        }

        filosofo.usarTenedores();
        filosofo.comer();
        wait(new Random().nextInt(MAX - MIN) + MIN);
        filosofo.dejarDeUsarTenedores();

        notify();

    }
}
