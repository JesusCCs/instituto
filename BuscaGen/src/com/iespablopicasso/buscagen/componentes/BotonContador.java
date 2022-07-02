package com.iespablopicasso.buscagen.componentes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Permite crear dos tipos de botones: uno para aumentar en uno el valor de un TextField que
 * contenga valores numéricos, otro para disminuir
 */
public class BotonContador extends JButton implements ActionListener {
    /**Constante para facilitar la definición del tipo de botón*/
    public static final int POSITIVO = 1;
    /**Constante para facilitar la definición del tipo de botón*/
    public static final int NEGATIVO = 0;
    /**Señalará si el botón debe aumentar o disminuir*/
    private boolean aumentar;
    /**El {@link JTextField} al que afectará el botón*/
    private JTextField tfObjetivo;

    /**
     * Constructor que le dará un icono al botón dependiendo de su tipo
     * @param tfObjetivo El JTextField al que se pretende aumentar o disminuir su valor
     * @param tipoBoton Se puede señalar con las constantes publicas
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
     * Cuando se pulse el botón se seteará el nuevo valor en el {@link BotonContador#tfObjetivo}. También
     * se controlará cuándo tiene sentido hacerlo: no se permitirá números negativos
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