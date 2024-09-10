package taller4;

import java.math.BigDecimal;

public class CuentaAhorros extends Cuenta {

    private BigDecimal tasaInteresAnual;

    public CuentaAhorros(BigDecimal tasaInteresAnual) {
        super();
        this.tasaInteresAnual = tasaInteresAnual;
    }

    @Override
    public void depositar(BigDecimal cantidad) {
        if (cantidad.compareTo(BigDecimal.ZERO) > 0) {
            actualizarSaldo(cantidad);
            System.out.printf("Depósito realizado. Nuevo saldo: $%.2f%n", consultarSaldo());
        } else {
            System.out.println("La cantidad a depositar debe ser positiva.");
        }
    }

    @Override
    public void retirar(BigDecimal cantidad) {
        if (cantidad.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("La cantidad a retirar debe ser positiva.");
        } else if (cantidad.compareTo(saldo) > 0) {
            System.out.println("Fondos insuficientes.");
        } else {
            descontarSaldo(cantidad);
            System.out.printf("Retiro realizado. Nuevo saldo: $%.2f%n", consultarSaldo());
        }
    }

    public void aplicarInteres() {
        BigDecimal intereses = saldo.multiply(tasaInteresAnual).divide(BigDecimal.valueOf(100));
        actualizarSaldo(intereses);
        System.out.printf("Interés aplicado. Nuevo saldo: $%.2f%n", consultarSaldo());
    }
}

