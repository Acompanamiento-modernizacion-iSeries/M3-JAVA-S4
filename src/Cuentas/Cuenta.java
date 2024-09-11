package Cuentas;

import java.math.BigDecimal;

      public abstract class Cuenta {
        private String titular;
        private BigDecimal saldo;
        private String nrocuenta;
        public Cuenta(String titular, BigDecimal saldo, String nrocuenta) {
            this.titular = titular;
            this.saldo = saldo;
            this.nrocuenta = nrocuenta;
        }

        public String consultarTitular() {
            return titular;
        }

        public void colocarTitular(String titular) {
            this.titular = titular;
        }

        public BigDecimal consultarSaldo() {
            return saldo;
        }

        public void colocarSaldo (BigDecimal saldo) {
            this.saldo = saldo;
        }
        public String consultarNrocuenta() {

            return nrocuenta;
        }

        public void colocarNrocuenta(String nrocuenta) {
            this.nrocuenta = nrocuenta;
        }


        public void depositar(BigDecimal cantidad) {
            saldo = saldo.add(cantidad);
        }

        public void retirar(BigDecimal cantidad) {
            if (saldo.compareTo(cantidad) >= 0 ){
                this.saldo = saldo.subtract(cantidad);
            }
            else {
                System.out.println("No hay fondos suficientes");
            }
        }

        }


