package cuentas;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CuentaAhorros extends Cuenta {
    private Double tasaInteres;

    public CuentaAhorros(String titular, BigDecimal saldo, Double tasaInteres, String tipoCuenta) {
        super(titular, saldo, tipoCuenta);
        this.tasaInteres = tasaInteres;
    }

    @Override
    public void aplicarInteres() {
        BigDecimal saldo = obtenerSaldo();
        BigDecimal tasaTemp = new BigDecimal(this.tasaInteres);
        BigDecimal cantidad = saldo.multiply(tasaTemp);
        System.out.println("Tú saldo luego de los intereses es de: " + cantidad);
        depositar(cantidad);
    }


    @Override
    public void mostrarInformacion() {
        System.out.println("Cuenta de ahorros");
        System.out.println(titular());
        System.out.println(obtenerSaldo());
    }

    @Override
    public void retirar(BigDecimal cantidad) {
        BigDecimal saldo = obtenerSaldo();
        if (saldo.compareTo(cantidad) >= 0 ){
            super.setSaldo(saldo.subtract(cantidad));
            System.out.println("Tú saldo luego del retiro en la cuenta de ahorros es de: " + super.obtenerSaldo());
        }
        else {
            System.out.println("No hay fondos suficientes");
        }
    }
}