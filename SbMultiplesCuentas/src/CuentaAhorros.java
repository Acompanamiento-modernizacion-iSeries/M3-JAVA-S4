import java.math.BigDecimal;
//Herencia
public class CuentaAhorros extends Cuenta{
    public Double getInteres() {
        return interes;
    }

    public void setInteres(Double interes) {
        this.interes = interes;
    }

    private Double interes;
    public CuentaAhorros(String titular, BigDecimal saldo, Integer tipoCuenta, Double interes) {
        super(titular, saldo, tipoCuenta);
        this.interes = interes;
    }

    public void calculaInteres(){
        if(obtieneTipoCuenta() == 1) {
            BigDecimal saldo = consultarSaldo();
            BigDecimal tasainteres = new BigDecimal(this.interes);
            BigDecimal cantidad = saldo.multiply(tasainteres);
            depositar(cantidad);
        }else{
            System.out.println("opcion no valida para este tipo de cuenta");
        }
    }
}
