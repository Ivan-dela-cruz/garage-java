/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * Factura.java
 *
 * Created on 08-abr-2013, 18:16:22
 */
package view;

import conexion.Conexion;
import java.awt.Color;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import static view.ventanaadmin.reloj;

/**
 *
 * @author Administrador
 */
public class EntradaVehiculos extends javax.swing.JInternalFrame {

    /**
     * Creates new form Factura
     */
    public static Connection sql;
    public static Statement s;

    String hora, minutos, segundos, ampm;
    Calendar calendario;
    Thread h1;

    public EntradaVehiculos() {
        initComponents();

        this.setLocation(25, 15);

        txtfec.setEnabled(false);
        txtfec.setDisabledTextColor(Color.blue);
        txtfec.setText(fechaactual());
        VerDisponibles();
        cargar(txtfec.getText());
        txtci.requestFocus();

    }

    void cargar(String fecha) {
        DefaultTableModel modelo = new DefaultTableModel();
        // String mostrar = "SELECT * FROM entradas WHERE CONCAT(ci,cod_barras,hora_llegada,hora_salida,fecha,estado,numero,placa) LIKE '%" + fecha + "%'";
        String mostrar = "SELECT * FROM entradas WHERE fecha='" + fecha + "'";
        String[] Titulos = {"CI", "CÓDIGO", "PLACA", "ESTADO", "FECHA", "HORA ENTRADA", "HORA SALIDA"};
        modelo.setColumnIdentifiers(Titulos);
        this.tbdet.setModel(modelo);

        Conexion j = new Conexion();
        j.conectar();
        sql = j.sql;

        try {

            String[] registros = new String[8];
            s = sql.createStatement();
            ResultSet rs = s.executeQuery(mostrar);

            while (rs.next()) {
                registros[0] = rs.getString("ci");
                registros[1] = rs.getString("cod_barras");
                registros[2] = rs.getString("placa");
                registros[3] = rs.getString("estado");
                registros[4] = rs.getString("fecha");
                registros[5] = rs.getString("hora_llegada");
                registros[6] = rs.getString("hora_salida");
               

                modelo.addRow(registros);
            }
            tbdet.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(IngresoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void actualizarEspacios() {
        try {

            Conexion j = new Conexion();
            j.conectar();
            j.editar("UPDATE disponibles SET libre_clientes='" + (Integer.parseInt(libres.getText())) + "' WHERE id='1'");

        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    void VerDisponibles() {
        int espacios = 0;

        Conexion j = new Conexion();
        j.conectar();
        sql = j.sql;
        try {
            String registros = "";

            s = sql.createStatement();
            String ConsultaSQL = "SELECT * FROM disponibles";

            ResultSet rs = s.executeQuery(ConsultaSQL);

            while (rs.next()) {
                registros = rs.getString("libre_clientes");
            }

            espacios = Integer.parseInt(registros) + 1;

            //comparo si hay espacios
            if (espacios > maxi()) {
                this.libres.setText("NO HAY DISPONIBLES");

            } else {
                this.libres.setText(String.valueOf(espacios));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ConsultasClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int maxi() {
        int maximo = 0;
        try {
            String reg = "";

            Conexion j = new Conexion();
            j.conectar();
            sql = j.sql;
            s = sql.createStatement();
            String ConsultaSQL2 = "SELECT * FROM estacionamientos";
            ResultSet rss = s.executeQuery(ConsultaSQL2);
            while (rss.next()) {
                reg = rss.getString("est_clientes");
            }
            maximo = Integer.parseInt(reg);
            lbmax.setText(String.valueOf(maximo));
            return maximo;
        } catch (Exception e) {
            return maximo;
        }

    }

    void getHora(String hour) {
        int decimal;
        int unidad;
        int horass = 0;
        String tiempo = "";
        String c = "";
        try {

            c = hour;
            System.out.println("resultado " + c);

            char r1 = c.charAt(0);
            char r2 = c.charAt(1);
            char r3 = c.charAt(9);
            char r4 = c.charAt(10);

            tiempo = "" + r3 + r4;

            decimal = Integer.parseInt(String.valueOf(r1)) * 10;
            unidad = Integer.parseInt(String.valueOf(r1));
            horass = decimal + unidad;
            if (tiempo.equals("PM")) {
                horass = horass + 12;
                txhora.setText(String.valueOf(horass));
            } else {
                txhora.setText(String.valueOf(horass));
            }
        } catch (Exception ex) {
            System.out.println(ex);

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtnomape = new javax.swing.JTextField();
        btnclientes = new javax.swing.JButton();
        txtplaca = new javax.swing.JTextField();
        txtcod = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtci = new javax.swing.JTextField();
        txhora = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtfec = new javax.swing.JTextField();
        cboentrada = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        libres = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnguardar = new javax.swing.JButton();
        btnsalir = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbdet = new javax.swing.JTable();
        lbmax = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Entrada vehículos de clientes");
        setPreferredSize(new java.awt.Dimension(0, 0));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel8.setText("Señor(a):");

        txtnomape.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtnomape.setForeground(new java.awt.Color(0, 51, 204));
        txtnomape.setEnabled(false);
        txtnomape.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnomapeActionPerformed(evt);
            }
        });

        btnclientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/buscar1.JPG"))); // NOI18N
        btnclientes.setText("...");
        btnclientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnclientesActionPerformed(evt);
            }
        });

        txtplaca.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtplaca.setEnabled(false);

        txtcod.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtcod.setEnabled(false);

        jLabel11.setText("Código:");

        txtci.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtci.setForeground(new java.awt.Color(51, 51, 255));
        txtci.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtciActionPerformed(evt);
            }
        });
        txtci.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtciKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtciKeyTyped(evt);
            }
        });

        txhora.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txhora.setEnabled(false);

        jLabel10.setText("Hora:");

        jLabel9.setText("CI:");

        jLabel13.setText("Placa:");

        jLabel12.setText("Fecha:");

        cboentrada.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Entrada", "Salida" }));
        cboentrada.setEnabled(false);

        jLabel16.setText("Estado:");

        jLabel3.setText("Entrada n°:");

        libres.setText("0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(txtplaca))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(5, 5, 5)
                        .addComponent(txtnomape, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtci)
                            .addComponent(txhora))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnclientes)
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(libres, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtcod)
                                    .addComponent(txtfec, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboentrada, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 38, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(txtnomape)
                        .addComponent(btnclientes, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtfec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txtci, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txhora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cboentrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16))
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtplaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel3)
                    .addComponent(libres, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Guardar1.JPG"))); // NOI18N
        btnguardar.setText("Guardar");
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });

        btnsalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/salir1.JPG"))); // NOI18N
        btnsalir.setText("Añadir");
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(btnguardar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnsalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        tbdet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CI", "Código", "Placa", "Estado", "Fecha", "Hora entrada", "Hora salida", "Entrada N°"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tbdet);

        lbmax.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbmax.setText("0");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel5.setText("Máximo estacionamientos :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 96, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbmax, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane2))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbmax, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(90, Short.MAX_VALUE))
        );

        setBounds(0, 0, 830, 540);
    }// </editor-fold>//GEN-END:initComponents
public static String fechaactual() {
        Date fecha = new Date();
        SimpleDateFormat formatofecha = new SimpleDateFormat("YYYY-MM-dd");
        return formatofecha.format(fecha);

    }
private void btnclientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnclientesActionPerformed
// TODO add your handling code here:
    txhora.setText(reloj.getText());
    ClientesBuscar cli = new ClientesBuscar();
    ventanaadmin.jdpescritorio.add(cli);
    cli.toFront();
    cli.setVisible(true);


}//GEN-LAST:event_btnclientesActionPerformed

private void txtnomapeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnomapeActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_txtnomapeActionPerformed

private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
    try {
        IngresoCliente ingcli = new IngresoCliente();
        ventanaadmin.jdpescritorio.add(ingcli);
        ingcli.toFront();
        ingcli.setVisible(true);
        this.dispose();

    } catch (Exception e) {
    }
}//GEN-LAST:event_btnsalirActionPerformed

private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
    String cod, fech, nom, horaa, estado, ci, pla;
    int puest;

    cod = txtcod.getText().toUpperCase();
    ci = txtci.getText();
    nom = txtnomape.getText().toUpperCase();
    pla = txtplaca.getText().toUpperCase();
    fech = txtfec.getText().toUpperCase();
    horaa = txhora.getText().toUpperCase();
    estado = cboentrada.getSelectedItem().toString();
    if (libres.getText().equalsIgnoreCase("NO HAY DISPONIBLES")) {
        JOptionPane.showMessageDialog(null, "PARQUEADERO LLENO");
    } else {

        puest = Integer.parseInt(libres.getText());
        if (txtci.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "REVISE  LOS CAMPOS O  EL USUARIO NO ESTA REGISTRADO");
        } else {
            if (ci.equals(BuscarUser(ci))) {

                if (ci.equals(validarentradas(ci))) {
                    JOptionPane.showMessageDialog(null, "El vehículo consta como estado de entrada");
                } else {
                    try {
                        Conexion j = new Conexion();
                        j.conectar();
                        String sql = "INSERT INTO entradas(ci,cod_barras,hora_llegada,hora_salida,fecha,estado,numero,placa) VALUES('"
                                + ci + "','"
                                + cod + "','"
                                + horaa + "','"
                                + "" + "','"
                                + fech + "','"
                                + estado + "','"
                                + puest + "','"
                                + pla + "')";
                        j.insertar(sql);

                        JOptionPane.showMessageDialog(null, "Guardado exitosamente");
                        limpiar();
                        actualizarEspacios();
                        VerDisponibles();
                        cargar(txtfec.getText());
                        txtci.requestFocus();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Cliente no encontrado");
            }

        }

    }
}//GEN-LAST:event_btnguardarActionPerformed

    private void txtciKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtciKeyReleased
//        String datos[] = new String[9];
//
//        datos = getCliente(txtci.getText());
//        txhora.setText(reloj.getText());
//        txtcod.setText(datos[0]);
//        txtci.setText(datos[1]);
//        txtnomape.setText(datos[2]+" "+datos[3]);
//        txtplaca.setText(datos[4]);


    }//GEN-LAST:event_txtciKeyReleased

    private void txtciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtciActionPerformed
        String datos[] = new String[9];

        datos = getCliente(txtci.getText());
        txhora.setText(reloj.getText());
        txtcod.setText(datos[0]);
        txtci.setText(datos[1]);
        txtnomape.setText(datos[2] + " " + datos[3]);
        txtplaca.setText(datos[4]);
    }//GEN-LAST:event_txtciActionPerformed

    private void txtciKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtciKeyTyped
        char car = evt.getKeyChar();
        if (txtci.getText().length() > 10) {
            evt.consume();
        }
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtciKeyTyped

    public String BuscarUser(String valor) {
        String val = "SELECT ci FROM cliente_mes WHERE ci = '" + valor + "'";

        Conexion j = new Conexion();
        String registros = "";
        j.conectar();

        sql = j.sql;
        try {
            s = sql.createStatement();
            ResultSet rs = s.executeQuery(val);
            while (rs.next()) {
                registros = rs.getString("ci");
            }
            return registros;

        } catch (SQLException ex) {

            Logger.getLogger(IngresoCliente.class.getName()).log(Level.SEVERE, null, ex);
            return registros;
        }

    }

    public String validarentradas(String valor) {
        String val = "SELECT cliente_mes.cod_barras, cliente_mes.ci,cliente_mes.nombre,cliente_mes.apellido,cliente_mes.placa FROM cliente_mes, entradas WHERE cliente_mes.ci = '" + valor + "' AND entradas.estado ='Entrada' AND entradas.ci = cliente_mes.ci";

        Conexion j = new Conexion();
        String registros = "";
        j.conectar();

        sql = j.sql;
        try {
            s = sql.createStatement();
            ResultSet rs = s.executeQuery(val);
            while (rs.next()) {
                registros = rs.getString("ci");
            }
            return registros;

        } catch (SQLException ex) {

            Logger.getLogger(IngresoCliente.class.getName()).log(Level.SEVERE, null, ex);
            return registros;
        }

    }

    public String[] getCliente(String valor) {
        String mostrar = "SELECT * FROM cliente_mes WHERE ci='" + valor + "'";
        Conexion j = new Conexion();
        String[] registros = new String[9];
        j.conectar();

        sql = j.sql;
        try {

            s = sql.createStatement();
            ResultSet rs = s.executeQuery(mostrar);
            while (rs.next()) {
                registros[0] = rs.getString("cod_barras");
                registros[1] = rs.getString("ci");
                registros[2] = rs.getString("nombre");
                registros[3] = rs.getString("apellido");
                registros[4] = rs.getString("placa");

            }

            return registros;

        } catch (SQLException ex) {

            Logger.getLogger(IngresoCliente.class.getName()).log(Level.SEVERE, null, ex);
            return registros;

        }

    }

    public void limpiar() {
        txtcod.setText("");
        txtci.setText("");
        txtnomape.setText("");
        txtplaca.setText("");
        txhora.setText("");
        VerDisponibles();

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnclientes;
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnsalir;
    private javax.swing.JComboBox cboentrada;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbmax;
    public static javax.swing.JLabel libres;
    public static javax.swing.JTable tbdet;
    public static javax.swing.JTextField txhora;
    public static javax.swing.JTextField txtci;
    public static javax.swing.JTextField txtcod;
    private javax.swing.JTextField txtfec;
    public static javax.swing.JTextField txtnomape;
    public static javax.swing.JTextField txtplaca;
    // End of variables declaration//GEN-END:variables

}
