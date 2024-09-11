import cuentas.CuentaAhorros;
import cuentas.CuentaCorriente;
import db.CuentasDB;

import java.util.Scanner;

public class Banco {
    public void opciones() {
        System.out.println("\n *** BIENVENIDO ***");

        Scanner scanner = new Scanner(System.in);
        int tipoTransacc;
        String titular = CuentasDB.agregarCuenta(Transaccion.crearCuenta(scanner));

        while (true) {
            tipoTransacc = Menu.principal(scanner);

            switch (tipoTransacc) {
                case 1:
                    titular = CuentasDB.agregarCuenta(Transaccion.crearCuenta(scanner));
                    break;
                case 2:
                    titular = Transaccion.seleccionarCuenta(scanner);
                    break;
                case 3:
                    Transaccion.consultaSaldo(CuentasDB.buscarCuenta(titular));
                    break;
                case 4:
                    Transaccion.deposito(scanner, CuentasDB.buscarCuenta(titular));
                    break;
                case 5:
                    if (CuentasDB.buscarCuenta(titular).getClass().getName().equals("cuentas.CuentaAhorros")) {
                        Transaccion.retiro(scanner, (CuentaAhorros) CuentasDB.buscarCuenta(titular));
                    } else {
                        Transaccion.retiro(scanner, (CuentaCorriente) CuentasDB.buscarCuenta(titular));
                    }
                    break;
                case 6:
                    if (CuentasDB.buscarCuenta(titular).getClass().getName().equals("cuentas.CuentaAhorros")) {
                        Transaccion.sumarInteres(scanner, (CuentaAhorros) CuentasDB.buscarCuenta(titular));
                    } else {
                        System.out.println("Lo sentimos, esta opcion solo esta disponible para Cuentas de Ahorro.\n");
                    }
                    break;
                case 7:
                    if (CuentasDB.buscarCuenta(titular).getClass().getName().equals("cuentas.CuentaCorriente")) {
                        Transaccion.consultaSobregiro((CuentaCorriente) CuentasDB.buscarCuenta(titular));
                    } else {
                        System.out.println("Lo sentimos, esta opcion solo esta disponible para Cuentas Corrientes.\n");
                    }
                    break;
                case 0:
                    System.out.println("Gracias por utilizar nuestro servicio!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Tipo de transaccion no reconocida!\n");
            }
        }
    }
}
