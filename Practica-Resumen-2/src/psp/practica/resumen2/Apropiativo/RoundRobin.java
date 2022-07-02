package psp.practica.resumen2.Apropiativo;

import psp.practica.resumen2.Proceso;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RoundRobin extends ProcesoApropiativo{
    private Queue<Proceso> colaRR;

    public RoundRobin (List<Proceso> procesos, int quantum) {
        super(procesos, quantum);
        colaRR = new LinkedList<>();
        procesoActual = procesos.remove(0); // En Round Robin ejecutamos el primer proceso al inicio. Además, lo eliminamos de la lista de procesos
    }

    @Override
    protected Proceso obtenerProceso() {
        completarCola();
        if (!procesoActual.estaTerminado()) colaRR.add(procesoActual);
        return colaRR.poll();
    }

    private void completarCola() {
        while (!procesos.isEmpty() && procesos.get(0).getLlegada() < ciclo) {
            colaRR.add(procesos.remove(0)); // Conforme vamos añadiendo a la cola vamos eliminando de la lista de procesos
        }
    }
}
