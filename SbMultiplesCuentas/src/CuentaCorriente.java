import java.math.BigDecimal;
//herencia se usa extends
public class CuentaCorriente extends Cuenta{
    private BigDecimal prSobregiro = BigDecimal.valueOf(0.1);
    private Integer wErr = 0;
    public CuentaCorriente(String titular, BigDecimal saldo, Integer tipoCuenta) {
        super(titular, saldo, tipoCuenta);
        this.prSobregiro = prSobregiro;
        this.wErr = wErr;
    }
    public void CalcularIntSobregiro(){
        BigDecimal saldo = consultarSaldo();
        if(saldo.compareTo(BigDecimal.valueOf(0))< 0){
            if(wErr == 0) {
                BigDecimal tasasobregiro = prSobregiro;
                BigDecimal cantidad = saldo.multiply(tasasobregiro);
                depositar(cantidad);
            }
        }
    }

    //polimorfismo- se llama igual el metodo pero se usa diferente
    @Override
    public void retirar(BigDecimal valor) {
        if (obtieneTipoCuenta() == 2) {
            BigDecimal saldo = consultarSaldo();
            BigDecimal saldoSg = saldo.add(saldo.multiply(prSobregiro));
            BigDecimal total = saldo.subtract(valor);
            if (valor.compareTo(saldoSg) <= 0 && valor.compareTo(saldo) > 0) {
                System.out.println("Se realiza retiro, Cuenta queda en sobregiro");
                creaSaldo(total);
                wErr = 0;
            } else if (valor.compareTo(saldo) <= 0) {
                System.out.println("Se realiza retiro");
                creaSaldo(total);
                wErr = 0;
            } else {
                System.out.println("No se puede realizar retiro, excede sobregiro");
                wErr = 1;
            }
        }else{
            System.out.println("Proceso no valido para este tipo de cuenta");
        }
    }
}
