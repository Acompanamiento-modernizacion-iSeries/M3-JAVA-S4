package Cuentas;

import java.math.BigDecimal;

public class CuentaAhorros extends Cuenta {
    private BigDecimal tasaInteres;

    public CuentaAhorros(String titular, BigDecimal saldo, String nrocuenta, Double tasaInteres) {
        super(titular, saldo, nrocuenta);
        this.tasaInteres = new BigDecimal(tasaInteres);
    }

    public BigDecimal consultaTasaInteres() {
        return tasaInteres;
    }

    public void colocarTasaInteres(BigDecimal tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    @Override
    public void depositar(BigDecimal cantidad) {
        BigDecimal saldo = consultarSaldo();
        cantidad = saldo.multiply(tasaInteres);
        super.depositar(cantidad);
    }

}
