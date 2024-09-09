import cuentas.Cuenta;
import cuentas.CuentaAhorros;
import cuentas.CuentaCorriente;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Banco {

    private List<Cuenta> cuentas = new ArrayList<>();
    private String opcion,titular,numeroCuenta,tipocuenta;
    private Double transaccion;
    private BigDecimal saldo;

    public void Menu(){

        do {
            opcionesMenuDefault();
            switch (opcion){
                case "C":
                     ingresarDatosCrearCuenta();
                    switch (tipocuenta){
                        case "A"://Ahorros
                            crearCuentaAhorros();
                            break;
                        case "C"://Corriente
                            crearCuentaCorriente();
                            break;
                        default:
                            MensajeGenerico("Tipo de cuenta no válido");
                    }
                    break;
                case "D":
                    depositar();
                    break;
                case "R":
                    retirar();
                    break;
                case "S":
                    ConsultarSaldo();
                    break;
                case "I":
                    interesAhorros();
                    break;
                default:
                    MensajeGenerico("Opción no válida");
                    break;
            }
        } while (SalirSiNo());

    }

    private String MostrarOpciones(String mensaje){
        return JOptionPane.showInputDialog(mensaje);
    }
    private void MensajeGenerico(String mensaje){
        JOptionPane.showMessageDialog(null,mensaje);
    }
    private boolean SalirSiNo(){
        int salirint;
        boolean salir;
        salirint = JOptionPane.showConfirmDialog(null, "¿Deseas continuar?", "Confirmación", JOptionPane.YES_NO_OPTION);
        salir = (salirint == JOptionPane.YES_OPTION);
        return salir;
    }
    public Cuenta buscarCuenta(String numeroCuenta) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                return cuenta;
            }
        }
        MensajeGenerico("Número de cuenta no encontrada");
        return null;
    }
    private void ConsultarSaldo(){
        Cuenta cuenta = buscarCuenta(numeroCuenta);
        if (cuenta == null)
            return;
        MensajeGenerico("El saldo es $"+cuenta.obtenerSaldo().toString());
    }
    private void crearCuentaAhorros(){
        Double tasaInteres = Double.parseDouble(MostrarOpciones
                ("Ingrese la tasa de interés"));
        cuentas.add(new CuentaAhorros(titular, saldo, numeroCuenta, tipocuenta, tasaInteres));
    }
    private void crearCuentaCorriente(){
        Double limiteSobregiro = Double.parseDouble(
                MostrarOpciones("Ingrese el tope de sobregiro"));
        cuentas.add(new CuentaCorriente(titular, saldo,limiteSobregiro,numeroCuenta, tipocuenta));
    }
    private void ingresarDatosCrearCuenta(){
        titular = MostrarOpciones("Ingrese el titular");
        saldo = BigDecimal.valueOf(Double.parseDouble
                (MostrarOpciones("Ingrese el saldo inicial de la cuenta")));
        tipocuenta = MostrarOpciones("A:Ahorros\nC:Corriente");
    }
    private void opcionesMenuDefault(){
        opcion = MostrarOpciones("C:Crear Cuenta\nD:Depositar\nR:Retirar" +
                "\nS:Consultar Saldo\nI:Intereses sobre cuenta ahorros");
        numeroCuenta = MostrarOpciones("Ingrese el número de cuenta");
    }
    private void depositar(){
        Cuenta cuenta = buscarCuenta(numeroCuenta);
        if (cuenta == null)
            return;
        transaccion = Double.parseDouble(MostrarOpciones("Ingrese la cantidad a Depositar"));
        cuenta.depositar(new BigDecimal(transaccion));
    }
    private void retirar(){
        Cuenta cuenta = buscarCuenta(numeroCuenta);
        if (cuenta == null)
            return;
        transaccion = Double.parseDouble(MostrarOpciones("Ingrese la cantidad a Retirar"));
        cuenta.retirar(new BigDecimal(transaccion));
    }
    private void interesAhorros(){
        Cuenta cuenta = buscarCuenta(numeroCuenta);
        if (cuenta == null)
            return;
       if (cuenta.getTipoCuenta().equals("A")){
           CuentaAhorros cuentaAho = (CuentaAhorros) cuenta;
           cuentaAho.apicarInteres();
       }else {
           MensajeGenerico("Tipo de cuenta debe ser ahorros");
       }
    }
}
