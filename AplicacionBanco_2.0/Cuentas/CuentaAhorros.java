package Cuentas;

import java.math.BigDecimal;

public class CuentaAhorros extends Cuenta {
    private BigDecimal tasaInteres;

    public CuentaAhorros(BigDecimal saldoInicial, BigDecimal tasaInteres) {
        super(saldoInicial);
        this.tasaInteres = tasaInteres;
    }

    public void aplicarInteres() {
        saldo = saldo.add(saldo.multiply(tasaInteres));
        System.out.println("Interés aplicado. Nuevo saldo: " + saldo);
    }

    @Override
    public void retirar(BigDecimal cantidad) {
        aplicarInteres();
        super.retirar(cantidad);
    }

    @Override
    public void depositar(BigDecimal cantidad) {
        aplicarInteres();
        super.depositar(cantidad);
    }
}
