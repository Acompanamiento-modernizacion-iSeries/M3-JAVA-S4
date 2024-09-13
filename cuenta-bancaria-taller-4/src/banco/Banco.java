package banco;

import cuentas.Cuenta;
import cuentas.CuentaAhorros;
import cuentas.CuentaCorriente;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class Banco {
    private ArrayList<Cuenta> cuentas;

    public Banco() {
        cuentas = new ArrayList<>();
        inicializarCuentas();
    }

    private void inicializarCuentas() {
        cuentas.add(new CuentaAhorros("Carlos Molano", new BigDecimal(1000), 2.5));
        cuentas.add(new CuentaAhorros("Daniela Sanchez", new BigDecimal(1500), 3.0));
        cuentas.add(new CuentaCorriente("Camilo Lopez", new BigDecimal(500), 200.0));
        cuentas.add(new CuentaCorriente("Jose Rodriguez", new BigDecimal(700), 150.0));
        cuentas.add(new CuentaAhorros("Javier Zambrano", new BigDecimal(1200), 1.5));
        cuentas.add(new CuentaCorriente("Camila Canencio", new BigDecimal(600), 100.0));
    }

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n=== Menú Banco ===");
            System.out.println("1. Consultar saldo");
            System.out.println("2. Depositar");
            System.out.println("3. Retirar");
            System.out.println("4. Aplicar intereses (solo cuentas de ahorro)");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    consultarSaldo(scanner);
                    break;
                case 2:
                    depositar(scanner);
                    break;
                case 3:
                    retirar(scanner);
                    break;
                case 4:
                    aplicarIntereses(scanner);
                    break;
                case 5:
                    continuar = false;
                    System.out.println("Ha salido del sistema bancario, ¡gracias!");
                    break;
                default:
                    System.out.println("Seleccione una opción valida.");
            }
        }
        scanner.close();
    }

    private void consultarSaldo(Scanner scanner) {
        Cuenta cuenta = seleccionarCuenta(scanner);
        if (cuenta != null) {
            cuenta.mostrarInformacion();
        }
    }

    private void depositar(Scanner scanner) {
        Cuenta cuenta = seleccionarCuenta(scanner);
        if (cuenta != null) {
            System.out.print("Ingrese el monto a depositar: ");
            BigDecimal monto = scanner.nextBigDecimal();
            cuenta.depositar(monto);
            System.out.println("Depósito realizado. Nuevo saldo: " + cuenta.obtenerSaldo());
        }
    }

    private void retirar(Scanner scanner) {
        Cuenta cuenta = seleccionarCuenta(scanner);
        if (cuenta != null) {
            System.out.print("Ingrese el monto a retirar: ");
            BigDecimal monto = scanner.nextBigDecimal();
            cuenta.retirar(monto);
        }
    }

    private void aplicarIntereses(Scanner scanner) {
        Cuenta cuenta = seleccionarCuenta(scanner);
        if (cuenta instanceof CuentaAhorros) {
            ((CuentaAhorros) cuenta).aplicarInteres();
            System.out.println("Intereses aplicados. Nuevo saldo: " + cuenta.obtenerSaldo());
        } else {
            System.out.println("Esta opción solo está disponible para cuentas de ahorro.");
        }
    }

    private Cuenta seleccionarCuenta(Scanner scanner) {
        System.out.println("Seleccione un número de cuenta:");
        for (int i = 0; i < cuentas.size(); i++) {
            System.out.println(i + 1 + ". " + cuentas.get(i).titular());
        }
        int seleccion = scanner.nextInt() - 1;
        if (seleccion >= 0 && seleccion < cuentas.size()) {
            return cuentas.get(seleccion);
        } else {
            System.out.println("Selección inválida.");
            return null;
        }
    }
}
