package modelo;

import java.math.BigDecimal;

public class CuentaAhorro extends Cuenta{

    private static Double interesMensual = 0.83;

    public BigDecimal calcularInteresMesnual(){
        return this.getSaldo().multiply(BigDecimal.valueOf(this.interesMensual/100));
    }
}
