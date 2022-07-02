package psp.practica.resumen2.NoApropiativo;

import psp.practica.resumen2.Proceso;
import java.util.List;

public class FIFO extends ProcesoNoApropiativo{

    public FIFO(List<Proceso> procesos) {
        super(procesos);
    }

    @Override
    protected Proceso obtenerProceso() {
        return procesos.get(0);
    }
}
