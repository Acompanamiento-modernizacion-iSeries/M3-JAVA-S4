package cuentas;
import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class Cuenta {
    private String titular;
    private BigInteger nrocuenta;
    private BigDecimal saldo;
    private int edad;




    public Cuenta(String titular, BigInteger nrocuenta) {
        setTitular( titular);
        this.saldo = new BigDecimal(0);
        this.nrocuenta = nrocuenta;
        setEdad(0);
    }

    public Cuenta(String titular, BigDecimal saldo, BigInteger nrocuenta, int edad) {
        setTitular( titular);
        this.saldo = saldo;
        this.nrocuenta = nrocuenta;
        setEdad( edad);
    }
    public abstract void mostrarInformacion();

    public BigInteger getNrocuenta() {
        return nrocuenta;
    }

    public void setNrocuenta(BigInteger nrocuenta) {
        this.nrocuenta = nrocuenta;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }



    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public void depositar(BigDecimal cantidad) {
        saldo = saldo.add(cantidad);
    }

    public BigDecimal obtenerSaldo() {
        return saldo;
    }

    public void retirar(BigDecimal cantidad) {
        BigDecimal saldo = obtenerSaldo();
        if (saldo.compareTo(cantidad) >= 0 ){
            this.saldo = saldo.subtract(cantidad);
        }
        else {
            System.out.println("No hay fondos suficientes");
        }
    }
    public  Boolean Elegibilidad(){

        return this.edad >= 18;
    }
    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
