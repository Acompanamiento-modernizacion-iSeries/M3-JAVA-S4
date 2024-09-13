package cuentas;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CuentaCorriente extends Cuenta {
    private Double limiteSobregiro;
    private Double tasaInteres;

    public CuentaCorriente(String numCuenta, String titular, BigDecimal saldo, Double limiteSobregiro, String tipoCuenta, Double tasaInteres) {
        super(numCuenta, titular, saldo, tipoCuenta);
        this.limiteSobregiro = limiteSobregiro;
        this.tasaInteres = tasaInteres;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Cuenta corriente");
        System.out.println(this.titular());
        System.out.println(this.mostrarSaldo());
    }

    @Override
    public void retirar(BigDecimal cantidad) {
        BigDecimal saldo = mostrarSaldo();
        BigDecimal saldoSobregiro = saldo.add(new BigDecimal(limiteSobregiro));

        if (saldoSobregiro.compareTo(cantidad) >= 0 ){
            setSaldo(saldo.subtract(cantidad));
            System.out.println("Nuevo saldo    : " + super.mostrarSaldo().setScale(4, RoundingMode.HALF_UP));
        }
        else {
            System.out.println("No hay fondos suficientes");
        }
    }

    @Override
    public void aplicarInteres() {
        BigDecimal saldo = mostrarSaldo();
        if (saldo.compareTo(BigDecimal.ZERO) <= 0 ){
            System.out.println("No hay fondos suficientes para aplicar intereses");
        }else{
            BigDecimal tasaTemp = new BigDecimal(this.tasaInteres);
            BigDecimal cantidad = saldo.multiply(tasaTemp);
            System.out.println("Nuevo saldo    : " + cantidad.setScale(4, RoundingMode.HALF_UP));
            depositar(cantidad);
        }
    }

}