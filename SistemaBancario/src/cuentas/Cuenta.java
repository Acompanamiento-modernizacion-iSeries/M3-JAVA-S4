package cuentas;

import java.math.BigDecimal;

public abstract class Cuenta {
    private String titular;
    private BigDecimal saldo;
    private String tipoCuenta;

    public Cuenta(String titular, BigDecimal saldo, String tipoCuenta) {
        this.titular = titular;
        this.saldo = saldo;
        this.tipoCuenta = tipoCuenta;
    }

    public Cuenta(String titular, BigDecimal saldo) {
        this.titular = titular;
        this.saldo = saldo;
    }

    public abstract void mostrarInformacion();

    public BigDecimal obtenerSaldo() {
        return saldo;
    }

    public void depositar(BigDecimal cantidad) {
        saldo = saldo.add(cantidad);
        System.out.println("Tú saldo luego del depósito para la cuenta de " + titular + " es de: " + saldo);
    }

    public void retirar(BigDecimal cantidad) {

    }

    public String titular() {
        return titular;
    }

    public void asignarTitutlar(String titular) {
        this.titular = titular;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void aplicarInteres() {
    }
}