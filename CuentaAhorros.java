package cuentas;

import java.math.BigDecimal;
import java.math.BigInteger;

public class CuentaAhorros extends Cuenta{
    private BigDecimal tasaInteres;

    public CuentaAhorros(String titular, BigDecimal saldo, BigInteger nrocuenta , BigDecimal tasaInteres, int edad) {
        super(titular, saldo, nrocuenta, edad);
        setTasaInteres(tasaInteres);
    }
    public CuentaAhorros(){
        super("", new BigInteger("0"));
        this.tasaInteres = new BigDecimal("0");
    }

    public void apicarInteres() {
        BigDecimal saldo = obtenerSaldo();
        BigDecimal tasaTemp = this.tasaInteres;
        BigDecimal cantidad = saldo.multiply(tasaTemp);
        depositar(cantidad);
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Cuenta de ahorros");
        System.out.println("Titular" + getTitular());
        System.out.println("saldo" + obtenerSaldo());
        System.out.println("Edad" + getEdad());
    }

    public BigDecimal getTasaInteres() {
        return tasaInteres;
    }

    public void setTasaInteres(BigDecimal tasaInteres) {
        this.tasaInteres = tasaInteres;
    }
}
