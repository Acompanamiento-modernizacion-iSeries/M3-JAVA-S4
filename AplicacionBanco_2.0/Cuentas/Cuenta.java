package Cuentas;

import java.math.BigDecimal;

public abstract class Cuenta {
    protected BigDecimal saldo;

    public Cuenta(BigDecimal saldoInicial) {
        this.saldo = saldoInicial;
    }

    public BigDecimal consultarSaldo() {
        return saldo;
    }

    public void depositar(BigDecimal cantidad) {
        if (cantidad.compareTo(BigDecimal.ZERO) > 0) {
            saldo = saldo.add(cantidad);
            System.out.println("Depósito exitoso. Nuevo saldo: " + saldo);
        } else {
            System.out.println("Cantidad inválida para depósito.");
        }
    }

    public void retirar(BigDecimal cantidad) {
        if (cantidad.compareTo(saldo) <= 0 && cantidad.compareTo(BigDecimal.ZERO) > 0) {
            saldo = saldo.subtract(cantidad);
            System.out.println("Retiro exitoso. Nuevo saldo: " + saldo);
        } else {
            System.out.println("Fondos insuficientes o cantidad inválida.");
        }
    }
}
