package psp.practica.resumen2;

/**
 * Clase que nos identifica un proceso
 */
public class Proceso implements Comparable<Proceso> {

    /** Identificador del proceso */
    private final char pid;

    /** El cilco en el que el proceso llega */
    private final int  llegada;

    /** Las veces que se debe ejecutar el proceso */
    private final int rafaga;

    /** Número de veces que al proceos le queda por ejecutar */
    private int rafagasPendientes;

    /** El ciclo en el que el proceso ha terminado */
    private int finalizacion;

    public Proceso(char pid, int llegada, int rafaga) {
        this.pid = pid;
        this.llegada = llegada;
        this.rafaga = rafaga;
        this.rafagasPendientes = rafaga;
    }

    /**
     * Aumentamos el número de ejecuciones del proceso y enviamos mensaje a consola para avisar de dicha ejecución
     * @param ciclo El ciclo en el que estamos ejecutando el proceso
     */
    public void ejecutar(int ciclo) {
        rafagasPendientes--;
        String mensaje = "CICLO " + ciclo + " - Proceso [id=" + pid + ", rafaga pendiente=" + rafagasPendientes + "]";
        System.out.println(!estaTerminado() ? mensaje : mensaje + " - TERMINADO");
    }

    /***
     * Polimorfismo de ejecutar. Permite múltiples ejecuciones
     * @param ciclo El ciclo en el que estamos ejecutando el proceso
     * @param numeroEjecuciones Las veces a ejecutar el proceso
     */
    public void ejecutar(int ciclo, int numeroEjecuciones) {
        for (int i = ciclo; i < ciclo + numeroEjecuciones; i++) {
            ejecutar(i);
        }
    }

    /**
     * Guardamos el ciclo final
     * @param ciclo Ciclo en el que terminamos
     */
    public void terminar(int ciclo) {
        finalizacion = ciclo;
    }

    /**
     * Calculamos el índice de penalización del proceso
     * @return El índice de penalización
     */
    public float calcularPenalizacion() {
        return (float) (finalizacion - llegada) / rafaga;
    }

    /**
     * Método necesario para poder hacer uso de Collections.sort() con una lista de procesos.
     * Se ordena por orden de llegada y en caso de empate por el PID.
     * @param otroProceso El proceso con el que comparamos
     */
    @Override
    public int compareTo(Proceso otroProceso) {
        int comparacion = this.llegada - otroProceso.getLlegada();
        return comparacion == 0 ? this.pid - otroProceso.getPid() : comparacion;
    }

    public char getPid() {
        return pid;
    }

    public int getLlegada() {
        return llegada;
    }

    public int getRafaga() {
        return rafaga;
    }

    public int getRafagasPendientes() {
        return rafagasPendientes;
    }

    public boolean estaTerminado() {
        return rafagasPendientes == 0;
    }
}
