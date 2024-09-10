package taller4;

import java.math.BigDecimal;

public abstract class Cuenta {

    protected BigDecimal saldo;

    public Cuenta() {
        this.saldo = BigDecimal.ZERO;
    }

    public abstract void depositar(BigDecimal cantidad);

    public abstract void retirar(BigDecimal cantidad);

    public BigDecimal consultarSaldo() {
        return saldo;
    }

    protected void actualizarSaldo(BigDecimal cantidad) {
        this.saldo = this.saldo.add(cantidad);
    }

    protected void descontarSaldo(BigDecimal cantidad) {
        this.saldo = this.saldo.subtract(cantidad);
    }
}

