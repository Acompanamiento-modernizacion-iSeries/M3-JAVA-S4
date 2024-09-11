import cuentas.Cuenta;
import cuentas.CuentaAhorros;
import cuentas.CuentaCorriente;

import java.math.BigDecimal;
import java.util.*;

public class Banco {

    private int idCuentaCorriente = 0;
    private int idCuentaAhorros = 0;

    private List<Cuenta> listCuentas = new ArrayList<>();
    private Scanner sc;

    public Banco() {
        //listCuentas = new ArrayList<>();
        sc = new Scanner(System.in);
    }

    public Cuenta crearCuenta(String titular, int tipoCta) {
        BigDecimal monto = new BigDecimal(0);
        String numCuenta = "";
        if (tipoCta == 1) {

            numCuenta = "CTAC" + idCuentaCorriente++;
            Double valor = 100.0;
            CuentaCorriente cuenta = new CuentaCorriente(titular, monto, valor, numCuenta);
            agregarCuenta(cuenta);
            return cuenta;
        } else {
            numCuenta = "CTAA" + idCuentaAhorros++;
            Double valor = 0.02;
            CuentaAhorros cuenta = new CuentaAhorros(titular, monto, valor, numCuenta);
            agregarCuenta(cuenta);
            return cuenta;
        }
    }

    public Boolean existeCuenta(List<Cuenta> cuentas, String numeroCuenta) {

        for (Cuenta cuenta : cuentas) {
            if (cuenta.numeroCuenta().equals(numeroCuenta)) {
                return true;

            }
        }
        return false;
    }


    public Cuenta obtenerCuenta(List<Cuenta> cuentas, String numeroCuenta) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta.numeroCuenta().equals(numeroCuenta)) {
                return cuenta;
            }
        }
        return null;
    }


    public void agregarCuenta(Cuenta cuenta) {
        listCuentas.add(cuenta);
    }

    public void depositarDinero(Cuenta cuenta, BigDecimal monto) {


        if (cuenta.numeroCuenta() != null) {
            cuenta.depositar(monto);
            System.out.println("Depósito realizado.");
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }

    public void retirarDinero(Cuenta cuenta, BigDecimal monto) {

        if (cuenta.numeroCuenta() != null) {
            cuenta.retirar(monto);
            System.out.println("Retiro realizado.");
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }

    public void consultarSaldo(Cuenta cuenta) {
        if (cuenta.numeroCuenta() != null) {
            System.out.println("Saldo: " + cuenta.obtenerSaldo());
        } else {
            System.out.println("Cuenta no encontrada.");
        }

    }


    public List<Cuenta> getListCuentas() {
        return listCuentas;
    }

    public void mostrarMenu() {
        while (true) {
            System.out.print("***MENU PRINCIPAL***\n ");
            System.out.print("1.Crear cuenta:\n ");
            System.out.print("2.Acciones con cuentas:\n ");
            System.out.print("3.Consultar cuentas:\n ");
            System.out.print("4.Salir:\n ");

            int opc = sc.nextInt();
            if (opc == 4) {
                break;
            }
            switch (opc) {
                case 1:
                    System.out.print("Ingrese el nombre del titular:\n ");
                    String titular = sc.next();
                    System.out.print("Ingrese el tipo de cuenta que quiere crear:\n 1.Corriente \n 2.Ahorros:\n ");
                    int tipoCta = sc.nextInt();
                    Cuenta cuenta = crearCuenta(titular, tipoCta);
                    System.out.println("Cuenta creada exitosamente, #cta : " + cuenta.numeroCuenta()) ;
                    break;
                case 2:
                    acciones();
                    break;
                case 3:
                    List<Cuenta> cuentas = getListCuentas();
                    for (Cuenta cuenta1 : cuentas) {
                        System.out.println("Titular: " + cuenta1.titular());
                        System.out.println("Cuenta numero: " + cuenta1.numeroCuenta());
                        System.out.println("Saldo en cuenta: " + cuenta1.obtenerSaldo());
                    }
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

        public void acciones(){
            System.out.println("Ingrese su numero de cuenta");
            String numeroCuenta = sc.nextLine();
            List<Cuenta> cuentas = getListCuentas();
            Boolean existe = existeCuenta(cuentas, numeroCuenta);
            if (existe) {
                Cuenta cuenta = obtenerCuenta(cuentas, numeroCuenta);
                while (true) {
                    System.out.println("1. Depositar");
                    System.out.println("2. Retirar");
                    System.out.println("3. Consultar saldo");
                    System.out.println("4. Salir");
                    System.out.print("Seleccione una opción: ");
                    int opcion = sc.nextInt();
                    sc.nextLine(); // Limpia el buffer
                    if (opcion == 4) {
                        break;
                    }
                    switch (opcion) {
                        case 1:
                            System.out.print("Ingrese la cantidad a depositar: ");
                            BigDecimal cantidadDeposito = sc.nextBigDecimal();
                            depositarDinero(cuenta, cantidadDeposito);
                            break;
                        case 2:
                            System.out.print("Ingrese la cantidad a retirar: ");
                            BigDecimal cantidadRetiro = sc.nextBigDecimal();
                            retirarDinero(cuenta, cantidadRetiro);
                            break;
                        case 3:
                            consultarSaldo(cuenta);
                            break;
                        default:
                            System.out.println("Opción no válida.");
                    }
                }
            } else {
                System.out.println("Numero de cuenta no existe");
            }

        }
}

