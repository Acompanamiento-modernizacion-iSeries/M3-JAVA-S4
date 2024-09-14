import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* Taller nro 4 Java Camilo Andres Garcia Cruz
* */
public class Banco {
    private List<Cuenta> cuentas;

    public Banco() {
        cuentas = new ArrayList<>();
    }

    public void agregarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
    }

    public void depositar(int indice, double monto) {
        if (indice >= 0 && indice < cuentas.size()) {
            cuentas.get(indice).depositar(monto);
        } else {
            System.out.println("Índice incorrecto. Use la opción 5 para listar cuentas.");
        }
    }

    public void retirar(int indice, double monto) {
        if (indice >= 0 && indice < cuentas.size()) {
            cuentas.get(indice).retirar(monto);
        } else {
            System.out.println("Índice incorrecto. Use la opción 5 para listar cuentas.");
        }
    }

    public double consultarSaldo(int indice) {
        if (indice >= 0 && indice < cuentas.size()) {
            Cuenta cuenta = cuentas.get(indice);
            System.out.println("Titular: " + cuenta.getNombreTitular() + ", Saldo: " + cuenta.consultarSaldo());
            return cuenta.consultarSaldo();
        } else {
            System.out.println("Índice incorrecto. Use la opción 5 para listar cuentas.");
            return 0;
        }
    }

    public void listarCuentas() {
        for (int i = 0; i < cuentas.size(); i++) {
            System.out.println("Índice: " + i + ", Titular: " + cuentas.get(i).getNombreTitular() +
            ", Tipo: " + cuentas.get(i).getClass().getSimpleName() + ", Saldo: " + cuentas.get(i).consultarSaldo());
        }
    }

    public void menuInteractivo() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n-------Menú-------");
            System.out.println("1. Agregar cuenta");
            System.out.println("2. Depositar");
            System.out.println("3. Retirar");
            System.out.println("4. Consultar saldo");
            System.out.println("5. Listar cuentas");
            System.out.println("6. Salir\n");
            int opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("Nombre del titular: ");
                    String nombreTitular = scanner.next();
                    System.out.println("Tipo de cuenta (1: Corriente, 2: Ahorros): ");
                    int tipo = scanner.nextInt();
                    System.out.println("Saldo inicial: ");
                    double saldoInicial = scanner.nextDouble();
                    if (tipo == 1) {
                        System.out.println("Límite de sobregiro: ");
                        double limiteSobregiro = scanner.nextDouble();
                        agregarCuenta(new CuentaCorriente(nombreTitular, saldoInicial, limiteSobregiro));
                    } else if (tipo == 2) {
                        System.out.println("Tasa de interés: ");
                        double tasaInteres = scanner.nextDouble();
                        agregarCuenta(new CuentaAhorros(nombreTitular, saldoInicial, tasaInteres));
                    }
                    break;
                case 2:
                    System.out.println("Índice de la cuenta: ");
                    int indiceDeposito = scanner.nextInt();
                    System.out.println("Monto a depositar: ");
                    double montoDeposito = scanner.nextDouble();
                    depositar(indiceDeposito, montoDeposito);
                    break;
                case 3:
                    System.out.println("Índice de la cuenta: ");
                    int indiceRetiro = scanner.nextInt();
                    System.out.println("Monto a retirar: ");
                    double montoRetiro = scanner.nextDouble();
                    retirar(indiceRetiro, montoRetiro);
                    break;
                case 4:
                    System.out.println("Índice de la cuenta: ");
                    int indiceConsulta = scanner.nextInt();
                    System.out.println("Saldo: " + consultarSaldo(indiceConsulta));
                    break;
                case 5:
                    listarCuentas();
                    break;
                case 6:
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    public static void main(String[] args) {
        Banco banco = new Banco();
        banco.menuInteractivo();
    }
}