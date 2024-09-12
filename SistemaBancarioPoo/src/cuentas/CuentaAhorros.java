package cuentas;
import java.math.BigDecimal;
public class CuentaAhorros extends Cuenta
{
    private BigDecimal tasaInteresPactada;

    public CuentaAhorros(String responsable, BigDecimal saldo, String numeroCuenta, String tipoCuenta, BigDecimal tasaInteresPactada) {
        super(responsable, saldo, numeroCuenta, tipoCuenta);
        this.tasaInteresPactada = tasaInteresPactada;
    }

    public BigDecimal getTasaInteresPactada()
    {
        return this.tasaInteresPactada;
    }

    @Override
    public BigDecimal depositar(BigDecimal cantidad) {
        BigDecimal saldo = consultarSaldo();
        cantidad = saldo.multiply(tasaInteresPactada);
        super.depositar(cantidad);
        return saldo;
    }

    public void setTasaInteresPactada(BigDecimal tasaInteresPactada) {
        this.tasaInteresPactada = tasaInteresPactada;
    }
}
