package com.iespablopicasso.buscagen;


import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;


class FastaTest {

    //método para facilitar las pruebas
    private Queue<Character> generarCola(String elementos){
        Queue<Character> cola = new LinkedList<>();

        for (int i = 0; i < elementos.length(); i++) {
            cola.add(elementos.charAt(i));
        }

        return cola;
    }


    @Test
    void pruebaSinError() {
        String[] actual = Fasta.comparar(generarCola("AAAAAA"),"AAAAAA",1);
        String[] esperado = {"AAAAAA","0"};

        assertArrayEquals(esperado,actual);
    }

    @Test
    void pruebaConError() {
        String[] actual = Fasta.comparar(generarCola("AAGAAA"),"AAAAAA",1);
        String[] esperado = {"AAGAAA","1"};

        assertArrayEquals(esperado,actual);
    }

    @Test
    void pruebaSinRespuesta() {
        String[] actual = Fasta.comparar(generarCola("AAGAGA"),"AAAAAA",1);
        //hay dos errores y como mucho permitimos 1, por lo que no esperamos respuesta
        String[] esperado = null;

        assertArrayEquals(esperado,actual);
    }

    @Test
    void pruebaSinErrorVariasLetras() {
        String[] actual = Fasta.comparar(generarCola("AGADTT"),"AGADTT",1);
        String[] esperado = {"AGADTT","0"};

        assertArrayEquals(esperado,actual);
    }

    @Test
    void pruebaConErrorVariasLetras() {
        String[] actual = Fasta.comparar(generarCola("CGCDTT"),"AGADTT",2);
        String[] esperado = {"CGCDTT","2"};

        assertArrayEquals(esperado,actual);
    }

    @Test
    void pruebaSinRespuestaVariasLetras() {
        String[] actual = Fasta.comparar(generarCola("CACDTT"),"AGADTT",2);
        //hay tres errores y como mucho permitimos 2, por lo que no esperamos respuesta
        String[] esperado = null;

        assertArrayEquals(esperado,actual);
    }
}