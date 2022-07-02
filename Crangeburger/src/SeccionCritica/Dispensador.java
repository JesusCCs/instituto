package SeccionCritica;

import Hilo.Cliente;

/**
 * Sección crítica del programa. Donde contendremos los métodos que controlarán la lógica principal del programa.
 */
public class Dispensador {

    /**
     * Estado que nos indica el número de hamburguesas presentes
     */
    private int tray;

    public Dispensador() {
        tray = 0;
    }

    /**
     * Método que aumentará el número de hamburguesas disponibles y notificará al resto de hilos siempre, ya que no
     * hay una condición por la que no se deba parar de hacer hamburguesas.
     * @param cocineroID Para tener una referencia al cocinero en concreto que realiza la acción
     */
    public synchronized void hacerHamburguesa(int cocineroID) {
        tray++;
        System.out.println("Cocinero " + cocineroID + " hace hamburguesa. Hamburguesas en el tray: " + tray);
        notify();
    }

    /**
     * Método que disminuye el contador de las hamburguesas que hay. Aquí sí habrá cierta condición que evite
     * que siempre pueda haber un descenso en el contador.
     * @param cliente La referencia al cliente para imprimir por pantalla los mensajes.
     * @throws InterruptedException
     */
    public synchronized void consumirHamburguesa(Cliente cliente) throws InterruptedException {
        System.out.println("Cliente " + cliente.getID() + " tiene hambre. Hamburguesas en el tray: " + tray);

        while (tray == 0) {
            System.out.println("Cliente " + cliente.getID() + " no puede comer.");
            wait();
        }

        tray--;

        System.out.println("Cliente " + cliente.getID() + " come una cangreburguer. Hamburguesas en el tray: " + tray);
        notify();
    }
}
