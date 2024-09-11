package Utils;

import java.math.BigDecimal;
import java.util.Scanner;

public class Util {
    private static String  separador100 = "-".repeat(100);
    private static String  separador60 = "-".repeat(60);
    private static Scanner scanner = new Scanner(System.in);

    public static void  mensaje(String texto, boolean sinSaltoDeLinea ){
        if (sinSaltoDeLinea) {
            System.out.print(texto);
        }else {
            System.out.println(texto);
        }
    }

    public static void  mensaje(String texto){
        mensaje(texto ,false);
    }

    public static String ingresarTexto(){
        String dato = scanner.nextLine();
        return dato;
    }

    public static Integer ingresarEntero(){
        Integer dato = scanner.nextInt();
        scanner.nextLine();
        return dato;
    }

    public static BigDecimal ingresarDecimal (){
        BigDecimal dato = scanner.nextBigDecimal();
        scanner.nextLine();
        return dato;
    }

    public static void mostrarSeparador60 (){
        mensaje(separador60);
    }

    public static void mostrarSeparador100 (){
        mensaje(separador100);
    }

    public static String obtenerSeparador100 (){
        return separador100;
    }

    public static String obtenerSeparador60 (){
        return separador60;
    }

}
