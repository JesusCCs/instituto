package com.iespablopicasso.buscagen;

import com.iespablopicasso.buscagen.componentes.AreaTexto;
import com.iespablopicasso.buscagen.componentes.InputFile;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

/**Clase que comunica la vista con el modelo, haciendo llegar los datos adecuados a este �ltimo
 * Sigue el modelo Singleton*/
public class Controlador implements ActionListener {

    /**El campo donde est� el archivo a leer*/
    private InputFile origen;
    /**El campo donde est� el archivo de escritura del resultado*/
    private InputFile destino;
    /**El campo donde est� el gen a encontrar*/
    private JTextField busqueda;
    /**El campo donde est� el n�mero de mutaciones m�ximas permitidas*/
    private JTextField errores;
    /**El campo donde se encuentran las primeras lineas de texto del fichero*/
    private AreaTexto area;
    /**El bot�n que indica cuando empezar a trabajar*/
    private JButton boton;
    /**Estado necesario para implementar el modelo Singleton*/
    static private Controlador controlador = null;

    /**Constructor privado
     * @param origen El InputFile que contiene la ruta al fichero FASTA
     * @param destino El InputFile que contiene el archivo donde se va a volcar los resultados
     * @param busqueda  El JTextField que contiene el gen a buscar
     * @param errores El JTextField que contiene el n�meor de mutaciones m�ximas permitidas
     * @param area El AreaTexto que contiene la informaci�n de las primeras l�neas de texto del fichero
     * @param boton El bot�n "Comenzar"*/
    private Controlador(InputFile origen, InputFile destino, JTextField busqueda, JTextField errores, AreaTexto area,JButton boton) {
        this.origen = origen;
        this.destino = destino;
        this.busqueda = busqueda;
        this.errores = errores;
        this.area = area;
        this.boton = boton;
    }

    /**Quien realiza la inicializaci�n del Controlador, garantizando que solo exista una instancia del mismo. Debe recibir los mismos par�metros
     * que necesita el {@link Controlador#Controlador(InputFile, InputFile, JTextField, JTextField, AreaTexto, JButton)}
     * @param origen El InputFile que contiene la ruta al fichero FASTA
     * @param destino El InputFile que contiene el archivo donde se va a volcar los resultados
     * @param busqueda  El JTextField que contiene el gen a buscar
     * @param errores El JTextField que contiene el n�meor de mutaciones m�ximas permitidas
     * @param area El AreaTexto que contiene la informaci�n de las primeras l�neas de texto del fichero
     * @param boton El bot�n "Comenzar"
     * @return {@link Controlador}*/
    static public Controlador getControlador(InputFile origen, InputFile destino, JTextField busqueda, JTextField errores,AreaTexto area,JButton boton) {
        if (controlador == null) return controlador = new Controlador(origen,destino,busqueda,errores,area,boton);
        else return controlador;
    }

    /**Se ejecuta al pulsar el bot�n de Comenzar en la interfaz, se usan m�todos soporte para hacer control de errores
     * y la clase {@link SwingWorker} para no bloquear la interfaz y as� poder actualizar al bot�n principal
     * @see SwingWorker*/
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if (!hayErrores()){
            boton.setText("Trabajando ...");
            boton.setEnabled(false);

            SwingWorker hilo = new SwingWorker() {
                @Override
                protected Void doInBackground() {
                    realizarBusqueda();
                    return null;
                }

                @Override
                protected void done() {
                    super.done();
                    boton.setText("Comenzar");
                    boton.setEnabled(true);
                    ImageIcon icon = new ImageIcon("res/check.png");
                    JOptionPane.showMessageDialog(null,
                            "Todo correcto. Ya puede abrir el archivo destino.","Resultado",JOptionPane.INFORMATION_MESSAGE,icon);
                }
            };

            hilo.execute();
        }
    }

    /**Comprueba si hay errores en los datos proporcionados por el usuario
     * @return True si hay alg�n error, false si no
     * @see com.iespablopicasso.buscagen.ControladorErrores*/
    private boolean hayErrores(){
        boolean errorCampos = ControladorErrores.hayErrorCampos(origen.getText(),busqueda.getText(),errores.getText());
        if (!errorCampos) return ControladorErrores.hayErrorFichero(area.getTexto());
        return true;
    }

    /**M�todo por el que se llama al modelo para hacer el trabajo
     * @see com.iespablopicasso.buscagen.Fasta*/
    private void realizarBusqueda() {
        File fileO = origen.getFichero();
        File fileD = getDestino();

        String cadena = busqueda.getText().toUpperCase();
        int numErrores = Integer.parseInt(errores.getText());

        Fasta fasta = new Fasta(fileO,fileD,cadena,numErrores);
        fasta.escribirCoincidencias();
    }

    /**Si no se ha proporcionado un {@link Controlador#destino} donde escribir la informaci�n, se crear� uno, si no
     * se usa el que ya se ha dado
     * @return Devuelve el fichero donde se va a depositar los resultados*/
    private File getDestino(){
        File fichero;

        if ((fichero = destino.getFichero()) != null) return fichero;
        else {
            File directorio = origen.getFichero().getParentFile();
            String nombre = "resultado";
            int contador = 1;
            while (Files.exists(Paths.get(directorio + "/" + nombre + ".txt"))){
                nombre = "resultado(" + contador + ")";
                contador++;
            }
            fichero = new File(directorio + "/" + nombre + ".txt");
        }
        return fichero;
    }
}
