package cuentas;

import java.math.BigDecimal;

public class CuentaAhorros extends Cuenta {

    protected Double tasaInteres;

    public CuentaAhorros(String titular, String numeroCuenta, double saldo, double tasaInteres) {
        super(titular, numeroCuenta, saldo);
        this.tasaInteres = tasaInteres;
    }

    @Override
    public boolean deposito(double vlrTransacc) {
        if (vlrTransacc >= 0) {
            saldo = saldo.add(BigDecimal.valueOf(vlrTransacc));
            return true;
        }
        return false;
    }

    @Override
    public boolean retiro(double vlrTransacc) {
        if (vlrTransacc >= 0 && vlrTransacc <= saldo.doubleValue()) {
            saldo = saldo.subtract(BigDecimal.valueOf(vlrTransacc));
            return true;
        }
        return false;
    }

    public boolean aplicarInteres(int tEnMeses) {
        if (tEnMeses > 0) {
            BigDecimal intereses = saldo.multiply(BigDecimal.valueOf(Math.pow((1+tasaInteres/1200),tEnMeses) - 1));
            saldo = saldo.add(intereses);
            return true;
        }
        return false;
    }
}
