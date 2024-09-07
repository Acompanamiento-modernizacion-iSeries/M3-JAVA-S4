package modelo;

import java.math.BigDecimal;

public class CuentaCorriente extends Cuenta{

    private BigDecimal sobreGiro;

    public BigDecimal getSobreGiro() {
        return sobreGiro;
    }

    public void setSobreGiro(BigDecimal sobreGiro) {
        this.sobreGiro = sobreGiro;
    }

    public BigDecimal ingresarSobreGiro(BigDecimal valor){
        this.sobreGiro = this.sobreGiro.add(valor);
        return this.sobreGiro;
    }

    public BigDecimal consultarSobreGiro(){
        return this.sobreGiro;
    }
}
