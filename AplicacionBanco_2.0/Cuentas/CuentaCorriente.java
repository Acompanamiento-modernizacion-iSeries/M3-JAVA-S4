package Cuentas;


import java.math.BigDecimal;

public class CuentaCorriente extends Cuenta {
    private BigDecimal limiteSobregiro;

    public CuentaCorriente(BigDecimal saldoInicial, BigDecimal limiteSobregiro) {
        super(saldoInicial);
        this.limiteSobregiro = limiteSobregiro;
    }

    @Override
    public void retirar(BigDecimal cantidad) {
        if (cantidad.compareTo(saldo.add(limiteSobregiro)) <= 0 && cantidad.compareTo(BigDecimal.ZERO) > 0) {
            saldo = saldo.subtract(cantidad);
            System.out.println("Retiro exitoso. Nuevo saldo: " + saldo);
        } else {
            System.out.println("Fondos insuficientes, incluso con sobregiro.");
        }
    }
}
