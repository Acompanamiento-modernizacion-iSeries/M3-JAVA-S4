package models;

import javax.swing.*;
import java.math.BigDecimal;

public class CuentaAhorros extends Cuenta{
    private BigDecimal tasaInteres;

    public CuentaAhorros(String usuario, String numeroCuenta, BigDecimal saldo, BigDecimal tasaInteres) {
        super(usuario, numeroCuenta, saldo);
        this.tasaInteres = tasaInteres;
    }

    public BigDecimal ConsultarTasaInteres() {
        return tasaInteres;
    }

    public void AsignarTasaInteres(BigDecimal tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    @Override
    public void depositar(BigDecimal cantidad) {
        super.depositar(cantidad);
        BigDecimal saldoConInteres = cantidad.multiply(tasaInteres);
        AsignarSaldo(ConsultarSaldo().add(saldoConInteres));
    }

    @Override
    public String toString() {
        return "Datos de la Cuenta :\n" +
                "Usuario: " + ConsultarUsuario() + "\n" +
                "Numero de Cuenta: " + ConsultarNumeroCuenta() + "\n" +
                "Saldo: " + ConsultarSaldo() + "\n" +
                "Tasa de interés: " + ConsultarTasaInteres();
    }
}
