/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainGarage;

import conexion.Conexion;
import reportes.GenerarReporte;
import view.LOGGIN;

/**
 *
 * @author ivan
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static Conexion hc;

    public static void main(String[] args) {
        // TODO code application logic here
        try {
            hc = new Conexion();
            System.out.println("conectado main");
////            FrameJasper g =  new FrameJasper();
////            g.setVisible(true);
//            GenerarReporte g = new GenerarReporte();
//            g.reporte("Automóvil");
            LOGGIN login = new LOGGIN();
            login.setVisible(true);

        } catch (Exception e) {
            System.out.println("ERROR AL INICIAR " + e);
        }
    }

}
