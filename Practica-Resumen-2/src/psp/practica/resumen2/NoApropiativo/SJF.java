package psp.practica.resumen2.NoApropiativo;

import psp.practica.resumen2.Proceso;

import java.util.Comparator;
import java.util.List;

public class SJF extends ProcesoNoApropiativo {

    public SJF(List<Proceso> procesos) {
        super(procesos);
    }

    @Override
    public Proceso obtenerProceso() {
        return procesos.stream()
                .filter(proceso -> proceso.getLlegada() < ciclo)
                .min(Comparator.comparing(Proceso::getRafaga))
                .orElse(null);
    }
}
