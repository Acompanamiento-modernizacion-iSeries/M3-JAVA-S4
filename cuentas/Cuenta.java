package cuentas;

import java.math.BigDecimal;

public class Cuenta {
    private String titular;
    BigDecimal saldo;

    public Cuenta(String titular, BigDecimal saldo){
        this.titular = titular;
        this.saldo = saldo;
        //this.saldo = new BigDecimal(saldo);
    }

    public Cuenta(String titular){
        this.titular = titular;
        this.saldo = new BigDecimal(0);
    }

    public Cuenta(){
    }

    public String getTitular() {
        return titular;
    }

    public void asignarTitular(String titular) {
        this.titular = titular;
    }

    public BigDecimal consultarSaldo() {
        return saldo;
    }

    public void depositar(BigDecimal saldo){
        this.saldo = saldo;
    }
    public void retirar(BigDecimal cantidad){
        BigDecimal saldo = consultarSaldo();
        if(saldo.compareTo(cantidad) >= 0){
            this.saldo = saldo.subtract(cantidad);
        }else{
            System.out.println("No hay fondos suficientes");
        }
    }
    public void setSaldo(BigDecimal saldo){
        this.saldo = saldo;
    }

}
