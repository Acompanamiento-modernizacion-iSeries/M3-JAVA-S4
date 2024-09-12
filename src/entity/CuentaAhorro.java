package entity;

import java.math.BigDecimal;

public class CuentaAhorro extends Cuenta {
    public CuentaAhorro(String numeroCuenta, BigDecimal saldoInicial, Persona titular) {
        super(numeroCuenta, saldoInicial, titular);
    }

    @Override
    public void depositar(BigDecimal monto) {
        saldo = saldo.add(monto);
    }

    @Override
    public void retirar(BigDecimal monto) {
        if (saldo.compareTo(monto) >= 0) {
            saldo = saldo.subtract(monto);
        } else {
            throw new IllegalArgumentException("Saldo insuficiente");
        }
    }
}