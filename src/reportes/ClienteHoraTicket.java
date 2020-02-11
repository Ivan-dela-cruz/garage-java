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
public class ClienteHoraTicket {

    private String ci;

    private String placa;
    private String modelo;
    private String fecha;
    private String entrada;

    public ClienteHoraTicket(String ci, String placa,String modelo, String fecha, String entrada) {
        this.ci = ci;
        this.placa = placa;
         this.modelo = modelo;
        this.fecha = fecha;
        this.entrada = entrada;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
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

}
