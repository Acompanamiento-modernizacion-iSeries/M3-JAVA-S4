package Cuentas;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Banco {

    private List<Cuenta> cuentas = new ArrayList<>();

    public void Menu() {
        boolean i=true;
        do {
            int opc = Integer.parseInt(JOptionPane.showInputDialog(null, "Opciones:\n\n 1. Consultar saldo\n 2. Retirar saldo\n 3. Depositar saldo\n 4. Agregar cuenta\n 5. Salir del Sistema\n\n"));
            switch (opc) {
                case 1:
                    ConsultarSaldo();
                    break;
                case 2:
                    RetirarSaldo();
                    break;
                case 3:
                    DepositarSaldo();
                    break;
                case 4:
                    AgregarCuenta();
                    break;
                case 5:
                    JOptionPane.showMessageDialog(null, "Gracias por usar nuestros servicios");
                    i=false;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opcion Incorrecta");

            }
        }while (i == true);
     }


    public void AgregarCuenta () {
        String titular = String.valueOf(JOptionPane.showInputDialog("Nombre del Titular:\n"));
        String nrocuenta = String.valueOf(JOptionPane.showInputDialog("Ingrese Numero de cuenta:\n"));
        BigDecimal saldo = BigDecimal.valueOf(Long.parseLong(JOptionPane.showInputDialog("Ingrese Saldo actual:\n")));
        String tipocuenta = String.valueOf(JOptionPane.showInputDialog(null, "Seleccione el tipo de cuenta:\n\n 1. Cuenta Ahorros\n 2. Cuenta Corriente\n"));

        if  (tipocuenta.equals("2")) {
            Double Sobregiro = Double.parseDouble(JOptionPane.showInputDialog("Maximo Valor de Sobregiro::\n"));
            CuentaCorriente cuentaCorriente = new CuentaCorriente(titular, saldo, nrocuenta,Sobregiro);
            cuentas.add(cuentaCorriente);
            JOptionPane.showMessageDialog(null, "Cuenta Corriente Agregada");
        }
        else if (tipocuenta.equals("1"))
        {
            Double tasaInteres = Double.parseDouble(JOptionPane.showInputDialog("Ingrese Tasa de Interes:\n"));
            CuentaAhorros cuentaAhorros = new CuentaAhorros(titular,saldo,nrocuenta,tasaInteres);
            cuentas.add(cuentaAhorros);
            JOptionPane.showMessageDialog(null, "Cuenta Ahorros Agregada");
        }
        else {
            JOptionPane.showMessageDialog(null, "Tipo cuenta no valido");
        }

    }

    public void ConsultarSaldo ()
    {
        Boolean existe = false;
        String nrocuenta = String.valueOf(JOptionPane.showInputDialog("Ingrese cuenta a consultar:\n"));

        for (Cuenta cuenta: cuentas) {
            if (cuenta.consultarNroCuenta().equals(nrocuenta)){
                JOptionPane.showMessageDialog(null, "Titular: "+cuenta.consultarTitular()+"\n Nrocuenta: "+cuenta.consultarNroCuenta()+"\n Saldo: "+cuenta.consultarSaldo());
                existe = true;
            }
        }
        if (!existe){
            JOptionPane.showMessageDialog(null, "La cuenta " + nrocuenta  +  " no existe \n");
        }
    }

    public void RetirarSaldo ()
    {
        Boolean existe = false;
        String nrocuenta = String.valueOf(JOptionPane.showInputDialog("Ingrese Numero de cuenta:\n"));
        Double cantidad = Double.parseDouble(JOptionPane.showInputDialog("Cantidad a retirar: \n"));

        if (cantidad <= 0) {
            JOptionPane.showMessageDialog(null, "Cantidad no valida");
            return;
        }

        for (Cuenta cuenta: cuentas) {
            if (cuenta.consultarNroCuenta().equals(nrocuenta)) {
                cuenta.retirar(BigDecimal.valueOf(cantidad));
                existe = true;
                JOptionPane.showMessageDialog(null, "Retiro realizado exitoso, el nuevoSaldo: "+cuenta.consultarSaldo());

            }
        }
        if (!existe){
            JOptionPane.showMessageDialog(null,"Cuenta"+nrocuenta+"no existe.\n No se realiza retiro" );
        }
    }

    public void DepositarSaldo ()
    {
        Boolean existe = false;

        String nrocuenta = String.valueOf(JOptionPane.showInputDialog("Ingrese Numero de cuenta:\n"));
        Double cantidad = Double.parseDouble(JOptionPane.showInputDialog("Cantidad a depositar: \n"));

        if (cantidad <= 0) {
            JOptionPane.showMessageDialog(null,"Cantidad a depositar no valida");
            return;
        }

        for (Cuenta cuenta: cuentas) {
            if (cuenta.consultarNroCuenta().equals(nrocuenta)) {
                if (cuenta instanceof CuentaCorriente) {
                    JOptionPane.showMessageDialog(null,"Cuenta Corriente no permite deposito");
                    existe = true;
                }
                else {
                    cuenta.depositar(BigDecimal.valueOf(cantidad));
                    JOptionPane.showMessageDialog(null,"Deposito realizado exitoso, el nuevoSaldo: " + cuenta.consultarSaldo());
                    existe = true;
                }
            }
        }
        if (!existe){
            JOptionPane.showMessageDialog(null,"La cuenta " + nrocuenta  +  " No existe; por lo tanto no se realiza Depósito: \n");        }
    }

}
