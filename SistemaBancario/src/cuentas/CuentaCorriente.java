package cuentas;

import java.math.BigDecimal;

public class CuentaCorriente extends Cuenta {
    private Double limiteSobregiro;
    private Double tasaInteres;

    public CuentaCorriente(String titular, BigDecimal saldo, Double limiteSobregiro, String tipoCuenta, Double tasaInteres) {
        super(titular, saldo, tipoCuenta);
        this.limiteSobregiro = limiteSobregiro;
        this.tasaInteres = tasaInteres;
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

        if (saldoSobregiro.compareTo(cantidad) >= 0 ){
            setSaldo(saldo.subtract(cantidad));
            System.out.println("Tú saldo luego del retiro en la cuenta corriente es de: " + super.obtenerSaldo());
        }
        else {
            System.out.println("No hay fondos suficientes");
        }
    }

    @Override
    public void aplicarInteres() {
        BigDecimal saldo = obtenerSaldo();
        if (saldo.compareTo(BigDecimal.ZERO) <= 0 ){
            System.out.println("No hay fondos suficientes para aplicar intereses");
        }else{
            BigDecimal tasaTemp = new BigDecimal(this.tasaInteres);
            BigDecimal cantidad = saldo.multiply(tasaTemp);
            System.out.println("Tú saldo luego de los intereses es de: " + cantidad);
            depositar(cantidad);
        }
    }

}