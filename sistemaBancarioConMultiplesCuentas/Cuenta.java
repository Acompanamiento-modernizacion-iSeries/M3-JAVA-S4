public abstract class Cuenta {
    protected double saldo;

    public Cuenta(double saldo) {
        this.saldo = saldo;
    }

    public void depositar(double cantidad) {
        if (cantidad > 0) {
            saldo += cantidad;
        }
    }

    public abstract void retirar(double cantidad);

    public double consultarSaldo() {
        return saldo;
    }
}