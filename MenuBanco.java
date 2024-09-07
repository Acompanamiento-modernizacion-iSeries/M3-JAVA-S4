import cuentas.Banco;
import cuentas.CuentaAhorros;
import cuentas.CuentaCorriente;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuBanco {

    public static void main(String[] args) {
        int i = 0;
        int opc ;
        Scanner sc = new Scanner(System.in);
        Banco banco = new Banco();

        int    edad ;
        double tasa;
        int    tipocuenta;
        double nrcuenta;
        double saldo;
        String titular;
        while (i < 9){
            System.out.println("Opciones: \n" );
            System.out.println("1        Consultar saldo: " );
            System.out.println("2        Crear cuenta " );
            System.out.println("3        Retirar saldo" );
            System.out.println("4        Realizar deposito " );
            System.out.println("5        Ver datos cuenta ahorros " );
            System.out.println("6        Ver datos cuenta corriente " );
            System.out.println("7        Elegibilidad para Credito: " );
            System.out.println("9        Salir del menu: " );
            opc = sc.nextInt();
            switch (opc){
                case 1:
                    //consultar saldo
                    try {
                        System.out.println("Ingrese numero de cuenta: \n");
                        nrcuenta = sc.nextDouble();
                        System.out.println("Ingrese tipo de cuenta(0-ahorror, 1-corriente): \n");
                        tipocuenta = sc.nextInt();
                        System.out.println("Saldo: \n");
                        System.out.println(banco.consultarsaldo(BigDecimal.valueOf(nrcuenta).toBigInteger(),tipocuenta) );
                    }catch (InputMismatchException E){
                        System.out.println("Ingrese dato valido!");
                    }
                    break;
                case 2:
                    try {
                    System.out.println("Ingrese numero de cuenta: \n" );
                    nrcuenta = sc.nextDouble();
                    System.out.println("Saldo inicial: \n" );
                    saldo = sc.nextDouble();
                    System.out.println("Ingrese Edad: \n" );
                    edad = sc.nextInt();
                    System.out.println("Ingrese tipo de cuenta(0-ahorror, 1-corriente): \n" );
                    tipocuenta=sc.nextInt();
                    if (tipocuenta == 0) {
                        System.out.println("Ingrese Tasa: \n");
                        tasa = sc.nextDouble();
                    }else if(tipocuenta == 1){
                        System.out.println("Ingrese Valor sobregiro: \n");
                        tasa = sc.nextDouble();
                    }else{
                        System.out.println("Tipo de cuenta invalido \n");
                        break;
                    }
                    System.out.println("Ingrese titular cuenta: \n" );
                    titular = sc.next();
                    banco.adicinarcuenta(titular,saldo,nrcuenta ,tasa,edad, tipocuenta );
                    }catch (InputMismatchException E){
                        System.out.println("Ingrese dato valido!");
                    }
                    break;
                case 3:
                    try {
                        System.out.println("Ingrese numero de cuenta: \n");
                        nrcuenta = sc.nextDouble();
                        System.out.println("Ingrese tipo de cuenta(0-ahorror, 1-corriente): \n");
                        tipocuenta = sc.nextInt();
                        System.out.println("Valor a Retirar : \n");
                        saldo = sc.nextDouble();
                        if (banco.retirarsaldo(BigDecimal.valueOf(nrcuenta).toBigInteger(),BigDecimal.valueOf(saldo),tipocuenta)){
                            System.out.println("Retiro exitoso, nuevo saldo: \n");
                            System.out.println(banco.consultarsaldo(BigDecimal.valueOf(nrcuenta).toBigInteger(),tipocuenta) );
                        }else{
                            System.out.println("Fondos insuficiente: \n");
                        }

                    }catch (InputMismatchException E){
                        System.out.println("Ingrese dato valido!");
                    }
                    break;
                case 4:
                    try {
                        System.out.println("Ingrese numero de cuenta: \n");
                        nrcuenta = sc.nextDouble();
                        System.out.println("Ingrese tipo de cuenta(0-ahorror, 1-corriente): \n");
                        tipocuenta = sc.nextInt();
                        System.out.println("Valor a depocitar: \n");
                        saldo = sc.nextDouble();

                        if (banco.depositar(BigDecimal.valueOf(nrcuenta).toBigInteger(),BigDecimal.valueOf(saldo),tipocuenta)){
                            System.out.println("Deposito exitoso, nuevo saldo: \n");
                            System.out.println(banco.consultarsaldo(BigDecimal.valueOf(nrcuenta).toBigInteger(),tipocuenta) );
                        }else{
                            System.out.println("Cuenta no existe \n");
                        }

                    }catch (InputMismatchException E){
                        System.out.println("Ingrese dato valido!");
                    }
                    break;
                case 5:
                    System.out.println("Ingrese numero de cuenta: \n" );
                    nrcuenta = sc.nextDouble();
                    CuentaAhorros ca ;
                    ca=banco.retornacuentaAhorro(BigDecimal.valueOf(nrcuenta).toBigInteger());
                    ca.mostrarInformacion();

                    break;
                case 6:
                    System.out.println("Ingrese numero de cuenta: \n" );
                    nrcuenta = sc.nextDouble();
                    CuentaCorriente cc ;
                    cc=banco.retornacuentaCorriente(BigDecimal.valueOf(nrcuenta).toBigInteger());
                    cc.mostrarInformacion();
                    break;
                case 7:
                    System.out.println("Ingrese numero de cuenta: \n" );
                    nrcuenta = sc.nextDouble();
                        if(banco.retornacuentaAhorro(BigDecimal.valueOf(nrcuenta).toBigInteger()).Elegibilidad() & banco.retornacuentaCorriente(BigDecimal.valueOf(nrcuenta).toBigInteger()).Elegibilidad()){
                            System.out.println("Usted es elegible para credito");
                        }else{
                            System.out.println("Usted no es elegible para credito");
                        }
                    break;
                case 9:
                    i = 10;
                    break;
                default:
                    System.out.println("Opción no valida: \n" );
                    break;
            }

        }
        sc.close();

    }

}
