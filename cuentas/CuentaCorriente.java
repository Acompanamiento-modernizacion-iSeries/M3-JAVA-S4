package cuentas;

import java.math.BigDecimal;

public class CuentaCorriente extends Cuenta {
    private Double limiteSobregiro;


    public CuentaCorriente(String titular, BigDecimal saldo, Double limiteSobregiro) {
        super(titular, saldo);
        this.limiteSobregiro=limiteSobregiro;
    }
    @Override
    public void retirar(BigDecimal cantidad){
        BigDecimal saldo = consultarSaldo();
        BigDecimal saldoSobregiro=saldo.add(new BigDecimal(limiteSobregiro));

        if(saldoSobregiro.compareTo(cantidad) >= 0){
            setSaldo(saldo.subtract(cantidad));
        }else{
            System.out.println("No hay fondos suficientes");
        }
    }
}
