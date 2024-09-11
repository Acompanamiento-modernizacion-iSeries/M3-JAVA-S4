package cuentas;

import java.math.BigDecimal;
public class CuentaCorriente extends Cuenta {
    private Double limiteSobregiro;

    public CuentaCorriente(String titular, BigDecimal saldo, Double limiteSobregiro, String numCuenta) {
        super(titular, saldo, numCuenta);
        this.limiteSobregiro = limiteSobregiro;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Cuenta corriente");
        System.out.println(this.titular());
        System.out.println(this.obtenerSaldo());
    }

    @Override
    public void retirar(BigDecimal cantidad) {
        BigDecimal saldo = obtenerSaldo();
        BigDecimal saldoSobregiro = saldo.add(new BigDecimal(limiteSobregiro));

        if (saldoSobregiro.compareTo(cantidad) >= 0) {
            setSaldo(saldo.subtract(cantidad));
        } else {
            System.out.println("No hay fondos suficientes");
        }
    }
}

