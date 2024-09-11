package Cuentas;

import java.math.BigDecimal;

public class CuentaCorriente extends Cuenta{
    private BigDecimal limiteSobregiro;

    public CuentaCorriente(String numeroCuenta, BigDecimal saldo, String titular , BigDecimal limiteSobregiro) {
        super(numeroCuenta, saldo, titular);
        this.limiteSobregiro = limiteSobregiro;
    }

    private BigDecimal obtenerLimiteSobregiro() {
        return limiteSobregiro;
    }

    private void asignarLimiteSobregiro(BigDecimal limiteSobregiro) {
        this.limiteSobregiro = limiteSobregiro;
    }

    @Override
    public void retirar(BigDecimal cantidad) {
        //Si la cantidad a retirar cumple con el saldo + sobregiro
        if (cantidad.compareTo(calcularDisponible()) <= 0) {
            BigDecimal saldoActual = consultarSaldo();
            //Se válida el saldo actual VS la cantidad a retirar.
            if (cantidad.compareTo(saldoActual) > 0) {
                //Si supera el saldo actual se asigna el valor directo ya que el metodo retirar tiene control de saldo insuficiente.
                BigDecimal diferencia = cantidad.subtract(saldoActual);
                super.asignarSaldo(saldoActual.subtract(cantidad));
            } else {
                //Si el valor a retirar lo cubre el saldo actual se retira.
                super.retirar(cantidad);
            }
        } else {
            //Excepción si se supera el límite de sobregiro.
            throw new IllegalArgumentException("Excede el límite de sobregiro permitido.");
        }
    }

    //Se calcula el disponible adicionando el sobregiro.
    private BigDecimal calcularDisponible() {
        return consultarSaldo().add(limiteSobregiro);
    }

}
