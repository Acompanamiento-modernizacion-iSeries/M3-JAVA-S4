package cuentas;
import java.math.BigDecimal;
public class CuentaCorriente extends Cuenta
{
    private BigDecimal topeSobregiro;

    public CuentaCorriente(String responsable, BigDecimal saldo, String numeroCuenta, String tipoCuenta, BigDecimal topeSobregiro) {
        super(responsable, saldo, numeroCuenta, tipoCuenta);
        this.topeSobregiro = topeSobregiro;
    }

    public BigDecimal gettopeSobregiro() {
        return this.topeSobregiro;
    }

    public void settopeSobregiro(BigDecimal topeSobregiro) {
        this.topeSobregiro = topeSobregiro;
    }

    @Override
    public void retirar(BigDecimal cantidad )
    {
        BigDecimal saldoActual = consultarSaldo();
        BigDecimal saldoSobregiro = consultarSaldo().add(topeSobregiro);
        if (saldoSobregiro.compareTo(cantidad) >= 0 )
        {
            BigDecimal diferencia = consultarSaldo().subtract(cantidad);
            if (diferencia.compareTo(BigDecimal.ZERO) < 0 )
            {
                super.setSaldo(diferencia);
            }
            else {
                super.retirar(cantidad);
            }
        }
        else
        {
            System.out.println("Excede límite de sobregiro");
        }
    }
}

