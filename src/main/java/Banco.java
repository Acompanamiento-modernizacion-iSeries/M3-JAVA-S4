import modelo.CuentaAhorro;
import modelo.CuentaCorriente;
import usecase.Validacion;

import java.math.BigDecimal;
import java.util.Scanner;

public class Banco {
    public static void main(String[] args) {
        var opcion = 0;
        var opcionSalir = 9;

        Scanner scanner = new Scanner(System.in);
        Validacion validacion = new Validacion();
        CuentaAhorro cuentaAhorro = new CuentaAhorro();
        CuentaCorriente cuentaCorriente = new CuentaCorriente();

        cuentaAhorro.setNombre(validacion.nombreValido(scanner));
        System.out.println("Cuenta de ahorros.");
        cuentaAhorro.setNumeroCuenta(validacion.numeroCuentaValido(scanner));
        System.out.println("Cuenta de ahorros.");
        cuentaAhorro.setSaldo(validacion.valorSaldoPositivo(scanner));

        cuentaCorriente.setNombre(cuentaAhorro.getNombre());
        System.out.println("Cuenta corriente.");
        cuentaCorriente.setNumeroCuenta(validacion.numeroCuentaValido(scanner));
        System.out.println("Cuenta corriente.");
        cuentaCorriente.setSaldo(validacion.valorSaldoPositivo(scanner));
        cuentaCorriente.setSobreGiro(new BigDecimal("0.0"));

        while (opcion < opcionSalir){
            System.out.println("Hola");
            System.out.println("Por favor seleccione unas de las siguientes opciones:");
            System.out.println("- Consultar saldo cuenta ahorros:               1");
            System.out.println("- Consultar saldo cuenta corriente:             2");
            System.out.println("- Realizar deposito cuenta ahorros:             3");
            System.out.println("- Realizar retiro cuenta ahorros:               4");
            System.out.println("- Calcular interes en la cuenta de ahorros:     5");
            System.out.println("- Consultar sobre giro en la cuenta corriente:  6");
            System.out.println("- Ingresar sobre giro en la cuenta corriente:   7");
            System.out.println("- Calcular elegibilidad:                        8");
            System.out.println("- Salir:                                        " + opcionSalir);

            opcion = scanner.nextInt();

            switch (opcion){
                case 1:
                    System.out.println("Su saldo total en la cuenta de ahorros es: " + cuentaAhorro.consultarSaldo());
                    break;
                case 2:
                    System.out.println("Su saldo total en la cuenta corriente es: " + cuentaCorriente.consultarSaldo());
                    break;
                case 3:
                    cuentaAhorro.depositar(validacion.valorTransaccionPositivo(scanner));
                    System.out.println("Su saldo total en la cuenta de ahorros es es: " +
                            cuentaAhorro.consultarSaldo());
                    break;
                case 4:
                    cuentaAhorro.retirar(validacion.valorTransaccionPositivo(scanner));
                    System.out.println("Su saldo total en la cuenta corriente es: " + cuentaAhorro.consultarSaldo());
                    break;
                case 5:
                    System.out.println("El interes mensual para el saldo en la cuenta de ahorros: " +
                            cuentaAhorro.consultarSaldo() + " es: " + cuentaAhorro.calcularInteresMesnual());
                    break;
                case 6:
                    System.out.println("El sobre giro de la cuenta corriente es: "
                            + cuentaCorriente.consultarSobreGiro());
                    break;
                case 7:
                    cuentaCorriente.ingresarSobreGiro(validacion.valorTransaccionPositivo(scanner));
                    System.out.println("El sobre giro de la cuenta corriente es: " +
                            cuentaCorriente.consultarSobreGiro());
                    break;
                case 8:
                    System.out.println(cuentaAhorro.elegible() ? "Su credito ha sido aprobado"
                            : "Su credito ha sido rechazado");
                    break;
                case 9:
                    System.out.println("Que tenga un buen día.");
                    break;
                default:
                    break;
            }
        }
    }
}
