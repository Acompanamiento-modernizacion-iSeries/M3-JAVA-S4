package cuentas;

import java.math.BigDecimal;

public abstract class Cuenta {
    private String titular;
    private String numeroCuenta;
    private BigDecimal saldo;

    public Cuenta(String titular, String numeroCuenta, double saldo) {
        this.titular = titular;
        this.numeroCuenta = numeroCuenta;
        this.saldo = BigDecimal.valueOf(saldo);
    }

    public String consultarTitular() {
        return titular;
    }

    public void asignarTitular(String titular) {
        this.titular = titular;
    }

    public String consultarNumeroCuenta() {
        return numeroCuenta;
    }

    public void asignarNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public BigDecimal consultarSaldo() {
        return saldo;
    }

    public void asignarSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public void depositar(BigDecimal cantidad) {
            saldo = saldo.add(cantidad);
    }

    public void retirar(BigDecimal cantidad) {
        if (cantidad.compareTo(saldo) <= 0) {
            saldo = saldo.subtract(cantidad);
        } else {
            throw new IllegalArgumentException("Saldo insuficiente.");
        }

    }
}

