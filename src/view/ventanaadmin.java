/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * Principal.java
 *
 * Created on 05-abr-2013, 20:15:45
 */
package view;

import java.sql.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrador
 */
public class ventanaadmin extends javax.swing.JFrame implements Runnable {

    /**
     * Creates new form Principal
     */
    public static String hora, minutos, segundos, ampm;
    Calendar calendario;
    Thread h1;

    public ventanaadmin() {
        initComponents();
        h1 = new Thread(this);
        h1.start();
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jdpescritorio = new javax.swing.JDesktopPane();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        reloj2 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblusu = new javax.swing.JLabel();
        ho = new javax.swing.JLabel();
        reloj = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        cerrarsecion = new javax.swing.JMenuItem();
        salir = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        estacionamientos = new javax.swing.JMenuItem();
        clientes = new javax.swing.JMenuItem();
        entradas = new javax.swing.JMenu();
        movimientoEntradas = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        salidas = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        consultasClientes = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        reportecliente = new javax.swing.JMenu();
        menuClientesSi = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        menuClientesNo = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        menunuevouser = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Ventas");

        jdpescritorio.setBackground(new java.awt.Color(25, 153, 153));

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/car_14444.png"))); // NOI18N
        jLabel13.setLabelFor(entradas);
        jLabel13.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });
        jdpescritorio.add(jLabel13);
        jLabel13.setBounds(850, 430, 130, 130);

        jLabel14.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Salidas Horas");
        jdpescritorio.add(jLabel14);
        jLabel14.setBounds(850, 560, 130, 30);

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/adduser_1103.png"))); // NOI18N
        jLabel15.setLabelFor(entradas);
        jLabel15.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });
        jdpescritorio.add(jLabel15);
        jLabel15.setBounds(450, 430, 130, 130);

        jLabel16.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Añadir");
        jdpescritorio.add(jLabel16);
        jLabel16.setBounds(450, 560, 130, 30);

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/mimetypes_office_spreadsheet_table_xls_381.png"))); // NOI18N
        jLabel17.setLabelFor(entradas);
        jLabel17.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
        });
        jdpescritorio.add(jLabel17);
        jLabel17.setBounds(650, 430, 130, 130);

        jLabel18.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Egresos diario");
        jdpescritorio.add(jLabel18);
        jLabel18.setBounds(650, 560, 160, 30);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/car_icon-icons.com_54409.png"))); // NOI18N
        jLabel2.setLabelFor(entradas);
        jLabel2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jdpescritorio.add(jLabel2);
        jLabel2.setBounds(850, 220, 130, 130);

        reloj2.setFont(new java.awt.Font("Arial Narrow", 1, 60)); // NOI18N
        reloj2.setForeground(new java.awt.Color(255, 255, 255));
        reloj2.setText("jLabel11");
        jdpescritorio.add(reloj2);
        reloj2.setBounds(480, 30, 290, 80);

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Citycons_car_icon-icons.com_67916.png"))); // NOI18N
        jLabel11.setLabelFor(entradas);
        jLabel11.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });
        jdpescritorio.add(jLabel11);
        jLabel11.setBounds(250, 430, 130, 130);

        jLabel12.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Salidas Mensuales");
        jdpescritorio.add(jLabel12);
        jLabel12.setBounds(250, 560, 170, 30);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/car_23773.png"))); // NOI18N
        jLabel3.setLabelFor(entradas);
        jLabel3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jdpescritorio.add(jLabel3);
        jLabel3.setBounds(250, 220, 130, 130);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/1486504829-clipboard-tasks-report-business-checking-verification-list_81387.png"))); // NOI18N
        jLabel4.setLabelFor(entradas);
        jLabel4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jdpescritorio.add(jLabel4);
        jLabel4.setBounds(450, 220, 130, 130);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/search_find_client_user_16693.png"))); // NOI18N
        jLabel5.setLabelFor(entradas);
        jLabel5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jdpescritorio.add(jLabel5);
        jLabel5.setBounds(650, 220, 130, 130);

        jLabel6.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Reportes");
        jdpescritorio.add(jLabel6);
        jLabel6.setBounds(450, 350, 130, 30);

        jLabel7.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Entradas Horas");
        jdpescritorio.add(jLabel7);
        jLabel7.setBounds(850, 350, 130, 30);

        jLabel8.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Entradas Mensuales");
        jdpescritorio.add(jLabel8);
        jLabel8.setBounds(250, 350, 170, 30);

        jLabel9.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Clientes");
        jdpescritorio.add(jLabel9);
        jLabel9.setBounds(650, 350, 130, 30);

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/degradados-wallpapers-7.jpg"))); // NOI18N
        jdpescritorio.add(jLabel10);
        jLabel10.setBounds(0, 0, 1840, 1200);

        jLabel1.setText("Usuario Conectado:");

        ho.setText("Hora:");

        reloj.setText("jLabel3");

        jMenu1.setText("Archivo");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        cerrarsecion.setText("Cerrar Sesion");
        cerrarsecion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrarsecionActionPerformed(evt);
            }
        });
        jMenu1.add(cerrarsecion);

        salir.setText("Salir");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });
        jMenu1.add(salir);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Mantenimiento");
        jMenu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu2ActionPerformed(evt);
            }
        });

        estacionamientos.setText("Estacionamientos");
        estacionamientos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estacionamientosActionPerformed(evt);
            }
        });
        jMenu2.add(estacionamientos);

        clientes.setText("Clientes");
        clientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientesActionPerformed(evt);
            }
        });
        jMenu2.add(clientes);

        jMenuBar1.add(jMenu2);

        entradas.setText("Entrada vehículos");
        entradas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entradasActionPerformed(evt);
            }
        });

        movimientoEntradas.setText("Clientes mensuales");
        movimientoEntradas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                movimientoEntradasActionPerformed(evt);
            }
        });
        entradas.add(movimientoEntradas);

        jMenuItem3.setText("Clientes horas");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        entradas.add(jMenuItem3);

        jMenuBar1.add(entradas);

        jMenu3.setText("Salidas vehículos");

        salidas.setText("Clientes registrados");
        salidas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salidasActionPerformed(evt);
            }
        });
        jMenu3.add(salidas);

        jMenuItem4.setText("Clientes Horas");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Consultas");
        jMenu4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu4ActionPerformed(evt);
            }
        });

        consultasClientes.setText("Clientes registrados");
        consultasClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultasClientesActionPerformed(evt);
            }
        });
        jMenu4.add(consultasClientes);

        jMenuItem1.setText("Clientes no registrados");
        jMenu4.add(jMenuItem1);

        jMenuBar1.add(jMenu4);

        reportecliente.setText("Reportes");

        menuClientesSi.setText("Ingresos del dia");
        menuClientesSi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuClientesSiActionPerformed(evt);
            }
        });
        reportecliente.add(menuClientesSi);

        jMenuItem2.setText("Ingresos mensual");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        reportecliente.add(jMenuItem2);

        menuClientesNo.setText("Clientes mensuales");
        menuClientesNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuClientesNoActionPerformed(evt);
            }
        });
        reportecliente.add(menuClientesNo);

        jMenuBar1.add(reportecliente);

        jMenu6.setText("Administrador");

        menunuevouser.setText("Nuevo Usuario");
        menunuevouser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menunuevouserActionPerformed(evt);
            }
        });
        jMenu6.add(menunuevouser);

        jMenuBar1.add(jMenu6);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblusu, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 440, Short.MAX_VALUE)
                .addComponent(ho)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(reloj)
                .addGap(71, 71, 71))
            .addComponent(jdpescritorio, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jdpescritorio, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblusu)
                    .addComponent(ho)
                    .addComponent(reloj))
                .addGap(11, 11, 11))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
// TODO add your handling code here:

}//GEN-LAST:event_jMenu1ActionPerformed

private void cerrarsecionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarsecionActionPerformed
// TODO add your handling code here:
    LOGGIN principal = new LOGGIN();
    principal.setVisible(true);
    principal.pack();
    this.setVisible(false);
}//GEN-LAST:event_cerrarsecionActionPerformed

private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
// TODO add your handling code here:
    this.dispose();
}//GEN-LAST:event_salirActionPerformed

private void estacionamientosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estacionamientosActionPerformed
// TODO add your handling code here:
    Estacionamiento ip = new Estacionamiento();

    jdpescritorio.add(ip);
    ip.show();
}//GEN-LAST:event_estacionamientosActionPerformed

private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu2ActionPerformed
// TODO add your handling code here:

}//GEN-LAST:event_jMenu2ActionPerformed

private void entradasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entradasActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_entradasActionPerformed

private void movimientoEntradasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_movimientoEntradasActionPerformed
    EntradaVehiculos ip = new EntradaVehiculos();

    jdpescritorio.add(ip);
    ip.show();

}//GEN-LAST:event_movimientoEntradasActionPerformed

private void menuClientesSiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuClientesSiActionPerformed
// TODO add your handling code here:


}//GEN-LAST:event_menuClientesSiActionPerformed

private void consultasClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultasClientesActionPerformed
    ConsultasClientes ip = new ConsultasClientes();

    jdpescritorio.add(ip);
    ip.show();

}//GEN-LAST:event_consultasClientesActionPerformed

private void salidasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salidasActionPerformed
    SalidaVehiculos ip = new SalidaVehiculos();

    jdpescritorio.add(ip);
    ip.show();

}//GEN-LAST:event_salidasActionPerformed

private void jMenu4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu4ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_jMenu4ActionPerformed

private void menuClientesNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuClientesNoActionPerformed
// TODO add your handling code here:

}//GEN-LAST:event_menuClientesNoActionPerformed

private void menunuevouserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menunuevouserActionPerformed

}//GEN-LAST:event_menunuevouserActionPerformed

    private void clientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientesActionPerformed
        IngresoCliente ip = new IngresoCliente();

        jdpescritorio.add(ip);
        ip.show();
    }//GEN-LAST:event_clientesActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        IngresoClienteHoras ip = new IngresoClienteHoras();

        jdpescritorio.add(ip);
        ip.show();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        EntradaVehiculos ip = new EntradaVehiculos();

        jdpescritorio.add(ip);
        ip.show();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        SalidaVehiculos ip = new SalidaVehiculos();

        jdpescritorio.add(ip);
        ip.show();
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        ConsultasClientes ip = new ConsultasClientes();

        jdpescritorio.add(ip);
        ip.show();
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        ConsultasRegistrosClientes ip = new ConsultasRegistrosClientes();
        jdpescritorio.add(ip);
        ip.show();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        SalidaVehiculosHoras ip = new SalidaVehiculosHoras();

        jdpescritorio.add(ip);
        ip.show();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        IngresoClienteHoras ip = new IngresoClienteHoras();

        jdpescritorio.add(ip);
        ip.show();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        SalidaVehiculosHoras ip = new SalidaVehiculosHoras();

        jdpescritorio.add(ip);
        ip.show();
    }//GEN-LAST:event_jLabel13MouseClicked

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
         IngresoCliente ip = new IngresoCliente();

        jdpescritorio.add(ip);
        ip.show();
    }//GEN-LAST:event_jLabel15MouseClicked

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
         ConsultasRegistrosHoras ip = new ConsultasRegistrosHoras();
        jdpescritorio.add(ip);
        ip.show();
    }//GEN-LAST:event_jLabel17MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ventanaadmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ventanaadmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ventanaadmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ventanaadmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ventanaadmin().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem cerrarsecion;
    private javax.swing.JMenuItem clientes;
    private javax.swing.JMenuItem consultasClientes;
    private javax.swing.JMenu entradas;
    private javax.swing.JMenuItem estacionamientos;
    private javax.swing.JLabel ho;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    public static javax.swing.JDesktopPane jdpescritorio;
    public static javax.swing.JLabel lblusu;
    private javax.swing.JMenuItem menuClientesNo;
    private javax.swing.JMenuItem menuClientesSi;
    private javax.swing.JMenuItem menunuevouser;
    private javax.swing.JMenuItem movimientoEntradas;
    public static javax.swing.JLabel reloj;
    private javax.swing.JLabel reloj2;
    private javax.swing.JMenu reportecliente;
    private javax.swing.JMenuItem salidas;
    private javax.swing.JMenuItem salir;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        Thread ct = Thread.currentThread();
        while (ct == h1) {
            calcula();
            reloj.setText(hora + ":" + minutos + ":" + segundos + " " + ampm);
            reloj2.setText(hora + ":" + minutos + ":" + segundos + " " + ampm);
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
        }
    }

    private void calcula() {
        Calendar calendario = new GregorianCalendar();
        java.util.Date fechahoraActual = new java.util.Date();
        calendario.setTime(fechahoraActual);
        ampm = calendario.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";
        if (ampm.equals("PM")) {
            int h = calendario.get(Calendar.HOUR_OF_DAY) - 12;
            hora = h > 9 ? "" + h : "0" + h;
        } else {
            hora = calendario.get(Calendar.HOUR_OF_DAY) > 9 ? "" + calendario.get(Calendar.HOUR_OF_DAY) : "0" + calendario.get(Calendar.HOUR_OF_DAY);
        }
        minutos = calendario.get(Calendar.MINUTE) > 9 ? "" + calendario.get(Calendar.MINUTE) : "0" + calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND) > 9 ? "" + calendario.get(Calendar.SECOND) : "0" + calendario.get(Calendar.SECOND);
    }
}