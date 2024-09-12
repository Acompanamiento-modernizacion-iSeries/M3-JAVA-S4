package cuentas;
import java.math.BigDecimal;
public abstract class Cuenta {
    private String responsable;
    private BigDecimal saldo;
    private String numeroCuenta;
    private String tipoCuenta;

    public Cuenta(String responsable, BigDecimal saldo, String numeroCuenta, String tipoCuenta) {
        this.responsable = responsable;
        this.saldo = saldo;
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
    }

    public String getResponsable() {
        return responsable;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public String gettipoCuenta() {
        return tipoCuenta;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public void settipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }
    public BigDecimal consultarSaldo()
    {
        return this.saldo;
    }

    public BigDecimal depositar(BigDecimal cantidad)
    {
        this.saldo = saldo.add(cantidad);
        return saldo;
    }

    public BigDecimal SaldoInicial(BigDecimal valor)
    {
        this.saldo= saldo.add(valor);
        return saldo;
    }

    public void retirar(BigDecimal cantidad)
    {
        if (saldo.compareTo(cantidad) >= 0) {
            this.saldo = saldo.subtract(cantidad);
        } else {
            System.out.println("No hay fondos suficientes");
        }
    }

}