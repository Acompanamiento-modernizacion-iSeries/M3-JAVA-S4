package modelo;

import java.math.BigDecimal;

public class Cuenta {

    private String nombre;
    private Integer numeroCuenta;
    private BigDecimal saldo;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(Integer numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public BigDecimal consultarSaldo(){
        return this.saldo;
    }

    public BigDecimal depositar(BigDecimal valor){
        this.saldo = this.saldo.add(valor);
        return this.saldo;
    }

    public BigDecimal retirar(BigDecimal valor){
        this.saldo = this.saldo.subtract(valor);
        return this.saldo;
    }

    public Boolean elegible(){
        return this.saldo.compareTo(new BigDecimal(1000)) >= 0;
    }
}
