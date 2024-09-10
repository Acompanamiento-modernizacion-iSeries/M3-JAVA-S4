import cuentas.Cuenta;
import cuentas.CuentaCorriente;
import cuentas.CuentaAhorros;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class Banco {
    private ArrayList<Cuenta> cuentas;

    public Banco() {
        this.cuentas = new ArrayList<>();
    }

    public void mostrarMenu() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Bienvenido al Banco. Seleccione una opción:");
            System.out.println("1. Agregar cuenta");
            System.out.println("2. Depositar");
            System.out.println("3. Retirar");
            System.out.println("4. Consultar saldo");
            System.out.println("5. Aplicar interés a una cuenta de ahorro");
            System.out.println("6. Mostrar información de la cuenta");
            System.out.println("7. Salir");

            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    agregarNuevaCuenta(sc);
                    break;
                case 2:
                    realizarDeposito(sc);
                    break;
                case 3:
                    realizarRetiro(sc);
                    break;
                case 4:
                    consultarSaldoCuenta(sc);
                    break;
                case 5:
                    aplicarInteresCuenta(sc);
                    break;
                case 6:
                    mostrarInformacionCuenta(sc);
                    break;
                case 7:
                    System.out.println("¡Hasta luego!");
                    System.exit(0);
                default:
                    System.out.println("Seleccionaste una opción incorrecta");
            }
        }
    }

    public void agregarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta); //agregamos la cuenta al arraylist
        System.out.println("Cuenta con número " + cuenta.getNumeroCuenta() + " agregada.");
    }

    public Cuenta buscarCuenta(String numeroCuenta) {
        for (Cuenta cuenta : cuentas) { //recorremos el arraylist para encontrar el numero de cuenta agregado
            if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                return cuenta;
            }
        }
        return null;
    }

    private void agregarNuevaCuenta(Scanner sc) {
        System.out.println("Ingrese el nombre del titular:");
        String titular = sc.nextLine();
        System.out.println("Ingrese el número de cuenta:");
        String numeroCuenta = sc.nextLine();
        System.out.println("Ingrese el saldo inicial:");
        BigDecimal saldoInicial = sc.nextBigDecimal();
        sc.nextLine();

        System.out.println("Seleccione el tipo de cuenta:");
        System.out.println("1. Cuenta Corriente");
        System.out.println("2. Cuenta de Ahorros");

        int tipoCuenta = sc.nextInt();
        sc.nextLine();

        if (tipoCuenta == 1) {
            System.out.println("Ingrese el límite de sobregiro:");
            Double limiteSobregiro = sc.nextDouble();
            sc.nextLine();
            agregarCuenta(new CuentaCorriente(titular, numeroCuenta, saldoInicial, limiteSobregiro));
        } else if (tipoCuenta == 2) {
            System.out.println("Ingrese la tasa de interés en % Ej 3:");
            Double tasaInteres = sc.nextDouble();
            Double tasaInteresDecimal = tasaInteres/100;
            sc.nextLine();
            agregarCuenta(new CuentaAhorros(titular, numeroCuenta, saldoInicial, tasaInteresDecimal));
        } else {
            System.out.println("Tipo de cuenta no válido.");
        }
    }

    private void realizarDeposito(Scanner sc) {
        System.out.println("Ingrese el número de cuenta:");
        String numeroCuenta = sc.nextLine();
        Cuenta cuenta = buscarCuenta(numeroCuenta);
        if (cuenta != null) {
            System.out.println("Ingrese el monto a depositar:");
            BigDecimal monto = sc.nextBigDecimal();
            sc.nextLine();
            cuenta.depositar(monto);
            System.out.println("Se ha realizado un depósito. Su saldo actual es de: $" + cuenta.obtenerSaldo());
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }

    private void realizarRetiro(Scanner sc) {
        System.out.println("Ingrese el número de cuenta:");
        String numeroCuenta = sc.nextLine();
        Cuenta cuenta = buscarCuenta(numeroCuenta);
        if (cuenta != null) {
            System.out.println("Ingrese el monto a retirar:");
            BigDecimal monto = sc.nextBigDecimal();
            sc.nextLine();
            cuenta.retirar(monto);
            System.out.println("Retiro realizado. Saldo actual: $" + cuenta.obtenerSaldo());
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }

    private void consultarSaldoCuenta(Scanner sc) {
        System.out.println("Ingrese el número de cuenta:");
        String numeroCuenta = sc.nextLine();
        Cuenta cuenta = buscarCuenta(numeroCuenta);
        if (cuenta != null) {
            System.out.println("El saldo de la cuenta " + numeroCuenta + " es $" + cuenta.obtenerSaldo());
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }

    private void aplicarInteresCuenta(Scanner sc) {
        System.out.println("Ingrese el número de cuenta:");
        String numeroCuenta = sc.nextLine();
        Cuenta cuenta = buscarCuenta(numeroCuenta);
        if (cuenta != null) {
            cuenta.aplicarIntereses();
            System.out.println("Saldo actual: $" + cuenta.obtenerSaldo());
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }


    private void mostrarInformacionCuenta(Scanner sc) {
        System.out.println("Ingrese el número de cuenta:");
        String numeroCuenta = sc.nextLine();
        Cuenta cuenta = buscarCuenta(numeroCuenta);
        if (cuenta != null) {
            cuenta.mostrarInformacion();
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }

}
