package com.iespablopicasso.buscagen.componentes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Permite crear dos tipos de botones: uno para aumentar en uno el valor de un TextField que
 * contenga valores num�ricos, otro para disminuir
 */
public class BotonContador extends JButton implements ActionListener {
    /**Constante para facilitar la definici�n del tipo de bot�n*/
    public static final int POSITIVO = 1;
    /**Constante para facilitar la definici�n del tipo de bot�n*/
    public static final int NEGATIVO = 0;
    /**Se�alar� si el bot�n debe aumentar o disminuir*/
    private boolean aumentar;
    /**El {@link JTextField} al que afectar� el bot�n*/
    private JTextField tfObjetivo;

    /**
     * Constructor que le dar� un icono al bot�n dependiendo de su tipo
     * @param tfObjetivo El JTextField al que se pretende aumentar o disminuir su valor
     * @param tipoBoton Se puede se�alar con las constantes publicas
     */
    public BotonContador(JTextField tfObjetivo,int tipoBoton) {
        this.tfObjetivo = tfObjetivo;
        ImageIcon icono;
        if (aumentar = (tipoBoton == POSITIVO)){
            icono = new ImageIcon("res/positive.png");
        }else{
            icono = new ImageIcon("res/negative.png");
        }
        setIcon(icono);
        setFocusable(false);
        setBorderPainted(false);
        setPreferredSize(new Dimension(20,20));
    }

    /**
     * Cuando se pulse el bot�n se setear� el nuevo valor en el {@link BotonContador#tfObjetivo}. Tambi�n
     * se controlar� cu�ndo tiene sentido hacerlo: no se permitir� n�meros negativos
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        int num = Integer.parseInt(tfObjetivo.getText());
        if (num >= 0){
            if (num == 0 && !aumentar);
            else num += (aumentar) ? 1:-1;
        }
        tfObjetivo.setText(String.valueOf(num));
    }
}