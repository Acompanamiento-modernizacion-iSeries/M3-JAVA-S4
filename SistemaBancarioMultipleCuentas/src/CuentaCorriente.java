import java.math.BigDecimal;

public class CuentaCorriente extends Cuenta{
    private BigDecimal sobregiro;

    public CuentaCorriente(String nombre, Integer numero, BigDecimal saldo, BigDecimal sobregiro) {
        super(nombre, numero, saldo);
        this.sobregiro = sobregiro;
    }
    public BigDecimal getSobregiro() {
        return sobregiro;
    }
}
