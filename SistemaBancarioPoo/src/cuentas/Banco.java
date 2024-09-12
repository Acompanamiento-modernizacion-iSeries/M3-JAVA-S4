package cuentas;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Banco
{
    private List<Cuenta> cuentas = new ArrayList<>();

    public void menu() {
        int seleccion = 0;
        Double  tasaInteres =1.5;
        Scanner sc = new Scanner(System.in);
        while (seleccion != 9)
        {
            opciones();
            seleccion = sc.nextInt();
            switch (seleccion) {
                case 1:// Crear cuenta
                    //cliente = CrearCliente();
                    System.out.println("=".repeat(30));
                    creaCuenta();
                    System.out.println("=".repeat(30));
                    break;

                case 2:// Crear cuenta corriente
                    //saldoCliente = saldoInicial(cliente);
                    System.out.println("=".repeat(30));
                    creaCuenta();
                    System.out.println("=".repeat(30));
                    break;
                case 3:// Consulta saldo
                    System.out.println("=".repeat(30));
                    ConsultarSaldo();
                    System.out.println("=".repeat(30));
                    break;
                case 4:// Depositar
                    System.out.println("=".repeat(30));
                    Depositar();
                    System.out.println("=".repeat(30));
                    break;
                case 5:// Retirar
                    System.out.println("=".repeat(30));
                    Retirar();
                    System.out.println("=".repeat(30));
                    break;
                case 6://interes simple
                    //BigDecimal interesSimple = calculaInteresSimple(saldoCliente, tasaInteres);
                    break;
                case 7:// interes compuesto
                    //BigDecimal interesCompuesto = calculaInteresCompuesto(saldoCliente, tasaInteres);
                    //System.out.println(" Calculo Intereses compuesto para un periodo de un año es :" + interesCompuesto);
                    break;
                case 8:// Elegibilidad cliente
/*                boolean aceptado = elegibilidad(cliente, saldoCliente);
                if (aceptado)
                {
                    System.out.println("Cliente pasa requisito de eligibilidad");
                }
                else
                {
                    System.out.println("Cliente no cumple requisito de eligibilidad");
                }*/
                    break;
                case 9:
                    sc.close();
                    break;
                default:
                    System.out.println("Opción especificada no es correcta: ");
                    break;
            }

        }

    }
    public void opciones() {
        System.out.println("=".repeat(30));
        System.out.println("Sistema Bancario 3.0\n");
        System.out.println("Opciones: \n");
        System.out.println("1. Crear cuenta");
      // System.out.println("2. Crear cuenta corriente ");
        System.out.println("3. Consultar saldo ");
        System.out.println("4. Realizar deposito");
        System.out.println("5. Realizar retiro");
      //  System.out.println("6. Calculo de interese simple");
      //  System.out.println("7. Calculo de interese compuesto");
      //  System.out.println("8. Elegibilidad para crédito");
        System.out.println("9.  Salir del menu: ");
        System.out.println("=".repeat(30));
    }


    public void creaCuenta() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Responsable: \n");
        String responsable = sc.next();
        System.out.println("Numero de cuenta: \n");
        String numeroCuenta = sc.next();
        System.out.println("Saldo: \n");
        BigDecimal saldo = sc.nextBigDecimal();
        System.out.println("Tipo Cuentas   1: Cuenta Ahorros/2. Cuenta.Corriente: \n");
        String tipocuenta = sc.next();
        if (tipocuenta.equals("2")) {
            System.out.println("Ingres valor Límite de Sobregiro: \n");
            BigDecimal Sobregiro = sc.nextBigDecimal();
            CuentaCorriente cuentaCorriente = new CuentaCorriente(responsable, saldo, numeroCuenta,  tipocuenta , Sobregiro);
            cuentas.add(cuentaCorriente);
            System.out.println("Cuenta Corriente creada... \n");
        } else if (tipocuenta.equals("1")) {
            System.out.println("Tasa de Interes: \n");
            BigDecimal tasaInteres = sc.nextBigDecimal();
            CuentaAhorros cuentaAhorros = new CuentaAhorros(responsable, saldo, numeroCuenta, tipocuenta , tasaInteres);
            cuentas.add(cuentaAhorros);
            System.out.println("Cuenta Ahorros creada... \n");
        } else {
            System.out.println("Tipo cuenta no existe! \n");
        }

    }

    public void ConsultarSaldo() {
        Scanner sc = new Scanner(System.in);
        Boolean existe = false;
        System.out.println("Ingrese cuenta a consultar: \n");
        String numeroCuenta = sc.next();
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                System.out.println("Responsable:  \n" + cuenta.getResponsable());
                System.out.println("Numero de cuenta:  \n" + cuenta.getNumeroCuenta());
                System.out.println("Saldo:  \n" + cuenta.consultarSaldo());
                System.out.println("Tipo:  \n" + cuenta.gettipoCuenta());
                existe = true;
            }
        }
        if (!existe) {
            System.out.println("La cuenta :" + numeroCuenta + " No existe: \n");
        }
    }

    public void Retirar() {
        Scanner sc = new Scanner(System.in);
        Boolean existe = false;
        System.out.println("Ingrese cuenta: \n");
        String numeroCuenta = sc.next();
        System.out.println("Cantidad a retirar: \n");
        Double cantidad = sc.nextDouble();
        if (cantidad <= 0) {
            System.out.println("Cantidad no valida: \n");
            return;
        }
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getNumeroCuenta().equals(numeroCuenta))
            {
                existe = true;
                if (cuenta.gettipoCuenta().equals("1"))
                {
                    cuenta.retirar(BigDecimal.valueOf(cantidad));
                    System.out.println("Retiro realizado , el nuevoSaldo:" + cuenta.consultarSaldo() + "\n");
                }
                else
                {
                    cuenta.retirar(BigDecimal.valueOf(cantidad));
                    System.out.println("Retiro realizado , el nuevoSaldo:" + cuenta.consultarSaldo() + "\n");
                }
            }
        }
        if (!existe) {
            System.out.println("Cuenta no existe :" + numeroCuenta + " \n");
        }
    }

    public void Depositar() {
        Scanner sc = new Scanner(System.in);
        Boolean existe = false;
        System.out.println("Ingrese cuenta: \n");
        String numeroCuenta = sc.next();
        System.out.println("Cantidad a depositar: \n");
        Double cantidad = sc.nextDouble();
        if (cantidad <= 0) {
            System.out.println("Cantidad a depositar no valida: \n");
            return;
        }
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getNumeroCuenta().equals(numeroCuenta))
            {
                    cuenta.depositar(BigDecimal.valueOf(cantidad));
                    System.out.println("Deposito realizado, el nuevoSaldo:" + cuenta.consultarSaldo() + "\n");
                    existe = true;
            }
        }
        if (!existe) {
            System.out.println("La cuenta :" + numeroCuenta + " No existe \n");
        }
    }

}

