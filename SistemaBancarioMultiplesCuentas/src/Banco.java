import Validaciones.ValidarDatos;
import models.Cuenta;
import models.CuentaAhorros;
import models.CuentaCorriente;

import javax.swing.*;
import java.math.BigDecimal;

public class Banco {
    public static void main(String[] args) {

        ValidarDatos validarEntradaDatos = new ValidarDatos();
        String usuario = validarEntradaDatos.ValidarUsuario();
        String numeroCuenta = validarEntradaDatos.ValidarCuenta();
        Integer TipoTransaccion = validarEntradaDatos.ValidarTipoTRansaccion();

        Cuenta cuentaUsuario = new Cuenta(usuario, numeroCuenta, new BigDecimal(0));
        MostrarmenuTrasacciones(cuentaUsuario, TipoTransaccion);

    }

    public static void MostrarmenuTrasacciones(Cuenta cuentaUsuario, Integer tipoTransaccion) {
        BigDecimal valorSobregiro = new BigDecimal(500000);
        BigDecimal TasaInteres = new BigDecimal(0.09);
        ValidarDatos validardatos = new ValidarDatos();
        BigDecimal saldo = validardatos.validarsaldo();

        Cuenta cuentacorriente = new CuentaCorriente(cuentaUsuario.ConsultarUsuario(),
                cuentaUsuario.ConsultarNumeroCuenta(), saldo, valorSobregiro);

        Cuenta cuentaAhorros = new CuentaAhorros(cuentaUsuario.ConsultarUsuario(),
                cuentaUsuario.ConsultarNumeroCuenta(), saldo, TasaInteres);


        while (true) {
            Integer opcion = MostrarMenu(tipoTransaccion);
            switch (opcion) {
                case 1:
                    if (tipoTransaccion.equals(1)) {
                        JOptionPane.showMessageDialog(null, "Saldo actual :" + cuentacorriente.ConsultarSaldo());
                    }else{
                        JOptionPane.showMessageDialog(null, "Saldo actual :" + cuentaAhorros.ConsultarSaldo());
                    }
                    break;
                case 2:
                    BigDecimal valordeposito = validardatos.validaEntradaValordeposito();
                    if (tipoTransaccion.equals(1)) {
                        cuentacorriente.depositar(valordeposito);
                    } else {
                        cuentaAhorros.depositar(valordeposito);
                    }
                    break;
                case 3:
                    BigDecimal valorretiro = validardatos.validaEntradaValorRetiro();
                    if (tipoTransaccion.equals(1)) {
                        cuentacorriente.retirar(valorretiro);
                    } else {
                        cuentaAhorros.retirar(valorretiro);
                    }
                    break;
                case 4:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción incorrecta");
            }
            if (opcion == 4) {
                break;
            }

        }
        if (tipoTransaccion.equals(1)) {
            JOptionPane.showMessageDialog(null, cuentacorriente.toString());
        }else{
            JOptionPane.showMessageDialog(null, cuentaAhorros.toString());
        }
    }

    public static Integer MostrarMenu(Integer tipoTransaccion) {
        String TipoTransacciontexto;
        if (tipoTransaccion.equals(1)) {
            TipoTransacciontexto = "corriente";
        } else {
            TipoTransacciontexto = "ahorros";
        }
        Integer opcion = Integer.parseInt(JOptionPane.showInputDialog(
                "Menu transacciones cuenta " + TipoTransacciontexto + "\n" +
                        "  1 - consultar saldo\n" +
                        "  2 - Deposito\n" +
                        "  3 - Retiro\n" +
                        "  4 - Salir"));
        return opcion;

    }
}





