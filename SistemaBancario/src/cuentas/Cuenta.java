package cuentas;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class Cuenta {
    private String numCuenta;
    private String titular;
    private BigDecimal saldo;
    private String tipoCuenta;


    public Cuenta(String numCuenta, String titular, BigDecimal saldo, String tipoCuenta) {
        this.numCuenta = numCuenta;
        this.titular = titular;
        this.saldo = saldo;
        this.tipoCuenta = tipoCuenta;

    }

    public Cuenta(String titular, BigDecimal saldo) {
        this.titular = titular;
        this.saldo = saldo;
    }

    public abstract void mostrarInformacion();

    public String getNumeroCuenta() {
        return numCuenta;
    }

    public BigDecimal mostrarSaldo() {
        return saldo;
    }



    public String getnumCuenta() {
        return numCuenta;
    }
    public void depositar(BigDecimal cantidad) {
        saldo = saldo.add(cantidad);
        System.out.println("Nuevo saldo    : " + saldo.setScale(4, RoundingMode.HALF_UP));
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

    public String toString() {
        return "Cuenta [Número: " + numCuenta + ", Titular: " + titular + ", Saldo: " + saldo.setScale(4, RoundingMode.HALF_UP) + "]";
    }
}