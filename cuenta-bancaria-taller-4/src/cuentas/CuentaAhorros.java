package cuentas;

import java.math.BigDecimal;

public class CuentaAhorros extends Cuenta {
    private Double tasaInteres;

    public CuentaAhorros(String titular, BigDecimal saldo, Double tasaInteres) {
        super(titular, saldo);
        this.tasaInteres = tasaInteres;
    }

    public void aplicarInteres() {
        BigDecimal saldo = obtenerSaldo();
        BigDecimal tasaTemp = new BigDecimal(this.tasaInteres).divide(new BigDecimal(100));
        BigDecimal cantidad = saldo.multiply(tasaTemp);
        depositar(cantidad);
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Cuenta de Ahorros - Titular: " + titular());
        System.out.println("Saldo: " + obtenerSaldo());
        System.out.println("Tasa de Interés: " + tasaInteres + "%");
    }
}
