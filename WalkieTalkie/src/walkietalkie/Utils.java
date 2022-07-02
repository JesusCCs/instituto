package walkietalkie;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Clase wrapper de métodos de utilidad
 */
public class Utils {

    public static int leerNumero(Scanner lector) {
        boolean continuar; int numero = 0;

        do {
            try {
                numero = Integer.parseInt(lector.nextLine());
                continuar = true;
            } catch (Exception e) {
                System.out.println("Introduzca un número, por favor");
                continuar = false;
            }
        } while (!continuar);

        return numero;
    }

    public static int leerNumero(Scanner lector, Integer[] condiciones) {
        boolean continuar;
        do {
            int numero = leerNumero(lector);
            continuar = Arrays.stream(condiciones).anyMatch(integer -> integer == numero);
            if (!continuar)  System.out.println("Introduzca una opción válida, por favor");
            else return numero;
        } while (true);
    }

    // Referencia: https://owasp.org/www-community/OWASP_Validation_Regex_Repository
    private static final String IPV4_REGEX =
            "^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                    "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                    "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                    "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";

    private static final Pattern IPv4_PATTERN = Pattern.compile(IPV4_REGEX);

    public static String leerIP(Scanner lector) {
        while (true) {
            String ip = lector.nextLine();
            if (IPv4_PATTERN.matcher(ip).matches()) return ip;
            System.out.println("Introduzca una IP válida, por favor");
        }
    }

}
