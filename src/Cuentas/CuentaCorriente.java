package Cuentas;

import Cuentas.Cuenta;

import java.math.BigDecimal;

public class CuentaCorriente extends Cuenta {
    private BigDecimal maximoValorSobregiro;

    public CuentaCorriente(String titular, BigDecimal saldo, String nrocuenta, Double maximoValorSobregiro) {
        super(titular, saldo, nrocuenta );
        this.maximoValorSobregiro = new BigDecimal(maximoValorSobregiro);
    }

    public BigDecimal consultaMaximoValorSobregiro() {
        return maximoValorSobregiro;
    }

    public void colocarMaximoValorSobregiro(BigDecimal maximoValorSobregiro)
    {
        this.maximoValorSobregiro = maximoValorSobregiro;
    }

    @Override
    public void retirar(BigDecimal cantidad) {
        BigDecimal saldoSobregiro = consultarSaldo().add(maximoValorSobregiro);
        if (saldoSobregiro.compareTo(cantidad) >= 0 ){
            BigDecimal diferencia = consultarSaldo().subtract(cantidad);
            if (diferencia.compareTo(BigDecimal.ZERO) < 0 ){
                super.colocarSaldo(diferencia);

            }
            else {
                super.retirar(cantidad);
            }
        }
        else {
            System.out.println("Excede limite permitido de sobregiro");
        }
    }
}


