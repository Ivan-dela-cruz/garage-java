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
public class ReporteClientesMensual {

    /*
    ci varchar(50) primary key NOT NULL,
  nombre text NOT NULL,
  apellido text NOT NULL,
  telefono text,
  direccion text,
  placa text NOT NULL,
  modelo text,
  color text,
  cod_barras varchar(200)
     */
    private String cod_barras;
    private String ci;
    private String nombre;
    private String apellido;
    private String direccion;
    private String placa;
    private String modelo;
    private String color;

    public ReporteClientesMensual(String cod_barras, String ci, String nombre, String apellido, String direccion, String placa, String modelo, String color) {
        this.cod_barras = cod_barras;
        this.ci = ci;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.placa = placa;
        this.modelo = modelo;
        this.color = color;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
    

}
