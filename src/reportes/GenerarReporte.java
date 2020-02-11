/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import mainGarage.Main;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author ivan
 */
public class GenerarReporte {
    public void reporte(String idusuario) {
        try {
            JasperReport reporte = (JasperReport) JRLoader.loadObject("ReporteClientesRegistrados.jasper");
            Map parametro = new HashMap();
             parametro.put("modelo", idusuario);
             
             JasperPrint j = JasperFillManager.fillReport(reporte, parametro, Main.hc.sql);
             JasperViewer jv = new JasperViewer(j,false);
             jv.setTitle("Reporte Clientes Registrados");
             jv.setVisible(true);
             
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Erros al mostrar :  "+ e);
        }
    }
    public void reporteClientesModelo(String vacio) {
        try {
            JasperReport reporte = (JasperReport) JRLoader.loadObject("ClientesRegistrados.jasper");
            Map parametro = new HashMap();
             parametro.put("modelo", vacio);
             
             JasperPrint j = JasperFillManager.fillReport(reporte, parametro, Main.hc.sql);
             JasperViewer jv = new JasperViewer(j,false);
             jv.setTitle("Reporte Clientes Registrados");
             jv.setVisible(true);
             
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Erros al mostrar :  "+ e);
        }
    }
    
    
}
