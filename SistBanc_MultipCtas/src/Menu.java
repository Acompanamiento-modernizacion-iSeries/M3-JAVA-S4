import java.util.Scanner;

public class Menu {

    public static int principal(Scanner scanner) {
        while (true) {
            System.out.println("Ingrese el numero de la Transaccion a realizar:\n" +
                    " 1. Crear una nueva cuenta.\n" +
                    " 2. Cambiar entre cuentas existentes.\n" +
                    " 3. Consultar el saldo de la cuenta.\n" +
                    " 4. Realizar deposito a la cuenta.\n" +
                    " 5. Realizar retiro de la cuenta.\n" +
                    " 6. Calcular el interes sobre el saldo (Cuenta Ahorros).\n" +
                    " 7. Consultar el sobregiro (Cuenta Corriente).\n" +
                    " 0. TERMINAR");
            if (scanner.hasNextInt()) return scanner.nextInt();
            else {
                System.out.println("Tipo de transaccion no reconocida!");
                scanner.next(); // Limpiar el buffer del scanner para evitar un bucle infinito
            }
        }
    }

}
