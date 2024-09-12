package entity;

import java.math.BigDecimal;

public abstract class Cuenta {
    protected String numeroCuenta;
    protected BigDecimal saldo;
    protected Persona titular;

    public Cuenta(String numeroCuenta, BigDecimal saldoInicial, Persona titular) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldoInicial;
        this.titular = titular;
    }

    public abstract void depositar(BigDecimal monto);
    public abstract void retirar(BigDecimal monto);

    public String obtenerNumeroCuenta() {
        return numeroCuenta;
    }

    public BigDecimal obtenerSaldo() {
        return saldo;
    }

    public Persona obtenerTitular() {
        return titular;
    }

    public void imprimirDatos() {
        System.out.println("Número de cuenta: " + numeroCuenta);
        System.out.println("Saldo: " + saldo);
        System.out.println("Titular: " + titular.obtenerNombre());
    }
}