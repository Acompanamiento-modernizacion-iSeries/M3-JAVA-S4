package models;

import java.math.BigDecimal;

public class Cuenta {
    private String usuario;
    private String numeroCuenta;
    private BigDecimal saldo;

    public Cuenta(String usuario, String numeroCuenta, BigDecimal saldo) {
        this.usuario = usuario;
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
    }

    public String ConsultarUsuario() {
        return usuario;
    }

    public void asignarUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String ConsultarNumeroCuenta() {
        return numeroCuenta;
    }

    public void AsignarNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public BigDecimal ConsultarSaldo() {
        return saldo;
    }

    public void AsignarSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public void depositar(BigDecimal cantidad){
        AsignarSaldo(ConsultarSaldo().add(cantidad));
    }

    public void retirar(BigDecimal cantidad){
        AsignarSaldo(ConsultarSaldo().subtract(cantidad));
    }

    @Override
    public String toString() {
        return "Datos de la Cuenta :\n" +
                "Usuario :" + usuario + "\n" +
                "Numero de Cuenta :" + numeroCuenta + "\n" +
                "Saldo :" + saldo;
    }
}
