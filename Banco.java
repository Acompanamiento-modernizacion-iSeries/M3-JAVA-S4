import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Scanner;

public class Banco {

    private ArrayList<Cuenta> cuentas = new ArrayList<>();

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\n--- Menú del Banco ---");
            System.out.println("1. Agregar cuenta");
            System.out.println("2. Consultar saldo");
            System.out.println("3. Depositar");
            System.out.println("4. Retirar");
            System.out.println("5. Aplicar intereses (solo para cuentas de ahorros)");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    agregarCuenta(scanner);
                    break;
                case 2:
                    manejarConsultaSaldo(scanner);
                    break;
                case 3:
                    manejarDeposito(scanner);
                    break;
                case 4:
                    manejarRetiro(scanner);
                    break;
                case 5:
                    manejarAplicarIntereses(scanner);
                    break;
                case 6:
                    System.out.println("Gracias por usar el sistema bancario.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 6);
    }

    public void agregarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
    }

    public Cuenta buscarCuenta(String numeroCuenta) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta.obtenerNumeroCuenta().equals(numeroCuenta)) {
                return cuenta;
            }
        }
        return null;
    }

    private void agregarCuenta(Scanner scanner) {
        String numeroCuenta;
        do {
            System.out.print("Ingrese el número de cuenta (Alfanumerico): ");
            numeroCuenta = scanner.next();
            if (numeroCuenta.isEmpty()) {
                System.out.println("El número de cuenta no puede estar vacío.");
            }
        } while (numeroCuenta.isEmpty());

        String titular;
        do {
            System.out.print("Ingrese el nombre del titular: ");
            titular = scanner.next();
            if (titular.isEmpty()) {
                System.out.println("El nombre del titular no puede estar vacío.");
            }
        } while (titular.isEmpty());

        double saldoInicial;
        do {
            System.out.print("Ingrese el saldo inicial (mayor o igual a 0): ");
            while (!scanner.hasNextDouble()) {
                System.out.println("Por favor ingrese un valor numérico válido.");
                scanner.next();
            }
            saldoInicial = scanner.nextDouble();
            if (saldoInicial < 0) {
                System.out.println("El saldo inicial no puede ser negativo.");
            }
        } while (saldoInicial < 0);

        int tipoCuenta;
        do {
            System.out.println("Seleccione el tipo de cuenta:");
            System.out.println("1. Cuenta Corriente");
            System.out.println("2. Cuenta de Ahorros");
            while (!scanner.hasNextInt()) {
                System.out.println("Por favor ingrese una opción válida (1 o 2).");
                scanner.next(); // Descarta el valor incorrecto
            }
            tipoCuenta = scanner.nextInt();
            if (tipoCuenta != 1 && tipoCuenta != 2) {
                System.out.println("Opción inválida, debe ser 1 o 2.");
            }
        } while (tipoCuenta != 1 && tipoCuenta != 2);

        if (tipoCuenta == 1) {
            double limiteSobregiro;
            do {
                System.out.print("Ingrese el límite de sobregiro (mayor o igual a 0): ");
                while (!scanner.hasNextDouble()) {
                    System.out.println("Por favor ingrese un valor numérico válido.");
                    scanner.next(); // Descarta el valor incorrecto
                }
                limiteSobregiro = scanner.nextDouble();
                if (limiteSobregiro < 0) {
                    System.out.println("El límite de sobregiro no puede ser negativo.");
                }
            } while (limiteSobregiro < 0);

            CuentaCorriente cuentaCorriente = new CuentaCorriente(numeroCuenta, titular, saldoInicial, limiteSobregiro);
            agregarCuenta(cuentaCorriente);
            System.out.println("Cuenta Corriente agregada exitosamente.");
        } else if (tipoCuenta == 2) {
            double tasaInteres;
            do {
                System.out.print("Ingrese la tasa de interés (ej: 0.02 para 2%): ");
                while (!scanner.hasNextBigDecimal()) {
                    System.out.println("Por favor ingrese un valor numérico válido.");
                    scanner.next();
                }
                tasaInteres = scanner.nextDouble();
                if ((tasaInteres < 0) || (tasaInteres > 100)) {
                    System.out.println("La tasa de interés debe estar entre 0 y 100.");
                }
            } while ((tasaInteres < 0) || (tasaInteres > 100));

            CuentaAhorros cuentaAhorros = new CuentaAhorros(numeroCuenta, titular, saldoInicial, tasaInteres);
            agregarCuenta(cuentaAhorros);
            System.out.println("Cuenta de Ahorros agregada exitosamente.");
        }
    }


    private void manejarConsultaSaldo(Scanner scanner) {
        String numeroCuenta;
        do {
            System.out.print("Ingrese el número de cuenta para consultar el saldo: ");
            numeroCuenta = scanner.next();
            if (numeroCuenta.isEmpty()) {
                System.out.println("El número de cuenta no puede estar vacío.");
            } else if (!existeCuenta(numeroCuenta)) {
                System.out.println("La cuenta con el número " + numeroCuenta + " no existe. Por favor ingrese un número de cuenta válido.");
            }
        } while (numeroCuenta.isEmpty() || !existeCuenta(numeroCuenta));

        Cuenta cuenta = obtenerCuenta(numeroCuenta);
        if (cuenta != null) {
            System.out.println("El saldo de la cuenta " + numeroCuenta + " es: " + cuenta.consultarSaldo().setScale(2, RoundingMode.HALF_UP));
        } else {
            System.out.println("Error: No se pudo encontrar la cuenta con el número " + numeroCuenta);
        }
    }

    public boolean existeCuenta(String numeroCuenta) {
        return cuentas.stream().anyMatch(c -> c.obtenerNumeroCuenta().equals(numeroCuenta));
    }

    public Cuenta obtenerCuenta(String numeroCuenta) {
        return cuentas.stream().filter(c -> c.obtenerNumeroCuenta().equals(numeroCuenta)).findFirst().orElse(null);
    }

    private void manejarDeposito(Scanner scanner) {
        String numeroCuenta;
        do {
            System.out.print("Ingrese el número de cuenta donde desea realizar el depósito: ");
            numeroCuenta = scanner.next();
            if (numeroCuenta.isEmpty()) {
                System.out.println("El número de cuenta no puede estar vacío.");
            } else if (!existeCuenta(numeroCuenta)) {
                System.out.println("La cuenta con el número " + numeroCuenta + " no existe. Por favor, ingrese un número de cuenta válido.");
            }
        } while (numeroCuenta.isEmpty() || !existeCuenta(numeroCuenta));

        double cantidadDeposito;
        do {
            System.out.print("Ingrese la cantidad a depositar (mayor a 0): ");
            while (!scanner.hasNextBigDecimal()) {
                System.out.println("Por favor ingrese un valor numérico válido.");
                scanner.next(); // Descarta el valor incorrecto
            }
            cantidadDeposito = scanner.nextDouble();
            if (cantidadDeposito <= 0) {
                System.out.println("La cantidad a depositar debe ser mayor a 0.");
            }
        } while (cantidadDeposito <= 0);

        Cuenta cuenta = obtenerCuenta(numeroCuenta);
        if (cuenta != null) {
            cuenta.depositar(cantidadDeposito);
            System.out.println("Depósito realizado exitosamente. El nuevo saldo de la cuenta " + numeroCuenta + " es: " + cuenta.consultarSaldo());
        } else {
            System.out.println("Error: No se pudo encontrar la cuenta con el número " + numeroCuenta);
        }
    }


    private void manejarRetiro(Scanner scanner) {
        System.out.print("Ingrese el número de cuenta: ");
        String numeroCuenta = scanner.next();
        Cuenta cuenta = buscarCuenta(numeroCuenta);
        if (cuenta != null) {
            System.out.print("Ingrese la cantidad a retirar: ");
            BigDecimal retiro = scanner.nextBigDecimal();
            cuenta.retirar(retiro);
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }

    private void manejarAplicarIntereses(Scanner scanner) {
        System.out.print("Ingrese el número de cuenta: ");
        String numeroCuenta = scanner.next();
        Cuenta cuenta = buscarCuenta(numeroCuenta);
        if (cuenta instanceof CuentaAhorros) {
            ((CuentaAhorros) cuenta).aplicarInteres();
        } else {
            System.out.println("La cuenta no es de ahorros.");
        }
    }

    public static void main(String[] args) {
        Banco banco = new Banco();
        banco.menu(); // Iniciar el menú
    }
}
