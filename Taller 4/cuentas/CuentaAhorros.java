package cuentas;

import java.math.BigDecimal;

public class CuentaAhorros extends Cuenta {
    private Double tasaInteres;

    public CuentaAhorros(String titular, String numeroCuenta, BigDecimal saldo, Double tasaInteres) {
        super(titular, numeroCuenta, saldo);
        this.tasaInteres = tasaInteres;
    }

    public void aplicarInteres() {
        BigDecimal saldo = obtenerSaldo();
        BigDecimal tasaTemp = new BigDecimal(this.tasaInteres);
        BigDecimal cantidad = saldo.multiply(tasaTemp);
        depositar(cantidad);
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("El titular: ");
        System.out.println(titular());
        System.out.println("Tiene un saldo total de: $");
        System.out.println(obtenerSaldo());
    }

    @Override
    public void aplicarIntereses(){
        aplicarInteres();
    }
}
