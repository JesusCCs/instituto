package ThreadSuma;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        int size = 100_000_000;
        int numHilos = Runtime.getRuntime().availableProcessors();
        System.out.println("Hilos disponibles: " + numHilos);

        int[] array = new int[size];

        // Llenar array con valores
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 10 + 1);
        }

        // Sumar
        sumaFor(array);
        sumaMultihilo(array, numHilos);
    }

    public static void sumaFor(int[] array) {
        long inicio = System.currentTimeMillis();

        long suma = 0;
        for (int j : array) suma += j;

        long finale = System.currentTimeMillis();

        mandarMensaje("sumaFor", suma, (float) (finale - inicio) / 1000);
    }

    public static void sumaMultihilo(int[] array, int numHilos) throws InterruptedException {
        long inicio = System.currentTimeMillis();

        long resultado = 0;

        int numElementos = array.length / numHilos;
        int iniIndex = 0;
        int finalIndex = numElementos;

        HiloSumar[] hilos = new HiloSumar[numHilos];

        for (int i = 0; i < numHilos; i++) {
            HiloSumar hiloSumar = new HiloSumar(array,iniIndex,finalIndex);
            hiloSumar.start();

            hilos[i] = hiloSumar;

            iniIndex = finalIndex;
            finalIndex = (i != numHilos - 1) ? finalIndex + numElementos : array.length;
        }

        for (HiloSumar hilo : hilos) {
            hilo.join();
            resultado += hilo.getSuma();
        }

        long finale = System.currentTimeMillis();

        mandarMensaje("sumaMultihiloDirecta", resultado, (float) (finale - inicio) / 1000);
    }

    public static void mandarMensaje(String metodo, long suma, float segundos) {
        System.out.format("La %s es de %d y ha tardado %.4f segundos\n", metodo, suma, segundos);
    }
}
