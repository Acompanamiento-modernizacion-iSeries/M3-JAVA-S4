import cuentas.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Banco {
    //Listado de cuentas.
    private List<Cuenta> cuentas;

    public Banco() {
        cuentas = new ArrayList<>();
    }

    //Menu principal del banco.
    public void mostrarMenu() {
        String menu = "¡Bienvenid@!\n"
                + "\nMenú Principal:\n"
                + "1. Agregar cuenta.\n"
                + "2. Depositar.\n"
                + "3. Retirar.\n"
                + "4. Consultar saldo.\n"
                + "5. Salir.\n"
                + "\nElige una opción entre (1-5):";

        while (true) {
            String opcionStr = JOptionPane.showInputDialog(menu);
            int opcion;
            try {
                opcion = Integer.parseInt(opcionStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Opción inválida. debe elegir un número entre 1 y 5.");
                continue;
            }

            switch (opcion) {
                case 1:
                    agregarCuenta();
                    break;
                case 2:
                    realizarDeposito();
                    break;
                case 3:
                    realizarRetiro();
                    break;
                case 4:
                    consultarSaldo();
                    break;
                case 5:
                    JOptionPane.showMessageDialog(null, "¡Gracias por usar nuestro sistema Bancario!");
                    return;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida. debe elegir un número entre 1 y 5.");
            }
        }
    }

    //Agregar cuenta validando datos de ingreso.
    public void agregarCuenta() {
        int tipoCuenta;
        try {
            tipoCuenta = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el tipo de cuenta (1 - Ahorros, 2 - Corriente):"));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Tipo de cuenta inválido.");
            return;
        }

        if (tipoCuenta != 1 && tipoCuenta != 2) {
            JOptionPane.showMessageDialog(null, "Tipo de cuenta inválido. Use (1 - Ahorros, 2 - Corriente).");
            return;
        }

        String titular = JOptionPane.showInputDialog("Ingrese el titular de la cuenta:");

        if (titular == null || titular.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El titular no puede estar vacío.");
            return;
        }

        String numeroCuenta = JOptionPane.showInputDialog("Ingrese el número de cuenta:");

        if (numeroCuenta == null || numeroCuenta.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El número de cuenta no puede estar vacío.");
            return;
        }else if (existeCuenta(numeroCuenta)) {
            JOptionPane.showMessageDialog(null, "La cuenta con el número " + numeroCuenta + " ya existe.");
            return;
        }

        double saldo;
        try {
            saldo = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el saldo inicial:"));
            if (saldo<0){
                JOptionPane.showMessageDialog(null, "Saldo inválido. el saldo inicial no puede ser negativo.");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Saldo inválido. Debe ingresar un número.");
            return;
        }

        switch (tipoCuenta) {
            case 1:
                double tasaInteres;
                try {
                    tasaInteres = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la tasa de interés %:"));
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Tasa de interés inválida. Debe ingresar un número.");
                    return;
                }
                Cuenta cuentaAhorros = new CuentaAhorros(titular, numeroCuenta, saldo, tasaInteres);
                agregarCuentaLista(cuentaAhorros);
                break;
            case 2:
                double limiteSobregiro;
                try {
                    limiteSobregiro = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el límite de sobregiro:"));
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Límite de sobregiro inválido. Debe ingresar un número.");
                    return;
                }
                Cuenta cuentaCorriente = new CuentaCorriente(titular, numeroCuenta, saldo, limiteSobregiro);
                agregarCuentaLista(cuentaCorriente);
                break;
        }
    }

    //Validar si una cuenta ya existe en la lista.
    public boolean existeCuenta(String numeroCuenta) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta.consultarNumeroCuenta().equals(numeroCuenta)) {
                return true;
            }
        }
        return false;
    }

    //Agregar cuenta a la lista.
    public void agregarCuentaLista(Cuenta cuenta) {
        cuentas.add(cuenta);
        JOptionPane.showMessageDialog(null, "Cuenta agregada exitosamente.");
    }

    //Deposito y sus validaciones.
    public void realizarDeposito() {
        String numeroCuenta = JOptionPane.showInputDialog("Ingrese el número de cuenta:");

        if (!existeCuenta(numeroCuenta)) {
            JOptionPane.showMessageDialog(null, "La cuenta ingresada NO existe.");
            return;
        }

        double cantidad;
        try {
            cantidad = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la cantidad a depositar:"));
            if (cantidad<0){
                JOptionPane.showMessageDialog(null, "Saldo inválido. cantidad a depositar no puede ser negativa.");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Cantidad inválida. Debe ingresar un número.");
            return;
        }
        depositar(numeroCuenta, cantidad);
    }

    //Realizar el deposito sobre la cuenta.
    public void depositar(String numeroCuenta, double cantidad) {
        Cuenta cuenta = buscarCuenta(numeroCuenta);
        if (cuenta != null) {
            try {
                cuenta.depositar(BigDecimal.valueOf(cantidad));
                JOptionPane.showMessageDialog(null, "Depósito realizado. Saldo actual: " + cuenta.consultarSaldo());
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Cuenta no encontrada.");
        }
    }

    //Retiro y sus validaciones.
    public void realizarRetiro() {
        String numeroCuenta = JOptionPane.showInputDialog("Ingrese el número de cuenta:");

        if (!existeCuenta(numeroCuenta)) {
            JOptionPane.showMessageDialog(null, "La cuenta ingresada NO existe.");
            return;
        }

        double cantidad;
        try {
            cantidad = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la cantidad a retirar:"));
            if (cantidad<0){
                JOptionPane.showMessageDialog(null, "Saldo inválido. cantidad a retirar no puede ser negativa.");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Cantidad inválida. Debe ingresar un número.");
            return;
        }
        retirar(numeroCuenta, cantidad);
    }

    //Realizar el retiro sobre la cuenta.
    public void retirar(String numeroCuenta, double cantidad) {
        Cuenta cuenta = buscarCuenta(numeroCuenta);
        if (cuenta != null) {
            try {
                cuenta.retirar(BigDecimal.valueOf(cantidad));
                JOptionPane.showMessageDialog(null, "Retiro realizado. Saldo actual: " + cuenta.consultarSaldo());
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Cuenta no encontrada.");
        }
    }

    //Buscar cuenta solicitando número.
    public void consultarSaldo() {
        String numeroCuenta = JOptionPane.showInputDialog("Ingrese el número de cuenta:");
        consultarCuenta(numeroCuenta);
    }

    //Se muestran los datos de la cuenta si se encuentra.
    public void consultarCuenta(String numeroCuenta) {
        Cuenta cuenta = buscarCuenta(numeroCuenta);
        if (cuenta != null) {
            //Verificar el tipo de cuenta usando instanceof
            String tipoCuenta = "";
            if (cuenta instanceof CuentaAhorros) {
                tipoCuenta = "Ahorros";
            } else if (cuenta instanceof CuentaCorriente) {
                tipoCuenta = "Corriente";
            }
            String mensaje = "Titular: " + cuenta.consultarTitular() +
                    "\nNúmero de cuenta "+tipoCuenta+": " + cuenta.consultarNumeroCuenta() +
                    "\nSaldo: " + cuenta.consultarSaldo();
            JOptionPane.showMessageDialog(null, mensaje);
        } else {
            JOptionPane.showMessageDialog(null, "Cuenta no encontrada.");
        }
    }

    //Busqueda de cuenta por número.
    public Cuenta buscarCuenta(String numeroCuenta) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta.consultarNumeroCuenta().equals(numeroCuenta)) {
                return cuenta;
            }
        }
        return null;
    }

}
