import java.math.BigDecimal;

public abstract class Cuenta {
    private String numeroCuenta;
    private BigDecimal saldo;

    public Cuenta(String numeroCuenta, BigDecimal saldoInicial) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldoInicial;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void depositar(BigDecimal monto) {
        if (monto.compareTo(BigDecimal.ZERO) > 0) {
            saldo = saldo.add(monto);
            System.out.println("Depósito exitoso. Saldo actual: $" + saldo);
        } else {
            System.out.println("El monto a depositar debe ser positivo.");
        }
    }

    public void retirar(BigDecimal monto) {
        if (monto.compareTo(BigDecimal.ZERO) > 0 && monto.compareTo(saldo) <= 0) {
            saldo = saldo.subtract(monto);
            System.out.println("Retiro exitoso. Saldo actual: $" + saldo);
        } else {
            System.out.println("Fondos insuficientes o monto inválido.");
        }
    }

    public abstract void aplicarInteres();
}
