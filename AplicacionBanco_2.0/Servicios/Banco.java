package Servicios;

import Cuentas.Cuenta;
import Cuentas.CuentaAhorros;
import Cuentas.CuentaCorriente;

import java.util.ArrayList;
import java.util.Scanner;
import java.math.BigDecimal;

public class Banco {
    private ArrayList<Cuenta> cuentas;

    public Banco() {
        cuentas = new ArrayList<>();
    }

    public void agregarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
    }

    public void mostrarSaldos() {
        for (int i = 0; i < cuentas.size(); i++) {
            System.out.println("Cuenta " + (i + 1) + " Saldo: " + cuentas.get(i).consultarSaldo());
        }
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- Menú Banco ---");
            System.out.println("1. Agregar cuenta corriente");
            System.out.println("2. Agregar cuenta de ahorros");
            System.out.println("3. Depositar");
            System.out.println("4. Retirar");
            System.out.println("5. Consultar saldos");
            System.out.println("6. Salir");
            System.out.print("Elija una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese saldo inicial: ");
                    BigDecimal saldoInicialCC = scanner.nextBigDecimal();
                    System.out.print("Ingrese límite de sobregiro: ");
                    BigDecimal sobregiro = scanner.nextBigDecimal();
                    agregarCuenta(new CuentaCorriente(saldoInicialCC, sobregiro));
                    break;
                case 2:
                    System.out.print("Ingrese saldo inicial: ");
                    BigDecimal saldoInicialCA = scanner.nextBigDecimal();
                    System.out.print("Ingrese tasa de interés (ejemplo 0.02 para 2%): ");
                    BigDecimal tasaInteres = scanner.nextBigDecimal();
                    agregarCuenta(new CuentaAhorros(saldoInicialCA, tasaInteres));
                    break;
                case 3:
                    realizarOperacion(scanner, "depositar");
                    break;
                case 4:
                    realizarOperacion(scanner, "retirar");
                    break;
                case 5:
                    mostrarSaldos();
                    break;
                case 6:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
        scanner.close();
    }

    private void realizarOperacion(Scanner scanner, String operacion) {
        System.out.print("Ingrese el número de la cuenta: ");
        int numeroCuenta = scanner.nextInt() - 1;

        if (numeroCuenta >= 0 && numeroCuenta < cuentas.size()) {
            System.out.print("Ingrese la cantidad: ");
            BigDecimal cantidad = scanner.nextBigDecimal();

            if (operacion.equals("depositar")) {
                cuentas.get(numeroCuenta).depositar(cantidad);
            } else if (operacion.equals("retirar")) {
                cuentas.get(numeroCuenta).retirar(cantidad);
            }
        } else {
            System.out.println("Número de cuenta no válido.");
        }
    }
}
