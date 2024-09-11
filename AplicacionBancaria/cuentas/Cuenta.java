package cuentas;

import java.math.BigDecimal;


    public abstract class Cuenta {
        private String titular;
        private BigDecimal saldo;
        private String numeroCuenta;

        public Cuenta(String titular,String numeroCuenta) {
            this.titular = titular;
            this.saldo = new BigDecimal(0);
            this.numeroCuenta = numeroCuenta;
        }

        public Cuenta(){

        }




        public Cuenta(String titular, BigDecimal saldo, String numeroCuenta) {
            this.titular = titular;
            this.saldo = saldo;
            this.numeroCuenta = numeroCuenta;
        }

        public abstract void mostrarInformacion();

        public void depositar(BigDecimal cantidad) {
            saldo = saldo.add(cantidad);
        }

        public void retirar(BigDecimal cantidad) {
            BigDecimal saldo = obtenerSaldo();
            if (saldo.compareTo(cantidad) >= 0) {
                this.saldo = saldo.subtract(cantidad);
            } else {
                System.out.println("No hay fondos suficientes");
            }
        }

        public String titular() {
            return titular;
        }

        public void asignarTitutlar(String titular) {
            this.titular = titular;
        }

        public String numeroCuenta() {
            return numeroCuenta;
        }

        public BigDecimal obtenerSaldo() {
            return saldo;
        }

        public void setSaldo(BigDecimal saldo) {
            this.saldo = saldo;
        }


    }

