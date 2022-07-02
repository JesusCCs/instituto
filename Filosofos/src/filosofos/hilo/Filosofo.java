package filosofos.hilo;

import filosofos.Tenedor;
import filosofos.seccioncritica.Mesa;
import java.util.Arrays;
import java.util.Random;

public class Filosofo extends Thread {

    private Tenedor[] tenedores;
    private final String nombre;
    private final Mesa mesa;
    private int hambre;

    private static final int MIN = 2000;
    private static final int MAX = 4000;

    public Filosofo(String nombre, Mesa mesa) {
        this.nombre = nombre;
        this.mesa = mesa;
    }

    @Override
    public void run() {
        while (true) {
            try {
                pensar();
                mesa.comer(this);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void pensar() throws InterruptedException {
        System.out.println(nombre + " comienza a pensar");
        sleep(new Random().nextInt(MAX - MIN) + MIN);
        System.out.println(nombre + " deja de pensar");
    }

    public boolean tenedoresDisponibles() {
        return Arrays.stream(tenedores).allMatch(Tenedor::isDisponible);
    }

    public void usarTenedores() throws InterruptedException {
        for (var tenedor : tenedores) tenedor.setDisponible(false);
        System.out.println(nombre + " usa los tenedores: " + tenedores[0].getId() + " y " + tenedores[1].getId());
    }

    public void dejarDeUsarTenedores() {
        for (var tenedor : tenedores) tenedor.setDisponible(true);
        System.out.println(nombre + " deja de usar los tenedores: " + tenedores[0].getId() + " y " + tenedores[1].getId());
    }

    public void noPuedeComer() {
        hambre++;
        System.out.println(nombre + " no puede comer. Tenedores " +
                tenedores[0].getId() + " o " + tenedores[1].getId() + " siendo usados. Hambre: " + hambre);
    }

    public void comer() {
        hambre = 0;
    }

    public void setTenedores(Tenedor izquierdo, Tenedor derecho) {
        this.tenedores = new Tenedor[]{izquierdo, derecho};
    }

    public String getNombre() {
        return nombre;
    }
}
