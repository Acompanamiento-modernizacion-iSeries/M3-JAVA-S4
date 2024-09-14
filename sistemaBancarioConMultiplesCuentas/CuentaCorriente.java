public class CuentaCorriente extends Cuenta {
    private double sobregiro;

    public CuentaCorriente(double saldo, double sobregiro) {
        super(saldo);
        this.sobregiro = sobregiro;
    }

    @Override
    public void retirar(double cantidad) {
        if (cantidad <= saldo + sobregiro) {
            saldo -= cantidad;
        }
    }
}