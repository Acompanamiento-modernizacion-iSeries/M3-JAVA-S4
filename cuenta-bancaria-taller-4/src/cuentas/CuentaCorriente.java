package cuentas;

import java.math.BigDecimal;

public class CuentaCorriente extends Cuenta {
    private Double limiteSobregiro;

    public CuentaCorriente(String titular, BigDecimal saldo, Double limiteSobregiro) {
        super(titular, saldo);
        this.limiteSobregiro = limiteSobregiro;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Cuenta Corriente - Titular: " + titular());
        System.out.println("Saldo: " + obtenerSaldo());
        System.out.println("Límite de Sobregiro: " + limiteSobregiro);
    }

    @Override
    public void retirar(BigDecimal cantidad) {
        BigDecimal saldo = obtenerSaldo();
        BigDecimal saldoDisponible = saldo.add(new BigDecimal(limiteSobregiro));

        if (saldoDisponible.compareTo(cantidad) >= 0) {
            setSaldo(saldo.subtract(cantidad));
            System.out.println("Retiro exitoso.");
        } else {
            System.out.println("Sus fondos son insuficientes.");
        }
    }
}
