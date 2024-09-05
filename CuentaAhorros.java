import java.math.BigDecimal;
import java.math.RoundingMode;

public class CuentaAhorros extends Cuenta {
    private BigDecimal tasaInteres;

    public CuentaAhorros(String numeroCuenta, String titular, double saldoInicial, double tasaInteres) {
        super(numeroCuenta, titular, saldoInicial);
        this.tasaInteres = new BigDecimal(tasaInteres);
    }

    @Override
    public void retirar(BigDecimal cantidad) {
        if (saldo.compareTo(cantidad) >= 0) {
            saldo = saldo.subtract(cantidad);
            System.out.println("Se ha retirado: " + cantidad);
        } else {
            System.out.println("Fondos insuficientes.");
        }
    }

    public void aplicarInteres() {
        BigDecimal interes = saldo.multiply(tasaInteres);
        saldo = saldo.add(interes);
        System.out.println("Se ha aplicado un interés de: " + interes.setScale(2, RoundingMode.HALF_UP));

    }
}
