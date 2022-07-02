package Utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Commons {

    public static int leerNumero(Scanner lector) {
        boolean continuar; int numero = 0;

        do {
            try {
                numero = Integer.parseInt(lector.nextLine());
                continuar = numero >= 1;
                if (!continuar) throw new Exception();
            } catch (Exception e) {
                System.out.println("Introduzca un número mayor que 0, por favor");
                continuar = false;
            }
        } while (!continuar);

        return numero;
    }

    public static int leerNumero(Scanner lector, int numFinal) {
        boolean continuar;
        do {
            int numero = leerNumero(lector);
            continuar = numero <= numFinal && numero > 0;
            if (!continuar)  System.out.println("Introduzca una opción válida, por favor");
            else return numero;
        } while (true);
    }

    public static String fechaHoy() {
        Date date = Calendar.getInstance().getTime();
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        return formatter.format(date);
    }

    public static String dateToDB(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

}
