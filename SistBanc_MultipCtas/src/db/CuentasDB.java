package db;

import cuentas.Cuenta;

import java.util.HashMap;
import java.util.Map;

public class CuentasDB {

    private static Map<String,Cuenta> listaCuentas = new HashMap<>();

    public static String agregarCuenta(Cuenta cuenta) {
        String titular = cuenta.consultarTitular().toLowerCase();
        listaCuentas.put(titular, cuenta);
        return titular;
    }

    public static Cuenta buscarCuenta(String titular) {
        return listaCuentas.get(titular);
    }
}
