public class CuentaAhorros extends Cuenta {
    private double tasaInteres;

    public CuentaAhorros(String nombreTitular, double saldoInicial, double tasaInteres) {
        super(nombreTitular, saldoInicial);
        this.tasaInteres = tasaInteres;
    }

    public void aplicarInteres() {
        saldo += saldo * tasaInteres;
    }
}