import java.util.ArrayList;
import java.util.List;

public class Banco {
    private List<Cuenta> cuentas;

    public Banco() {
        cuentas = new ArrayList<>();
    }

    public void agregarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
    }

    public void depositar(int numeroCuenta, double cantidad) {
        Cuenta cuenta = cuentas.get(numeroCuenta);
        cuenta.depositar(cantidad);
    }

    public void retirar(int numeroCuenta, double cantidad) {
        Cuenta cuenta = cuentas.get(numeroCuenta);
        cuenta.retirar(cantidad);
    }

    public double consultarSaldo(int numeroCuenta) {
        Cuenta cuenta = cuentas.get(numeroCuenta);
        return cuenta.consultarSaldo();
    }

    public int numeroDeCuentas() {
        return cuentas.size();
    }
}