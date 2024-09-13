package cuentas;
import java.math.BigDecimal;

public class CuentaAhorros extends Cuenta{
    private BigDecimal interesSimple;
    public CuentaAhorros(BigDecimal interesSimple, String nombre, Integer numero, BigDecimal saldo) {
        super(nombre, numero, saldo);
        this.interesSimple = interesSimple;
    }
    public BigDecimal getInteresSimple() {
        return interesSimple;
    }
    public BigDecimal calcularInteresSimple() {
        return this.getSaldo().multiply(interesSimple.divide(BigDecimal.valueOf(100)));
    }
}
