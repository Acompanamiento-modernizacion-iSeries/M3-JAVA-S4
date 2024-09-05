import java.math.BigDecimal;

public abstract class Cuenta {
    protected String numeroCuenta;
    protected String titular;
    protected BigDecimal saldo;

    public Cuenta(String numeroCuenta, String titular, double saldoInicial) {
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = new BigDecimal(saldoInicial);
    }

    public void depositar(double cantidad) {
        BigDecimal cantidad2= new BigDecimal(cantidad);
        saldo = saldo.add(cantidad2);
        System.out.println("Se ha depositado: " + cantidad);
    }

    public abstract void retirar(BigDecimal cantidad);

    public BigDecimal consultarSaldo() {
        return saldo;
    }

    public String obtenerNumeroCuenta() {
        return numeroCuenta;
    }

    public String obtenerTitular() {
        return titular;
    }
}