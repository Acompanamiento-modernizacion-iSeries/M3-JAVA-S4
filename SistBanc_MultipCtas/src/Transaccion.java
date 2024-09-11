import cuentas.Cuenta;
import cuentas.CuentaAhorros;
import cuentas.CuentaCorriente;
import db.CuentasDB;

import java.math.BigDecimal;
import java.util.Scanner;

public class Transaccion {

    public static Cuenta crearCuenta(Scanner scanner) {
        String tipoCuenta;
        double saldoActual;

        boolean transccValida;
        boolean valorValido = false;
        boolean esNumero = true;

        System.out.println("Ingrese el Nombre del titular:");
        String nombre = scanner.next();

        System.out.println("Ingrese el Numero de Cuenta:");
        String numCuenta = scanner.next();

        do {
            System.out.println("Ingrese el Tipo de Cuenta (Ahorros / Corriente):");
            tipoCuenta = scanner.next().toLowerCase();

            transccValida = (tipoCuenta.equals("ahorros") || tipoCuenta.equals("corriente"));
            if (!transccValida) {
                System.out.println("Tipo de cuenta no reconocida!\n");
            }
        } while (!transccValida);

        do {
            System.out.println("Ingrese el Saldo Inicial:");
            if (scanner.hasNextDouble()) saldoActual = scanner.nextDouble();
            else {
                saldoActual = -1.0;
                esNumero = false;
            }

            if (saldoActual >= 0) {
                valorValido = true;
            } else {
                System.out.println("Lo sentimos, debe ingresar un valor igual o mayor a 0!\n");
                if (!esNumero) scanner.next(); // Limpiar el buffer del scanner para evitar un bucle infinito
                esNumero = true;
            }
        } while (!valorValido);

        System.out.println("Ahora se encuentra gestionando la cuenta de " + nombre + "\n");

        if (tipoCuenta.equals("ahorros")) {
            return new CuentaAhorros(nombre, numCuenta, saldoActual, 13);
        } else {
            return new CuentaCorriente(nombre,numCuenta,saldoActual,saldoActual*0.2);
        }
    }

    public static String seleccionarCuenta(Scanner scanner) {
        String nombre;
        while (true) {
            System.out.println("Ingrese el Nombre del titular de la Cuenta que quiere gestionar:");
            nombre = scanner.next();

            if (CuentasDB.buscarCuenta(nombre.toLowerCase()) != null) {
                System.out.println("Ahora se encuentra gestionando la cuenta de " + nombre + "\n");
                return nombre.toLowerCase();
            }
            else
                System.out.println("No se encontró una Cuenta cuyo titular sea " + nombre + "\n");
        }
    }

    public static void consultaSaldo(Cuenta cuenta) {
        System.out.println("El saldo de la Cuenta es: " + cuenta.consultarSaldoDisponible() + "\n");
    }

    public static void deposito(Scanner scanner, Cuenta cuenta) {
        double vlrTransacc;
        boolean esNumero = true;
        boolean transaccOk;
        String msgNoOk = "Lo sentimos, debe ingresar un valor igual o mayor a 0!";

        System.out.println("Ingrese el Valor del Deposito :");
        if (scanner.hasNextDouble()) vlrTransacc = scanner.nextDouble();
        else {
            vlrTransacc = -1.0;
            esNumero = false;
        }

        transaccOk = cuenta.deposito(vlrTransacc);

        if (transaccOk) System.out.println("Deposito por " + vlrTransacc + " realizado!\n");
        else {
            System.out.println(msgNoOk);
            if (!esNumero) scanner.next(); // Limpiar el buffer del scanner
        }
    }

    public static void retiro(Scanner scanner, CuentaAhorros cuenta) {
        double vlrTransacc;
        boolean esNumero = true;
        boolean transaccOk;
        String msgNoOk = "Lo sentimos, debe ingresar un valor igual o mayor a 0 y menor o igual al saldo disponible!";

        System.out.println("Ingrese el Valor del Retiro :");
        if (scanner.hasNextDouble()) vlrTransacc = scanner.nextDouble();
        else {
            vlrTransacc = cuenta.consultarSaldoDisponible().add(BigDecimal.ONE).doubleValue();
            esNumero = false;
        }

        transaccOk = cuenta.retiro(vlrTransacc);

        if (transaccOk) System.out.println("Retiro por " + vlrTransacc + " realizado!\n");
        else {
            System.out.println(msgNoOk);
            if (!esNumero) scanner.next(); // Limpiar el buffer del scanner
        }
    }

    public static void retiro(Scanner scanner, CuentaCorriente cuenta) {
        double vlrTransacc;
        boolean esNumero = true;
        boolean transaccOk;
        String msgNoOk = "Lo sentimos, debe ingresar un valor igual o mayor a 0 y menor o igual al saldo más el sobregiro disponible!";

        System.out.println("Ingrese el Valor del Retiro :");
        if (scanner.hasNextDouble()) vlrTransacc = scanner.nextDouble();
        else {
            vlrTransacc = cuenta.consultarSaldoDisponible().add(cuenta.consultarSobregiroDisponible()).add(BigDecimal.ONE).doubleValue();
            esNumero = false;
        }

        transaccOk = cuenta.retiro(vlrTransacc);

        if (transaccOk) System.out.println("Retiro por " + vlrTransacc + " realizado!\n");
        else {
            System.out.println(msgNoOk);
            if (!esNumero) scanner.next(); // Limpiar el buffer del scanner
        }
    }

    public static void sumarInteres(Scanner scanner, CuentaAhorros cuenta) {
        boolean esNumero = true;
        int tEnMeses;
        boolean transaccOk;

        System.out.println("Ingrese el tiempo en meses transcurridos con el saldo actual:");
        if (scanner.hasNextInt()) tEnMeses = scanner.nextInt();
        else {
            tEnMeses = -1;
            esNumero = false;
        }

        transaccOk = cuenta.aplicarInteres(tEnMeses);

        if (transaccOk) {
            System.out.println("Intereses Aplicados!\n" +
                    "El nuevo saldo de la Cuenta es:\n" +
                    cuenta.consultarSaldoDisponible());
        } else {
            System.out.println("Lo sentimos, debe ingresar un tiempo en meses mayor a 0");
            if (!esNumero) scanner.next(); // Limpiar el buffer del scanner
        }

    }

    public static void consultaSobregiro(CuentaCorriente cuenta) {
        System.out.println("El sobregiro disponible de la Cuenta es: " + cuenta.consultarSobregiroDisponible() +
                "\ny el sobregiro máximo de la Cuenta es: " + cuenta.consultarLimiteSobregiro() + "\n");
    }
}
