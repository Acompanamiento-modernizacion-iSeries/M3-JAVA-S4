package Validaciones;

import javax.swing.*;
import java.math.BigDecimal;

public class ValidarDatos {
    public String ValidarUsuario(){
        String usuario = "";
        while (usuario.isEmpty()) {
            usuario = JOptionPane.showInputDialog("Por favor ingresa tu usuario");
            if (usuario.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Usuario incorrecto");
            }
        }
        return usuario;
    }

    public String ValidarCuenta(){
        String numeroCuenta = "";
        while (numeroCuenta.isEmpty()) {
            numeroCuenta = JOptionPane.showInputDialog("Por favor ingresa tu número de cuenta");
            if (numeroCuenta.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Número de cuenta bancaria debe ser ingresada");
            }
        }
        return numeroCuenta;
    }

    public Integer ValidarTipoTRansaccion(){
        Integer TipoTransaccion = 0;
        while (TipoTransaccion <= 0) {
            TipoTransaccion = Integer.parseInt(JOptionPane.showInputDialog(" 1 - Transacciones Cuenta cortiente \n" +
                    " 2 - Transacciones Cuenta ahorros \n"));
            if (!TipoTransaccion.equals(1) && !TipoTransaccion.equals(2)) {
                JOptionPane.showMessageDialog(null, "Opción incorrecta");
            }
        }
        return TipoTransaccion;
    }

    public BigDecimal validarsaldo(){
        BigDecimal saldo = new BigDecimal(0);
        while (saldo.signum() <= 0) {
            try {
                saldo = BigDecimal.valueOf(Long.parseLong(JOptionPane.showInputDialog("Por favor ingresa el saldo actual")));
            }catch (Exception e){
                saldo = new BigDecimal(0);
            }
            if (saldo.signum() <= 0) {
                JOptionPane.showMessageDialog(null, "Ingrese un saldo correcto");
            }
        }
        return saldo;
    }

    public BigDecimal validaEntradaValorRetiro(){
        BigDecimal ValorRetiro = new BigDecimal(0);
        while (ValorRetiro.signum() <= 0) {
            try {
                ValorRetiro = BigDecimal.valueOf(Long.parseLong(JOptionPane.showInputDialog("Por favor ingresa el valor del retiro")));
            }catch (Exception e){
                ValorRetiro = new BigDecimal(0);
            }
            if (ValorRetiro.signum() <= 0) {
                JOptionPane.showMessageDialog(null, "Ingrese un valor correcto");
            }
        }
        return ValorRetiro;

    }

    public BigDecimal validaEntradaValordeposito(){
        BigDecimal Valordeposito = new BigDecimal(0);
        while (Valordeposito.signum() <= 0) {
            try {
                Valordeposito = BigDecimal.valueOf(Long.parseLong(JOptionPane.showInputDialog("Por favor ingresa el valor del deposto")));
            }catch (Exception e){
                Valordeposito = new BigDecimal(0);
            }
            if (Valordeposito.signum() <= 0) {
                JOptionPane.showMessageDialog(null, "Ingrese un valor correcto");
            }
        }
        return Valordeposito;

    }
}
