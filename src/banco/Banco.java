package banco;
import cuentas.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Banco {
    private List<Cuenta> cuentas;
    private Scanner scanner;

    public Banco() {
        cuentas = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void agregarCuenta(Cuenta cuenta)
    {
        cuentas.add(cuenta);
    }

    public void mostrarMenu() {
        while (true) {
            System.out.println("----------------------");
            System.out.println("--- Menú del Banco ---");
            System.out.println("----------------------");
            System.out.println("1. Crear cuenta");
            System.out.println("2. Depositar");
            System.out.println("3. Retirar");
            System.out.println("4. Consultar saldo");
            System.out.println("5. Aplicar interés (solo cuentas de ahorro)");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            Scanner sc = new Scanner(System.in);
            int opcion;

            opcion = obtenerOpcionValida(sc);

            switch (opcion) {
                case 1:
                    crearCuenta();
                    break;
                case 2:
                    realizarDeposito();
                    break;
                case 3:
                    realizarRetiro();
                    break;
                case 4:
                    consultarSaldo();
                    break;
                case 5:
                    aplicarInteres();
                    break;
                case 6:
                    System.out.println("Gracias por usar nuestro sistema bancario.");
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private void crearCuenta() {
        System.out.print("Ingrese el nombre del titular: ");
        String titular = scanner.nextLine();
        System.out.print("Ingrese el saldo inicial: ");
        BigDecimal saldo = scanner.nextBigDecimal();
        System.out.print("Tipo de cuenta (1: Ahorro, 2: Corriente): ");
        int tipoCuenta = scanner.nextInt();

        if (tipoCuenta == 1) {
            System.out.print("Ingrese la tasa de interés: ");
            double tasaInteres = scanner.nextDouble();
            agregarCuenta(new CuentaAhorros(titular, saldo, tasaInteres));
        } else if (tipoCuenta == 2) {
            System.out.print("Ingrese el límite de sobregiro: ");
            BigDecimal sobregiro = scanner.nextBigDecimal();
            agregarCuenta(new CuentaCorriente(titular, saldo, sobregiro));
        } else {
            System.out.println("Tipo de cuenta no válido.");
            return;
        }
        System.out.println("Cuenta creada exitosamente.");
    }

    private void realizarDeposito() {
        Cuenta cuenta = seleccionarCuenta();
        if (cuenta != null) {
            System.out.print("Ingrese la cantidad a depositar: ");
            BigDecimal cantidad = scanner.nextBigDecimal();
            cuenta.depositar(cantidad);
            System.out.println("Depósito realizado exitosamente.");
        }
    }

    private void realizarRetiro() {
        Cuenta cuenta = seleccionarCuenta();
        if (cuenta != null) {
            System.out.print("Ingrese la cantidad a retirar: ");
            BigDecimal cantidad = scanner.nextBigDecimal();
            cuenta.retirar(cantidad);
        }
    }

    private void consultarSaldo() {
        Cuenta cuenta = seleccionarCuenta();
        if (cuenta != null) {
            cuenta.mostrarInformacion();
        }
    }

    private void aplicarInteres() {
        Cuenta cuenta = seleccionarCuenta();
        if (cuenta != null && cuenta instanceof CuentaAhorros) {
            ((CuentaAhorros) cuenta).aplicarInteres();
            System.out.println("Interés aplicado exitosamente.");
        } else {
            System.out.println("Esta operación solo es válida para cuentas de ahorro.");
        }
    }

    private Cuenta seleccionarCuenta() {
        if (cuentas.isEmpty()) {
            System.out.println("No hay cuentas registradas.");
            return null;
        }

        System.out.println("Seleccione una cuenta:");
        for (int i = 0; i < cuentas.size(); i++) {
            System.out.println((i + 1) + ". " + cuentas.get(i).titular());
        }

        int seleccion = scanner.nextInt();
        if (seleccion < 1 || seleccion > cuentas.size()) {
            System.out.println("Selección no válida.");
            return null;
        }

        return cuentas.get(seleccion - 1);
    }

    private static int obtenerOpcionValida(Scanner scanner) {
        int opcion = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            String entrada = scanner.nextLine();
            if (esNumero(entrada)) {
                opcion = Integer.parseInt(entrada);
                entradaValida = true;
            } else {
                System.out.print("Por favor, ingrese un número válido: ");
            }
        }

        return opcion;
    }

    private static boolean esNumero(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

}