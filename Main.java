import cuentas.Banco;
import cuentas.Cuenta;
import cuentas.CuentaAhorros;
import cuentas.CuentaCorriente;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    /*Cuenta cuentaSnaider = new Cuenta("Snaider", new BigDecimal(1000));

    System.out.println(cuentaSnaider.consultarSaldo());
    System.out.println(cuentaSnaider.getTitular());

    CuentaAhorros cuentaBeatriz = new CuentaAhorros("Beatriz", new BigDecimal(5000), 0.5);
        System.out.println(cuentaBeatriz.consultarSaldo());
        System.out.println(cuentaBeatriz.getTitular());
        cuentaBeatriz.aplicarInteres();
        System.out.println(cuentaBeatriz.consultarSaldo());

    CuentaCorriente cuentaIvan = new CuentaCorriente("Ivan", new BigDecimal(5000), 0.5);
        System.out.println(cuentaIvan.consultarSaldo());
        System.out.println(cuentaIvan.getTitular());
        cuentaIvan.retirar(new BigDecimal(500));
        System.out.println(cuentaIvan.consultarSaldo());*/
        Banco banco = new Banco();
        Scanner sc = new Scanner(System.in);
        double saldo = 0;
        String nombre;
        while (true) {
            System.out.println("***Menu Bancario***");
            System.out.println("1. Apertura de cuenta:");
            System.out.println("2. Depositar en cuenta:");
            System.out.println("3. Retirar de la cuenta:");
            System.out.println("4. Consultar saldo:");
            System.out.println("5. Salir del menu:\n");
            int opc = sc.nextInt();

            switch (opc) {
                case 1:
                    System.out.println("***Apertura de cuenta***");
                    System.out.println("Ingrese el nombre de la persona:");
                    nombre = sc.next();
                    System.out.println("Ingrese el saldo de la cuenta:");
                    saldo += sc.nextDouble();
                    banco.agregarCuenta(nombre, BigDecimal.valueOf(saldo));
                    System.out.println("Apertura de cuenta realizada con exito: "+banco.consultarTitular());
                    System.out.println("Saldo de la cuenta: "+banco.consultarSaldo());
                    break;

                case 2:
                    System.out.println("***Depositar en la cuenta***");
                    System.out.println("Ingrese el valor a depositar:");
                    saldo += sc.nextDouble();
                    banco.depositar(BigDecimal.valueOf(saldo));
                    break;

                case 3:
                    System.out.println("***Retirar de la cuenta***");
                    System.out.println("Ingrese el valor a retirar:");
                    saldo += sc.nextDouble();
                    banco.retirar(BigDecimal.valueOf(saldo));
                    break;

                case 4:
                    System.out.println("Saldo de la cuenta: "+banco.consultarSaldo());
                    break;

                case 5:
                    //Salir
                    System.out.println("Gracias por usar nuestros servicios");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Por favor seleccione una opción valida");
                    break;
            }

        }
    }

}
