import java.math.BigDecimal;

public class CuentaCorriente extends Cuenta {
    private BigDecimal limiteSobregiro;

    public CuentaCorriente(String numeroCuenta, String titular, double saldoInicial, double limiteSobregiro) {
        super(numeroCuenta, titular, saldoInicial);
        this.limiteSobregiro = new BigDecimal(limiteSobregiro);
    }

    @Override
    public void retirar(BigDecimal cantidad) {
        if (saldo.add(limiteSobregiro).compareTo(cantidad) >= 0) {
            saldo = saldo.subtract(cantidad);
            System.out.println("Se ha retirado: " + cantidad);
        } else {
            System.out.println("Fondos insuficientes, incluyendo sobregiro.");
        }
    }
}
