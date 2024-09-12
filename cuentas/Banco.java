package cuentas;

import java.math.BigDecimal;

public class Banco {
    String nombre;
    BigDecimal saldo;
    Cuenta cuenta = new Cuenta(nombre, saldo);
    public void agregarCuenta(String nombre, BigDecimal saldo){
        this. nombre = nombre;
        this.saldo = saldo;
        cuenta.asignarTitular(nombre);
        cuenta.setSaldo(saldo);
    }

    public BigDecimal depositar(BigDecimal valDeposito){
        cuenta.depositar(valDeposito);
        return cuenta.consultarSaldo();
    }

    public BigDecimal retirar(BigDecimal saldo){
        cuenta.retirar(saldo);
        return cuenta.consultarSaldo();
    }

    public BigDecimal consultarSaldo(){
        return cuenta.consultarSaldo();
    }

    public String consultarTitular(){
        return cuenta.getTitular();
    }
}
