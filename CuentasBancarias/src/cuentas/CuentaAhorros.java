package cuentas;

import javax.swing.*;
import java.math.BigDecimal;

public class CuentaAhorros extends Cuenta {
    private Double tasaInteres;

    public CuentaAhorros(String titular, BigDecimal saldo,String numeroCuenta,
                         String tipoCuenta, Double tasaInteres) {
        super(titular,saldo,numeroCuenta,tipoCuenta);
        this.tasaInteres = tasaInteres;
    }

    public void apicarInteres() {
        BigDecimal saldo = obtenerSaldo();
        BigDecimal tasaTemp = new BigDecimal(this.tasaInteres);
        BigDecimal cantidad = saldo.multiply(tasaTemp);
        depositar(cantidad);
    }

    @Override
    public void mostrarInformacion() {
        MensajeGenerico("Cuenta de ahorros #"+getNumeroCuenta()+" del titular "+titular()+
                " con saldo $"+obtenerSaldo());
    }

    @Override
    public void crearCuenta() {

    }

    private void MensajeGenerico(String mensaje){
        JOptionPane.showMessageDialog(null,mensaje);
    }
}