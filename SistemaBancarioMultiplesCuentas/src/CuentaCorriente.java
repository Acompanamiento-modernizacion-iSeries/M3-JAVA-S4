import java.math.BigDecimal;

public class CuentaCorriente extends Cuenta {
    private BigDecimal limiteSobregiro;

    public CuentaCorriente(String nombre, Integer numeroCuenta, BigDecimal saldoInicial, BigDecimal limiteSobregiro) {
        super(nombre, numeroCuenta, saldoInicial);
        this.limiteSobregiro = limiteSobregiro.setScale(2);
    }

    
    public void depositar(BigDecimal monto) {
        if (monto.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal nuevoSaldo = getSaldo().add(monto);
            setSaldo(nuevoSaldo);
            System.out.printf("Depósito realizado. Nuevo saldo: $%.2f\n", getSaldo());
        } else {
            System.out.println("El monto debe ser positivo.");
        }
    }

    
    public void retirar(BigDecimal monto) {
        if (monto.compareTo(BigDecimal.ZERO) > 0) {
            if (getSaldo().add(limiteSobregiro).compareTo(monto) >= 0) {
                BigDecimal nuevoSaldo = getSaldo().subtract(monto);
                setSaldo(nuevoSaldo);
                System.out.printf("Retiro realizado. Nuevo saldo: $%.2f\n", getSaldo());
            } else {
                System.out.println("Fondos insuficientes incluyendo el límite de sobregiro.");
            }
        } else {
            System.out.println("El monto debe ser positivo.");
        }
    }

    public BigDecimal getLimiteSobregiro() {
        return limiteSobregiro;
    }
}

