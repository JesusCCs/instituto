package psp.practica.resumen2;

import psp.practica.resumen2.Apropiativo.RoundRobin;
import psp.practica.resumen2.Apropiativo.SRT;
import psp.practica.resumen2.NoApropiativo.FIFO;
import psp.practica.resumen2.NoApropiativo.SJF;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("FIFO: ");
        new FIFO(generarLista()).run();

        System.out.println("SJF: ");
        new SJF(generarLista()).run();

        System.out.println("SRT: ");
        new SRT(generarLista()).run();

        System.out.println("Round Robin: ");
        new RoundRobin(generarLista(), 2).run();
    }


    private static List<Proceso> generarLista() {
        ArrayList<Proceso> procesos = new ArrayList<>();

        procesos.add(new Proceso('A', 0, 5));
        procesos.add(new Proceso('B', 2, 4));
        procesos.add(new Proceso('C', 3, 3));
        procesos.add(new Proceso('D', 5, 2));
        procesos.add(new Proceso('E', 6, 3));

        Collections.sort(procesos);

        return procesos;
    }
}
