package entity;

import java.math.BigDecimal;

public class CuentaCorriente extends Cuenta {
    private BigDecimal limiteSobregiro;

    public CuentaCorriente(String numeroCuenta, BigDecimal saldoInicial, Persona titular, BigDecimal limiteSobregiro) {
        super(numeroCuenta, saldoInicial, titular);
        this.limiteSobregiro = limiteSobregiro;
    }

    @Override
    public void depositar(BigDecimal monto) {
        saldo = saldo.add(monto);
    }

    @Override
    public void retirar(BigDecimal monto) {
        if (saldo.add(limiteSobregiro).compareTo(monto) >= 0) {
            saldo = saldo.subtract(monto);
        } else {
            throw new IllegalArgumentException("Excede el límite de sobregiro");
        }
    }
}