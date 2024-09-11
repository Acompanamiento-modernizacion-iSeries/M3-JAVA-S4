package cuentas;

import java.math.BigDecimal;

public abstract class Cuenta {
    protected String titular;
    protected String numeroCuenta;
    protected BigDecimal saldo;

    public Cuenta(String titular, String numeroCuenta, double saldo) {
        this.titular = titular.toLowerCase();
        this.numeroCuenta = numeroCuenta;
        this.saldo = BigDecimal.valueOf(saldo);
    }

    public String consultarTitular() {
        return titular;
    }

    public BigDecimal consultarSaldoDisponible() {
        return saldo;
    }

    public abstract boolean deposito(double vlrTransacc);

    public abstract boolean retiro(double vlrTransacc);
}
