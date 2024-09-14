import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco();
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.FRANCE);

        while (true) {
            mostrarMenu();
            try {
                int opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        double saldoInicial = solicitarDouble(scanner, "Ingrese el saldo inicial: ");
                        System.out.print("Es una cuenta de ahorros? (s/n): ");
                        String esAhorros = scanner.next();
                        if (esAhorros.equalsIgnoreCase("s")) {
                            double tasaInteres = solicitarDouble(scanner, "Ingrese la tasa de interés: ");
                            banco.agregarCuenta(new CuentaAhorros(saldoInicial, tasaInteres));
                        } else {
                            double sobregiro = solicitarDouble(scanner, "Ingrese el límite de sobregiro: ");
                            banco.agregarCuenta(new CuentaCorriente(saldoInicial, sobregiro));
                        }
                        break;
                    case 2:
                        if (banco.numeroDeCuentas() == 0) {
                            System.out.println("No existen cuentas.");
                            break;
                        }
                        int numeroCuentaDeposito = solicitarInt(scanner, "Ingrese el número de cuenta: ", banco.numeroDeCuentas());
                        double cantidadDeposito = solicitarDouble(scanner, "Ingrese la cantidad a depositar: ");
                        banco.depositar(numeroCuentaDeposito, cantidadDeposito);
                        break;
                    case 3:
                        if (banco.numeroDeCuentas() == 0) {
                            System.out.println("No existen cuentas.");
                            break;
                        }
                        int numeroCuentaRetiro = solicitarInt(scanner, "Ingrese el número de cuenta: ", banco.numeroDeCuentas());
                        double cantidadRetiro = solicitarDouble(scanner, "Ingrese la cantidad a retirar: ");
                        banco.retirar(numeroCuentaRetiro, cantidadRetiro);
                        break;
                    case 4:
                        if (banco.numeroDeCuentas() == 0) {
                            System.out.println("No existen cuentas.");
                            break;
                        }
                        int numeroCuentaSaldo = solicitarInt(scanner, "Ingrese el número de cuenta: ", banco.numeroDeCuentas());
                        double saldo = banco.consultarSaldo(numeroCuentaSaldo);
                        System.out.println("El saldo de la cuenta es: " + saldo);
                        break;
                    case 5:
                        System.exit(0);
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingrese un número válido.");
                scanner.next();
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("********************************");
        System.out.println("**    1. Agregar cuenta       **");
        System.out.println("**    2. Depositar            **");
        System.out.println("**    3. Retirar              **");
        System.out.println("**    4. Consultar saldo      **");
        System.out.println("**    5. Salir                **");
        System.out.println("**    Seleccione una opción:  **");
        System.out.println("********************************");
    }

    private static double solicitarDouble(Scanner scanner, String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingrese un número válido.");
                scanner.next();
            }
        }
    }

    private static int solicitarInt(Scanner scanner, String mensaje, int max) {
        while (true) {
            try {
                System.out.print(mensaje);
                int numero = scanner.nextInt();
                if (numero >= 0 && numero < max) {
                    return numero;
                } else {
                    System.out.println("Por favor, ingrese un número válido entre 0 y " + (max - 1) + ".");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingrese un número válido.");
                scanner.next();
            }
        }
    }
}