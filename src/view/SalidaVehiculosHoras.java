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
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import mainGarage.Main;
import static mainGarage.Main.hc;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import static view.IngresoClienteHoras.txhora;

/**
 *
 * @author Administrador
 */
public class SalidaVehiculosHoras extends javax.swing.JInternalFrame implements Runnable {

    /**
     * Creates new form Factura
     */
    public static Connection sql;
    public static Statement s;

    String hora, minutos, segundos, ampm;
    Calendar calendario;
    Thread h1;

    public SalidaVehiculosHoras() {
        initComponents();

        this.setLocation(25, 15);
        h1 = new Thread(this);
        h1.start();
        txtfec.setEnabled(false);
        txtfec.setDisabledTextColor(Color.blue);
        txtfec.setText(fechaactual());
        this.btnsalir1.setEnabled(false);
        this.jButton2.setEnabled(false);
        VerDisponibles();
        txtcod.requestFocus(true);

    }

    @Override
    public void run() {
        Thread ct = Thread.currentThread();
        while (ct == h1) {
            calcula();
            reloj_salida.setText(hora + ":" + minutos + ":" + segundos + " " + ampm);
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

    void cargar(String fecha) {
        DefaultTableModel modelo = new DefaultTableModel();
        // String mostrar = "SELECT * FROM entradas WHERE CONCAT(ci,cod_barras,hora_llegada,hora_salida,fecha,estado,numero,placa) LIKE '%" + fecha + "%'";
        String mostrar = "SELECT * FROM entradas WHERE fecha='" + fecha + "'";
        String[] Titulos = {"CI", "CÓDIGO", "PLACA", "ESTADO", "FECHA", "HORA ENTRADA", "HORA SALIDA", "PUESTO"};
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
                registros[7] = rs.getString("numero");

                modelo.addRow(registros);
            }
            tbdet.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(IngresoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    int id_entrada;

    public String cargarHoraPagado(String ci) {
        DefaultTableModel modelo = new DefaultTableModel();
        // String mostrar = "SELECT * FROM entradas WHERE CONCAT(ci,cod_barras,hora_llegada,hora_salida,fecha,estado,numero,placa) LIKE '%" + fecha + "%'";
        String mostrar = "SELECT cliente_hora.ci,entradashoras.id,entradashoras.cod_barras,entradashoras.placa,entradashoras.estado,entradashoras.fecha,entradashoras.hora_llegada,entradashoras.hora_salida FROM entradashoras,cliente_hora WHERE entradashoras.cod_barras='" + ci + "'AND entradashoras.estado = 'Salida' AND entradashoras.id_hora = cliente_hora.id";
        String[] Titulos = {"CI", "CÓDIGO", "PLACA", "ESTADO", "FECHA", "HORA ENTRADA", "HORA SALIDA"};
        modelo.setColumnIdentifiers(Titulos);
        this.tbdet.setModel(modelo);
        String fechas = "";

        Conexion j = new Conexion();
        j.conectar();
        sql = j.sql;

        try {

            String[] registros = new String[8];
            s = sql.createStatement();
            ResultSet rs = s.executeQuery(mostrar);

            while (rs.next()) {
                id_entrada = rs.getInt("id");
                registros[0] = rs.getString("ci");
                registros[1] = rs.getString("cod_barras");
                registros[2] = rs.getString("placa");
                registros[3] = rs.getString("estado");
                registros[4] = rs.getString("fecha");
                registros[5] = rs.getString("hora_llegada");
                fechas = registros[5];
                registros[6] = rs.getString("hora_salida");

            }

            if (registros[5] == "") {
                JOptionPane.showMessageDialog(null, "No se encontro registro de entrada");
            } else {
                modelo.addRow(registros);
                tbdet.setModel(modelo);
            }

            return fechas;
        } catch (SQLException ex) {

            Logger.getLogger(IngresoCliente.class.getName()).log(Level.SEVERE, null, ex);
            return fechas;
        }

    }

    public String cargarHora(String ci) {
        DefaultTableModel modelo = new DefaultTableModel();
        // String mostrar = "SELECT * FROM entradas WHERE CONCAT(ci,cod_barras,hora_llegada,hora_salida,fecha,estado,numero,placa) LIKE '%" + fecha + "%'";
        String mostrar = "SELECT cliente_hora.ci,entradashoras.id,entradashoras.cod_barras,entradashoras.placa,entradashoras.estado,entradashoras.fecha,entradashoras.hora_llegada,entradashoras.hora_salida FROM entradashoras,cliente_hora WHERE entradashoras.cod_barras='" + ci + "'AND entradashoras.estado = 'Entrada' AND entradashoras.id_hora = cliente_hora.id";
        String[] Titulos = {"CI", "CÓDIGO", "PLACA", "ESTADO", "FECHA", "HORA ENTRADA", "HORA SALIDA"};
        modelo.setColumnIdentifiers(Titulos);
        this.tbdet.setModel(modelo);
        String fechas = "";

        Conexion j = new Conexion();
        j.conectar();
        sql = j.sql;

        try {

            String[] registros = new String[8];
            s = sql.createStatement();
            ResultSet rs = s.executeQuery(mostrar);

            while (rs.next()) {
                id_entrada = rs.getInt("id");
                registros[0] = rs.getString("ci");
                registros[1] = rs.getString("cod_barras");
                registros[2] = rs.getString("placa");
                registros[3] = rs.getString("estado");
                registros[4] = rs.getString("fecha");
                registros[5] = rs.getString("hora_llegada");
                fechas = registros[5];
                registros[6] = rs.getString("hora_salida");

            }

            if (registros[5] == "") {
                JOptionPane.showMessageDialog(null, "No se encontro registro de entrada");
            } else {
                modelo.addRow(registros);
                tbdet.setModel(modelo);
            }

            return fechas;
        } catch (SQLException ex) {

            Logger.getLogger(IngresoCliente.class.getName()).log(Level.SEVERE, null, ex);
            return fechas;
        }

    }

    // cargar cancelacion  a pagar
    public void cargarHoraPago(String ci) {
        DefaultTableModel modelo = new DefaultTableModel();
        // String mostrar = "SELECT * FROM entradas WHERE CONCAT(ci,cod_barras,hora_llegada,hora_salida,fecha,estado,numero,placa) LIKE '%" + fecha + "%'";
        String mostrar = "SELECT pagoshoras.cod_barras, pagoshoras.horas,pagoshoras.tota,pagoshoras.fecha,pagoshoras.tipo_cliente FROM pagoshoras,entradashoras WHERE pagoshoras.cod_barras='" + ci + "'AND pagoshoras.id_entrada = entradashoras.id";
        String[] Titulos = {"CÓDIGO", "TOTAL HORAS", "VALOR TOTAL", "FECHA", "TIPO CLIENTE"};
        modelo.setColumnIdentifiers(Titulos);
        this.tbsalida.setModel(modelo);

        Conexion j = new Conexion();
        j.conectar();
        sql = j.sql;

        try {

            String[] registros = new String[5];
            s = sql.createStatement();
            ResultSet rs = s.executeQuery(mostrar);

            while (rs.next()) {
                registros[0] = rs.getString("cod_barras");
                registros[1] = rs.getString("horas");
                registros[2] = rs.getString("tota");
                registros[3] = rs.getString("fecha");
                registros[4] = rs.getString("tipo_cliente");

                modelo.addRow(registros);
            }

            tbsalida.setModel(modelo);

        } catch (SQLException ex) {
            Logger.getLogger(IngresoCliente.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

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

            espacios = Integer.parseInt(registros) - 1;

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

    public float precioEsta() {
        float maximo = 0;
        try {
            String reg = "";

            Conexion j = new Conexion();
            j.conectar();
            sql = j.sql;
            s = sql.createStatement();
            String ConsultaSQL2 = "SELECT * FROM estacionamientos";
            ResultSet rss = s.executeQuery(ConsultaSQL2);
            while (rss.next()) {
                reg = rss.getString("est_precio");
            }
            maximo = (float) Double.parseDouble(reg);

            return maximo;
        } catch (Exception e) {
            return maximo;
        }

    }

    public void calculos() {

    }

    public int getHora(String hour) {
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
            unidad = Integer.parseInt(String.valueOf(r2));
            horass = decimal + unidad;
            if (tiempo.equals("PM")) {
                horass = horass + 12;

            }

            return horass;

        } catch (Exception ex) {
            System.out.println(ex);
            return horass;
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
        txtplaca = new javax.swing.JTextField();
        txtcod = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtci = new javax.swing.JTextField();
        txhoraENTRADA = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtfec = new javax.swing.JTextField();
        cboentrada = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        libres = new javax.swing.JLabel();
        horasalida = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        lbcod = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnsalir1 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbdet = new javax.swing.JTable();
        lbmax = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        reloj_salida = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lbhorasT = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lbpago = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbsalida = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();

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
        setTitle("SALIDA DE VEHÍCULOS POR HORAS");
        setPreferredSize(new java.awt.Dimension(0, 0));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        txtplaca.setEditable(false);
        txtplaca.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtplaca.setEnabled(false);

        txtcod.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtcod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcodActionPerformed(evt);
            }
        });
        txtcod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcodKeyTyped(evt);
            }
        });

        jLabel11.setText("Código:");

        txtci.setEditable(false);
        txtci.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtci.setForeground(new java.awt.Color(51, 51, 255));
        txtci.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtciActionPerformed(evt);
            }
        });
        txtci.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtciKeyTyped(evt);
            }
        });

        txhoraENTRADA.setEditable(false);
        txhoraENTRADA.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txhoraENTRADA.setEnabled(false);

        jLabel10.setText("Entrada:");

        jLabel9.setText("CI:");

        jLabel13.setText("Placa:");

        jLabel12.setText("Fecha:");

        cboentrada.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Salida" }));
        cboentrada.setEnabled(false);

        jLabel16.setText("Estado:");

        jLabel3.setForeground(new java.awt.Color(240, 240, 240));
        jLabel3.setText("Puesto:");

        libres.setForeground(new java.awt.Color(240, 240, 240));
        libres.setText("0");

        horasalida.setEditable(false);

        jLabel4.setText("Salida:");

        lbcod.setText("codigo");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtplaca, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                            .addComponent(horasalida)
                            .addComponent(txhoraENTRADA)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtcod, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboentrada, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtci)
                                    .addComponent(txtfec))))
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(lbcod, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(libres)
                        .addGap(88, 88, 88))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtcod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txhoraENTRADA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(horasalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtplaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtfec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtci, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboentrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbcod, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(libres, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnsalir1.setText("Cancelar");
        btnsalir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalir1ActionPerformed(evt);
            }
        });

        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Comprobante");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                    .addComponent(btnsalir1, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnsalir1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        tbdet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CI", "Código", "Placa", "Estado", "Fecha", "Hora entrada", "Hora salida", "Puesto"
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

        reloj_salida.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        reloj_salida.setText("0");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel6.setText("Hora salida:");

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lbhorasT.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbhorasT.setForeground(new java.awt.Color(0, 204, 0));
        lbhorasT.setText("0");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 204, 0));
        jLabel14.setText("$ Total");

        lbpago.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lbpago.setForeground(new java.awt.Color(0, 204, 0));
        lbpago.setText("0.0");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 204, 0));
        jLabel17.setText("Horas : ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbhorasT, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbpago, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbpago)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbhorasT)
                    .addComponent(jLabel17))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbsalida.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CI", "Código", "Placa", "Estado", "Fecha", "Hora entrada", "Hora salida", "Puesto"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tbsalida);

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel15.setText("Entrada");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel18.setText("Pagos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(lbmax, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(reloj_salida, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane3))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(reloj_salida, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbmax, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40))
        );

        setBounds(0, 0, 1065, 557);
    }// </editor-fold>//GEN-END:initComponents
public static String fechaactual() {
        Date fecha = new Date();
        SimpleDateFormat formatofecha = new SimpleDateFormat("YYYY-MM-dd");
        return formatofecha.format(fecha);

    }
// gauardar entrada
    private void IngresoPagos() {
        String cod, fech, pla, tipo_clien = "Horas";
        double tota = 0;
        int horaa;

        cod = txtcod.getText().toUpperCase();

        fech = txtfec.getText().toUpperCase();
        int entrada = getHora(txhoraENTRADA.getText());
        int salida = getHora(horasalida.getText());
        float costo = precioEsta();

        int total = salida - entrada;

        if (total == 0) {
            total = 1;
            horaa = total;
            tota = costo;
        } else {
            horaa = total;
            costo = costo * total;
            tota = costo;
        }

        try {
            Conexion j = new Conexion();
            j.conectar();
            String sql = "INSERT INTO pagoshoras(cod_barras,horas,tota,fecha,tipo_cliente,id_entrada) VALUES('"
                    + cod + "','"
                    + horaa + "','"
                    + tota + "','"
                    + fech + "','"
                    + tipo_clien + "','"
                    + id_entrada + "')";
            j.insertar(sql);
            System.out.println("EXITO pago");
            JOptionPane.showMessageDialog(null, "Guardado exitosamente");

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private void btnsalir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalir1ActionPerformed

        int entrada = getHora(txhoraENTRADA.getText());
        int salida = getHora(horasalida.getText());
        float costo = precioEsta();

        int total = salida - entrada;

        if (txhoraENTRADA.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "NO SE ENCONTRARON REGISTROS");
            this.jButton1.setEnabled(true);

            this.btnsalir1.setEnabled(false);
            this.jButton2.setEnabled(false);
            this.txtcod.setEditable(true);
            this.txtcod.requestFocus(true);
            limpiar();
        } else {

            if (total == 0) {
                total = 1;
            } else {
                costo = costo * total;
            }
            try {
                lbpago.setText(String.valueOf(costo));
                lbhorasT.setText(String.valueOf(total));
                Conexion j = new Conexion();
                j.conectar();
                j.editar("UPDATE entradashoras SET estado='" + cboentrada.getSelectedItem().toString() + "',hora_salida='" + horasalida.getText() + "' WHERE cod_barras='" + txtcod.getText() + "' AND estado = 'Entrada'");
                IngresoPagos();
                actualizarEspacios();
                VerDisponibles();

                cargarHoraPago(txtcod.getText());

                cargarHoraPagado(txtcod.getText());
                this.btnsalir1.setEnabled(false);
                this.jButton2.setEnabled(true);
                lbcod.setText(txtcod.getText());
                limpiar();

            } catch (Exception e) {
                System.out.print(e.getMessage());
            }
        }
    }//GEN-LAST:event_btnsalir1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        txhoraENTRADA.setText(cargarHora(txtcod.getText()));
        this.jButton1.setEnabled(false);
        this.btnsalir1.setEnabled(true);
        this.txtcod.setEditable(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    public String[] getCliente(String valor) {
        String mostrar = "SELECT * FROM cliente_hora WHERE cod_barras='" + valor + "'";
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
                registros[2] = rs.getString("placa");

            }

            return registros;

        } catch (SQLException ex) {

            Logger.getLogger(IngresoCliente.class.getName()).log(Level.SEVERE, null, ex);
            return registros;

        }

    }

    private void txtciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtciActionPerformed

    }//GEN-LAST:event_txtciActionPerformed

    private void txtciKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtciKeyTyped
        char car = evt.getKeyChar();
        if (txtci.getText().length() >= 10) {
            evt.consume();
        }
        if ((car < '0' || car > '9')) {
            evt.consume();
        }// TODO add your handling code here:
    }//GEN-LAST:event_txtciKeyTyped

    private void txtcodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcodActionPerformed
        String datos[] = new String[9];

        datos = getCliente(txtcod.getText());
        horasalida.setText(reloj_salida.getText());
        txtcod.setText(datos[0]);
        txtci.setText(datos[1]);
        txtplaca.setText(datos[2]);
    }//GEN-LAST:event_txtcodActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        String cod;

        cod = lbcod.getText();

        this.jButton1.setEnabled(true);

        this.btnsalir1.setEnabled(false);
        this.jButton2.setEnabled(false);
        this.txtcod.setEditable(true);
        this.txtcod.requestFocus(true);

        hc = new Conexion();
        try {
            hc = new Conexion();
            JasperReport reporte = (JasperReport) JRLoader.loadObject("Factura.jasper");
            Map parametro = new HashMap();
            parametro.put("codigo", cod);

            JasperPrint j = JasperFillManager.fillReport(reporte, parametro, Main.hc.sql);
            JasperViewer jv = new JasperViewer(j, false);
            jv.setTitle("Comprobante de pago");
            jv.setVisible(true);

        } catch (Exception e) {
            System.out.printf(e.getMessage());
        }


    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtcodKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcodKeyTyped
        char car = evt.getKeyChar();
        if (txtcod.getText().length() >= 10) {
            evt.consume();
        }
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtcodKeyTyped

    public void limpiar() {
        txtcod.setText("");
        txtci.setText("");

        txtplaca.setText("");
        txhoraENTRADA.setText("");
        horasalida.setText("");
        VerDisponibles();

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnsalir1;
    private javax.swing.JComboBox cboentrada;
    private javax.swing.JTextField horasalida;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
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
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbcod;
    private javax.swing.JLabel lbhorasT;
    private javax.swing.JLabel lbmax;
    private javax.swing.JLabel lbpago;
    public static javax.swing.JLabel libres;
    private javax.swing.JLabel reloj_salida;
    public static javax.swing.JTable tbdet;
    public static javax.swing.JTable tbsalida;
    public static javax.swing.JTextField txhoraENTRADA;
    public static javax.swing.JTextField txtci;
    public static javax.swing.JTextField txtcod;
    private javax.swing.JTextField txtfec;
    public static javax.swing.JTextField txtplaca;
    // End of variables declaration//GEN-END:variables

}
