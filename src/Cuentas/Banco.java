package Cuentas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Banco {


    private List<Cuenta> cuentas = new ArrayList<>();

    public void Menu() {
        int i = 0;
        int opc;
        Scanner sc = new Scanner(System.in);

        while (i < 9) {
            System.out.println("Opciones: \n");
            System.out.println("1        Consultar saldo: ");
            System.out.println("2        Retirar saldo: ");
            System.out.println("3        Depositar saldo: ");
            System.out.println("4        Agregar cuenta: ");
            System.out.println("9        Salir del Sistema: ");
            opc = sc.nextInt();
            switch (opc) {
                case 1:
                    //consultar saldo
                    ConsultarSaldo(sc);
                    break;
                case 2:
                    RetirarSaldo(sc);
                    break;
                case 3:
                    DepositarSaldo(sc);
                    break;
                case 4:
                    /*Agregar cuenta*/
                    AgregarCuenta (sc);

                    break;
                case 9:
                    break;
                default:
                    break;
            }


        }
    }

    public void AgregarCuenta (Scanner sc) {

        System.out.println("Titular: \n");
        String titular = sc.next();
        System.out.println("Numero de cuenta: \n");
        String nrocuenta = sc.next();
        System.out.println("Saldo: \n");
        BigDecimal saldo = sc.nextBigDecimal();
        System.out.println("Tipo Cuentas   1: Cuenta Ahorros/2. Cuenta.Corriente: \n");
        String tipocuenta = sc.next();
        if  (tipocuenta.equals("2")) {
            System.out.println("Limite de Sobregiro: \n");
            Double Sobregiro = sc.nextDouble();
            CuentaCorriente cuentaCorriente = new CuentaCorriente(titular, saldo, nrocuenta,Sobregiro);
            cuentas.add(cuentaCorriente);
            System.out.println("Cuenta Corriente adicionada: \n");
        }
        else if (tipocuenta.equals("1"))
        {
            System.out.println("Tasa de interes: \n");
            Double tasaInteres  = sc.nextDouble();
            CuentaAhorros cuentaAhorros = new CuentaAhorros(titular,saldo,nrocuenta,tasaInteres);
            cuentas.add(cuentaAhorros);
            System.out.println("Cuenta Ahorros adicionada: \n");
        }
        else {
            System.out.println("Tipo cuenta no valido: \n");
        }

    }
    public void ConsultarSaldo (Scanner sc)
    {
      Boolean existe = false;
      System.out.println("Ingrese cuenta a consultar: \n");
      String nrocuenta = sc.next();
      for (Cuenta cuenta: cuentas) {
          if (cuenta.consultarNrocuenta().equals(nrocuenta)) {
              System.out.println("Titular:  \n" + cuenta.consultarTitular());
              System.out.println("Nrocuenta:  \n" + cuenta.consultarNrocuenta());
              System.out.println("Saldo:  \n" + cuenta.consultarSaldo());
              existe = true;

          }

      }
      if (!existe){
          System.out.println("La cuenta :" + nrocuenta  +  " No existe: \n");
      }

    }

    public void RetirarSaldo (Scanner sc)
    {
        Boolean existe = false;
        System.out.println("Ingrese cuenta: \n");
        String nrocuenta = sc.next();
        System.out.println("Cantidad a retirar: \n");
        Double cantidad = sc.nextDouble();
        if (cantidad <= 0) {
            System.out.println("Cantidad no valida: \n");
            return;
        }

        for (Cuenta cuenta: cuentas) {
            if (cuenta.consultarNrocuenta().equals(nrocuenta)) {
                cuenta.retirar(BigDecimal.valueOf(cantidad));
                existe = true;
                System.out.println("Retiro realizado exitoso, el nuevoSaldo:" + cuenta.consultarSaldo() + "\n"  );

            }
        }
        if (!existe){
            System.out.println("Cuenta no exste :" + nrocuenta  +  "no se realiza retiro: \n");
        }
    }
    public void DepositarSaldo (Scanner sc)
    {
        Boolean existe = false;
        System.out.println("Ingrese cuenta: \n");
        String nrocuenta = sc.next();
        System.out.println("Cantidad a depositar: \n");
        Double cantidad = sc.nextDouble();
        if (cantidad <= 0) {
            System.out.println("Cantidad a depositar no valida: \n");
            return;
        }

        for (Cuenta cuenta: cuentas) {
            if (cuenta.consultarNrocuenta().equals(nrocuenta)) {
                if (cuenta instanceof CuentaCorriente) {
                   System.out.println("Cuenta Corriente no permite deposito: \n");
                    existe = true;
                }
                else {
                  cuenta.depositar(BigDecimal.valueOf(cantidad));
                  System.out.println("Deposito realizado exitoso, el nuevoSaldo:" + cuenta.consultarSaldo() + "\n");
                  existe = true;
                }
            }
        }
        if (!existe){
            System.out.println("La cuenta :" + nrocuenta  +  " No existe; por lo tanto no se realiza Depósito: \n");
        }
    }

}
