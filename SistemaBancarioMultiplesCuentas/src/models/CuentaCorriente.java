package models;

import javax.swing.*;
import java.math.BigDecimal;

public class CuentaCorriente extends Cuenta{
    private BigDecimal sobtegiro;


    public CuentaCorriente(String usuario, String numeroCuenta, BigDecimal saldo, BigDecimal sobregiro) {
        super(usuario, numeroCuenta, saldo);
        this.sobtegiro = sobregiro;
    }

    public BigDecimal ConsultarSobtegiro() {
        return sobtegiro;
    }

    public void AsignarSobtegiro(BigDecimal sobtegiro) {
        this.sobtegiro = sobtegiro;
    }

    @Override
    public void retirar(BigDecimal cantidad) {
        super.retirar(cantidad);
        BigDecimal saldoConSobregiro = ConsultarSaldo().add(sobtegiro);
        if(cantidad.compareTo(saldoConSobregiro) < 1){
            JOptionPane.showMessageDialog(null, "Saldo insuficientes");
        }else{
            AsignarSaldo(ConsultarSaldo().subtract(cantidad));
        }
    }

    @Override
    public String toString() {
        return "Datos de la Cuenta :\n" +
                "Usuario: " + ConsultarUsuario() + "\n" +
                "Numero de Cuenta: " + ConsultarNumeroCuenta() + "\n" +
                "Saldo: " + ConsultarSaldo() + "\n" +
                "Valor sobre giro: " + ConsultarSobtegiro();
    }
}
