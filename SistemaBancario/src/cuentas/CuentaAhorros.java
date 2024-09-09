package cuentas;

import java.math.BigDecimal;

public class CuentaAhorros extends Cuenta {
    private BigDecimal tasaInteres;

    //Contructor.
    public CuentaAhorros(String titular, String numeroCuenta, double saldo, double tasaInteres) {
        super(titular, numeroCuenta, saldo);
        this.tasaInteres = BigDecimal.valueOf(tasaInteres);
    }

    public BigDecimal obtenerTasaInteres() {
        return tasaInteres;
    }

    public void asignarTasaInteres(BigDecimal tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    @Override
    public void depositar(BigDecimal cantidad) {
        //Para cada deposito nuevo en cuenta de ahorro se hace el deposito inicialmente.
        super.depositar(cantidad);
        //Luego se calcula el interes generado para ese deposito y se agrega como saldo a favor.
        aplicarInteres(cantidad);
    }

    public void aplicarInteres(BigDecimal cantidad) {
        //la tasa de interes se divide por 100 y se calcula el interese generado sobre cada deposito nuevo.
        BigDecimal tasaDecimal = tasaInteres.divide(BigDecimal.valueOf(100));
        BigDecimal interes = cantidad.multiply(tasaDecimal);
        //se agregan los intereses a favor del cliente por cada nuevo deposito.
        super.depositar(interes);
    }
}


