package taller4;

import java.math.BigDecimal;

public class CuentaCorriente extends Cuenta {

    private BigDecimal sobregiroPermitido;

    public CuentaCorriente(BigDecimal sobregiroPermitido) {
        super();
        this.sobregiroPermitido = sobregiroPermitido;
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
        } else if (cantidad.compareTo(saldo.add(sobregiroPermitido)) > 0) {
            System.out.println("Fondos insuficientes.");
        } else {
            descontarSaldo(cantidad);
            System.out.printf("Retiro realizado. Nuevo saldo: $%.2f%n", consultarSaldo());
        }
    }
}

