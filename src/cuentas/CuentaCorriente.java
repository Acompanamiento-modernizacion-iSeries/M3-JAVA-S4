package cuentas;
import java.math.BigDecimal;

public class CuentaCorriente extends Cuenta {
    private BigDecimal sobregiro;

    public CuentaCorriente(String titular, BigDecimal saldo, BigDecimal sobregiro) {
        super(titular, saldo);
        this.sobregiro = sobregiro;
    }

    @Override
    public void retirar(BigDecimal cantidad) {
        BigDecimal saldo = obtenerSaldo();
        BigDecimal limiteRetiro = saldo.add(sobregiro);
        if (limiteRetiro.compareTo(cantidad) >= 0) {
            setSaldo(saldo.subtract(cantidad));
        } else {
            System.out.println("Excede el límite de sobregiro");
        }
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Cuenta corriente");
        System.out.println("Titular: " + titular());
        System.out.println("Saldo: " + obtenerSaldo());
        System.out.println("Sobregiro disponible: " + sobregiro);
    }
}