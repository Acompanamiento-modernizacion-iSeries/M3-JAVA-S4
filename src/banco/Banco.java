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

    public void agregarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
    }

    public void mostrarMenu() {
        while (true) {
            System.out.println("------------------------");
            System.out.println("--- Menú del Banco ---");
            System.out.println("------------------------");
            System.out.println("1. Crear cuenta");
            System.out.println("2. Depositar");
            System.out.println("3. Retirar");
            System.out.println("4. Consultar saldo");
            System.out.println("5. Aplicar interés (solo cuentas de ahorro)");
            System.out.println("6. Salir");

            int opcion = leerEntero("Seleccione una opción: ", 1, 6);

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
                    System.out.println("Gracias por usar nuestro sistema");
                    return;
            }
        }
    }

    private void crearCuenta() {
        String titular = leerString("Ingrese el nombre del titular: ");
        BigDecimal saldo = leerBigDecimal("Ingrese el saldo inicial: ", BigDecimal.ZERO);
        int tipoCuenta = leerEntero("Tipo de cuenta (1: Ahorro, 2: Corriente): ", 1, 2);

        if (tipoCuenta == 1) {
            double tasaInteres = leerDouble("Ingrese la tasa de interés: ", 0, 100);
            agregarCuenta(new CuentaAhorros(titular, saldo, tasaInteres));
        } else {
            BigDecimal sobregiro = leerBigDecimal("Ingrese el límite de sobregiro: ", BigDecimal.ZERO);
            agregarCuenta(new CuentaCorriente(titular, saldo, sobregiro));
        }
        System.out.println("Cuenta creada exitosamente.");
    }

    private void realizarDeposito() {
        Cuenta cuenta = seleccionarCuenta();
        if (cuenta != null) {
            BigDecimal cantidad = leerBigDecimal("Ingrese la cantidad a depositar: ", BigDecimal.ZERO);
            cuenta.depositar(cantidad);
            System.out.println("Depósito realizado exitosamente.");
        }
    }

    private void realizarRetiro() {
        Cuenta cuenta = seleccionarCuenta();
        if (cuenta != null) {
            BigDecimal cantidad = leerBigDecimal("Ingrese la cantidad a retirar: ", BigDecimal.ZERO);
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

        int seleccion = leerEntero("Ingrese el número de cuenta: ", 1, cuentas.size());
        return cuentas.get(seleccion - 1);
    }

    private String leerString(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    private int leerEntero(String mensaje, int min, int max) {
        while (true) {
            System.out.print(mensaje);
            if (scanner.hasNextInt()) {
                int valor = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer
                if (valor >= min && valor <= max) {
                    return valor;
                }
            } else {
                scanner.nextLine(); // Limpiar el buffer
            }
            System.out.println("Por favor, ingrese un número válido entre " + min + " y " + max + ".");
        }
    }

    private double leerDouble(String mensaje, double min, double max) {
        while (true) {
            System.out.print(mensaje);
            if (scanner.hasNextDouble()) {
                double valor = scanner.nextDouble();
                scanner.nextLine(); // Limpiar el buffer
                if (valor >= min && valor <= max) {
                    return valor;
                }
            } else {
                scanner.nextLine(); // Limpiar el buffer
            }
            System.out.println("Por favor, ingrese un número válido entre " + min + " y " + max + ".");
        }
    }

    private BigDecimal leerBigDecimal(String mensaje, BigDecimal min) {
        while (true) {
            System.out.print(mensaje);
            if (scanner.hasNextBigDecimal()) {
                BigDecimal valor = scanner.nextBigDecimal();
                scanner.nextLine(); // Limpiar el buffer
                if (valor.compareTo(min) >= 0) {
                    return valor;
                }
            } else {
                scanner.nextLine(); // Limpiar el buffer
            }
            System.out.println("Por favor, ingrese un número válido mayor o igual a " + min + ".");
        }
    }
}

