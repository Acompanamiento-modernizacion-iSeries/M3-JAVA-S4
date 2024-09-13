import cuentas.Cuenta;
import cuentas.CuentaAhorros;
import cuentas.CuentaCorriente;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Banco{
    private static String nombre;
    private static String cuenta;
    private static BigDecimal saldo ;
    private static Integer periodos;
    private static boolean control ;
    public static int opcion = 0;
    public static String numCuenta;
    public static Scanner scanner = new Scanner(System.in);
    List<Cuenta> cuentas = new ArrayList<>();

    public void iniciar(){
        control = true ;
        menuPrincipal();
        do {
            switch (opcionMenu()) {
                case 1: // GESTION DE CUENTAS
                    menuGestionCuentas();
                    switch (opcionMenu()){
                        case 1: // ADICIONAR CUENTA
                            addCuenta();
                            break;
                        case 2: // LISTAR CUENTAS
                            if(cuentas.size() == 0){
                                System.out.println("No existen cuentas");
                            }else{
                                for (Cuenta cuenta : cuentas) {
                                    System.out.println(cuenta);
                                }
                            }
                            break;
                        case 3: //MENU ANTERIOR
                            menuPrincipal();
                            break;
                        default:
                            System.out.println("La opción seleccionada es invalida.");
                            break;
                    }
                    break;
                case 2: //TRANSACCIONES
                    menuTransacciones();
                    switch (opcionMenu()){
                        case 1: // SALDO
                            scanner.nextLine();
                            System.out.print("Numero de cuenta     : ");
                            numCuenta = scanner.nextLine();
                            consultarSaldo(numCuenta);
                            break;
                        case 2: //DEPOSITOS
                            scanner.nextLine();
                            System.out.print("Numero de cuenta     : ");
                            numCuenta = scanner.nextLine();
                            if(cuentas.size() == 0){
                                System.out.println("No existen cuentas.");
                                break;
                            }
                            System.out.print("Valor deposito: ");
                            for (Cuenta cuenta : cuentas) {
                                if (cuenta.getNumeroCuenta().equals(numCuenta)) {
                                    if (scanner.hasNextBigDecimal()) {
                                        BigDecimal deposito = scanner.nextBigDecimal();
                                        cuenta.depositar(deposito);
                                    } else {
                                        System.out.println("El valor ingresado no es valido");
                                        scanner.next();
                                    }
                                }
                            }
                            break;
                        case 3: //RETIRO
                            scanner.nextLine();
                            System.out.print("Numero de cuenta     : ");
                            numCuenta = scanner.nextLine();
                            if(cuentas.size() == 0){
                                System.out.println("No existen cuentas.");
                            }
                            System.out.print("Valor retiro: ");
                            for (Cuenta cuenta : cuentas) {
                                if (cuenta.getNumeroCuenta().equals(numCuenta)) {
                                    if (scanner.hasNextBigDecimal()) {
                                        BigDecimal retiro = scanner.nextBigDecimal();
                                        cuenta.retirar(retiro);
                                    } else {
                                        System.out.println("El valor ingresado no es valido");
                                        scanner.next();
                                    }
                                }
                            }
                            break;
                        case 4: //MENU ANTERIOR
                            menuPrincipal();
                            break;
                        default:
                            System.out.println("La opción seleccionada es invalida.");
                            break;
                    }
                    break;
                case 3:  // CALCULAR INTERES
                    scanner.nextLine();
                    System.out.print("Numero de cuenta     : ");
                    numCuenta = scanner.nextLine();
                    if(cuentas.size() == 0){
                        System.out.println("No existen cuentas.");
                        break;
                    }
                    for (Cuenta cuenta : cuentas) {
                        if (cuenta.getNumeroCuenta().equals(numCuenta)) {
                            cuenta.aplicarInteres();
                        }
                    }
                    break;
                case 4: //VIABILIDAD CREDITICIA
                        viabilidadCrediticia();
                        break;
                default:
                    System.out.println("La opción seleccionada es invalida.");
                    break;
            }
            System.out.print("Desea realizar otra operacion (S/N) ?  ");
            String finPrograma = scanner.next();
            switch (finPrograma.toUpperCase()) {
                case "S":
                    menuPrincipal();
                    control = true;
                    break;
                case "N":
                    System.out.println("GRACIAS POR USAR NUESTROS SERVICIOS !!!");
                    control = false;
                    break;
                default:
                    System.out.println("Opcion Invalida. Debe seleccions S o N");
                    break;
            }
        }while (control == true);
    }
    public static int opcionMenu(){
        //Scanner scanner = new Scanner(System.in);
        System.out.print("\n\nSeleccione Opcion :");
        if (scanner.hasNextBigDecimal()) {
            opcion = scanner.nextBigDecimal().intValue();
        } else {
            scanner.next();
        }
        return opcion;
    }
    private static void menuPrincipal(){
        System.out.print(" TIPO OPERACION \n");
        System.out.print("1. Gestion de cuentas\n2. Transacciones (Saldo / Depósitos / Retiros)\n3. Cálculo de interés\n4. Viabilidad crediticia");
    }
    private static void menuGestionCuentas(){
        System.out.print(" GESTION DE CUENTAS \n");
        System.out.print("1. Crear cuenta\n2. Listar cuentas \n3. Menú anterior");
    }
    private static void menuTransacciones(){
        System.out.print(" TRANSACCIONES \n");
        System.out.print("1. Consultar saldo\n2. Depósitos\n3. Retiros\n4. Menú anterior");
    }
    private static void menuCalculoInteres(){
        System.out.print(" CALCULAR INTERES \n");
        System.out.print("1. Interés corriente\n2. Interés compuesto\n3. Menú anterior");
    }
    public void addCuenta(){
        scanner.nextLine();
        System.out.print("Numero de cuenta     : ");
        numCuenta = scanner.nextLine();
        if(numCuenta == null){
            numCuenta = scanner.nextLine();
        }
        System.out.print("Titular de la cuenta : ");
        String nombre = scanner.nextLine();
        if(nombre.isEmpty()){
            nombre = scanner.nextLine();
        }
        System.out.print("Saldo inicial        : ");
        if (scanner.hasNextBigDecimal()) {
            BigDecimal saldoInicial = scanner.nextBigDecimal();
            System.out.println("Tipo de cuenta       : \n1. Cuenta de Ahorros\n 2.Cuenta corriente ");
            if (scanner.hasNextBigDecimal()) {
                int tipoCuenta = scanner.nextInt();
                if (tipoCuenta == 1) {
                    CuentaAhorros cuenta = new CuentaAhorros(numCuenta,nombre, saldoInicial, 0.15,  "Ahorros");
                    cuentas.add(cuenta);
                }else {
                    CuentaCorriente cuenta = new CuentaCorriente(numCuenta, nombre, saldoInicial, 100.0,  "Corriente", 0.15);
                    cuentas.add(cuenta);
                }
                System.out.println("CUENTA CREADA");
            }else {
                System.out.println("Valor no valido");
                scanner.next();
            }
        }else{
            System.out.println("Valor no valido");
            scanner.next();
        }
    }
    public void consultarSaldo(String numeroCuenta) {
        if(numCuenta == null){
            System.out.println("La cuenta no puede estar en blanco"   );
            numCuenta = scanner.nextLine();
        }else{
            for (Cuenta cuenta : cuentas) {
                if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                    System.out.println("Saldo de la cuenta " + numeroCuenta + ": $" + cuenta.mostrarSaldo());
                    return;
                }
            }
            System.out.println("Cuenta no encontrada.");
        }
    }
    public static void viabilidadCrediticia(){
        if (saldo.compareTo(BigDecimal.valueOf(3000)) == 1){
            System.out.println("Es elegible para crédito");
        }else{
            System.out.println("No es elegible para crédito");
        }
    }
}
