package cuentas;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CuentaAhorros extends Cuenta {
    private Double tasaInteres;

    public CuentaAhorros(String numCuenta, String titular, BigDecimal saldo, Double tasaInteres, String tipoCuenta) {
        super(numCuenta, titular, saldo, tipoCuenta);
        this.tasaInteres = tasaInteres;
    }
    @Override
    public void retirar(BigDecimal cantidad) {
        BigDecimal saldo = mostrarSaldo();
        if (saldo.compareTo(cantidad) >= 0 ){
            super.setSaldo(saldo.subtract(cantidad));
            System.out.println("Nuevo saldo    : " + super.mostrarSaldo().setScale(4, RoundingMode.HALF_UP));
        }
        else {
            System.out.println("No hay fondos suficientes");
        }
    }
    @Override
    public void aplicarInteres() {
        BigDecimal saldo = mostrarSaldo();
        BigDecimal tasaTemp = new BigDecimal(this.tasaInteres);
        BigDecimal cantidad = saldo.multiply(tasaTemp);
        System.out.println("Interes: " + cantidad.setScale(4, RoundingMode.HALF_UP));
        depositar(cantidad);
    }
    @Override
    public void mostrarInformacion() {
        System.out.println("Cuenta de ahorros");
        System.out.println(titular());
        System.out.println(mostrarSaldo());
    }
}