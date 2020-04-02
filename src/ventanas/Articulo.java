/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import clases.Conexion;
import clases.Marca;
import clases.Rubro;
import clases.SubRubro;
import clases.SupRubro;
import clases.TablaArticulo;
import com.itextpdf.text.DocumentException;
import static com.sun.javafx.scene.control.skin.Utils.getResource;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.WindowConstants;

/**
 *
 * @author USUARIO
 */
public class Articulo extends javax.swing.JFrame {

    /**
     * Creates new form Articulo
     */
    SupRubro obsupr = new SupRubro();
    Rubro obrub = new Rubro();
    SubRubro obsubr = new SubRubro();
    Marca obmar = new Marca();
    int filaseleccionada;
    public static String idarticulo = "";
    public static String bandera = "";
    public static String usuarioupdate = "";
    String sql;
    String usuario, valor1, valor2, valor3, valor4;
    int idart;
    int suprubro;
    int rubro;
    int subrubro;
    int numsupr;
    int numr;
    int numsubr;
    int numm;
    String filtro1="Libreria", filtro2="Accesorios de escritorio", filtro3="Carpetas escolares", filtro4="Rivadavia";
    //lo declaro como público para poder usarlo en la búsqueda de articulos
    DefaultTableModel model;

    public Articulo() {
        initComponents();
        setSize(1340, 698);
        setResizable(false);
        usuario = Login.user;
        setTitle("Usuario registrado - Sesión de " + usuario);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        //Llamo a la clase TablaArticulo para cambiar color de letra de la fila seleccionada
        jTableArticulo.setDefaultRenderer(Object.class, new TablaArticulo());
        ImageIcon wallpaper = new ImageIcon("src/images/FondoAzul.png");// Tapis de fondo
        Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(jLabelFondo.getWidth(),
                jLabelFondo.getHeight(), Image.SCALE_DEFAULT));
        jLabelFondo.setIcon(icono);
        this.repaint();
        numsupr = 0;
        numr = 0;

        //Cargo ComboBox SupRubro
        DefaultComboBoxModel modelSupRubro = new DefaultComboBoxModel(obsupr.mostrarSuprubro());
        jComboBoxSupRubro.setModel(modelSupRubro);

        //Cargo ComboBox Rubro
        DefaultComboBoxModel modelRubro = new DefaultComboBoxModel(obrub.mostrarRubro(numsupr));
        jComboBoxRubro.setModel(modelRubro);

        //Cargo ComboBox SubRubro
        DefaultComboBoxModel modelSubRubro = new DefaultComboBoxModel(obsubr.mostrarSubrubro(numr));
        jComboBoxSubRubro.setModel(modelSubRubro);

        //Cargo ComboBox Marca
        DefaultComboBoxModel modelMarca = new DefaultComboBoxModel(obmar.mostrarMarca());
        jComboBoxMarca.setModel(modelMarca);

        try {
            //Realizo la conexión a la base de datos
            Connection cn;
            cn = Conexion.conectar();
            PreparedStatement pst = null;
            sql = "select * from articulo as ar\n"
                    + "			  LEFT JOIN superrubro as sru ON ar.art_idsuprubro=sru.suprub_idsuprubro\n"
                    + "			  LEFT JOIN rubro as ru       ON ar.art_idrubro=ru.rub_idrubro\n"
                    + "			  LEFT JOIN subrubro as sr    ON ar.art_idsubrubro=sr.subru_idsubrubro\n"
                    + "			  LEFT JOIN marca as ma       ON ar.art_idmarca=ma.mar_idmarca where 1";

            pst = cn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            //Carga de la tabla de articulos
            DefaultTableModel modelo = new DefaultTableModel();
            modelo.setColumnIdentifiers(new Object[]{"Nro articulo", "Suprubro", "Rubro", "Subrubro", "Nombre", "Marca", "Precio final", "Stock"});
            while (rs.next()) {
                // asigna el modelo a la tabla
                this.jTableArticulo.setModel(modelo);

                // añade los resultado al modelo de tabla
                modelo.addRow(new Object[]{rs.getString("art_idarticulo"), rs.getString("suprub_nombre"), rs.getString("rub_nombre"), rs.getString("subru_nombre"), rs.getString("art_nombre"), rs.getString("mar_nombremarca"), rs.getString("art_preciofinal"), rs.getString("art_stock")});
            }

            if (rs.next()) {
                numsupr = rs.getInt("art_idsuprubro");
                jComboBoxSupRubro.setSelectedIndex(numsupr);
                numr = rs.getInt("art_idrubro");
                jComboBoxRubro.setSelectedIndex(numr);
                numsubr = rs.getInt("art_idsubrubro");
                jComboBoxSubRubro.setSelectedIndex(numsubr);
                numm = rs.getInt("art_idmarca");
                jComboBoxMarca.setSelectedIndex(numm);

            }
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Articulo.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Es para abrir ArticuloABM haciendo click con el mouse en cualquier fila y la columna 5
        jTableArticulo.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                int fila_point = jTableArticulo.rowAtPoint(e.getPoint());
                int columna_point = 5;
                if (fila_point >-1){
                    try {
                        usuarioupdate = (String) model.getValueAt(fila_point, columna_point);
                        ArticuloABM infousuario = new ArticuloABM();
                        infousuario.setVisible(true);
                    } catch (DocumentException ex) {
                        Logger.getLogger(Articulo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

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
        jTableArticulo = new javax.swing.JTable();
        jComboBoxSubRubro = new javax.swing.JComboBox<>();
        jLabelMarca = new javax.swing.JLabel();
        jButtonAgregar = new javax.swing.JButton();
        jButtonModificar = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();
        jLabelBuscar = new javax.swing.JLabel();
        jLabelSupRubro = new javax.swing.JLabel();
        jComboBoxSupRubro = new javax.swing.JComboBox<>();
        jLabelRubro = new javax.swing.JLabel();
        jComboBoxRubro = new javax.swing.JComboBox<>();
        jLabelSubRubro = new javax.swing.JLabel();
        jComboBoxMarca = new javax.swing.JComboBox<>();
        jTextFieldBuscar = new javax.swing.JTextField();
        jButtonBuscar = new javax.swing.JButton();
        jButtonSalir = new javax.swing.JButton();
        jLabelTitulo = new javax.swing.JLabel();
        jLabelFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTableArticulo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Nro articulo", "Suprubro", "Rubro", "Subrubro", "Nombre ", "Marca", "Precio final", "Stock", "Estado", "Código barras"
            }
        ));
        jTableArticulo.setGridColor(new java.awt.Color(240, 240, 240));
        jTableArticulo.setSelectionForeground(new java.awt.Color(255, 0, 51));
        jTableArticulo.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTableArticulo);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 1290, 360));

        jComboBoxSubRubro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSubRubroActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBoxSubRubro, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 90, 190, 40));

        jLabelMarca.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelMarca.setForeground(new java.awt.Color(51, 153, 255));
        jLabelMarca.setText("Marca");
        getContentPane().add(jLabelMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 70, -1, -1));

        jButtonAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add_insert_plus_1588.png"))); // NOI18N
        jButtonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 550, 100, 100));

        jButtonModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/editar.png"))); // NOI18N
        jButtonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificarActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 550, 100, 100));

        jButtonEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete_delete_exit_1577.png"))); // NOI18N
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 550, 100, 100));

        jLabelBuscar.setFont(new java.awt.Font("Perkeo", 1, 18)); // NOI18N
        jLabelBuscar.setText("Busqueda de Articulo");
        getContentPane().add(jLabelBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 570, 160, 24));

        jLabelSupRubro.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelSupRubro.setForeground(new java.awt.Color(51, 153, 255));
        jLabelSupRubro.setText("SupRubro");
        getContentPane().add(jLabelSupRubro, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, -1, -1));

        jComboBoxSupRubro.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxSupRubroItemStateChanged(evt);
            }
        });
        jComboBoxSupRubro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSupRubroActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBoxSupRubro, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 191, 40));

        jLabelRubro.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelRubro.setForeground(new java.awt.Color(51, 153, 255));
        jLabelRubro.setText("Rubro");
        getContentPane().add(jLabelRubro, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 70, -1, -1));

        jComboBoxRubro.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxRubroItemStateChanged(evt);
            }
        });
        jComboBoxRubro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxRubroActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBoxRubro, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 90, 190, 40));

        jLabelSubRubro.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelSubRubro.setForeground(new java.awt.Color(51, 153, 255));
        jLabelSubRubro.setText("SubRubro");
        getContentPane().add(jLabelSubRubro, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 70, -1, -1));

        jComboBoxMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxMarcaActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBoxMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 90, 190, 40));

        jTextFieldBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldBuscarMouseClicked(evt);
            }
        });
        jTextFieldBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldBuscarActionPerformed(evt);
            }
        });
        jTextFieldBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldBuscarKeyPressed(evt);
            }
        });
        getContentPane().add(jTextFieldBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 600, 200, 40));

        jButtonBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/buscar2.png"))); // NOI18N
        jButtonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 10, 130, 130));

        jButtonSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit_closethesession_close_6317.png"))); // NOI18N
        jButtonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalirActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 540, 100, 100));

        jLabelTitulo.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabelTitulo.setForeground(new java.awt.Color(51, 153, 255));
        jLabelTitulo.setText("Listado completo de Articulo");
        getContentPane().add(jLabelTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 20, 310, 30));
        getContentPane().add(jLabelFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, -70, 1500, 850));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxSubRubroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSubRubroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxSubRubroActionPerformed

    private void jButtonAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarActionPerformed
        try {
            // Insertar articulo nuevo
            idarticulo = "0";
            bandera = "1";
            ArticuloABM Art = new ArticuloABM();
            Art.setVisible(true);
            this.setVisible(false);
        } catch (DocumentException ex) {
            Logger.getLogger(Articulo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButtonAgregarActionPerformed

    private void jButtonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificarActionPerformed
        // TODO add your handling code here:
        filaseleccionada = jTableArticulo.getSelectedRow();
        if (filaseleccionada == -1) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado articulo.");
        } else {
            try {
                //JOptionPane.showMessageDialog(null, "Selecciono articulo.");
                idarticulo = (String) jTableArticulo.getValueAt(filaseleccionada, 0);
                bandera = "2";
                ArticuloABM Art = new ArticuloABM();
                Art.setVisible(true);
                this.setVisible(false);
            } catch (DocumentException ex) {
                Logger.getLogger(Articulo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonModificarActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        // TODO add your handling code here:
        //Seleccionar fila encontrando id de articulo
        filaseleccionada = jTableArticulo.getSelectedRow();
        if (filaseleccionada == -1) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado articulo.");
        } else {
            try {
                //JOptionPane.showMessageDialog(null, "Selecciono articulo.");
                idarticulo = (String) jTableArticulo.getValueAt(filaseleccionada, 0);
                bandera = "3";
                ArticuloABM Art = new ArticuloABM();
                Art.setVisible(true);
                this.setVisible(false);
            } catch (DocumentException ex) {
                Logger.getLogger(Articulo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jComboBoxSupRubroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxSupRubroItemStateChanged

    }//GEN-LAST:event_jComboBoxSupRubroItemStateChanged

    private void jComboBoxSupRubroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSupRubroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxSupRubroActionPerformed

    private void jComboBoxRubroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxRubroItemStateChanged

    }//GEN-LAST:event_jComboBoxRubroItemStateChanged

    private void jComboBoxRubroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxRubroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxRubroActionPerformed

    private void jComboBoxMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxMarcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxMarcaActionPerformed

    private void jTextFieldBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldBuscarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldBuscarMouseClicked

    private void jTextFieldBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBuscarKeyPressed
        // TODO add your handling code here:
        String[] titulos = {"Nro articulo", "Suprubro", "Rubro", "Subrubro", "Nombre", "Marca", "Precio final", "Stock"};
        String[] registros = new String[500];
        String sql;
        sql = "select * from articulo as ar\n"
                    + "LEFT JOIN superrubro as sru ON ar.art_idsuprubro=sru.suprub_idsuprubro\n"
                    + "LEFT JOIN rubro as ru       ON ar.art_idrubro=ru.rub_idrubro\n"
                    + "LEFT JOIN subrubro as sr    ON ar.art_idsubrubro=sr.subru_idsubrubro\n"
                    + "LEFT JOIN marca as ma       ON ar.art_idmarca=ma.mar_idmarca\n"
                    + "where art_idarticulo like '%" + jTextFieldBuscar.getText() + "%'"
                    + "or suprub_nombre like '%" + jTextFieldBuscar.getText() + "%'"
                    + "or rub_nombre like '%" + jTextFieldBuscar.getText() + "%'"
                    + "or subru_nombre like '%" + jTextFieldBuscar.getText() + "%'"
                    + "or art_nombre like '%" + jTextFieldBuscar.getText() + "%'"
                    + "or mar_nombremarca like '%" + jTextFieldBuscar.getText() + "%'";
        System.out.print(sql);
        model = new DefaultTableModel(null, titulos);
        try {
            Connection cn;
            cn = Conexion.conectar();
            PreparedStatement pst;
            pst = cn.prepareStatement(sql);
            System.out.println(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                registros[0] = rs.getString("art_idarticulo");
                registros[1] = rs.getString("suprub_nombre");
                registros[2] = rs.getString("rub_nombre");
                registros[3] = rs.getString("subru_nombre");
                registros[4] = rs.getString("art_nombre");
                registros[5] = rs.getString("mar_nombremarca");
                registros[6] = rs.getString("art_preciofinal");
                registros[7] = rs.getString("art_stock");
                model.addRow(registros);
            }
            jTableArticulo.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(Articulo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTextFieldBuscarKeyPressed

    private void jButtonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalirActionPerformed
        //Volver de donde vino clase Administrador
        Administrador Adm = new Administrador();
        Adm.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonSalirActionPerformed

    private void jTextFieldBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldBuscarActionPerformed

    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed

        //Busqueda filtrada
        String[] titulos = {"Nro articulo", "Suprubro", "Rubro", "Subrubro", "Nombre", "Marca", "Precio final", "Stock"};
        String[] registros = new String[500];
        
        if (jComboBoxSupRubro.getSelectedItem() == "Libreria") {
                filtro1 = "Libreria";
            } 
        if (jComboBoxSupRubro.getSelectedItem() == "Artistica") {
                filtro1 = "Artistica";
                System.out.print(filtro1);
            }
        if (jComboBoxSupRubro.getSelectedItem() == "Computación") {
                filtro1 = "Computación";
            } 
        if (jComboBoxSupRubro.getSelectedItem() == "Regalería") {
                filtro1 = "Regalería";
            } 
        if (jComboBoxSupRubro.getSelectedItem() == "Tarjetería") {
                filtro1 = "Tarjetería";
            } 
        if (jComboBoxSupRubro.getSelectedItem() == "Impresiones/Fotocopias") {
                filtro1 = "Impresiones/Fotocopias";
            } 
        if (jComboBoxSupRubro.getSelectedItem() == "Cotillón") {
                filtro1 = "Cotillón";
            } 
        if (jComboBoxSupRubro.getSelectedItem() == "Mercería") {
                filtro1 = "Mercería";
            }
        if (jComboBoxRubro.getSelectedItem() == "Accesorios de escritorio") {
                filtro2 = "Accesorios de escritorio";
            } 
        if (jComboBoxRubro.getSelectedItem() == "Adhesivos") {
                filtro2 = "Adhesivos";
            }
        if (jComboBoxRubro.getSelectedItem() == "Artículos Patrios") {
                filtro2 = "Artículos Patrios";
            } 
        if (jComboBoxRubro.getSelectedItem() == "Calculadoras") {
                filtro2 = "Calculadoras";
            } 
        if (jComboBoxRubro.getSelectedItem() == "Cartucheras") {
                filtro2 = "Cartucheras";
            }
        if (jComboBoxRubro.getSelectedItem() == "Carpetas y archivos") {
                filtro2 = "Carpetas y archivos";
            }  
        if (jComboBoxRubro.getSelectedItem() == "Marroquineria") {
                filtro2 = "Marroquineria";
            }  
        if (jComboBoxRubro.getSelectedItem() == "Pizarras y accesorios") {
                filtro2 = "Pizarras y accesorios";
            }  
        if (jComboBoxRubro.getSelectedItem() == "Papelería") {
                filtro2 = "Papelería";
            } 
        if (jComboBoxRubro.getSelectedItem() == "Folios") {
                filtro2 = "Folios";
            } 
        if (jComboBoxRubro.getSelectedItem() == "Escritura") {
                filtro2 = "Escritura";
            } 
        if (jComboBoxRubro.getSelectedItem() == "Encuadernación") {
                filtro2 = "Encuadernación";
            } 
        if (jComboBoxRubro.getSelectedItem() == "Embalaje") {
                filtro2 = "Embalaje";
            } 
        if (jComboBoxRubro.getSelectedItem() == "Geometría y dibujo") {
                filtro2 = "Geometría y dibujo";
            } 
        if (jComboBoxRubro.getSelectedItem() == "Didáctico y manualidades") {
                filtro2 = "Didáctico y manualidades";
            } 
        if (jComboBoxRubro.getSelectedItem() == "Cuadernos") {
                filtro2 = "Cuadernos";
            } 
        if (jComboBoxRubro.getSelectedItem() == "Libros") {
                filtro2 = "Libros";
            } 
        if (jComboBoxRubro.getSelectedItem() == "Repuestos") {
                filtro2 = "Repuestos";
            } 
        if (jComboBoxRubro.getSelectedItem() == "Tijeras") {
                filtro2 = "Tijeras";
            } 
        if (jComboBoxRubro.getSelectedItem() == "Resmas") {
                filtro2 = "Resmas";
            } 
        if (jComboBoxRubro.getSelectedItem() == "Accesorios") {
                filtro2 = "Accesorios";
            } 
        if (jComboBoxRubro.getSelectedItem() == "Pinturas") {
                filtro2 = "Pinturas";
            } 
        if (jComboBoxRubro.getSelectedItem() == "Herramientas") {
                filtro2 = "Herramientas";
            } 
        if (jComboBoxRubro.getSelectedItem() == "Pinceles") {
                filtro2 = "Pinceles";
            } 
        if (jComboBoxRubro.getSelectedItem() == "Técnicas Secas") {
                filtro2 = "Técnicas Secas";
            } 
        if (jComboBoxRubro.getSelectedItem() == "Accesorios Madera") {
                filtro2 = "Accesorios Madera";
            } 
        if (jComboBoxRubro.getSelectedItem() == "Masas Manualidades") {
                filtro2 = "Masas Manualidades";
            } 
        if (jComboBoxRubro.getSelectedItem() == "Decoopages") {
                filtro2 = "Decoopages";
            } 
        if (jComboBoxRubro.getSelectedItem() == "Accesorios y Cables") {
                filtro2 = "Accesorios y Cables";
            } 
        if (jComboBoxRubro.getSelectedItem() == "Accesorios Celulares") {
                filtro2 = "Accesorios Celulares";
            }

        if (jComboBoxSubRubro.getSelectedItem() == "Carpetas escolares") {
                filtro3 = "Carpetas escolares";
            } 
        if (jComboBoxSubRubro.getSelectedItem() == "Biblioratos") {
                filtro3 = "Biblioratos";
            } 
        if (jComboBoxSubRubro.getSelectedItem() == "Carpetas Universitarias") {
                filtro3 = "Carpetas Universitarias";
            } 
        if (jComboBoxSubRubro.getSelectedItem() == "Carpetas Dibujo") {
                filtro3 = "Carpetas Dibujo";
            } 
        if (jComboBoxSubRubro.getSelectedItem() == "Tapa dura") {
                filtro3 = "Tapa dura";
            } 
        if (jComboBoxSubRubro.getSelectedItem() == "Tapa flexible") {
                filtro3 = "Tapa flexible";
            } 
        if (jComboBoxSubRubro.getSelectedItem() == "Espiralado") {
                filtro3 = "Espiralado";
            } 
        if (jComboBoxSubRubro.getSelectedItem() == "Block") {
                filtro3 = "Block";
            } 
        if (jComboBoxSubRubro.getSelectedItem() == "Carpetas Oficina") {
                filtro3 = "Carpetas Oficina";
            } 
        if (jComboBoxSubRubro.getSelectedItem() == "Repuestos Escolares") {
                filtro3 = "Repuestos Escolares";
            } 
        if (jComboBoxSubRubro.getSelectedItem() == "Repuestos Universitarios") {
                filtro3 = "Repuestos Universitarios";
            } 
        if (jComboBoxSubRubro.getSelectedItem() == "Repuestos Dibujo") {
                filtro3 = "Repuestos Dibujo";
            }

        if (jComboBoxMarca.getSelectedItem() == "Faber Castell") {
                filtro4 = "Faber Castell";
            } 
        if (jComboBoxMarca.getSelectedItem() == "Rivadavia") {
                filtro4 = "Rivadavia";
            } 
        if (jComboBoxMarca.getSelectedItem() == "Rexon") {
                filtro4 = "Rexon";
            } 
        if (jComboBoxMarca.getSelectedItem() == "Generica") {
                filtro4 = "Generica";
            } 
        if (jComboBoxMarca.getSelectedItem() == "Pizzini") {
                filtro4 = "Pizzini";
            } 
        if (jComboBoxMarca.getSelectedItem() == "Laprida") {
                filtro4 = "Laprida";
            } 
        if (jComboBoxMarca.getSelectedItem() == "Exito") {
                filtro4 = "Exito";
            } 
        if (jComboBoxMarca.getSelectedItem() == "Señorita") {
                filtro4 = "Señorita";
            } 
        if (jComboBoxMarca.getSelectedItem() == "Boligoma") {
                filtro4 = "Boligoma";
            } 
        if (jComboBoxMarca.getSelectedItem() == "Filgo") {
                filtro4 = "Filgo";
            } 
        if (jComboBoxMarca.getSelectedItem() == "Pelikan") {
                filtro4 = "Pelikan";
            } 
        if (jComboBoxMarca.getSelectedItem() == "Faber") {
                filtro4 = "Faber";
            } 
        if (jComboBoxMarca.getSelectedItem() == "BIC") {
                filtro4 = "BIC";
            } 
        if (jComboBoxMarca.getSelectedItem() == "Micro") {
                filtro4 = "Micro";
            } 
        if (jComboBoxMarca.getSelectedItem() == "Paper Mate") {
                filtro4 = "Paper Mate";
            } 
        if (jComboBoxMarca.getSelectedItem() == "Maped") {
                filtro4 = "Maped";
            } 
        if (jComboBoxMarca.getSelectedItem() == "Tintoretto") {
                filtro4 = "Tintoretto";
            } 
        if (jComboBoxMarca.getSelectedItem() == "Alba") {
                filtro4 = "Alba";
            } 
        if (jComboBoxMarca.getSelectedItem() == "Staedler") {
                filtro4 = "Staedler";
            } 
        if (jComboBoxMarca.getSelectedItem() == "Trabi") {
                filtro4 = "Trabi";
            } 
        if (jComboBoxMarca.getSelectedItem() == "Playcolor") {
                filtro4 = "Playcolor";
            } 
        if (jComboBoxMarca.getSelectedItem() == "Pagoda") {
                filtro4 = "Pagoda";
            } 
        if (jComboBoxMarca.getSelectedItem() == "Ezco") {
                filtro4 = "Ezco";
            } 
        if (jComboBoxMarca.getSelectedItem() == "Pampa") {
                filtro4 = "Pampa";
            } 
        if (jComboBoxMarca.getSelectedItem() == "Boreal") {
                filtro4 = "Boreal";
            } 
        if (jComboBoxMarca.getSelectedItem() == "América") {
                filtro4 = "América";
            } 
        if (jComboBoxMarca.getSelectedItem() == "Gloria") {
                filtro4 = "Gloria";
            } 
        if (jComboBoxMarca.getSelectedItem() == "Triunfante") {
                filtro4 = "Triunfante";
            } 
        if (jComboBoxMarca.getSelectedItem() == "Arte") {
                filtro4 = "Arte";
            } 
        if (jComboBoxMarca.getSelectedItem() == "Proarte") {
                filtro4 = "Proarte";
            }
        valor1 = (String) jComboBoxSupRubro.getSelectedItem();
        //System.out.print(valor1);
        valor2 = (String) jComboBoxRubro.getSelectedItem();
        //System.out.print(valor2);
        valor3 = (String) jComboBoxSubRubro.getSelectedItem();
        //System.out.print(valor3);
        valor4 = (String) jComboBoxMarca.getSelectedItem();
        //System.out.print(valor4);
        String sql;
        sql = "select * from articulo as ar\n"
                    + "LEFT JOIN superrubro as sru ON ar.art_idsuprubro=sru.suprub_idsuprubro\n"
                    + "LEFT JOIN rubro as ru       ON ar.art_idrubro=ru.rub_idrubro\n"
                    + "LEFT JOIN subrubro as sr    ON ar.art_idsubrubro=sr.subru_idsubrubro\n"
                    + "LEFT JOIN marca as ma       ON ar.art_idmarca=ma.mar_idmarca\n"
                    + "where" +filtro1+ "like '%" + valor1 + "%'"
                    + "or" +filtro2+ "like '%" + valor2 + "%'"
                    + "or" +filtro3+ "like '%" + valor3 + "%'"
                    + "or" +filtro4+ "like '%" + valor4 + "%'";
        System.out.print(sql);
        model = new DefaultTableModel(null, titulos);
        try {
            Connection cn;
            cn = Conexion.conectar();
            PreparedStatement pst;
            pst = cn.prepareStatement(sql);
            System.out.println(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                registros[0] = rs.getString("art_idarticulo");
                registros[1] = rs.getString("suprub_nombre");
                registros[2] = rs.getString("rub_nombre");
                registros[3] = rs.getString("subru_nombre");
                registros[4] = rs.getString("art_nombre");
                registros[5] = rs.getString("mar_nombremarca");
                registros[6] = rs.getString("art_preciofinal");
                registros[7] = rs.getString("art_stock");
                model.addRow(registros);
            }
            jTableArticulo.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(Articulo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonBuscarActionPerformed

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
            java.util.logging.Logger.getLogger(Articulo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Articulo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Articulo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Articulo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Articulo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAgregar;
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonModificar;
    private javax.swing.JButton jButtonSalir;
    private javax.swing.JComboBox<String> jComboBoxMarca;
    private javax.swing.JComboBox<String> jComboBoxRubro;
    private javax.swing.JComboBox<String> jComboBoxSubRubro;
    private javax.swing.JComboBox<String> jComboBoxSupRubro;
    private javax.swing.JLabel jLabelBuscar;
    private javax.swing.JLabel jLabelFondo;
    private javax.swing.JLabel jLabelMarca;
    private javax.swing.JLabel jLabelRubro;
    private javax.swing.JLabel jLabelSubRubro;
    private javax.swing.JLabel jLabelSupRubro;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableArticulo;
    private javax.swing.JTextField jTextFieldBuscar;
    // End of variables declaration//GEN-END:variables
}
