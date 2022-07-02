package com.iespablopicasso.buscagen.componentes;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Aunque extienda de JButton, en realidad tendrá aspecto de JTextField. Servirá para seleccionar un archivo de la
 * máquina del usuario, mostrando su ruta en el campo de texto*/
public class InputFile extends JButton implements ActionListener {
    /**Para poder seleccionar el fichero*/
    private JFileChooser fc;
    /**Nos almacena el fichero seleccionado*/
    private File fichero;
    /**Salvo que se indique lo contrario en el constructor, no habrá una definida por defecto*/
    private AreaTexto atObjetivo = null;

    /**Constructor base, da el estilo y crea el {@link JFileChooser}, al que se le añade la opción de filtrar por
     * archivos .txt y .fa (FASTA)*/
    public InputFile() {
        super();
        setBackground(Color.white);
        setPreferredSize(new Dimension(430,20));
        setFocusable(false);
        setBorderPainted(false);
        fc = new JFileChooser();
        fc.addChoosableFileFilter(new FileNameExtensionFilter("*.txt","txt"));
        fc.addChoosableFileFilter(new FileNameExtensionFilter("*.fa","fa"));
        fc.setDialogTitle("Escoja un archivo");
    }

    /**Constructor que permite dar un AreaTexto para que se muestren en ella las primeras lineas de contenido del fichero
     * @param atObjetivo El AreaTexto donde se muestra la información del fichero
     * @see AreaTexto*/
    public InputFile(AreaTexto atObjetivo){
        this();
        this.atObjetivo = atObjetivo;
    }

    /**Cuando se pulsa, usa {@link #fc} para que se pueda seleccionar un archivo.
     * Si en el constructor se le ha pasado un {@link #atObjetivo}, da la orden de que se muestre en ésta el contendido del fichero
     * @see JFileChooser
     * @see AreaTexto*/
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        fc.updateUI();
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            fichero = fc.getSelectedFile();
            setText(fichero.getAbsolutePath());
            if (atObjetivo != null) atObjetivo.mostrarDatos(fichero);
        }
    }

    /**
     * Devuelve la ruta del fichero seleccionado
     * @return {@link File} El fichero que se ha seleccionado*/
    public File getFichero() {
        return fichero;
    }
}
