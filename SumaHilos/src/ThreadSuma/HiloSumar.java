package ThreadSuma;

public class HiloSumar extends Thread {

    private final int[] array;
    private final int posInicial;
    private final int posFinal;
    private long suma;

    public HiloSumar(int[] array, int posInicial, int posFinal) {
        this.array = array;
        this.posInicial = posInicial;
        this.posFinal = posFinal;
        this.suma = 0;
    }

    public HiloSumar(int[] array) {
        this(array, 0, array.length);
    }

    @Override
    public void run() {
        for (int i = posInicial; i < posFinal; i++) {
            suma += array[i];
        }
    }

    public long getSuma() {
        return suma;
    }
}
