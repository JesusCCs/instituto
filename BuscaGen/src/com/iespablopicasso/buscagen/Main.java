package com.iespablopicasso.buscagen;

import com.iespablopicasso.buscagen.componentes.AreaTexto;
import com.iespablopicasso.buscagen.componentes.BotonContador;
import com.iespablopicasso.buscagen.componentes.InputFile;

import javax.swing.*;
import java.awt.*;

/**Clase principal. Es donde se monta la interfaz.*/
public class Main {

    /**Método de ejecución de nuestro programa, aquí es donde construimos la interfaz y establecemos las relaciones
     * entre las clases
     * @param args Parámetros que se reciben por consola, en nuestro caso no hay ninguna opción configurada al respecto*/
    public static void main(String[] args) {
        //establecemos la ventana
        JFrame miVentana = new JFrame();
        miVentana.setSize(600,400);
        miVentana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        miVentana.setLocationRelativeTo(null);
        miVentana.setResizable(false);

        //creamos los paneles y le añadimos el Layout que se va a usar en cada uno, también aplicamos el padding
        JPanel miPanel = new JPanel(new BorderLayout());
        JPanel panelNorte = new JPanel(new BorderLayout());
        panelNorte.setBorder(BorderFactory.createEmptyBorder(15,0,0,0));
        JPanel panelCentral = new JPanel();
        JPanel panel = new JPanel(new BorderLayout());

        //el area de texto donde se verá el archivo origen
        AreaTexto atOrigen = new AreaTexto();

        //Sección del Fichero Origen (FO)
        JPanel panelFO = new JPanel();
        JLabel jlFicheroO = new JLabel("Fichero Origen: ");
        panelFO.add(jlFicheroO);
        InputFile ifOrigen = new InputFile(atOrigen); //le pasamos el area de texto al inputfile origen
        ifOrigen.addActionListener(ifOrigen);
        panelFO.add(ifOrigen);

        //Sección del Fichero Destino (FD)
        JPanel panelFD = new JPanel();
        JLabel jlFicheroD = new JLabel("Fichero Destino:");
        panelFD.add(jlFicheroD);
        InputFile ifDestino = new InputFile();
        ifDestino.addActionListener(ifDestino);
        ifDestino.setToolTipText("Campo opcional. Si no selecciona un destino, se creará 'resultado.txt' en la localización del fichero origen");
        ToolTipManager.sharedInstance().setInitialDelay(0);
        ToolTipManager.sharedInstance().setDismissDelay(10000);
        panelFD.add(ifDestino);

        //Añadimos ambas secciones al panel correspondiente
        panelNorte.add(panelFO, BorderLayout.NORTH);
        panelNorte.add(panelFD, BorderLayout.SOUTH);

        //Seleccion de la cadena a buscar
        JLabel jlBusqueda = new JLabel("Secuencia: ");
        JTextField tfBusqueda = new JTextField(25);

        panelCentral.add(jlBusqueda);
        panelCentral.add(tfBusqueda);

        //Seleccion del número de errores permitidos
        JLabel jlErrores = new JLabel("Nº de errores:");
        JTextField tfErrores = new JTextField("0",2);
        tfErrores.setHorizontalAlignment(SwingConstants.RIGHT);
        tfErrores.setEnabled(false);

        panelCentral.add(jlErrores);
        panelCentral.add(tfErrores);

        //los botones de aumentar y disminuir
        BotonContador mas = new BotonContador(tfErrores,BotonContador.POSITIVO);
        mas.addActionListener(mas);
        BotonContador menos = new BotonContador(tfErrores,BotonContador.NEGATIVO);
        menos.addActionListener(menos);

        panelCentral.add(mas);
        panelCentral.add(menos);

        //empaquetamos los elementos en el panel
        panel.add(panelNorte,BorderLayout.NORTH);
        panel.add(panelCentral, BorderLayout.CENTER);

        //el boton principal
        JButton boton = new JButton("Comenzar");
        boton.setFocusable(false);

        //Iniciamos el controlador
        Controlador controlador = Controlador.getControlador(ifOrigen,ifDestino,tfBusqueda,tfErrores,atOrigen,boton);
        boton.addActionListener(controlador); //y lo añadimos como listener al boton


        miPanel.add(panel,BorderLayout.NORTH);
        miPanel.add(atOrigen,BorderLayout.CENTER);
        miPanel.add(boton, BorderLayout.SOUTH);
        miVentana.add(miPanel);

        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.put("Button.disabledText",Color.black);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        miVentana.setVisible(true);
    }
}