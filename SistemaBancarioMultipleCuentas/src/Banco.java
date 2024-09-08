import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Banco {
    private static Scanner scanner = new Scanner(System.in);
    private static CuentaAhorros cuentaAhorros;
    private static CuentaCorriente cuentaCorriente;

    public static void main(String[] args) {
        Boolean cerrar = true;
        leerInformacion();
        while (cerrar) {
            mostrarMenu();
            Integer opcion = leerOpcion();
            switch (opcion) {
                case 1:
                    mostrarSaldo(cuentaAhorros.getSaldo());
                    break;
                case 2:
                    mostrarSaldo(cuentaCorriente.getSaldo());
                    break;
                case 3:
                    cuentaAhorros.depositar(valorDeposito());
                    mostrarSaldo(cuentaAhorros.getSaldo());
                    break;
                case 4:
                    cuentaCorriente.depositar(valorDeposito());
                    mostrarSaldo(cuentaCorriente.getSaldo());
                    break;
                case 5:
                    cuentaAhorros.retirar(valorRetiro());
                    mostrarSaldo(cuentaAhorros.getSaldo());
                    break;
                case 6:
                    cuentaCorriente.retirar(valorRetiro());
                    mostrarSaldo(cuentaCorriente.getSaldo());
                    break;
                case 7:
                    System.out.println("El interes simple sobre la cuenta de ahorros es: " + cuentaAhorros.calcularInteresSimple());
                    break;
                case 8:
                    System.out.println("El valor de sobregiro de la cuenta corriente es: " + cuentaCorriente.getSobregiro());
                    break;
                case 9:
                    System.out.println("El interes simple de su cuenta de ahorros es(porcentaje): " + cuentaAhorros.getInteresSimple());
                    break;
                case 10:
                    cerrar = false;
                    System.out.println("Sesion finalizada exitosamente!");
                    break;
                default:
                    System.out.println("\nOpción no válida. Intente nuevamente.");
            }
        }
        scanner.close();
    }
    private static void mostrarMenu() {
        System.out.println("\n Sistema Bancario, Cuentas de " + cuentaAhorros.getNombre());
        System.out.println("1. Consultar saldo de la cuenta ahorros");
        System.out.println("2. Consultar saldo de la cuenta corriente");
        System.out.println("3. Realizar deposito en la cuenta ahorros");
        System.out.println("4. Realizar deposito en lacuenta corriente");
        System.out.println("5. Realizar retiro de la cuenta ahorros");
        System.out.println("6. Realizar retiro de la cuenta corriente");
        System.out.println("7. Calcular interes simple de la cuenta de ahorro");
        System.out.println("8. Consultar sobre giro de la cuenta corriente");
        System.out.println("9. Consultar interes simple de la cuenta de ahorros");
        System.out.println("10. Salir");
        System.out.print("Seleccione una opción: ");
    }
    private static void leerInformacion(){
        String nombre;
        Integer numeroCuenta;
        BigDecimal saldo;

        System.out.println("Ingresa tu nombre:");
        nombre = scanner.next();

        System.out.println("Ingresa tu número de la cuenta de ahorros:");
        numeroCuenta = leerEnteros();

        System.out.println("Ingresa el saldo inicial de tu cuenta de ahorros:");
        saldo = leerDecimal();

        cuentaAhorros = new CuentaAhorros(BigDecimal.valueOf(5) , nombre, numeroCuenta, saldo);

        System.out.println("Ingresa tu número de la cuenta corriente:");
        numeroCuenta = leerEnteros();

        System.out.println("Ingresa el saldo inicial de tu cuenta corriente");
        saldo = leerDecimal();

        cuentaCorriente = new CuentaCorriente(nombre, numeroCuenta, saldo, BigDecimal.valueOf(3000000));
    }
    private static Integer leerOpcion() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Opción no valida.");
                scanner.next();
            }
        }
    }
    private static void mostrarSaldo(BigDecimal saldo) {
        System.out.println("\nSaldo actual: " + saldo);
    }
    private static BigDecimal valorDeposito(){
        System.out.print("\nIngrese valor del deposito: ");
        return leerDecimal();
    }
    private static BigDecimal valorRetiro(){
        System.out.print("\nIngrese valor del retiro: ");
        return leerDecimal();
    }
    private static BigDecimal leerDecimal() {
        while (true) {
            try {
                BigDecimal valor = scanner.nextBigDecimal();
                if (valor.compareTo(BigDecimal.ZERO) == 1) {
                    return valor;
                } else {
                    System.out.println("El valor ingresado no puede ser negativo. Intente nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor ingrese un número.");
                scanner.next();
            }
        }
    }
    private static Integer leerEnteros() {
        while (true) {
            try {
                Integer entero = scanner.nextInt();
                if (entero > 0) {
                    return entero;
                } else {
                    System.out.println("El valor ingresado no puede ser negativo. Intente nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor ingrese un número.");
                scanner.next();
            }
        }
    }
}