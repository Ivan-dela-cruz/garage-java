/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * IngresoCliente.java
 *
 * Created on 07-abr-2013, 11:16:29
 */
package view;

import ModelClass.GenerarCodigos;
import conexion.Conexion;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.util.logging.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Administrador
 */
public class IngresoCliente extends javax.swing.JInternalFrame {

    DefaultTableModel model;

    /**
     * Creates new form IngresoCliente
     */
    public static Connection sql;
    public static Statement s;

    public IngresoCliente() {
        initComponents();
        bloquear();
        cargar("");

    }

    void bloquear() {
        txtcod.setEnabled(false);
        txtnom.setEnabled(false);
        txtape.setEnabled(false);
        txtdir.setEnabled(false);
        txtcolor.setEnabled(false);
        txttel.setEnabled(false);
        txtplaca.setEnabled(false);
        txtci.setEnabled(false);
        cbotipo.setEnabled(false);
        btnguardar.setEnabled(false);
        btnnuevo.setEnabled(true);
        btncancelar.setEnabled(true);
        btnactualizar.setEnabled(false);

    }

    void limpiar() {
        txtcod.setText("");
        txtnom.setText("");
        txtdir.setText("");
        txtci.setText("");
        txtcolor.setText("");
        txtplaca.setText("");
        txttel.setText("");
        txtape.setText("");

    }

    void desbloquear() {
        txtcod.setEnabled(true);
        txtnom.setEnabled(true);
        txtape.setEnabled(true);
        txtdir.setEnabled(true);
        txtcolor.setEnabled(true);
        txttel.setEnabled(true);
        txtplaca.setEnabled(true);
        txtci.setEnabled(true);
        cbotipo.setEnabled(true);
        btnguardar.setEnabled(true);
        btnnuevo.setEnabled(false);
        btncancelar.setEnabled(true);
        btnactualizar.setEnabled(false);
    }

    void cargar(String valor) {
        DefaultTableModel modelo = new DefaultTableModel();
        String mostrar = "SELECT * FROM cliente_mes WHERE CONCAT(ci,nombre,apellido,telefono,direccion,placa,modelo,color,cod_barras) LIKE '%" + valor + "%'";

        String[] Titulos = {"CÓDIGO", "CÉDULA", "NOMBRES", "APELLIDOS", "DIRECCIÓN", "TELEFONO", "PLACA", "MODELO", "COLOR"};
        modelo.setColumnIdentifiers(Titulos);
        this.tbclientes.setModel(modelo);

        Conexion j = new Conexion();
        j.conectar();
        sql = j.sql;

        try {

            String[] registros = new String[9];
            s = sql.createStatement();
            ResultSet rs = s.executeQuery(mostrar);
            while (rs.next()) {
                registros[0] = rs.getString("cod_barras");
                registros[1] = rs.getString("ci");
                registros[2] = rs.getString("nombre");
                registros[3] = rs.getString("apellido");
                registros[4] = rs.getString("direccion");
                registros[5] = rs.getString("telefono");
                registros[6] = rs.getString("placa");
                registros[7] = rs.getString("modelo");
                registros[8] = rs.getString("color");
                modelo.addRow(registros);
            }
            tbclientes.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(IngresoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void codigosclientes() {
        int j;
        int cont = 1;
        String num = "";
        String c = "";
        String SQL = "select max(cod_barras) from cliente_mes";
        // String SQL="select count(*) from factura";
        //String SQL="SELECT MAX(cod_emp) AS cod_emp FROM empleado";
        //String SQL="SELECT @@identity AS ID";
        try {

            s = sql.createStatement();
            ResultSet rs = s.executeQuery(SQL);
            if (rs.next()) {
                c = rs.getString(1);
            }
            System.out.println("resultado " + c);
            if (c == null) {
                txtcod.setText("0001");
            } else {
                char r1 = c.charAt(0);
                char r2 = c.charAt(1);
                char r3 = c.charAt(2);
                char r4 = c.charAt(3);
                String r = "";
                r = "" + r1 + r2 + r3 + r4;

                j = Integer.parseInt(r);
                GenerarCodigos gen = new GenerarCodigos();
                gen.generar(j);
                txtcod.setText(gen.serie());

            }

        } catch (SQLException ex) {
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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        mnmodificar = new javax.swing.JMenuItem();
        mneliminar = new javax.swing.JMenuItem();
        jPanel2 = new javax.swing.JPanel();
        btnnuevo = new javax.swing.JButton();
        btnguardar = new javax.swing.JButton();
        btnactualizar = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();
        btnsalir = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        Label_ciCliente1 = new javax.swing.JLabel();
        Label_ciCliente2 = new javax.swing.JLabel();
        Label_ciCliente3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtplaca = new javax.swing.JTextField();
        cbotipo = new javax.swing.JComboBox();
        txtcolor = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        Label_ciCliente4 = new javax.swing.JLabel();
        txtcod = new javax.swing.JTextField();
        Label_ciCliente = new javax.swing.JLabel();
        txtci = new javax.swing.JTextField();
        Label_NombreCliente = new javax.swing.JLabel();
        txtnom = new javax.swing.JTextField();
        txtape = new javax.swing.JTextField();
        Label_ApellidoCliente = new javax.swing.JLabel();
        Label_DireccionCliente = new javax.swing.JLabel();
        txttel = new javax.swing.JTextField();
        Label_TelefonoCliente = new javax.swing.JLabel();
        txtdir = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbclientes = new javax.swing.JTable();
        btnbuscar = new javax.swing.JButton();
        txtbuscar = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();

        mnmodificar.setText("Modificar");
        mnmodificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnmodificarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(mnmodificar);

        mneliminar.setText("Eliminar");
        mneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mneliminarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(mneliminar);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("REGISTRO DE CLIENTES");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnnuevo.setText("Nuevo");
        btnnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoActionPerformed(evt);
            }
        });

        btnguardar.setText("Guardar");
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });

        btnactualizar.setText("Actualizar");
        btnactualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnactualizarActionPerformed(evt);
            }
        });

        btncancelar.setText("Cancelar");
        btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarActionPerformed(evt);
            }
        });

        btnsalir.setText("Salir");
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnnuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnactualizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btncancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnnuevo)
                    .addComponent(btnguardar)
                    .addComponent(btnactualizar)
                    .addComponent(btncancelar)
                    .addComponent(btnsalir))
                .addGap(142, 142, 142))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Label_ciCliente1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Label_ciCliente1.setForeground(new java.awt.Color(102, 102, 102));
        Label_ciCliente1.setText("Placa");
        Label_ciCliente1.setToolTipText("");
        jPanel4.add(Label_ciCliente1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 88, -1));

        Label_ciCliente2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Label_ciCliente2.setForeground(new java.awt.Color(102, 102, 102));
        Label_ciCliente2.setText("Modelo");
        Label_ciCliente2.setToolTipText("");
        jPanel4.add(Label_ciCliente2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 88, -1));

        Label_ciCliente3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Label_ciCliente3.setForeground(new java.awt.Color(102, 102, 102));
        Label_ciCliente3.setText("Color");
        Label_ciCliente3.setToolTipText("");
        jPanel4.add(Label_ciCliente3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 88, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(80, 80, 97));
        jLabel11.setText("Datos del vehículo");
        jLabel11.setToolTipText("");
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        txtplaca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtplacaKeyTyped(evt);
            }
        });
        jPanel4.add(txtplaca, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, 250, -1));

        cbotipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "AUTOMOVIL", "BUS", "BUS ESCOLAR", "CAMION", "CAMIONETA", "FURGONETA", "MOTOCICLETA" }));
        jPanel4.add(cbotipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 250, -1));
        jPanel4.add(txtcolor, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 250, -1));

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("")));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(80, 80, 97));
        jLabel4.setText("Datos del cliente");
        jLabel4.setToolTipText("");

        Label_ciCliente4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Label_ciCliente4.setForeground(new java.awt.Color(102, 102, 102));
        Label_ciCliente4.setText("Código");
        Label_ciCliente4.setToolTipText("");

        Label_ciCliente.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Label_ciCliente.setForeground(new java.awt.Color(102, 102, 102));
        Label_ciCliente.setText("CI");
        Label_ciCliente.setToolTipText("");

        txtci.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtciActionPerformed(evt);
            }
        });
        txtci.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtciKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtciKeyTyped(evt);
            }
        });

        Label_NombreCliente.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Label_NombreCliente.setForeground(new java.awt.Color(102, 102, 102));
        Label_NombreCliente.setText("Nombre completo");
        Label_NombreCliente.setToolTipText("");

        txtnom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnomKeyTyped(evt);
            }
        });

        txtape.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtapeKeyTyped(evt);
            }
        });

        Label_ApellidoCliente.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Label_ApellidoCliente.setForeground(new java.awt.Color(102, 102, 102));
        Label_ApellidoCliente.setText("Apellido");

        Label_DireccionCliente.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Label_DireccionCliente.setForeground(new java.awt.Color(102, 102, 102));
        Label_DireccionCliente.setText("Dirección");
        Label_DireccionCliente.setToolTipText("");

        txttel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txttelKeyTyped(evt);
            }
        });

        Label_TelefonoCliente.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Label_TelefonoCliente.setForeground(new java.awt.Color(102, 102, 102));
        Label_TelefonoCliente.setText("Teléfono");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Label_ciCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel4)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addGap(2, 2, 2)
                            .addComponent(Label_ciCliente4, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(Label_NombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Label_ApellidoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Label_DireccionCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Label_TelefonoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtcod, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtci, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnom, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtape, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttel, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdir, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel4)
                .addGap(5, 5, 5)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label_ciCliente4)
                    .addComponent(txtcod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label_ciCliente)
                    .addComponent(txtci, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(Label_NombreCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Label_ApellidoCliente)
                            .addComponent(txtape, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txtnom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label_DireccionCliente)
                    .addComponent(txtdir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label_TelefonoCliente)
                    .addComponent(txttel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        tbclientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbclientes.setComponentPopupMenu(jPopupMenu1);
        tbclientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tbclientesKeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(tbclientes);

        btnbuscar.setText("Mostrar Todos");

        txtbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbuscarActionPerformed(evt);
            }
        });
        txtbuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscarKeyReleased(evt);
            }
        });

        jLabel10.setText("BUSCAR:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnbuscar))
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnbuscar)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void btnactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnactualizarActionPerformed
// TODO add your handling code here:
    try {
        Conexion j = new Conexion();
        j.conectar();
        j.editar("UPDATE cliente_mes SET nombre='" + txtnom.getText().toUpperCase()
                + "',apellido='" + txtape.getText().toUpperCase()
                + "',telefono='" + txttel.getText()
                + "',direccion='" + txtdir.getText()
                + "',placa='" + txtplaca.getText().toUpperCase()
                + "',modelo='" + cbotipo.getSelectedItem().toString().toUpperCase()
                + "',color='" + txtcolor.getText().toUpperCase()
                + "',cod_barras='" + txtcod.getText()
                + "' WHERE ci='" + txtci.getText() + "'");

        cargar("");
        bloquear();
    } catch (Exception e) {
        System.out.print(e.getMessage());
    }

}//GEN-LAST:event_btnactualizarActionPerformed

private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
// TODO add your handling code here:
    this.dispose();
}//GEN-LAST:event_btnsalirActionPerformed

private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoActionPerformed
// TODO add your handling code here:
    desbloquear();
    limpiar();
    codigosclientes();
    txtcod.requestFocus();
}//GEN-LAST:event_btnnuevoActionPerformed

private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
// TODO add your handling code here:
    bloquear();
}//GEN-LAST:event_btncancelarActionPerformed

private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
// TODO add your handling code here:
    String cod, dir, nom, ape, tel, tip, ci, col, pla;

    cod = txtcod.getText().toUpperCase();
    nom = txtnom.getText().toUpperCase();
    ape = txtape.getText().toUpperCase();
    dir = txtdir.getText().toUpperCase();
    tel = txttel.getText();
    tip = cbotipo.getSelectedItem().toString();
    pla = txtplaca.getText().toUpperCase();
    col = txtcolor.getText().toUpperCase();
    ci = txtci.getText();

    try {
        Conexion j = new Conexion();
        j.conectar();
        String sql = "INSERT INTO cliente_mes(ci,nombre,apellido,telefono,direccion,placa,modelo,color,cod_barras) VALUES('"
                + ci + "','"
                + nom + "','"
                + ape + "','"
                + tel + "','"
                + dir + "','"
                + pla + "','"
                + tip + "','"
                + col + "','"
                + cod + "')";
        j.insertar(sql);
        System.out.println("EXITO");
        JOptionPane.showMessageDialog(null, "Guardado exitosamente");
        limpiar();
        cargar("");
        bloquear();
    } catch (Exception e) {
        System.out.println("ERROR");
    }
}//GEN-LAST:event_btnguardarActionPerformed

private void txtbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbuscarActionPerformed
// TODO add your handling code here:

}//GEN-LAST:event_txtbuscarActionPerformed

private void txtbuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarKeyReleased
// TODO add your handling code here:
    cargar(txtbuscar.getText());
}//GEN-LAST:event_txtbuscarKeyReleased

private void txtciKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtciKeyPressed
// TODO add your handling code here:

}//GEN-LAST:event_txtciKeyPressed

private void txtciKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtciKeyTyped
// TODO add your handling code here:
    char car = evt.getKeyChar();
    if (txtci.getText().length() > 10) {
        evt.consume();
    }
    if ((car < '0' || car > '9')) {
        evt.consume();
    }
}//GEN-LAST:event_txtciKeyTyped

private void txttelKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttelKeyTyped
// TODO add your handling code here:
    char car = evt.getKeyChar();
    if (txttel.getText().length() >10) {
        evt.consume();
    }
    if ((car < '0' || car > '9')) {
        evt.consume();
    }
}//GEN-LAST:event_txttelKeyTyped

private void txtplacaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtplacaKeyTyped
 char car = evt.getKeyChar();
    if (txtplaca.getText().length() > 7) {
        evt.consume();
    }
    
}//GEN-LAST:event_txtplacaKeyTyped

private void txtnomKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnomKeyTyped
// TODO add your handling code here:
    char car = evt.getKeyChar();
    if ((car < 'a' || car > 'z') && (car < 'A' || car > 'Z') && (car != (char) KeyEvent.VK_SPACE)) {
        evt.consume();
    }
}//GEN-LAST:event_txtnomKeyTyped

private void txtapeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtapeKeyTyped
// TODO add your handling code here:
    char car = evt.getKeyChar();
    if ((car < 'a' || car > 'z') && (car < 'A' || car > 'Z') && (car != (char) KeyEvent.VK_SPACE)) {
        evt.consume();
    }
}//GEN-LAST:event_txtapeKeyTyped

private void txtciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtciActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_txtciActionPerformed

private void mneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mneliminarActionPerformed
// TODO add your handling code here:
    int fila = tbclientes.getSelectedRow();
    String ci = "";
    ci = tbclientes.getValueAt(fila, 1).toString();
    if (fila >= 0) {
        try {
            Conexion j = new Conexion();
            j.conectar();
            String sql = "DELETE FROM cliente_mes WHERE ci='" + ci + "'";
            j.eliminar(sql);

            cargar("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    } else {
        JOptionPane.showMessageDialog(this, "No ha selecionada ninguna fila");
    }

}//GEN-LAST:event_mneliminarActionPerformed

private void mnmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnmodificarActionPerformed
// "CÓDIGO", "CÉDULA", "NOMBRES", "APELLIDOS", "DIRECCIÓN", "TELEFONO", "PLACA", "MODELO", "COLOR"    
    desbloquear();
    btnactualizar.setEnabled(true);
    int filamodificar = tbclientes.getSelectedRow();
    if (filamodificar >= 0) {
        txtcod.setText(tbclientes.getValueAt(filamodificar, 0).toString());
        txtci.setText(tbclientes.getValueAt(filamodificar, 1).toString());
        txtnom.setText(tbclientes.getValueAt(filamodificar, 2).toString());
        txtape.setText(tbclientes.getValueAt(filamodificar, 3).toString());
        txtdir.setText(tbclientes.getValueAt(filamodificar, 4).toString());
        txttel.setText(tbclientes.getValueAt(filamodificar, 5).toString());
        txtplaca.setText(tbclientes.getValueAt(filamodificar, 6).toString());
        cbotipo.setSelectedItem(tbclientes.getValueAt(filamodificar, 7).toString());

        txtcolor.setText(tbclientes.getValueAt(filamodificar, 8).toString());
        this.txtci.setEnabled(false);

    } else {
        JOptionPane.showMessageDialog(this, "No ha seleccionado ");
    }
}//GEN-LAST:event_mnmodificarActionPerformed

    private void tbclientesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbclientesKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tbclientesKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Label_ApellidoCliente;
    private javax.swing.JLabel Label_DireccionCliente;
    private javax.swing.JLabel Label_NombreCliente;
    private javax.swing.JLabel Label_TelefonoCliente;
    private javax.swing.JLabel Label_ciCliente;
    private javax.swing.JLabel Label_ciCliente1;
    private javax.swing.JLabel Label_ciCliente2;
    private javax.swing.JLabel Label_ciCliente3;
    private javax.swing.JLabel Label_ciCliente4;
    private javax.swing.JButton btnactualizar;
    private javax.swing.JButton btnbuscar;
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnnuevo;
    private javax.swing.JButton btnsalir;
    private javax.swing.JComboBox cbotipo;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenuItem mneliminar;
    private javax.swing.JMenuItem mnmodificar;
    private javax.swing.JTable tbclientes;
    private javax.swing.JTextField txtape;
    private javax.swing.JTextField txtbuscar;
    private javax.swing.JTextField txtci;
    private javax.swing.JTextField txtcod;
    private javax.swing.JTextField txtcolor;
    private javax.swing.JTextField txtdir;
    private javax.swing.JTextField txtnom;
    private javax.swing.JTextField txtplaca;
    private javax.swing.JTextField txttel;
    // End of variables declaration//GEN-END:variables

}