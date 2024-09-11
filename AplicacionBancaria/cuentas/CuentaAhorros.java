package cuentas;

import java.math.BigDecimal;

public class CuentaAhorros extends Cuenta {
    private Double tasaInteres;

    public CuentaAhorros(String titular, BigDecimal saldo, Double tasaInteres, String numCuenta) {
        super(titular, saldo, numCuenta );
        this.tasaInteres = tasaInteres;
    }

    public void apicarInteres() {
        BigDecimal saldo = obtenerSaldo();
        BigDecimal tasaTemp = new BigDecimal(this.tasaInteres);
        BigDecimal cantidad = saldo.multiply(tasaTemp);
        depositar(cantidad);
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Cuenta de ahorros");
        System.out.println(titular());
        System.out.println(obtenerSaldo());
    }
}


