package taller4;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Banco {

    private Map<String, Cuenta> cuentas;

    public Banco() {
        cuentas = new HashMap<>();
    }

    public void agregarCuenta(String numeroCuenta, Cuenta cuenta) {
        cuentas.put(numeroCuenta, cuenta);
    }

    public Cuenta obtenerCuenta(String numeroCuenta) {
        return cuentas.get(numeroCuenta);
    }

    public void realizarDeposito(String numeroCuenta, BigDecimal cantidad) {
        Cuenta cuenta = obtenerCuenta(numeroCuenta);
        if (cuenta != null) {
            cuenta.depositar(cantidad);
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }

    public void realizarRetiro(String numeroCuenta, BigDecimal cantidad) {
        Cuenta cuenta = obtenerCuenta(numeroCuenta);
        if (cuenta != null) {
            cuenta.retirar(cantidad);
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }

    public void consultarSaldo(String numeroCuenta) {
        Cuenta cuenta = obtenerCuenta(numeroCuenta);
        if (cuenta != null) {
            System.out.printf("Saldo de la cuenta %s: $%.2f%n", numeroCuenta, cuenta.consultarSaldo());
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }

    public void mostrarMenu() {
        System.out.println("1. Consultar saldo");
        System.out.println("2. Realizar depósito");
        System.out.println("3. Realizar retiro");
        System.out.println("4. Aplicar interés (solo cuenta de ahorros)");
        System.out.println("5. Salir");
    }

    public void menuInteractivo() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            mostrarMenu();
            System.out.print("Seleccione una opción: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Entrada inválida. Ingrese un número entero.");
                scanner.next();
                System.out.print("Seleccione una opción: ");
            }
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el número de cuenta: ");
                    String cuentaConsulta = scanner.nextLine();
                    consultarSaldo(cuentaConsulta);
                    break;
                case 2:
                    System.out.print("Ingrese el número de cuenta: ");
                    String cuentaDeposito = scanner.nextLine();
                    System.out.print("Ingrese la cantidad a depositar: $");
                    BigDecimal deposito = new BigDecimal(scanner.nextLine());
                    realizarDeposito(cuentaDeposito, deposito);
                    break;
                case 3:
                    System.out.print("Ingrese el número de cuenta: ");
                    String cuentaRetiro = scanner.nextLine();
                    System.out.print("Ingrese la cantidad a retirar: $");
                    BigDecimal retiro = new BigDecimal(scanner.nextLine());
                    realizarRetiro(cuentaRetiro, retiro);
                    break;
                case 4:
                    System.out.print("Ingrese el número de cuenta: ");
                    String cuentaInteres = scanner.nextLine();
                    Cuenta cuenta = obtenerCuenta(cuentaInteres);
                    if (cuenta instanceof CuentaAhorros) {
                        ((CuentaAhorros) cuenta).aplicarInteres();
                    } else {
                        System.out.println("La cuenta no es de ahorros.");
                    }
                    break;
                case 5:
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("Opción no válida. Seleccione una opción del menú.");
            }
        } while (opcion != 5);

        scanner.close();
    }

    public static void main(String[] args) {
        Banco banco = new Banco();
        banco.agregarCuenta("12345", new CuentaCorriente(new BigDecimal("5000")));
        banco.agregarCuenta("67890", new CuentaAhorros(new BigDecimal("0.03")));

        banco.menuInteractivo();
    }
}

