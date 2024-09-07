package usecase;

import java.math.BigDecimal;
import java.util.Scanner;

public class Validacion {

    public String nombreValido(Scanner scanner){
        boolean valid = false;
        String nombre = null;
        while (!valid){
            System.out.println("Ingrese el Nombre:");
            nombre = scanner.nextLine();
            if(nombre != null && !nombre.equals("")){
                valid = true;
            }else{
                System.out.println("Por favor validar el nombre ingresado, no es valido.");
            }
        }
        return nombre;
    }

    public Integer numeroCuentaValido(Scanner scanner){
        boolean valid = false;
        Integer cuenta = 0;
        while (!valid){
            System.out.println("Ingrese el Número de la cuenta:");
            cuenta = scanner.nextInt();
            if (cuenta.compareTo(0) > 0){
                valid = true;
            }else{
                System.out.println("Por favor validar el número de la cuenta ingresado, es igual o inferior a cero.");
            }
        }
        return cuenta;
    }

    public BigDecimal valorSaldoPositivo(Scanner scanner){
        boolean valid = false;
        BigDecimal valor = new BigDecimal("0.0");
        while(!valid){
            System.out.println("Ingrese el Saldo:");
            valor  = scanner.nextBigDecimal();
            if(valor.signum() >= 0){
                valid = true;
            } else {
                System.out.println("Por favor validar el número ingresado, es negativo.");
            }
        }

        return valor;
    }

    public BigDecimal valorTransaccionPositivo(Scanner scanner){
        boolean valid = false;
        BigDecimal valor = new BigDecimal("0.0");
        while(!valid){
            System.out.println("Ingrese Valor de transacción:");
            valor  = scanner.nextBigDecimal();
            if(valor.signum() > 0){
                valid = true;
            } else {
                System.out.println("Por favor validar el número ingresado, es cero o negativo.");
            }
        }
        return valor;
    }

    public Double valorInteresPositivo(Scanner scanner){
        boolean valid = false;
        Double valor = 0.0;
        while(!valid){
            System.out.println("Ingrese Valor del Interes mensual:");
            valor  = scanner.nextDouble();
            if(valor.compareTo(0.0) > 0){
                valid = true;
            } else {
                System.out.println("Por favor validar el número ingresado, es cero o negativo.");
            }
        }
        return valor;
    }

    public BigDecimal valorSobreGiroPositivo(Scanner scanner){
        boolean valid = false;
        BigDecimal valor = new BigDecimal("0.0");
        while(!valid){
            System.out.println("Ingrese Valor para el sobre giro:");
            valor  = scanner.nextBigDecimal();
            if(valor.signum() > 0){
                valid = true;
            } else {
                System.out.println("Por favor validar el número ingresado, es cero o negativo.");
            }
        }
        return valor;
    }
}
