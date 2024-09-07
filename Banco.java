package cuentas;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Banco {
    private List<CuentaAhorros> cuentaAhorrosList;
    private List<CuentaCorriente> cuentaCorrienteList;

    public  Banco(){
        List<CuentaAhorros> cuentaAhorrosList = new ArrayList<>();
        List<CuentaCorriente> cuentaCorrienteList = new ArrayList<>();
        setCuentaAhorrosList(cuentaAhorrosList);
        setCuentaCorrienteList(cuentaCorrienteList);
    }


    public boolean encontrarcuentaAhorro(BigInteger nrocuenta){
        boolean encontro = false;
        if (this.cuentaAhorrosList != null) {
            for (CuentaAhorros cuentaAhorrosbuscada : this.cuentaAhorrosList) {
                if (cuentaAhorrosbuscada.getNrocuenta().compareTo(nrocuenta) == 0) {
                    encontro = true;
                    break;
                }
            }
        }
        return encontro;
    }
    public boolean encontrarcuentaCorriente(BigInteger nrocuenta){
        boolean encontro = false;
        if (this.cuentaAhorrosList != null) {
            for (CuentaCorriente cuentaCorrientebuscada : this.cuentaCorrienteList) {
                if (cuentaCorrientebuscada.getNrocuenta().compareTo(nrocuenta) == 0) {
                    encontro = true;
                    break;
                }
            }
        }
        return encontro;
    }
    public boolean adicinarcuenta(String titular, double saldo, double nrocuenta, double tasaosobregiro, int edad, int tipocuenta  ){
        BigDecimal valor;
        boolean retor ;
        valor = new BigDecimal(tasaosobregiro);
        if (tipocuenta == 0){
            CuentaAhorros cuentaAhorros = new CuentaAhorros(titular, BigDecimal.valueOf(saldo) , BigDecimal.valueOf(nrocuenta).toBigInteger(), valor, edad );
            retor= addicionarCuentaAhorro(cuentaAhorros);
        }else{
            CuentaCorriente cuentaCorriente = new CuentaCorriente(titular, BigDecimal.valueOf(saldo) , BigDecimal.valueOf(nrocuenta).toBigInteger(), valor, edad );
            retor= addicionarCuentaCoriente(cuentaCorriente);
        }
        return retor;
    }
    public boolean addicionarCuentaAhorro(CuentaAhorros cuentaAhorros){
        if (encontrarcuentaAhorro(cuentaAhorros.getNrocuenta())){
            return false;
        }else {
            this.cuentaAhorrosList.add(cuentaAhorros);
            return true;
        }

    }

    public boolean addicionarCuentaCoriente(CuentaCorriente cuentaCorriente){
        if(encontrarcuentaCorriente(cuentaCorriente.getNrocuenta())){
            return false;
        }else {
            this.cuentaCorrienteList.add(cuentaCorriente);
            return true;
        }
    }
    public boolean depositarcuentaahorro(BigInteger nrocuenta, BigDecimal saldo){
        boolean actualizo = false;
        for (int x = 0; x < cuentaAhorrosList.size(); x++) {
            CuentaAhorros cuentaAhorrosbuscada = cuentaAhorrosList.get(x);
            if (cuentaAhorrosbuscada.getNrocuenta().compareTo(nrocuenta) == 0 ){
                cuentaAhorrosbuscada.depositar(saldo);
                cuentaAhorrosList.set(x,cuentaAhorrosbuscada);
                actualizo= true;
            }
        }
        return actualizo;
    }
    public boolean depositarcuentacorriente(BigInteger nrocuenta, BigDecimal saldo){
        boolean actualizo = false;
        for (int x = 0; x < cuentaCorrienteList.size(); x++) {
            CuentaCorriente cuentaCorrientebuscada = cuentaCorrienteList.get(x);
            if (cuentaCorrientebuscada.getNrocuenta().compareTo(nrocuenta) == 0 ){
                cuentaCorrientebuscada.depositar(saldo);
                cuentaCorrienteList.set(x,cuentaCorrientebuscada);
                actualizo= true;
            }
        }
        return actualizo;
    }
///
public boolean depositar(BigInteger nrocuenta, BigDecimal saldo,  int tipocuenta ){
    if (tipocuenta == 0){
        return depositarcuentaahorro(nrocuenta, saldo) ;

    }else if(tipocuenta == 1){
        return depositarcuentacorriente(nrocuenta, saldo) ;
    }else{
        return false;
    }

}
    ////
    public boolean retirarcuentaahorro(BigInteger nrocuenta, BigDecimal saldo){
        boolean actualizo = false;
        for (int x = 0; x < cuentaAhorrosList.size(); x++) {
            CuentaAhorros cuentaAhorrosbuscada = cuentaAhorrosList.get(x);
            if (cuentaAhorrosbuscada.getNrocuenta().compareTo(nrocuenta) == 0 ){
                cuentaAhorrosbuscada.retirar(saldo);
                cuentaAhorrosList.set(x,cuentaAhorrosbuscada);
                actualizo= true;
            }
        }
        return actualizo;
    }
    public boolean retirarcuentacorriente(BigInteger nrocuenta, BigDecimal saldo){
        boolean actualizo = false;
        for (int x = 0; x < cuentaCorrienteList.size(); x++) {
            CuentaCorriente cuentaCorrientebuscada = cuentaCorrienteList.get(x);
            if (cuentaCorrientebuscada.getNrocuenta().compareTo(nrocuenta) == 0 ){
                cuentaCorrientebuscada.retirar(saldo);
                cuentaCorrienteList.set(x,cuentaCorrientebuscada);
                actualizo= true;
            }
        }
        return actualizo;
    }
    public boolean retirarsaldo(BigInteger nrocuenta, BigDecimal saldo,  int tipocuenta ){
        if (tipocuenta == 0){
            return retirarcuentaahorro(nrocuenta, saldo) ;

        }else if(tipocuenta == 1){
            return retirarcuentacorriente(nrocuenta, saldo) ;
        }else{
            return false;
        }

    }
////
public BigDecimal consultarsaldocuentaahorro(BigInteger nrocuenta){
    BigDecimal saldoactual = new BigDecimal("0.0");
    for (CuentaAhorros cuentaAhorrosbuscada : cuentaAhorrosList) {
        if (cuentaAhorrosbuscada.getNrocuenta().compareTo(nrocuenta) == 0) {
            saldoactual = cuentaAhorrosbuscada.obtenerSaldo();
        }
    }
    return saldoactual;
}
    public BigDecimal consultarsaldocuentacorriente(BigInteger nrocuenta){
        BigDecimal saldoactual = new BigDecimal("0.0");
        for (CuentaCorriente cuentaCorrientebuscada : cuentaCorrienteList) {
            if (cuentaCorrientebuscada.getNrocuenta().compareTo(nrocuenta) == 0) {
                saldoactual = cuentaCorrientebuscada.obtenerSaldo().add(cuentaCorrientebuscada.getLimiteSobregiro());
            }
        }
        return saldoactual;
    }
    ////
    public BigDecimal consultarsaldo(BigInteger nrocuenta, int tipocuenta){
        if (tipocuenta == 0){
            return consultarsaldocuentaahorro(nrocuenta) ;

        }else if(tipocuenta == 1){
            return consultarsaldocuentacorriente(nrocuenta) ;
        }else{
           return null;
        }

    }
    public CuentaAhorros retornacuentaAhorro(BigInteger nrocuenta){
        CuentaAhorros cuentaAhorrosbuscada = new CuentaAhorros();

        for (CuentaAhorros cuentaAhorros : cuentaAhorrosList) {
            cuentaAhorrosbuscada = cuentaAhorros;
            if (cuentaAhorrosbuscada.getNrocuenta().compareTo(nrocuenta) == 0) {

                break;
            }
        }
        return cuentaAhorrosbuscada;

    }
    public CuentaCorriente retornacuentaCorriente(BigInteger nrocuenta){
        CuentaCorriente cuentabuscada = new CuentaCorriente();
        for (CuentaCorriente cuentaCorriente : cuentaCorrienteList) {
            cuentabuscada = cuentaCorriente;
            if (cuentabuscada.getNrocuenta().compareTo(nrocuenta) == 0) {
                break;
            }
        }
        return cuentabuscada;
    }


    public void setCuentaAhorrosList(List<CuentaAhorros> cuentaAhorrosList) {
        this.cuentaAhorrosList = cuentaAhorrosList;
    }



    public void setCuentaCorrienteList(List<CuentaCorriente> cuentaCorrienteList) {
        this.cuentaCorrienteList = cuentaCorrienteList;
    }
}
