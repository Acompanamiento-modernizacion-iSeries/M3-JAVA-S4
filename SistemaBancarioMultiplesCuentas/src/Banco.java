import java.math.BigDecimal;
import java.util.Scanner;

public class Banco {
    private static final int MAX_CUENTAS = 5;
    private Cuenta[] cuentas = new Cuenta[MAX_CUENTAS];
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Banco banco = new Banco();
        banco.ejecutar();
    }

    public void ejecutar() {
        boolean continuar = true;
        while (continuar) {
            mostrarMenu();
            int opcion = obtenerEntero();
            switch (opcion) {
                case 1:
                    agregarCuenta();
                    break;
                case 2:
                    realizarOperacion();
                    break;
                case 3:
                    consultarSaldo();
                    break;
                case 4:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        }
    }

    private void mostrarMenu() {
        System.out.println("\nMenú del Banco");
        System.out.println("1. Agregar cuenta");
        System.out.println("2. Realizar operación");
        System.out.println("3. Consultar saldo");
        System.out.println("4. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private void agregarCuenta() {
        if (hayEspacio()) {
            System.out.println("Ingrese el tipo de cuenta (1. Corriente, 2. Ahorros): ");
            int tipoCuenta = obtenerEntero();
            scanner.nextLine(); // Limpiar el buffer del scanner
            System.out.println("Ingrese su Nombre:");
            String nombre = scanner.nextLine();
            System.out.println("Ingrese su Número de Cuenta:");
            Integer numeroCuenta = obtenerEntero();

            // Validación para evitar cuentas duplicadas
            if (buscarCuenta(numeroCuenta) != null) {
                System.out.println("El número de cuenta ya existe.");
                return;
            }

            System.out.println("Ingrese su Saldo Inicial:");
            BigDecimal saldoInicial = obtenerBigDecimal().setScale(2);

            if (tipoCuenta == 1) {
                System.out.println("Ingrese el límite de sobregiro:");
                BigDecimal limiteSobregiro = obtenerBigDecimal().setScale(2);
                CuentaCorriente cuenta = new CuentaCorriente(nombre, numeroCuenta, saldoInicial, limiteSobregiro);
                agregarCuenta(cuenta);
                System.out.println("Cuenta Corriente creada con éxito.");
            } else if (tipoCuenta == 2) {
                System.out.println("Ingrese la tasa de interés anual (en porcentaje):");
                BigDecimal tasaInteres = obtenerBigDecimal().setScale(2);
                CuentaAhorros cuenta = new CuentaAhorros(nombre, numeroCuenta, saldoInicial, tasaInteres);
                agregarCuenta(cuenta);
                System.out.println("Cuenta de Ahorros creada con éxito.");
            } else {
                System.out.println("Tipo de cuenta no válido.");
            }
        } else {
            System.out.println("No hay espacio para más cuentas.");
        }
    }

    private boolean hayEspacio() {
        for (Cuenta cuenta : cuentas) {
            if (cuenta == null) {
                return true;
            }
        }
        return false;
    }

    private void agregarCuenta(Cuenta cuenta) {
        for (int i = 0; i < cuentas.length; i++) {
            if (cuentas[i] == null) {
                cuentas[i] = cuenta;
                break;
            }
        }
    }

    private void realizarOperacion() {
        System.out.println("Ingrese el Número de Cuenta:");
        Integer numeroCuenta = obtenerEntero();
        Cuenta cuenta = buscarCuenta(numeroCuenta);

        if (cuenta != null) {
            System.out.println("Seleccione operación (1. Depositar, 2. Retirar):");
            int operacion = obtenerEntero();
            System.out.print("Ingrese el monto: ");
            BigDecimal monto = obtenerBigDecimal().setScale(2);

            if (operacion == 1) {
                cuenta.depositar(monto);
            } else if (operacion == 2) {
                cuenta.retirar(monto);
            } else {
                System.out.println("Operación no válida.");
            }
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }

    private void consultarSaldo() {
        System.out.println("Ingrese el Número de Cuenta:");
        Integer numeroCuenta = obtenerEntero();
        Cuenta cuenta = buscarCuenta(numeroCuenta);

        if (cuenta != null) {
            cuenta.mostrarSaldo();
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }

    private Cuenta buscarCuenta(Integer numeroCuenta) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta != null && cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                return cuenta;
            }
        }
        return null;
    }

    private static Integer obtenerEntero() {
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static BigDecimal obtenerBigDecimal() {
        while (!scanner.hasNextBigDecimal()) {
            System.out.println("Entrada inválida. Por favor, ingrese un número decimal.");
            scanner.next();
        }
        return scanner.nextBigDecimal();
    }
}
