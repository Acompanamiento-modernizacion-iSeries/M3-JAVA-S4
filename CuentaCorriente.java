public class CuentaCorriente extends Cuenta {
    private double limiteSobregiro;

    public CuentaCorriente(String nombreTitular, double saldoInicial, double limiteSobregiro) {
        super(nombreTitular, saldoInicial);
        this.limiteSobregiro = limiteSobregiro;
    }

    @Override
    public boolean retirar(double monto) {
        if (monto > 0 && saldo + limiteSobregiro >= monto) {
            saldo -= monto;
            return true;
        }
        return false;
    }
}