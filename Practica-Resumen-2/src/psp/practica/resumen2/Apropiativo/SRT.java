package psp.practica.resumen2.Apropiativo;

import psp.practica.resumen2.Proceso;
import java.util.Comparator;
import java.util.List;

public class SRT extends ProcesoApropiativo {

    public SRT (List<Proceso> procesos) {
        super(procesos, 1);
        procesoActual = obtenerProceso();
    }

    @Override
    protected void finalizarProceso() {
        super.finalizarProceso();
        procesos.remove(procesoActual);
    }

    @Override
    protected Proceso obtenerProceso() {
        return procesos.stream()
                .filter(proceso -> proceso.getLlegada() < ciclo)
                .min(Comparator.comparing(Proceso::getRafagasPendientes)).orElse(null);
    }
}
