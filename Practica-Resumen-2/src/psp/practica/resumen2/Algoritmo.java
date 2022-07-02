package psp.practica.resumen2;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que contiene los métodos que debe tener cualquier algoritmo independientemente del tipo
 */
public abstract class Algoritmo {

    /**
     * La lista de procesos. Conforme vayamos terminando procesos iremos eliminándolos de dicha lista para evitar
     * redundancias a la hora de recorrerla
     */
    protected List<Proceso> procesos;

    /**
     * Alamacenamos los índices de penalización de los procesos que vayan terminando, para poder hacer una media
     * al final y obtener el rendimiento
     */
    protected List<Float> indicesPenalizacion;

    /**
     * Número de procesos inicial a ejecutar
     */
    protected final int numProcesos;

    /**
     * El ciclo de ejecución en el que nos encontramos
     */
    protected int ciclo;

    /**
     * El número de procesos que ya han finalizado
     */
    protected int procesosTerminados;

    /**
     * El proceso en ejecución
     */
    protected Proceso procesoActual;


    public Algoritmo (List<Proceso> procesos) {
        this.procesos = procesos;
        this.numProcesos = procesos.size();
        this.procesosTerminados = 0;

        this.indicesPenalizacion = new ArrayList<>();
        this.ciclo = procesos.get(0).getLlegada() + 1; // El ciclo inicial será aquel dónde haya un proceso ejecutable por primera vez
    }

    /**
     * Contiene la lógica principal de cómo funciona el algoritmo. Cada tipo de algoritmo deberá hacer
     * su implementación.
     */
    abstract protected void run();

    /**
     * Ejecutamos el proceso el número de veces indicado y aumentamos el ciclo en dicho número
     * @param numEjecuciones Las veces que queremos que se ejecute el proceso
     */
    protected void ejecutarProceso(int numEjecuciones) {
        procesoActual.ejecutar(ciclo, numEjecuciones);
        ciclo+= numEjecuciones;
    }

    /**
     * Damos orden al proceso de terminar. Calculamos su índice de penalización
     * y aumentamos el contador de procesosTerminados
     */
    protected void finalizarProceso() {
        procesoActual.terminar(ciclo - 1);
        indicesPenalizacion.add(procesoActual.calcularPenalizacion());
        procesosTerminados++;
    }

    /**
     * Calculamos y mostramos en pantalla la eficiencia del algoritmo
     */
    protected void mostrarEficiencia() {
        double eficiencia = indicesPenalizacion.stream().reduce(Float::sum).orElse(0f) / numProcesos;
        System.out.format("La eficicencia es: %.2f \n", eficiencia);
    }

    /**
     * Cambia el proceso en ejecución por el siguiente al que le toca o por null si ya hemos terminado
     */
    protected void siguienteProceso() {
        procesoActual = procesosTerminados == numProcesos ? null : obtenerProceso();
    }

    /**
     * Cada algoritmo debe ejecutar este método de una forma particular. Nos indicará qué proceso
     * debemos ejecutar en cada momento.
     * @return El nuevo proceso a ejecutar
     */
    abstract protected Proceso obtenerProceso();
}
