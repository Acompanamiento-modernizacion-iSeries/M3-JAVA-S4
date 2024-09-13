import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Banco {
    private static Scanner sc = new Scanner(System.in);
    private static CuentaAhorros cuentaAhorros;
    private static CuentaCorriente cuentaCorriente;

    public static void main(String[] args) {
        IngresaDatosCuenta();
        while (true) {
            switch (showMenu()) {
                case 1:
                    //Consulta saldo cuanta de ahorro
                    mostrarSaldo(cuentaAhorros.getSaldo());
                    break;
                case 2:
                    //Consulta saldo cuenta corriente
                    mostrarSaldo(cuentaCorriente.getSaldo());
                    break;
                case 3:
                    //deposito cuenta ahorros
                    cuentaAhorros.depositar(valorDeposito());
                    mostrarSaldo(cuentaAhorros.getSaldo());
                    break;
                case 4:
                    //deposito cuenta corriente
                    cuentaCorriente.depositar(valorDeposito());
                    mostrarSaldo(cuentaCorriente.getSaldo());
                    break;
                case 5:
                    //Retiro cuenta ahorros
                    cuentaAhorros.retirar(valorRetiro());
                    mostrarSaldo(cuentaAhorros.getSaldo());
                    break;
                case 6:
                    //Retiro cuenta corriente
                    cuentaCorriente.retirar(valorRetiro());
                    mostrarSaldo(cuentaCorriente.getSaldo());
                    break;
                case 7:
                    //Calcula interés cuenta ahorros
                    System.out.println("El interés simple sobre la cuenta de ahorros es: " + cuentaAhorros.calcularInteresSimple());
                    break;
                case 8:
                    //Consulta cupo sobregiro
                    System.out.println("El valor de sobregiro de la cuenta corriente es: " + cuentaCorriente.getSobregiro());
                    break;
                case 9:
                    System.out.println("El interés simple de su cuenta de ahorros es(porcentaje): " + cuentaAhorros.getInteresSimple());
                    break;
                case 10:
                    //Salir
                    System.out.println("Gracias por usar nuestros servicios");
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("\nOpción no válida. Intente nuevamente.");
            }
        }
    }
    public static int showMenu() {
        System.out.println("=================================");
        System.out.println("***Cuenta Bancaria***");
        System.out.println("=================================");
        System.out.println("Por favor seleccione una opción: ");
        System.out.println("1. Consultar Saldo cuenta de ahorros");
        System.out.println("2. Consultar Saldo cuenta de corriente");
        System.out.println("3. Hacer deposito cuenta de ahorros");
        System.out.println("4. Hacer deposito cuenta corriente");
        System.out.println("5. Hacer retiro cuenta de ahorros");
        System.out.println("6. Hacer retiro cuenta corriente");
        System.out.println("7. Calcular interés simple cuenta de ahorros");
        System.out.println("8. Consultar sobregiro de la cuenta corriente");
        System.out.println("9. Consultar interés simple cuenta ahorros");
        System.out.println("10. Salir");
        return sc.nextInt();
    }
    private static void IngresaDatosCuenta(){
        String nombre;
        Integer numeroCuenta;
        BigDecimal saldo;

        System.out.println("Ingresa tu nombre:");
        nombre = sc.next();

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
                BigDecimal valor = sc.nextBigDecimal();
                if (valor.compareTo(BigDecimal.ZERO) == 1) {
                    return valor;
                } else {
                    System.out.println("El valor debe ser mayor a 0. Intente nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor ingrese un valor numérico.");
                sc.next();
            }
        }
    }
    private static Integer leerEnteros() {
        while (true) {
            try {
                Integer entero = sc.nextInt();
                if (entero > 0) {
                    return entero;
                } else {
                    System.out.println("El valor debe ser mayor a 0. Intente nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor ingrese un valor numérico.");
                sc.next();
            }
        }
    }
}

