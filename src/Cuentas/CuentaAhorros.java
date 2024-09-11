package Cuentas;

import java.math.BigDecimal;

public class CuentaAhorros extends Cuenta{
    private BigDecimal tasaInteres;


    public CuentaAhorros(String numeroCuenta, BigDecimal saldo, String titular, BigDecimal tasaInteres ) {
        super(numeroCuenta, saldo, titular);
        this.tasaInteres =  tasaInteres;
    }

    private BigDecimal obtenerTasaInteres() {
        return tasaInteres;
    }

    private void asignarTasaInteres(BigDecimal tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    @Override
    public void depositar(BigDecimal cantidad) {

        super.depositar(cantidad);
        asignarInteres(cantidad);
    }

    private void asignarInteres(BigDecimal cantidad) {
        BigDecimal tasaDecimal = tasaInteres.divide(BigDecimal.valueOf(100));
        BigDecimal interes = cantidad.multiply(tasaDecimal);
        super.depositar(interes);
    }
}
