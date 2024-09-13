import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class Banco {
    private ArrayList<Cuenta> cuentas;

    public Banco() {
        cuentas = new ArrayList<>();
    }

    public void agregarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
        System.out.println("Cuenta agregada: " + cuenta.getNumeroCuenta());
    }

    public Cuenta buscarCuenta(String numeroCuenta) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                return cuenta;
            }
        }
        System.out.println("Cuenta no encontrada.");
        return null;
    }

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\n--- Menú Banco ---");
            System.out.println("1. Agregar Cuenta");
            System.out.println("2. Depositar");
            System.out.println("3. Retirar");
            System.out.println("4. Consultar Saldo");
            System.out.println("5. Aplicar Interés (Cuentas de Ahorro)");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    agregarCuentaInteractiva(scanner);
                    break;
                case 2:
                    depositarEnCuenta(scanner);
                    break;
                case 3:
                    retirarDeCuenta(scanner);
                    break;
                case 4:
                    consultarSaldo(scanner);
                    break;
                case 5:
                    aplicarInteres();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        } while (opcion != 0);
        scanner.close();
    }

    private void agregarCuentaInteractiva(Scanner scanner) {
        System.out.println("\n--- Agregar Nueva Cuenta ---");
        System.out.println("Seleccione el tipo de cuenta:");
        System.out.println("1. Cuenta Corriente");
        System.out.println("2. Cuenta de Ahorros");
        System.out.print("Seleccione una opción: ");
        int tipoCuenta = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Ingrese el número de cuenta: ");
        String numeroCuenta = scanner.nextLine();

        System.out.print("Ingrese el saldo inicial: ");
        BigDecimal saldoInicial = scanner.nextBigDecimal();

        switch (tipoCuenta) {
            case 1:
                System.out.print("Ingrese el sobregiro permitido: ");
                BigDecimal sobregiro = scanner.nextBigDecimal();
                CuentaCorriente cuentaCorriente = new CuentaCorriente(numeroCuenta, saldoInicial, sobregiro);
                agregarCuenta(cuentaCorriente);
                break;
            case 2:
                System.out.print("Ingrese la tasa de interés (en decimal, por ejemplo, 0.02 para 2%): ");
                BigDecimal tasaInteres = scanner.nextBigDecimal();
                CuentaAhorros cuentaAhorros = new CuentaAhorros(numeroCuenta, saldoInicial, tasaInteres);
                agregarCuenta(cuentaAhorros);
                break;
            default:
                System.out.println("Opción inválida. Regresando al menú principal.");
                break;
        }
    }

    private void depositarEnCuenta(Scanner scanner) {
        System.out.println("\n--- Depositar en Cuenta ---");
        System.out.print("Ingrese el número de cuenta: ");
        String numeroCuenta = scanner.nextLine();

        Cuenta cuenta = buscarCuenta(numeroCuenta);
        if (cuenta != null) {
            System.out.print("Ingrese el monto a depositar: ");
            BigDecimal monto = scanner.nextBigDecimal();
            cuenta.depositar(monto);
        }
    }

    private void retirarDeCuenta(Scanner scanner) {
        System.out.println("\n--- Retirar de Cuenta ---");
        System.out.print("Ingrese el número de cuenta: ");
        String numeroCuenta = scanner.nextLine();

        Cuenta cuenta = buscarCuenta(numeroCuenta);
        if (cuenta != null) {
            System.out.print("Ingrese el monto a retirar: ");
            BigDecimal monto = scanner.nextBigDecimal();
            cuenta.retirar(monto);
        }
    }

    private void consultarSaldo(Scanner scanner) {
        System.out.println("\n--- Consultar Saldo ---");
        System.out.print("Ingrese el número de cuenta: ");
        String numeroCuenta = scanner.nextLine();

        Cuenta cuenta = buscarCuenta(numeroCuenta);
        if (cuenta != null) {
            System.out.println("Saldo actual de la cuenta " + numeroCuenta + ": $" + cuenta.getSaldo());
        }
    }

    private void aplicarInteres() {
        System.out.println("\n--- Aplicar Interés a Cuentas de Ahorro ---");
        for (Cuenta cuenta : cuentas) {
            if (cuenta instanceof CuentaAhorros) {
                ((CuentaAhorros) cuenta).aplicarInteres();
                System.out.println("Interés aplicado a la cuenta: " + cuenta.getNumeroCuenta());
            }
        }
    }
}