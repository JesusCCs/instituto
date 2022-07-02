package psp.practica.resumen2.Apropiativo;

import psp.practica.resumen2.Algoritmo;
import psp.practica.resumen2.Proceso;
import java.util.List;

public abstract class ProcesoApropiativo extends Algoritmo {
    protected final int quantum;

    public ProcesoApropiativo (List<Proceso> procesos, int quantum) {
        super(procesos);
        this.quantum = quantum;
    }

    @Override
    public void run() {

        while (procesosTerminados != numProcesos) {

            if (procesoActual.getLlegada() < ciclo) {

                int numEjecuciones = Math.min(quantum, procesoActual.getRafagasPendientes());
                ejecutarProceso(numEjecuciones);
                if (procesoActual.estaTerminado()) finalizarProceso();
                siguienteProceso();

            } else {
                ciclo++;
            }
        }

        super.mostrarEficiencia();
    }
}
