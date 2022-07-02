package practica1;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class Consola {

	private static final Scanner sc = new Scanner(System.in);
	private static boolean continuar;

	/**
	 * Punto de entrada de la aplicación. Es donde se encuentra el menú de selección de acciones
	 * @param args
	 */
	public static void main(String[] args) {
		Controlador controlador = Controlador.getControlador();

		continuar = true;
		do {
			System.out.println("Introduzca la acción que quiere realizar:");
			System.out.println(
					"1. Crear una carpeta dada una ruta y el nombre\n" +
					"2. Crear un fichero dada la ruta y el nombre\n" +
					"3. Listar todas las interfaces de red de nuestro ordenador\n" +
					"4. Mostrar la IP del ordenador dado el nombre de la interfaz de red\n" +
					"5. Mostrar la dirección MAC dado el nombre de la interfaz de red\n" +
					"6. Comprobar conectividad con internet\n" +
					"7. Salir"
			);
			System.out.print(">");
			int opcion = pedirNumero();
			
			try {
				controlador.ejecutarAccion(opcion);
			} catch (Exception e) {
				System.out.println("Hubo un problema y no se pudo realizar la operación.");
			}

			if (continuar) {
				String respuesta;
				do {
					System.out.println("¿Quiere continuar? (S/N)");
					respuesta = sc.next();
				} while (!(respuesta.equalsIgnoreCase("s") || respuesta.equalsIgnoreCase("n")));

				continuar = respuesta.equalsIgnoreCase("s");
			}

		} while (continuar);

		System.out.println("Terminando...");
		sc.close();
	}

	/**
	 * Se pude un número al ususario. Si el input no es numérico se vuelve a preguntar
	 * @return El número que el usuario introduce
	 */
	public static int pedirNumero() {
		int numero = 0;
		boolean continuar;

		do {
			try {
				numero = Integer.parseInt(sc.nextLine());
				continuar = false;
			} catch (Exception e) {
				System.out.println("Por favor, introduzca un número: ");
				continuar = true;
			}
		} while (continuar);
		return numero;
	}

	/**
	 * Se pide un ruta válida al ususario. Si no es válida se vuelve a preguntar
	 * @return La ruta que el usuario ha escrito
	 */
	public static String pedirRuta() {
		String ruta;
		do {
			System.out.println("Introduce una ruta válida: ");
			ruta = sc.nextLine();
		} while (checkRuta(ruta));

		return ruta;
	}

	private static boolean checkRuta(String ruta) {
		return new File(ruta).getAbsoluteFile().exists();
	}

	/**
	 * Pide input al usuario
	 * @param esFichero Según estemos pidiendo un nombre para un fichero o no, mostramos distintos mensajes
	 * @return El nombre del fichero/carpeta que el usuario introduce
	 */
	public static String pedirNombre(boolean esFichero) {
		String mensaje = esFichero ? "Introduce un nombre para el fichero:" : "Introduce un nombre para la carpeta:";
		System.out.println(mensaje);
		return sc.nextLine();
	}

	/**
	 * Da a escoger al usuario una interfaz de todas las que hay disponibles
	 * @param interfaces
	 * @return La interfaz señalada por el usuario
	 */
	public static InterfazRed pedirInterfaz(List<InterfazRed> interfaces) {
		System.out.println("interfaces disponibles: \n");
		for (int i = 0; i < interfaces.size(); i++) {
			System.out.println((i + 1) + "-> " + interfaces.get(i).getNombre() + "\n");
		}

		boolean escogido;
		int numInterfaz;
		do {
			System.out.println("Escoge una interfaz:");
			numInterfaz = pedirNumero();
			escogido = numInterfaz >= 1 && numInterfaz <= interfaces.size();
		} while (!escogido);

		return interfaces.get(numInterfaz);
	}

	/**
	 * Da un mensaje al usuario según haya o noconexión a Internet
	 * @param hayConexion
	 */
	public static void mostrarConectividad(boolean hayConexion) {
		if (hayConexion) System.out.println("Hay conexión a internet");
		else System.out.println("No hay conexión");
	}

	/**
	 * Lista los nombres de las interfaces de red
	 * @param nombresInterfaces
	 */
	public static void mostrarInterfaces(List<String> nombresInterfaces) {
		System.out.println("Se han encontrado las siguientes interfaces:");
		nombresInterfaces.forEach(System.out::println);
	}

	/**
	 * Mensaje formateado para dar a conocer la IP
	 * @param ip
	 */
	public static void mostrarIP(String ip) {
		System.out.println("La IP es: " + ip);
	}

	/**
	 * Mensaje formateado para dar a conocer la MAC
	 * @param mac
	 */
	public static void mostrarMAC(String mac) {
		System.out.println("La MAC es: " + mac);;
	}

	/**
	 * Terminamos el programa
	 */
	public static void finalizarPrograma() {
		continuar = false;
	}
}
