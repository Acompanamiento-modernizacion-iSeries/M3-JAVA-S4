import java.math.BigDecimal;

public class CuentaAhorros extends Cuenta {
    private BigDecimal tasaInteres;

    public CuentaAhorros(String numeroCuenta, BigDecimal saldoInicial, BigDecimal tasaInteres) {
        super(numeroCuenta, saldoInicial);
        this.tasaInteres = tasaInteres;
    }

    @Override
    public void aplicarInteres() {
        BigDecimal interes = getSaldo().multiply(tasaInteres);
        depositar(interes);
        System.out.println("Interés aplicado: $" + interes + ". Nuevo saldo: $" + getSaldo());
    }
}
