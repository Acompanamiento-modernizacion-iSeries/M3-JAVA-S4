import java.math.BigDecimal;

public class CuentaAhorros extends Cuenta {
    private BigDecimal tasaInteres;

    public CuentaAhorros(String nombre, Integer numeroCuenta, BigDecimal saldoInicial, BigDecimal tasaInteres) {
        super(nombre, numeroCuenta, saldoInicial);
        this.tasaInteres = tasaInteres.setScale(2);
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
            if (getSaldo().compareTo(monto) >= 0) {
                BigDecimal nuevoSaldo = getSaldo().subtract(monto);
                setSaldo(nuevoSaldo);
                System.out.printf("Retiro realizado. Nuevo saldo: $%.2f\n", getSaldo());
            } else {
                System.out.println("Fondos insuficientes.");
            }
        } else {
            System.out.println("El monto debe ser positivo.");
        }
    }

    public void aplicarInteres() {
        BigDecimal interes = getSaldo().multiply(tasaInteres).divide(new BigDecimal("100"));
        BigDecimal nuevoSaldo = getSaldo().add(interes);
        setSaldo(nuevoSaldo);
        System.out.printf("Interés aplicado. Nuevo saldo: $%.2f\n", getSaldo());
    }
}
