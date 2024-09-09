import cuentas.Cuenta;
import cuentas.CuentaAhorros;
import cuentas.CuentaCorriente;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Banco {

    Scanner scanner = new Scanner(System.in);
    Boolean continuar = true;
    List<Cuenta> cuentasCreadas = new ArrayList<>();

    public Banco(){
    }

    public void iniciar(){
        while (continuar) {
                mostrarMenu();
                switch (seleccionarOpcion()) {
                    case 1:
                        crearCuenta();
                        break;
                    case 2:
                        if(cuentasCreadas.size() == 0){
                            mostrarMensaje("Primero debe crear una cuenta");
                            break;
                        }
                        mostrarMensaje("Ingrese el valor a depositar: ");
                        if (scanner.hasNextBigDecimal()) {
                            BigDecimal deposito = scanner.nextBigDecimal();
                            cuentasCreadas.get(cuentasCreadas.size()-1).depositar(deposito);
                            break;
                        } else {
                            mostrarMensaje("Valor no valido");
                            scanner.next();
                            break;
                        }
                    case 3:
                        if(cuentasCreadas.size() == 0){
                            mostrarMensaje("Primero debe crear una cuenta");
                            break;
                        }
                        mostrarMensaje("Ingrese el valor a retirar: ");
                        if (scanner.hasNextBigDecimal()) {
                            BigDecimal retiro = scanner.nextBigDecimal();
                                cuentasCreadas.get(cuentasCreadas.size()-1).retirar(retiro);
                                break;
                        } else {
                            mostrarMensaje("Valor no valido");
                            scanner.next();
                            break;
                        }
                    case 4:
                        if(cuentasCreadas.size() == 0){
                            mostrarMensaje("Primero debe crear una cuenta");
                            break;
                        }
                        System.out.println("Tú saldo actual es de: " + cuentasCreadas.get(cuentasCreadas.size()-1).obtenerSaldo());
                        break;
                    case 5:
                        if(cuentasCreadas.size() == 0){
                            mostrarMensaje("Primero debe crear una cuenta");
                            break;
                        }
                        cuentasCreadas.get(cuentasCreadas.size()-1).aplicarInteres();
                        break;
                    case 6:
                        mostrarMensaje("Gracias por usar nuestro servicio");
                        continuar = false;
                        break;
                    case 0:
                        mostrarMensaje("Esta opción no existe en el menú");
                        break;
                    default:
                        mostrarMensaje("Opción no valida");
                }
        }
    }

    public void crearCuenta(){
        System.out.println("Ingrese el nombre del titular de la cuenta: ");
        String nombre = scanner.nextLine();
        if(nombre.isEmpty()){
            nombre = scanner.nextLine();
        }
        System.out.println("Ingrese el saldo inicial de la cuenta: ");
        if (scanner.hasNextBigDecimal()) {
            BigDecimal saldoInicial = scanner.nextBigDecimal();
            System.out.println("Para cuenta de ahorros digite 1, para corriente 2: ");
            if (scanner.hasNextBigDecimal()) {
                int tipoCuenta = scanner.nextInt();
                if (tipoCuenta == 1) {
                    CuentaAhorros cuenta = new CuentaAhorros(nombre, saldoInicial, 0.15,  "Ahorros");
                    cuentasCreadas.add(cuenta);
                }else {
                    CuentaCorriente cuenta = new CuentaCorriente(nombre, saldoInicial, 100.0,  "Corriente", 0.15);
                    cuentasCreadas.add(cuenta);
                }
                System.out.println("Cuenta creada con éxito para el cliente " + cuentasCreadas.get(cuentasCreadas.size()-1).titular());
            }else {
                mostrarMensaje("Valor no valido");
                scanner.next();
            }
        }else{
            mostrarMensaje("Valor no valido");
            scanner.next();
        }
    }

    public static void mostrarMenu(){
        System.out.println("===========================Bienvenido al sistema Bancario BANK===========================");
        System.out.println("=========================================================================================");
        System.out.println("===== Como cliente preferencial cuenta con una tasa de interes 0.15 sobre su saldo ======");
        System.out.println("=========================================================================================");
        System.out.println("=====================Si desea crear una cuenta indique la opción 1 ======================");
        System.out.println("=====================Si desea realizar Depósitos indique la opción 2 ====================");
        System.out.println("=====================Si desea realizar Retiros indique la opción 3 ======================");
        System.out.println("=====================Si desea consultar su saldo indique la opción 4 ====================");
        System.out.println("=====================Si desea calcular intereses sobre el saldo indique la opción 5 =====");
        System.out.println("=====================Si desea salir del sistema indique la opción 6 =====================");
        System.out.println("=========================================================================================");
    }

    public static int seleccionarOpcion(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese la opción que desea usar ");
        int opcion = 0;
        if (scanner.hasNextBigDecimal()) {
            opcion = scanner.nextBigDecimal().intValue();
        } else {
            scanner.next();
        }
        return opcion;
    }

    public static void mostrarMensaje(String mensaje){
        System.out.println(mensaje);
    }

}
