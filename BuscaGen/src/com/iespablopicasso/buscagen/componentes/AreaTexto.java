package com.iespablopicasso.buscagen.componentes;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**Panel que posee un JTextArea y que será capaz de cambiar su contenido con el fin de mostrar el texto de un fichero*/
public class AreaTexto extends JPanel {

    /**El area de texto donde se mostrará la información*/
    private JTextArea taArea;

    /**Crea el panel. Dará margen y estilo al {@link #taArea}, envolviendola en un {@link JScrollPane}, por si el archivo tiene
     * una cantidad de texto tal que las dimensiones del {@link #taArea} no puede contener
     * @see JTextArea
     * @see JScrollPane*/
    public AreaTexto() {
        setLayout(new BorderLayout());

        taArea = new JTextArea("Se mostrará el texto del fichero origen cuando se escoja");
        taArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(taArea);

        add(scrollPane,BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(15,30,20,30));
    }

    /**Muestra las primeras 10 líneas de un archivo
     * @param fichero El fichero del que se quiere mostrar el texto*/
    public void mostrarDatos(File fichero){
        try {
            BufferedReader lector = new BufferedReader(new FileReader(fichero));
            String linea;
            int contador = 1;
            StringBuilder datos = new StringBuilder();

            while((linea = lector.readLine()) != null && contador <= 10){
                datos.append(linea).append("\n");
                contador++;
            }

            taArea.setText(datos.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**Se puede obtener el contenido de {@link #taArea}
     * @return Devuelve el texto que contiene*/
    public String getTexto() {
        return taArea.getText();
    }
}
