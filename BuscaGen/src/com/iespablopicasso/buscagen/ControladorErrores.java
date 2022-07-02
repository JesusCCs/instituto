package com.iespablopicasso.buscagen;

import javax.swing.*;

/**Clase que no necesitará de instanciación y que mostrará en pantalla diferentes mensajes según se vayan
 * encontrando problemas en la entrada del usuario*/
abstract public class ControladorErrores {

    /**
     * Método encargado de comprobar los errores de escritura en los campos de la interfaz
     * @param textoCampoOrigen El String que contenga el InputFile encargado de tener el fichero a leer
     * @param textoBusqueda El String del JTextField con el gen a buscar
     * @param textoErrores El String del JTextField con el número de errores máximos permitidos
     * @return True si hay algún error, false si no hay ningún problema*/
    static public boolean hayErrorCampos(String textoCampoOrigen, String textoBusqueda, String textoErrores) {

        if (textoCampoOrigen.isEmpty() || textoBusqueda.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Tanto el Fichero Origen como la Secuencia deben estar indicadas");
            return true;
        }

        if (!textoBusqueda.matches("[A-Za-z]+")){
            JOptionPane.showMessageDialog(null, "El campo de la secuencia solo debe contener letras");
            return true;
        }

        if (Integer.parseInt(textoErrores) >= textoBusqueda.length()){
            JOptionPane.showMessageDialog(null, "El número de errores permitidos no debe ser mayor o igual que la longitud del gen a buscar");
            return true;
        }

        return false;
    }

    /**
     * Método que comprueba los errores que puede haber en el fichero a leer
     * @param textoFichero El String que contiene el AreaTexto con las primeras lineas del fichero
     * @return True si hay algún problema, false si no*/
    static public boolean hayErrorFichero(String textoFichero) {
        if (textoFichero.equals("")){
            JOptionPane.showMessageDialog(null, "El fichero origen está vacío");
            return true;
        }

        if (textoFichero.charAt(0) != '>'){
            JOptionPane.showMessageDialog(null, "El fichero origen no tiene una primera línea con '>'. Revíselo");
            return true;
        }

        if (textoFichero.split("\n").length == 1){
            JOptionPane.showMessageDialog(null, "El fichero origen no tiene datos fuera de su descripción");
            return true;
        }

        return false;
    }
}
