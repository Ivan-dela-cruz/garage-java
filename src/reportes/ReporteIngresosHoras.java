/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes;

/**
 *
 * @author ivan
 */
public class ReporteIngresosHoras {
    private String cod_barras;
    private String ci;
    private String placa;
    private String fecha;
    private String entrada;
    private String salida;
    private String horas;
    private String valor;

    public ReporteIngresosHoras(String cod_barras, String ci,  String placa, String fecha, String entrada, String salida, String horas, String valor) {
        this.cod_barras = cod_barras;
        this.ci = ci;
        
        this.placa = placa;
        this.fecha = fecha;
        this.entrada = entrada;
        this.salida = salida;
        this.horas = horas;
        this.valor = valor;
    }
    

    public String getCod_barras() {
        return cod_barras;
    }

    public void setCod_barras(String cod_barras) {
        this.cod_barras = cod_barras;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

   

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public String getSalida() {
        return salida;
    }

    public void setSalida(String salida) {
        this.salida = salida;
    }

    public String getHoras() {
        return horas;
    }

    public void setHoras(String horas) {
        this.horas = horas;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
    
}
