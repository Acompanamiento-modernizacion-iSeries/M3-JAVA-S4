package cuentas;

import java.math.BigDecimal;

public class CuentaCorriente extends Cuenta {

    private final BigDecimal limiteSobregiro;
    private BigDecimal sobregiroDisponible;

    public CuentaCorriente(String titular, String numeroCuenta, double saldo, double limiteSobregiro) {
        super(titular, numeroCuenta, saldo);
        this.limiteSobregiro = BigDecimal.valueOf(limiteSobregiro);
        this.sobregiroDisponible = this.limiteSobregiro;
    }

    public BigDecimal consultarLimiteSobregiro() {
        return limiteSobregiro;
    }

    public BigDecimal consultarSobregiroDisponible() {
        return sobregiroDisponible;
    }

    @Override
    public boolean deposito(double vlrTransacc) {
        if (vlrTransacc >= 0) {
            BigDecimal sobregiroUtilizado = limiteSobregiro.subtract(sobregiroDisponible);
            if (vlrTransacc < sobregiroUtilizado.doubleValue()) {
                sobregiroDisponible = sobregiroDisponible.add(BigDecimal.valueOf(vlrTransacc));
            } else {
                double abonoSaldo = vlrTransacc - sobregiroUtilizado.doubleValue();
                sobregiroDisponible = limiteSobregiro;
                saldo = saldo.add(BigDecimal.valueOf(abonoSaldo));
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean retiro(double vlrTransacc) {
        if (vlrTransacc >= 0) {
            if (vlrTransacc <= saldo.doubleValue()) {
                saldo = saldo.subtract(BigDecimal.valueOf(vlrTransacc));
                return true;
            } else if (vlrTransacc <= saldo.add(sobregiroDisponible).doubleValue()) {
                double vlrSobregiro = vlrTransacc - saldo.doubleValue();
                saldo = BigDecimal.ZERO;
                sobregiroDisponible = sobregiroDisponible.subtract(BigDecimal.valueOf(vlrSobregiro));
                return true;
            }
        }
        return false;
    }
}
