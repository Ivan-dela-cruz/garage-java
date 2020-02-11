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
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import mainGarage.Main;
import static mainGarage.Main.hc;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import reportes.ClienteHoraTicket;
import reportes.ReporteIngresosClientes;
import static view.EntradaVehiculos.libres;
import static view.EntradaVehiculos.s;
import static view.EntradaVehiculos.sql;
import static view.EntradaVehiculos.txhora;
import static view.ventanaadmin.reloj;

/**
 *
 * @author Administrador
 */
public class IngresoClienteHoras extends javax.swing.JInternalFrame {

    DefaultTableModel model;

    /**
     * Creates new form IngresoCliente
     */
    public static Connection sql;
    public static Statement s;

    String hora, minutos, segundos, ampm;
    Calendar calendario;
    Thread h1;

    public IngresoClienteHoras() {
        initComponents();
        txtfechaHoras.setText(fechaactual());
        txhora.setText(reloj.getText());
        txtci.requestFocus();
        bloquear();
        cargar("");
        VerDisponibles();
         codigosclientes();

    }

    public static String fechaactual() {
        java.util.Date fecha = new java.util.Date();
        SimpleDateFormat formatofecha = new SimpleDateFormat("YYYY-MM-dd");
        return formatofecha.format(fecha);

    }

    void bloquear() {
        txtcod.setEnabled(false);

        btnticket.setEnabled(false);
        btncancelar.setEnabled(false);

    }

    void limpiar() {
        txtcod.setText("");

        txtci.setText("");

        txtplaca.setText("");

    }

    void desbloquear() {
        txtcod.setEnabled(true);
        txtplaca.setEnabled(true);
        txtci.setEnabled(true);
        cbotipo.setEnabled(true);

        btnticket.setEnabled(true);
        btncancelar.setEnabled(true);

    }

    void cargar(String valor) {
        DefaultTableModel modelo = new DefaultTableModel();
        String mostrar = "SELECT cliente_hora.id,cliente_hora.placa,cliente_hora.ci,cliente_hora.cod_barras,cliente_hora.tipo_vehiculo,cliente_hora.fecha,entradashoras.hora_llegada,entradashoras.hora_salida FROM cliente_hora, entradashoras WHERE CONCAT(cliente_hora.placa,cliente_hora.ci,cliente_hora.cod_barras,cliente_hora.tipo_vehiculo,cliente_hora.fecha) LIKE '%"+valor+"%' AND entradashoras.id_hora = cliente_hora.id ORDER BY id ASC";

        String[] Titulos = {"ID", "CÓDIGO", "CÉDULA", "PLACA", "MODELO VEHÍCULO", "FECHA","ENTRADA","SALIDA"};
        modelo.setColumnIdentifiers(Titulos);
        this.tbclientes.setModel(modelo);

        Conexion j = new Conexion();
        j.conectar();
        sql = j.sql;

        try {

            String[] registros = new String[8];
            s = sql.createStatement();
            ResultSet rs = s.executeQuery(mostrar);
            while (rs.next()) {
                registros[0] = rs.getString("id");
                registros[1] = rs.getString("cod_barras");
                registros[2] = rs.getString("ci");
                registros[3] = rs.getString("placa");
                registros[4] = rs.getString("tipo_vehiculo");
                registros[5] = rs.getString("fecha");
                registros[6] = rs.getString("hora_llegada");
                registros[7] = rs.getString("hora_salida");
                modelo.addRow(registros);
            }
            tbclientes.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(IngresoClienteHoras.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void codigosclientes() {
        int j;
        int cont = 1;
        String num = "";
        String c = "";
        String SQL = "select max(cod_barras) from cliente_hora";
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
        mneliminar = new javax.swing.JMenuItem();
        jPanel2 = new javax.swing.JPanel();
        btnguardar = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();
        btnsalir = new javax.swing.JButton();
        btnticket = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        cboentrada = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        libres = new javax.swing.JLabel();
        Label_ciCliente4 = new javax.swing.JLabel();
        txtcod = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        Label_ciCliente = new javax.swing.JLabel();
        txtci = new javax.swing.JTextField();
        Label_ciCliente5 = new javax.swing.JLabel();
        txtfechaHoras = new javax.swing.JTextField();
        txhora = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        Label_ciCliente1 = new javax.swing.JLabel();
        txtplaca = new javax.swing.JTextField();
        cbotipo = new javax.swing.JComboBox();
        Label_ciCliente2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbclientes = new javax.swing.JTable();
        btnbuscar = new javax.swing.JButton();
        txtbuscar = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbmax = new javax.swing.JLabel();
        lbid = new javax.swing.JLabel();
        lbplaca = new javax.swing.JLabel();

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
        setTitle("INGRESO VEHÍCULOS DE CLIENTES POR HORAS");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnguardar.setText("Guardar");
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });

        btncancelar.setText("Cancelar");
        btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarActionPerformed(evt);
            }
        });

        btnsalir.setText("Limpiar");
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });

        btnticket.setBackground(new java.awt.Color(0, 204, 153));
        btnticket.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnticket.setForeground(new java.awt.Color(255, 255, 255));
        btnticket.setText("Ticket");
        btnticket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnticketActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btncancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnticket, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnguardar)
                    .addComponent(btncancelar)
                    .addComponent(btnsalir)
                    .addComponent(btnticket, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(139, 139, 139))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(80, 80, 97));
        jLabel11.setText("Datos del vehículo");
        jLabel11.setToolTipText("");
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        cboentrada.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Entrada", "Salida" }));
        cboentrada.setEnabled(false);
        jPanel4.add(cboentrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 210, 22));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(102, 102, 102));
        jLabel16.setText("Estado:");
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Entrada N°:");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 65, 43));

        libres.setForeground(new java.awt.Color(0, 204, 0));
        libres.setText("0");
        jPanel4.add(libres, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 151, 20));

        Label_ciCliente4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Label_ciCliente4.setForeground(new java.awt.Color(102, 102, 102));
        Label_ciCliente4.setText("Código");
        Label_ciCliente4.setToolTipText("");
        jPanel4.add(Label_ciCliente4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 102, -1));

        txtcod.setEditable(false);
        jPanel4.add(txtcod, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 210, -1));

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("")));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(80, 80, 97));
        jLabel4.setText("Datos del cliente");
        jLabel4.setToolTipText("");

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

        Label_ciCliente5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Label_ciCliente5.setForeground(new java.awt.Color(102, 102, 102));
        Label_ciCliente5.setText("Fecha");
        Label_ciCliente5.setToolTipText("");

        txtfechaHoras.setEditable(false);
        txtfechaHoras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfechaHorasActionPerformed(evt);
            }
        });
        txtfechaHoras.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtfechaHorasKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtfechaHorasKeyTyped(evt);
            }
        });

        txhora.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txhora.setEnabled(false);

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setText("Hora:");

        Label_ciCliente1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Label_ciCliente1.setForeground(new java.awt.Color(102, 102, 102));
        Label_ciCliente1.setText("Placa");
        Label_ciCliente1.setToolTipText("");

        txtplaca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtplacaKeyTyped(evt);
            }
        });

        cbotipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "AUTOMOVIL", "BUS", "BUS ESCOLAR", "CAMION", "CAMIONETA", "FURGONETA", "MOTOCICLETA" }));
        cbotipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbotipoActionPerformed(evt);
            }
        });

        Label_ciCliente2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Label_ciCliente2.setForeground(new java.awt.Color(102, 102, 102));
        Label_ciCliente2.setText("Modelo");
        Label_ciCliente2.setToolTipText("");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(txhora))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Label_ciCliente2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(Label_ciCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                            .addComponent(jLabel4)
                                            .addGap(2, 2, 2)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtci, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                            .addComponent(Label_ciCliente5, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                            .addComponent(Label_ciCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(67, 67, 67)))
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtfechaHoras, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                                        .addComponent(txtplaca)
                                        .addComponent(cbotipo, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel4)
                .addGap(31, 31, 31)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label_ciCliente)
                    .addComponent(txtci, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtplaca)
                    .addComponent(Label_ciCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label_ciCliente5)
                    .addComponent(txtfechaHoras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label_ciCliente2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbotipo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txhora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(42, 42, 42))
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
        jScrollPane2.setViewportView(tbclientes);

        btnbuscar.setText("Mostrar Todos");
        btnbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarActionPerformed(evt);
            }
        });

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

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel5.setText("Máximo estacionamientos :");

        lbmax.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbmax.setText("0");

        lbid.setForeground(new java.awt.Color(240, 240, 240));
        lbid.setText("jLabel1");

        lbplaca.setForeground(new java.awt.Color(240, 240, 240));
        lbplaca.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 849, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbmax, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbid)
                        .addGap(30, 30, 30)
                        .addComponent(lbplaca))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnbuscar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnbuscar)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbmax, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbid)
                    .addComponent(lbplaca))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
// TODO add your handling code here:
    bloquear();
    txhora.setText(reloj.getText());
}//GEN-LAST:event_btncancelarActionPerformed

private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
// TODO add your handling code here:
    if (libres.getText().equalsIgnoreCase("NO HAY DISPONIBLES")) {
        JOptionPane.showMessageDialog(null, "No hay espacios disponibles");
    } else {
        txhora.setText(reloj.getText());
        desbloquear();
        
        //txtcod.requestFocus();
        String cod, tip, ci, fech, pla;

        cod = txtcod.getText();
        fech = txtfechaHoras.getText();

        tip = cbotipo.getSelectedItem().toString();
        pla = txtplaca.getText().toUpperCase();

        ci = txtci.getText();

        try {
            Conexion j = new Conexion();
            j.conectar();
            String sql = "INSERT INTO cliente_hora(ci,placa,cod_barras,tipo_vehiculo,fecha) VALUES('"
                    + ci + "','"
                    + pla + "','"
                    + cod + "','"
                    + tip + "','"
                    + fech + "')";
            j.insertar(sql);

            buscarCliente(txtci.getText());
            JOptionPane.showMessageDialog(null, "Guardado exitosamente");

            cargar("");
            desbloquear();
           
            this.btnguardar.setEnabled(false);
            this.btncancelar.setEnabled(false);
            this.btnsalir.setEnabled(false);
        } catch (Exception e) {
            System.out.println("ERROR");
        }
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
    if (txtci.getText().length() >= 10) {
        evt.consume();
    }
    if ((car < '0' || car > '9')) {
        evt.consume();
    }
}//GEN-LAST:event_txtciKeyTyped

private void txtplacaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtplacaKeyTyped
 char car = evt.getKeyChar();
    if (txtplaca.getText().length() > 7) {
        evt.consume();
    }
    
}//GEN-LAST:event_txtplacaKeyTyped

private void txtciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtciActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_txtciActionPerformed

private void mneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mneliminarActionPerformed
// TODO add your handling code here:
    int fila = tbclientes.getSelectedRow();
    String ci = "";
    ci = tbclientes.getValueAt(fila, 0).toString();
    if (fila >= 0) {
        try {
            Conexion j = new Conexion();
            j.conectar();
            String sql = "DELETE FROM cliente_hora WHERE id='" + ci + "'";
            j.eliminar(sql);

            cargar("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    } else {
        JOptionPane.showMessageDialog(this, "No ha selecionada ninguna fila");
    }

}//GEN-LAST:event_mneliminarActionPerformed

    private void txtfechaHorasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfechaHorasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfechaHorasActionPerformed

    private void txtfechaHorasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfechaHorasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfechaHorasKeyPressed

    private void txtfechaHorasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfechaHorasKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfechaHorasKeyTyped

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
        // TODO add your handling code here:
        limpiar();
        txhora.setText(reloj.getText());
    }//GEN-LAST:event_btnsalirActionPerformed

    public void actualizarEspacios() {
        try {

            Conexion j = new Conexion();
            j.conectar();
            j.editar("UPDATE disponibles SET libre_horas='" + (Integer.parseInt(libres.getText())) + "' WHERE id='1'");

        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    void VerDisponibles() {
        int espacios = 0;
        int mx = 0;

        Conexion j = new Conexion();
        j.conectar();
        sql = j.sql;
        try {
            String registros = "";

            s = sql.createStatement();
            String ConsultaSQL = "SELECT * FROM disponibles";

            ResultSet rs = s.executeQuery(ConsultaSQL);

            while (rs.next()) {
                registros = rs.getString("libre_horas");
            }
            System.out.println("librea horas ==>>> " + registros);
            espacios = Integer.parseInt(registros) + 1;
            System.out.println("espacios horas ==>>> " + espacios);

            mx = maxi();
            System.out.println("maximo horas ==>>> " + mx);
            //comparo si hay espacios
            if (espacios > mx) {
                this.libres.setText("NO HAY DISPONIBLES");
                this.libres.setBackground(Color.RED);

            } else {
                this.libres.setText(String.valueOf(espacios));
                this.libres.setBackground(Color.GREEN);
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
                reg = rss.getString("est_horas");
            }
            maximo = Integer.parseInt(reg);
            lbmax.setText(String.valueOf(maximo));
            return maximo;
        } catch (Exception e) {
            return maximo;
        }

    }

    public void buscarCliente(String id) {
        String ids = "";
        String mostrar = "SELECT * FROM cliente_hora WHERE ci= '" + id + "'";
        Conexion j = new Conexion();
        j.conectar();
        sql = j.sql;

        try {

            s = sql.createStatement();
            ResultSet rs = s.executeQuery(mostrar);
            while (rs.next()) {
                ids = rs.getString("id");

            }
            lbid.setText(ids);
            lbplaca.setText(txtplaca.getText());
        } catch (SQLException ex) {
            Logger.getLogger(IngresoClienteHoras.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Ticket() {
        String tip, horaa, ci, fech, pla,cod;

        fech = txtfechaHoras.getText();
        tip = cbotipo.getSelectedItem().toString();
        pla = txtplaca.getText().toUpperCase();
        horaa = txhora.getText().toUpperCase();
        ci = txtci.getText();
        cod = txtcod.getText();
        System.out.println(cod + "  " + pla + "  " + tip + "  " + horaa + "  " + fech);

        hc = new Conexion();

//        try {
//            // TODO add your handling code here:
//            List<ClienteHoraTicket> listaclientes = new ArrayList<ClienteHoraTicket>();
//            int filas = tbclientes.getRowCount();
//            //String cod_barras, String ci, String nombre, String apellido, String direccion, String placa, String modelo, String color
//            for (int i = 0; i < filas; i++) {
//                ClienteHoraTicket p = new ClienteHoraTicket(ci, pla, tip, horaa, fech);
//                listaclientes.add(p);
//            }
//
//            System.out.println(ci + "  " + pla + "  " + tip + "  " + horaa + "  " + fech);
//            JasperReport reporte = (JasperReport) JRLoader.loadObject("Barras.jasper");
//
//            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, new JRBeanCollectionDataSource(listaclientes));
//            JasperViewer.viewReport(jasperPrint, false);
//            JRExporter exporter = new JRPdfExporter();
//            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
//            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("Barras.pdf"));
//            exporter.exportReport();
//            System.out.println(ci + "  " + pla + "  " + tip + "  " + horaa + "  " + fech);
//        } catch (JRException ex) {
//            Logger.getLogger(ConsultasRegistrosClientes.class.getName()).log(Level.SEVERE, null, ex);
//        }
        try {
            hc = new Conexion();
            JasperReport reporte = (JasperReport) JRLoader.loadObject("DATABASE.jasper");
            Map parametro = new HashMap();
            parametro.put("id2", cod);

            JasperPrint j = JasperFillManager.fillReport(reporte, parametro, Main.hc.sql);
            JasperViewer jv = new JasperViewer(j, false);
            jv.setTitle("Reporte Clientes Registrados");
            jv.setVisible(true);

        } catch (Exception e) {
            System.out.printf(e.getMessage());
        }
    }


    private void btnticketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnticketActionPerformed
        String cod, fech, nom, horaa, estado, ci, pla, idclient;
        int puest;

        if (libres.getText().equalsIgnoreCase("NO HAY DISPONIBLES")) {
            JOptionPane.showMessageDialog(null, "No hay espacios disponibles");
        } else {
            if (txtci.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Complete todos los campos");
            } else {
                cod = txtcod.getText().toUpperCase();
                ci = lbid.getText();
                idclient = txtcod.getText();

                pla = lbplaca.getText().toUpperCase();
                fech = txtfechaHoras.getText().toUpperCase();
                horaa = txhora.getText().toUpperCase();

                estado = cboentrada.getSelectedItem().toString();

                try {
                    Conexion j = new Conexion();
                    j.conectar();
                    String sql = "INSERT INTO entradashoras(id_hora,placa,cod_barras,hora_llegada,hora_salida,fecha,estado) VALUES('"
                            + ci + "','"
                            + pla + "','"
                            + cod + "','"
                            + horaa + "','"
                            + "" + "','"
                            + fech + "','"
                            + estado + "')";
                    j.insertar(sql);
                    System.out.println("EXITO");
                    //JOptionPane.showMessageDialog(null, "Guardado exitosamente");
                   txtci.requestFocus();
                    Ticket();
                     codigosclientes();
                    limpiar();
                    actualizarEspacios();
                    VerDisponibles();
                    cargar("");
                    codigosclientes();
                    this.btnguardar.setEnabled(true);
                    this.btncancelar.setEnabled(true);
                    this.btnsalir.setEnabled(true);
                    this.btnticket.setEnabled(false);
                    
                } catch (Exception e) {
                    System.out.println(e);
                }

            }

        }

    }//GEN-LAST:event_btnticketActionPerformed

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnbuscarActionPerformed

    private void cbotipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbotipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbotipoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Label_ciCliente;
    private javax.swing.JLabel Label_ciCliente1;
    private javax.swing.JLabel Label_ciCliente2;
    private javax.swing.JLabel Label_ciCliente4;
    private javax.swing.JLabel Label_ciCliente5;
    private javax.swing.JButton btnbuscar;
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnsalir;
    private javax.swing.JButton btnticket;
    private javax.swing.JComboBox cboentrada;
    private javax.swing.JComboBox cbotipo;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbid;
    private javax.swing.JLabel lbmax;
    private javax.swing.JLabel lbplaca;
    public static javax.swing.JLabel libres;
    private javax.swing.JMenuItem mneliminar;
    private javax.swing.JTable tbclientes;
    public static javax.swing.JTextField txhora;
    private javax.swing.JTextField txtbuscar;
    private javax.swing.JTextField txtci;
    private javax.swing.JTextField txtcod;
    private javax.swing.JTextField txtfechaHoras;
    private javax.swing.JTextField txtplaca;
    // End of variables declaration//GEN-END:variables

}
