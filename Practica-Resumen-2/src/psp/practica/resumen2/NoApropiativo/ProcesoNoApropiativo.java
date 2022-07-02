package psp.practica.resumen2.NoApropiativo;

import psp.practica.resumen2.Algoritmo;
import psp.practica.resumen2.Proceso;
import java.util.List;

/**
 * Engloba tanto al FIFO como al SJF. Son aquellos que, una vez escogido el proceso, lo ejecutamos al completo
 * antes de pasar al siguiente.
 */
public abstract class ProcesoNoApropiativo extends Algoritmo {

    public ProcesoNoApropiativo(List<Proceso> procesos) {
        super(procesos);
        procesoActual = obtenerProceso();
    }

    @Override
    public void run() {

        while (procesosTerminados != numProcesos) {

            if (procesoActual.getLlegada() < ciclo) {

                ejecutarProceso(procesoActual.getRafaga()); // Ejecutamos todas las ráfagas
                finalizarProceso(); // Finalizamos el proceso
                siguienteProceso();// Avanzamos al siguiente proceso en la lista si lo hubiese

            } else {
                ciclo++;
            }

        }

        super.mostrarEficiencia();
    }

    @Override
    protected void finalizarProceso() {
        super.finalizarProceso();
        procesos.remove(procesoActual);
    }
}
