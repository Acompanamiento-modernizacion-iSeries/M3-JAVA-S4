package cuentas;

import java.math.BigDecimal;

public abstract class Cuenta {
    private String titular;
    private BigDecimal saldo;
    private String numeroCuenta;
    private String tipoCuenta;

    public Cuenta(String titular) {
        this.titular = titular;
        this.saldo = new BigDecimal(0);
    }

    public Cuenta(String titular, BigDecimal saldo,String numeroCuenta,String tipoCuenta) {
        this.titular = titular;
        this.saldo = saldo;
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;

    }

    public abstract void mostrarInformacion();
    public abstract void crearCuenta();

    public void depositar(BigDecimal cantidad) {
        saldo = saldo.add(cantidad);
    }

    public void retirar(BigDecimal cantidad) {
        BigDecimal saldo = obtenerSaldo();
        if (saldo.compareTo(cantidad) >= 0 ){
            this.saldo = saldo.subtract(cantidad);
        }
        else {
            System.out.println("No hay fondos suficientes");
        }
    }

    // Getter and Setters
    public String titular() {
        return titular;
    }

    public void asignarTitutlar(String titular) {
        this.titular = titular;
    }

    public BigDecimal obtenerSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

}