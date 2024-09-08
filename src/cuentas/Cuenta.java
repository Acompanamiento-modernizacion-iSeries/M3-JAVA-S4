package cuentas;
import java.math.BigDecimal;

public abstract class Cuenta {
    private String titular;
    private BigDecimal saldo;

    public Cuenta(String titular) {
        this.titular = titular;
        this.saldo = new BigDecimal(0);
    }

    public Cuenta(String titular, BigDecimal saldo) {
        this.titular = titular;
        this.saldo = saldo;
    }

    public abstract void mostrarInformacion();

    public void depositar(BigDecimal cantidad) {
        saldo = saldo.add(cantidad);
    }

    public void retirar(BigDecimal cantidad) {
        BigDecimal saldo = obtenerSaldo();
        if (saldo.compareTo(cantidad) >= 0) {
            this.saldo = saldo.subtract(cantidad);
        } else {
            System.out.println("No hay fondos suficientes");
        }
    }

    public String titular()
    {
        return titular;
    }

    public void asignarTitular(String titular)
    {
        this.titular = titular;
    }

    public BigDecimal obtenerSaldo()
    {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo)
    {
        this.saldo = saldo;
    }
}