public abstract class Cuenta {
    protected double saldo;
    protected String nombreTitular;

    public Cuenta(String nombreTitular, double saldoInicial) {
        this.nombreTitular = nombreTitular;
        this.saldo = saldoInicial;
    }

    public void depositar(double monto) {
        if (monto > 0) {
            saldo += monto;
        }
    }

    public boolean retirar(double monto) {
        if (monto > 0 && saldo >= monto) {
            saldo -= monto;
            return true;
        }
        return false;
    }

    public double consultarSaldo() {
        return saldo;
    }

    public String getNombreTitular() {
        return nombreTitular;
    }
}