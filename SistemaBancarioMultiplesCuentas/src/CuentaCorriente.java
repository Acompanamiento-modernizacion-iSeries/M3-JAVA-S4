import java.math.BigDecimal;

public class CuentaCorriente extends Cuenta {
    private BigDecimal sobregiroPermitido;

    public CuentaCorriente(String numeroCuenta, BigDecimal saldoInicial, BigDecimal sobregiroPermitido) {
        super(numeroCuenta, saldoInicial);
        this.sobregiroPermitido = sobregiroPermitido;
    }

    @Override
    public void retirar(BigDecimal monto) {
        BigDecimal saldoDisponible = getSaldo().add(sobregiroPermitido);
        if (monto.compareTo(BigDecimal.ZERO) > 0 && monto.compareTo(saldoDisponible) <= 0) {
            super.retirar(monto);
        } else {
            System.out.println("Fondos insuficientes o fuera del límite de sobregiro.");
        }
    }

    @Override
    public void aplicarInteres() {
        System.out.println("Las cuentas corrientes no generan intereses.");
    }
}
