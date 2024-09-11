package Cuentas;

import java.math.BigDecimal;

    public class CuentaCorriente extends Cuenta {
        private BigDecimal limiteSobregiro;

        public CuentaCorriente(String titular, BigDecimal saldo, String nrocuenta, Double limiteSobregiro) {
            super(titular, saldo, nrocuenta );
            this.limiteSobregiro = new BigDecimal(limiteSobregiro);
        }

        public BigDecimal consultaLimiteSobregiro() {
            return limiteSobregiro;
        }

        public void colocarLimiteSobregiro(BigDecimal limiteSobregiro)
            {
            this.limiteSobregiro = limiteSobregiro;
        }

        @Override
        public void retirar(BigDecimal cantidad) {
            BigDecimal saldoSobregiro = consultarSaldo().add(limiteSobregiro);
            if (saldoSobregiro.compareTo(cantidad) >= 0 ){
                BigDecimal diferencia = consultarSaldo().subtract(cantidad);
                if (diferencia.compareTo(BigDecimal.ZERO) < 0 ){
                    super.colocarSaldo(diferencia);

                }
                else {
                    super.retirar(cantidad);
                }


            }
            else {
                System.out.println("Excede límite de sobregiro");
            }
        }
    }


