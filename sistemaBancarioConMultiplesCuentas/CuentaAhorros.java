public class CuentaAhorros extends Cuenta {
    private double tasaInteres;

    public CuentaAhorros(double saldo, double tasaInteres) {
        super(saldo);
        this.tasaInteres = tasaInteres;
    }

    public void aplicarInteres() {
        saldo += saldo * tasaInteres;
    }

    @Override
    public void retirar(double cantidad) {
        if (cantidad <= saldo) {
            saldo -= cantidad;
        }
    }
}