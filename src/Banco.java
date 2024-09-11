import Cuentas.Cuenta;
import Cuentas.CuentaAhorros;
import Cuentas.CuentaCorriente;
import Utils.Util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public  class Banco {
    private List<Cuenta> cuentas;

    public Banco() {
        this.cuentas = new ArrayList<>();
    }

    private int mostrarMenuBanco(){
        Util.mostrarSeparador100();
        String separador60 =Util.obtenerSeparador60() ;

        String textoMenu= separador60
                + "\n                    Elige una Opción\n"
                +  separador60
                + "\nMenú Principal:\n"
                + "   1. Crear Cuenta Ahorro\n"
                + "   2. Crear Cuenta Corriente\n"
                + "   3. Trabajar con una Cuenta\n"
                + "   4. Calcular Interes Simple\n"
                + "   5. Calcular Interes Compuesto (1 Año)\n"
                + "   6. Verificar Elegibilidad Crediticia\n"
                + "   7. Salir\n"
                +  separador60;
        Util.mensaje(textoMenu);

        int opcionMenu = 0;
        try {
            Util.mensaje("Opción (1-7): ", true);
            opcionMenu = Util.ingresarEntero();
        }catch (IllegalArgumentException e){
            Util.mensaje(" ** Error al seleccionar una Opción: " + e.getMessage());
            opcionMenu = 0;
        }
        Util.mostrarSeparador100();
        return opcionMenu ;
    }

    private int mostrarMenuCuenta(){
        Util.mostrarSeparador100();
        String separador60 = Util.obtenerSeparador60() ;

        String textoMenu= separador60
                + "\n                    Elige una Opción\n"
                +  separador60
                + "\nMenú Principal:\n"
                + "   1. Consultar Saldo\n"
                + "   2. Realizar Depósito\n"
                + "   3. Realizar Retiro\n"
                + "   4. Salir\n"
                +  separador60;
        Util.mensaje(textoMenu);

        int opcionMenu = 0;
        try {
            Util.mensaje("Opción (1-4): ", true);
            opcionMenu = Util.ingresarEntero();
        }catch (IllegalArgumentException e){
            Util.mensaje(" ** Error al seleccionar una Opción: " + e.getMessage());
            opcionMenu = 0;
        }
        return opcionMenu ;
    }

    public void MenuBanco(){
        int opcionMenu  =  0;
        boolean continuar  = true;

        while (continuar){
            Util.mostrarSeparador100();
            opcionMenu = mostrarMenuBanco();
            switch(opcionMenu) {
                case 1:  crearCuentaAhorro();                break;
                case 2:  crearCuentaCorriente();             break;
                case 3:  trabajarConCuenta();                break;
                case 4:  calcularInteresSimple();            break;
                case 5:  calcularInteresCompuesto();         break;
                case 6:  verificarElegibilidadCredito();     break;
                case 7:  continuar = salirDelMenu();         break;
                default: continuar = opcionDeMenuNoValida(opcionMenu);
            }
        }

    }

    private void trabajarConCuenta() {
        String nroCuenta;
        int opcionMenu  =  0;
        boolean continuar  = true;

        Util.obtenerSeparador100();
        Util.mensaje("Ingrese el Numero de cuanta con el que desea Trabajar: ", true);
        nroCuenta = Util.ingresarTexto();
        Cuenta cuenta = buscarCuenta(nroCuenta);

        if (cuenta != null){
            while (continuar){
                Util.mostrarSeparador60();
                opcionMenu = mostrarMenuCuenta();
                switch(opcionMenu) {
                    case 1:  consultarSaldo(cuenta);             break;
                    case 2:  realizarDeposito(cuenta);           break;
                    case 3:  RealizarRetiro(cuenta);             break;
                    case 4:  continuar = salirDelMenu();         break;
                    default: continuar = opcionDeMenuNoValida(opcionMenu);
                }
            }

        }else{
            Util.mensaje("¡Cuenta " + nroCuenta + ", No existe!");
        }
        Util.obtenerSeparador100();
    }

    private void RealizarRetiro(Cuenta cuenta) {
        Util.mensaje("Ingrese el Monto a Retirar:  ",true);
        BigDecimal monto = Util.ingresarDecimal();
        cuenta.retirar(monto);
    }

    private void realizarDeposito(Cuenta cuenta) {
        Util.mensaje("Ingrese el Monto a Depositar:  ", true);
        BigDecimal monto = Util.ingresarDecimal();
        cuenta.depositar(monto);
    }

    private void consultarSaldo(Cuenta cuenta) {
        Util.mensaje("\nTu saldo actual es: $" + cuenta.consultarSaldo().doubleValue() + "\n");
    }

    private void crearCuentaCorriente() {
        BigDecimal saldo;
        BigDecimal sobreGiro;
        String titular ;
        String nroCuenta ;

        Util.mostrarSeparador60();
        Util.mensaje("Ingrese el Titular: ",true);
        titular = Util.ingresarTexto();
        Util.mensaje("Ingrese el numero de cuenta: ",true);
        nroCuenta = Util.ingresarTexto();
        Util.mensaje("Ingrese el Saldo Inicial: ",true);
        saldo = Util.ingresarDecimal();
        Util.mensaje("Ingrese el limite de Sobre Giro: ",true);
        sobreGiro= Util.ingresarDecimal();
        Util.mostrarSeparador60();

        CuentaCorriente cuenta = new CuentaCorriente(nroCuenta, saldo ,titular, sobreGiro);
        //cuentas.add(cuenta);

        Util.mensaje("** Cuenta Creada Exitosamente: ");
        Util.mensaje(cuenta.toString());
        Util.mostrarSeparador60();
    }

    private void crearCuentaAhorro() {
        BigDecimal saldo;
        BigDecimal tasaInteres;
        String titular ;
        String nroCuenta ;

        Util.mostrarSeparador60();
        Util.mensaje("Ingrese el Titular: ",true);
        titular = Util.ingresarTexto();
        Util.mensaje("Ingrese el numero de cuenta: ",true);
        nroCuenta = Util.ingresarTexto();
        Util.mensaje("Ingrese el Saldo Inicial: ",true);
        saldo = Util.ingresarDecimal();
        Util.mensaje("Ingrese la tasa de interes: ",true);
        tasaInteres= Util.ingresarDecimal();
        Util.mostrarSeparador60();

        CuentaAhorros cuenta = new CuentaAhorros(nroCuenta, saldo ,titular, tasaInteres);
        cuentas.add(cuenta);

        Util.mensaje("** Cuenta Creada Exitosamente: ");
        Util.mensaje(cuenta.toString());
        Util.mostrarSeparador60();
    }

    private  boolean opcionDeMenuNoValida(int opcion) {
        Util.mostrarSeparador60();
        Util.mensaje(" *** *** ALERTA - La Opción de Menú " + opcion + " NO es valida. *** ***");
        Util.mostrarSeparador60();
        return  true;
    }

    private static boolean salirDelMenu() {
        Util.mostrarSeparador60();
        Util.mensaje( "** Saliendo - Gracias por usar nuestro servicio.");
        return false;
    }

    private static void verificarElegibilidadCredito() {
        Util.mensaje("Ingrese el ingreso anual: $", true);
        BigDecimal ingresoAnual = Util.ingresarDecimal();

        Util.mensaje("Ingrese la deuda total: $", true);
        BigDecimal deudaTotal = Util.ingresarDecimal();
        boolean elegible;
        try {
            BigDecimal limite = ingresoAnual.multiply(BigDecimal.valueOf(0.5));
            elegible = ingresoAnual.compareTo(deudaTotal) > 0 && deudaTotal.compareTo(limite) <= 0;
        } catch (ArithmeticException e) {
            Util.mensaje("Error en la validación de elegibilidad para crédito: " + e.getMessage());
            elegible=  false;
        }
        System.out.println (elegible ? "Elegible para crédito." : "No elegible para crédito.");
    }

    private static void calcularInteresCompuesto() {
        Util.mensaje("Ingrese el capital:", true);
        BigDecimal capital = Util.ingresarDecimal();
        Util.mensaje("Ingrese la tasa de interes anual:", true);
        BigDecimal tasaInteres = Util.ingresarDecimal();
        Util.mensaje("Ingrese el tiempo en años:", true);
        int tiempo = Util.ingresarEntero();

        // Convertir la tasa de interés a un valor decimal dividiéndola por 100
        BigDecimal tasaInteresDecimal = tasaInteres.divide(BigDecimal.valueOf(100));

        // Calcular (1 + tasaInteres)
        BigDecimal base = BigDecimal.ONE.add(tasaInteresDecimal);

        // Calcular (1 + tasaInteres)^tiempo
        BigDecimal interesCompuesto = base.pow(tiempo);

        // Calcular capital * (1 + tasaInteres)^tiempo
        interesCompuesto = capital.multiply(interesCompuesto);

        Util.mensaje("El interes compuesto es: " + interesCompuesto.doubleValue());

    }

    private static void calcularInteresSimple() {
        Util.mensaje("Ingrese el capital:", true);
        BigDecimal capital = Util.ingresarDecimal();
        Util.mensaje("Ingrese la tasa de interes anual:", true);
        BigDecimal tasaInteres = Util.ingresarDecimal();
        Util.mensaje("Ingrese el tiempo en años:", true);
        int tiempo = Util.ingresarEntero();
        // BigDecimal interes = capital.multiply(tasaInteres).multiply(BigDecimal.valueOf(tiempo));

        BigDecimal interesSimple;
        // Convertir la tasa de interés a un valor decimal dividiéndola por 100
        BigDecimal tasaInteresDecimal = tasaInteres.divide(BigDecimal.valueOf(100));

        // Calcular el interés simple: capital * tasaInteres * tiempo
        interesSimple = capital.multiply(tasaInteresDecimal).multiply(BigDecimal.valueOf(tiempo));

        Util.mensaje("El interes simple es: " + interesSimple.doubleValue());
    }

    private Cuenta buscarCuenta(String numeroCuenta) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta.consultarNumeroCuenta().equals(numeroCuenta)) {
                return cuenta;
            }
        }
        return null;
    }

}
