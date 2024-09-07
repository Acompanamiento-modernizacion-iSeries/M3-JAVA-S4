package cuentas;
import java.math.BigDecimal;
import java.math.BigInteger;

public class CuentaCorriente extends Cuenta{
    private BigDecimal limiteSobregiro;


    public CuentaCorriente(String titular, BigDecimal saldo, BigInteger nrocuenta, BigDecimal limiteSobregiro, int edad) {
        super(titular, saldo, nrocuenta, edad);
        setLimiteSobregiro(limiteSobregiro);
    }
    public CuentaCorriente(){
        super("", new BigInteger("0"));
        this.limiteSobregiro =new BigDecimal(0);

    }

    public BigDecimal getLimiteSobregiro() {
        return limiteSobregiro;
    }

    public void setLimiteSobregiro(BigDecimal limiteSobregiro) {
        this.limiteSobregiro = limiteSobregiro;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Cuenta corriente");
        System.out.println("Titular" + getTitular());
        System.out.println("saldo" + obtenerSaldo());
        System.out.println("Edad" + getEdad());
    }

    @Override
    public void retirar(BigDecimal cantidad) {
        BigDecimal saldo = obtenerSaldo();
        BigDecimal saldoSobregiro = saldo.add(getLimiteSobregiro());

        if (saldoSobregiro.compareTo(cantidad) >= 0 ){
            setSaldo(saldo.subtract(cantidad));
        }
        else {
            System.out.println("No hay fondos suficientes");
        }
    }

}
