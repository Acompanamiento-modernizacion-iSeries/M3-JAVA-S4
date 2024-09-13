package Cuentas;

import java.math.BigDecimal;

public abstract class Cuenta {
    private String titular;
    private BigDecimal saldo;
    private String nroCuenta;
    public Cuenta(String titular, BigDecimal saldo, String nroCuenta) {
        this.titular = titular;
        this.saldo = saldo;
        this.nroCuenta = nroCuenta;
    }

    public String consultarTitular() {
        return titular;
    }

    public void colocarTitular(String titular) {
        this.titular = titular;
    }

    public BigDecimal consultarSaldo() {
        return saldo;
    }

    public void colocarSaldo (BigDecimal saldo) {
        this.saldo = saldo;
    }
    public String consultarNroCuenta() {

        return nroCuenta;
    }

    public void colocarNroCuenta(String nroCuenta) {
        this.nroCuenta = nroCuenta;
    }


    public void depositar(BigDecimal cantidad) {
        saldo = saldo.add(cantidad);
    }

    public void retirar(BigDecimal cantidad) {
        if (saldo.compareTo(cantidad) >= 0 ){
            this.saldo = saldo.subtract(cantidad);
        }
        else {
            System.out.println("No hay fondos suficientes");
        }
    }

}

