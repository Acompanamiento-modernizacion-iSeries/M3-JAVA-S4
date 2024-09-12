import java.math.BigDecimal;

public abstract class Cuenta {
    private String nombre;
    private Integer numeroCuenta;
    private BigDecimal saldo;

    public Cuenta(String nombre, Integer numeroCuenta, BigDecimal saldoInicial) {
        this.nombre = nombre;
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldoInicial.setScale(2);
    }

    public abstract void depositar(BigDecimal monto);
    public abstract void retirar(BigDecimal monto);

    public void mostrarSaldo() {
        System.out.printf("Saldo actual: $%.2f\n", this.saldo);
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getNumeroCuenta() {
        return numeroCuenta;
    }

    protected void setSaldo(BigDecimal saldo) {
        this.saldo = saldo.setScale(2);
    }
}