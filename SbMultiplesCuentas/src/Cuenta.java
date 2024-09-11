import java.math.BigDecimal;
import java.util.Scanner;

//Abstraccion
public class Cuenta {

    private String titular;
    private BigDecimal saldo;
    private  Integer tipoCuenta;

    public Cuenta(String titular, BigDecimal saldo, Integer tipoCuenta){
        this.titular = titular;
        this.saldo = saldo;
        this.tipoCuenta = tipoCuenta;
    }
    public String consultaTitular() {
        return titular;
    }

    public void adicionaTitular(String titular) {
        this.titular = titular;
    }

    public BigDecimal consultarSaldo() {
        return saldo;
    }

    public void creaSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Integer obtieneTipoCuenta() {
        return tipoCuenta;
    }
    public void creaTipoCuenta(Integer tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }


    public void depositar(BigDecimal valor){
        saldo = saldo.add(valor);
        System.out.println("Se realizo depósito");
    }

    public void retirar(BigDecimal valor){
        if(valor.compareTo(saldo) > 0){
            System.out.println("Fondos insuficientes");
        }else{
            saldo = saldo.subtract(valor);
            System.out.println("Se realizo retiro");
        }
    }
    public void consultar(){
        System.out.println(titular + " su saldo actual es: " + saldo);
    }
}
