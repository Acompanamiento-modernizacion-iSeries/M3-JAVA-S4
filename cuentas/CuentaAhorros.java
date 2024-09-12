package cuentas;

import java.math.BigDecimal;

public class CuentaAhorros  extends Cuenta {

    private Double tasaInteres;
    public CuentaAhorros(String titular, BigDecimal saldo, Double tasaInteres) {
        super(titular, saldo);
        this.tasaInteres=tasaInteres;
    }

    public void aplicarInteres(){
        BigDecimal saldo = consultarSaldo();
        BigDecimal tasatemp = new BigDecimal(this.tasaInteres);
        BigDecimal cantidad = saldo.multiply(tasatemp);
        depositar(cantidad);
    }
}
