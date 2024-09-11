package Cuentas;

import Utils.Util;
import java.math.BigDecimal;

public abstract class Cuenta {
    private String     numeroCuenta;
    private String     titular;
    private BigDecimal saldo;

    public Cuenta(String numeroCuenta, BigDecimal saldo, String titular) {
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo =  saldo;
    }

    public String consultarNumeroCuenta() {
        return numeroCuenta;
    }

    private void asignarNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public BigDecimal consultarSaldo() {
        return saldo;
    }

    public void asignarSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    private String consultarTitular() {
        return titular;
    }

    private void asignarTitular(String titular) {
        this.titular = titular;
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "numeroCuenta='" + numeroCuenta + '\'' +
                ", saldo=" + saldo +
                ", titular='" + titular + '\'' +
                '}';
    }

    public  void depositar(BigDecimal monto){
        if (monto.compareTo(BigDecimal.ZERO) <= 0){
            Util.mensaje(" *** ALERTA *** \n    - Transacción No exitosa, El monto debe ser mayor a Cero (0).");
        }else{
            saldo = saldo.add(monto);
            Util.mensaje(" *** DEPOSITO REALIZADO EXITOSAMENTE ***");
        }
    }

    public  void retirar(BigDecimal monto){
        if (monto.compareTo(BigDecimal.ZERO) <= 0){
            Util.mensaje(" *** ALERTA *** \n    - Transacción No exitosa, El monto debe ser mayor a Cero (0).");
        }
        if (monto.compareTo(saldo) > 0){
            Util.mensaje(" *** ALERTA *** \n    - Transacción No exitosa, Saldo Insuficiente.");
        }
        saldo = saldo.subtract(monto);
        Util.mensaje(" *** RETIRO REALIZADO EXITOSAMENTE ***");
    }
}
