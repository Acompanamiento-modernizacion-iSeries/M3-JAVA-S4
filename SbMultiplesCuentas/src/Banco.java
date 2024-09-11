import java.math.BigDecimal;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Banco {
    static Scanner scanner = new Scanner(System.in);
    private static String titularBuscado;
    private static BigDecimal saldoBuscado;
    private static Integer tipoCuentaBuscada;
    private static Double interesBuscado;
    String titular;

     static List<CuentaAhorros> listaa = new ArrayList<>();
     static List<CuentaCorriente> listac = new ArrayList<>();

    public static void menu() {
        listaa.add(new CuentaAhorros("nestor", new BigDecimal(800), 1, 0.02));
        listaa.add(new CuentaAhorros("betty", new BigDecimal(500), 1, 0.03));
        listac.add(new CuentaCorriente("milena", new BigDecimal(600), 2));
        listac.add(new CuentaCorriente("oscar", new BigDecimal(500), 2));

        validadatos();
        CuentaAhorros consulta = new CuentaAhorros(titularBuscado, saldoBuscado, tipoCuentaBuscada, interesBuscado);
        CuentaCorriente consulta1 = new CuentaCorriente(titularBuscado, saldoBuscado, tipoCuentaBuscada);

        while (true){
            //CuentaAhorros consulta = new CuentaAhorros(titularBuscado, saldoBuscado, tipoCuentaBuscada, interesBuscado);
            System.out.println("\n Seleccione opcion: \n");
            System.out.println("1. Consultar saldo. ");
            System.out.println("2. Realizar depósito. ");
            System.out.println("3. Realizar retiro. ");
            System.out.println("4. Calcular interes. ");
            System.out.println("5. Cambiar cuenta. ");
            System.out.println("6. Salir. \n");
            System.out.println("Opcion:");
            Integer Op = scanner.nextInt();

            switch (Op) {
                case 1:
                     if(tipoCuentaBuscada == 1){
                        consulta.consultar();
                    }else{
                        consulta1.consultar();
                    }
                    break;
                case 2:
                    System.out.println("\n Valor deposito: ");
                    Double valor = scanner.nextDouble();
                    if (tipoCuentaBuscada == 1) {
                        consulta.depositar(BigDecimal.valueOf(valor));
                    }else{
                        consulta1.depositar(BigDecimal.valueOf(valor));
                    }
                    break;
                case 3:
                    System.out.println("\n Valor retiro: ");
                    valor = scanner.nextDouble();
                    if (tipoCuentaBuscada == 1) {
                        consulta.retirar(BigDecimal.valueOf(valor));
                    }else{
                        consulta1.retirar(BigDecimal.valueOf(valor));
                    }
                    break;
                case 4:
                    if (tipoCuentaBuscada == 1) {
                        consulta.calculaInteres();
                    }else{
                        System.out.println("Proceso no habilitado para este tipo de cuenta");
                    }
                    break;
                case 5:
                    validadatos();
                    consulta = new CuentaAhorros(titularBuscado, saldoBuscado, tipoCuentaBuscada, interesBuscado);
                    consulta1 = new CuentaCorriente(titularBuscado, saldoBuscado, tipoCuentaBuscada);
                    break;
                case 6:
                    System.out.println("Fin proceso");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opcion invalida \n");
            }
        }

    }

    public static boolean validadatos(){
        while (true) {
            System.out.println("\nIngresa el nombre del titular:");
            titularBuscado = scanner.next();
            boolean encontrado = false;
            for (CuentaAhorros t : listaa) {
                if (t.consultaTitular().equalsIgnoreCase(titularBuscado)) { // Comparar ignorando mayúsculas/minúsculas
                    saldoBuscado = t.consultarSaldo();
                    tipoCuentaBuscada = t.obtieneTipoCuenta();
                    interesBuscado = t.getInteres();

                    encontrado = true;
                    break; // Salir del ciclo una vez encontrado
                }
            }
            if (!encontrado) {
                for (CuentaCorriente t : listac) {
                    if (t.consultaTitular().equalsIgnoreCase(titularBuscado)) { // Comparar ignorando mayúsculas/minúsculas
                        saldoBuscado = t.consultarSaldo();
                        tipoCuentaBuscada = t.obtieneTipoCuenta();
                        encontrado = true;
                        break; // Salir del ciclo una vez encontrado
                    }
                }
            }
            if (!encontrado){
                System.out.println("Titular no encontrado");
            }
            else {
                break;
            }
        }
        return false;
    }
}

