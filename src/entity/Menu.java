package entity;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private Banco banco;
    private Scanner scanner;

    public Menu(Banco banco) {
        this.banco = banco;
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        while (true) {
            System.out.println("\n--- Menú del Banco ---");
            System.out.println("1. Crear cuenta");
            System.out.println("2. Consultar saldo");
            System.out.println("3. Depositar");
            System.out.println("4. Retirar");
            System.out.println("5. Transferir");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = verificarNumeroEnteroScanner(scanner, "Ingrese una opción: ");
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    crearCuenta();
                    break;
                case 2:
                    consultarSaldo();
                    break;
                case 3:
                    depositar();
                    break;
                case 4:
                    retirar();
                    break;
                case 5:
                    transferir();
                    break;
                case 6:
                    System.out.println("Gracias por usar nuestro sistema bancario.");
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        }
    }

    private static int verificarNumeroEnteroScanner(Scanner scanner, String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                int numero = scanner.nextInt();
                validarNumeroPositivoEntero(numero);
                return numero;
            } catch (InputMismatchException e) {
                System.out.println("El dato ingresado no es un numero. Por favor, intenta de nuevo");
                scanner.next();
            }
        }
    }

    private static BigDecimal verificarNumeroBigDecimalScanner(Scanner scanner, String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                BigDecimal numero = scanner.nextBigDecimal();
                validarNumeroPositivoBigDecimal(numero);
                return numero;
            } catch (InputMismatchException e) {
                System.out.println("El dato ingresado no es válido. Por favor, intenta de nuevo");
                scanner.next();
            }
        }
    }

    private static void validarNumeroPositivoBigDecimal(BigDecimal numero) {
        if (numero.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El número debe ser positivo");
        }
    }

    private static void  validarNumeroPositivoEntero(int numero) {
        if (numero <= 0) {
            throw new IllegalArgumentException("El número debe ser positivo");
        }
    }

    private void crearCuenta() {
        System.out.print("Ingrese el nombre del titular: ");
        String nombre = scanner.nextLine();
        String identificacion = String.valueOf(verificarNumeroEnteroScanner(scanner, "Ingrese el numero de identificacion del titular: "));
        Persona titular = new Persona(nombre, identificacion);

        String numeroCuenta = String.valueOf(verificarNumeroEnteroScanner(scanner, "Ingrese el numero de cuenta: "));
        BigDecimal saldoInicial = verificarNumeroBigDecimalScanner(scanner, "Ingrese el saldo inicial: ");

        int tipoCuenta = verificarNumeroEnteroScanner(scanner, "Tipo de cuenta (1: Ahorro, 2: Corriente): ");
        while (tipoCuenta != 1 && tipoCuenta != 2) {
            System.out.println("Tipo de cuenta no válido. Por favor, intente de nuevo.");
            tipoCuenta = verificarNumeroEnteroScanner(scanner, "Tipo de cuenta (1: Ahorro, 2: Corriente): ");
        }

        Cuenta cuenta;
        if (tipoCuenta == 1) {
            cuenta = new CuentaAhorro(numeroCuenta, saldoInicial, titular);
        } else {
            BigDecimal limiteSobregiro = verificarNumeroBigDecimalScanner(scanner, "Ingrese el límite de sobregiro: ");
            cuenta = new CuentaCorriente(numeroCuenta, saldoInicial, titular, limiteSobregiro);
        }

        banco.agregarCuenta(cuenta);
        System.out.println("Cuenta creada exitosamente.");
    }

    private void consultarSaldo() {
        String numeroCuenta = String.valueOf(verificarNumeroEnteroScanner(scanner, "Ingrese el numero de cuenta: "));
        Cuenta cuenta = banco.buscarCuenta(numeroCuenta);
        if (cuenta != null) {
            System.out.println("Saldo actual: " + cuenta.obtenerSaldo());
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }

    private void depositar() {
        String numeroCuenta = String.valueOf(verificarNumeroEnteroScanner(scanner, "Ingrese el numero de cuenta: "));
        Cuenta cuenta = banco.buscarCuenta(numeroCuenta);
        if (cuenta != null) {
            BigDecimal monto = verificarNumeroBigDecimalScanner(scanner, "Ingrese el monto a depositar: ");
            cuenta.depositar(monto);
            System.out.println("Depósito realizado. Nuevo saldo: " + cuenta.obtenerSaldo());
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }

    private void retirar() {
        String numeroCuenta = String.valueOf(verificarNumeroEnteroScanner(scanner, "Ingrese el numero de cuenta: "));
        Cuenta cuenta = banco.buscarCuenta(numeroCuenta);
        if (cuenta != null) {
            BigDecimal monto = verificarNumeroBigDecimalScanner(scanner, "Ingrese el monto a retirar: ");
            try {
                cuenta.retirar(monto);
                System.out.println("Retiro realizado. Nuevo saldo: " + cuenta.obtenerSaldo());
            } catch (IllegalArgumentException e) {
                System.out.println("Error al retirar: " + e.getMessage());
            }
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }

    private void transferir() {
        String numeroCuentaOrigen = String.valueOf(verificarNumeroEnteroScanner(scanner, "Ingrese el numero de cuenta de origen (Ahorro): "));
        String numeroCuentaDestino = String.valueOf(verificarNumeroEnteroScanner(scanner, "Ingrese el numero de cuenta de destino (Corriente): "));

        Cuenta cuentaOrigen = banco.buscarCuenta(numeroCuentaOrigen);
        Cuenta cuentaDestino = banco.buscarCuenta(numeroCuentaDestino);

        if (cuentaOrigen instanceof CuentaAhorro && cuentaDestino instanceof CuentaCorriente) {
            System.out.print("Ingrese el monto a transferir: ");
            BigDecimal monto = scanner.nextBigDecimal();
            try {
                cuentaOrigen.retirar(monto);
                cuentaDestino.depositar(monto);
                System.out.println("Transferencia realizada con éxito.");
                System.out.println("Nuevo saldo cuenta origen: " + cuentaOrigen.obtenerSaldo());
                System.out.println("Nuevo saldo cuenta destino: " + cuentaDestino.obtenerSaldo());
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("Error: Asegúrese de que la cuenta de origen sea de ahorro y la de destino sea corriente.");
        }
    }
}
