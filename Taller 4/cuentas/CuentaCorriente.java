package cuentas;

import java.math.BigDecimal;

public class CuentaCorriente extends Cuenta {
    private Double limiteSobregiro;

    public CuentaCorriente(String titular, String numeroCuenta, BigDecimal saldo, Double limiteSobregiro) {
        super(titular, numeroCuenta, saldo);
        this.limiteSobregiro = limiteSobregiro;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("El titular: ");
        System.out.println(this.titular());
        System.out.println("Tiene un saldo total de: $");
        System.out.println(this.obtenerSaldo());
    }

    @Override
    public void retirar(BigDecimal cantidad) {
        BigDecimal saldo = obtenerSaldo();
        BigDecimal saldoSobregiro = saldo.add(new BigDecimal(limiteSobregiro));

        if (saldoSobregiro.compareTo(cantidad) >= 0 ){
            setSaldo(saldo.subtract(cantidad));
        }
        else {
            System.out.println("No hay fondos suficientes");
        }
    }

    @Override
    public void aplicarIntereses() {

    }
}
