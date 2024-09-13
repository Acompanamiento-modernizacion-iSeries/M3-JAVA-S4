import java.math.BigDecimal;

public class Cuenta {
    private String nombre;
    private Integer numero;
    private BigDecimal saldo;

    public Cuenta(String nombre, Integer numero, BigDecimal saldo) {
        this.nombre = nombre;
        this.numero = numero;
        this.saldo = saldo;
    }
    public String getNombre() {
        return nombre;
    }
    public BigDecimal getSaldo() {
        return saldo;
    }
    public BigDecimal depositar(BigDecimal valor){
        this.saldo = this.saldo.add(valor);
        return this.saldo;
    }
    public BigDecimal retirar(BigDecimal valor){
        this.saldo = this.saldo.subtract(valor);
        return this.saldo;
    }
}