/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import clases.CodigoBarra;
//import clases.NumeroAleatorio;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.sql.*;
import clases.Conexion;
import clases.Marca;
import clases.Rubro;
import clases.SubRubro;
import clases.SupRubro;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.Barcode;
import com.itextpdf.text.pdf.Barcode39;
import com.itextpdf.text.pdf.PdfWriter;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.awt.Color;
import java.awt.Graphics2D;

import java.awt.event.ItemEvent;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

/**
 *
 * @author USUARIO
 */
public class ArticuloABM extends javax.swing.JFrame {

    /**
     * Creates new form ArticuloABM
     */
    String idarticulo;
    int idarticuloaux;
    String banderaABM;
    String sql;

    SupRubro obsupr = new SupRubro();
    Rubro obrub = new Rubro();
    SubRubro obsubr = new SubRubro();
    Marca obmar = new Marca();

    //DECLARO VARIABLES TEMPORALES
    int idart;
    int suprubro;
    int rubro;
    int subrubro;
    String nomart;
    int marca;
    float prefinal;
    float precosto;
    int porganancia;
    int stock;
    int stockmin;
    int stockmax;
    String desc;
    String fecalta;
    String fecbaja;
    Blob imagencodigo;
    Blob imagenarticulo;
    String tipima;
    int codbar;
    int est;
    String usuario;
    int numsupr;
    int numr;
    int numsubr;
    int numm;
    
    public int NumeroAleatorio() {
        int aleatorio;

        Random r = new Random();

        aleatorio = (int) (r.nextDouble() * 1000000000);
        return (aleatorio);
    }

    public ArticuloABM() throws DocumentException {
        initComponents();
        setSize(801, 727);
        setResizable(false);
        usuario = Login.user;
        setTitle("Usuario registrado - Sesión de " + usuario);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        idarticulo = Articulo.idarticulo;
        idarticuloaux = Integer.parseInt(idarticulo);
        banderaABM = Articulo.bandera;
        String codbarras;

        ImageIcon wallpaper = new ImageIcon("src/images/FondoAzul.png");// Tapis de fondo
        Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(jLabelFondo.getWidth(),
                jLabelFondo.getHeight(), Image.SCALE_DEFAULT));
        jLabelFondo.setIcon(icono);
        this.repaint();

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

        if (banderaABM == "1") {
            //Alta
            //Apagar botones de eliminar y modificar
            jButtonEliminar.setEnabled(false);
            jButtonModificar.setEnabled(false);
            //Genera el identificador automático
            try {
                Connection cn = Conexion.conectar();
                //Selecciono el máximo valor de artículo para poder crear el siguiente
                sql = "SELECT MAX(art_idarticulo) AS art_idarticulo FROM articulo";
                PreparedStatement stm = cn.prepareStatement(sql);
                ResultSet rst = stm.executeQuery();
                System.out.println(sql);
                PreparedStatement pst = cn.prepareStatement(sql);
                if (rst.next()) {
                    int ticket = (rst.getInt(1) + 1);
                    jTextFieldIdentificacion.setText(Integer.toString(ticket));
                    idarticulo = Integer.toString(ticket);
                    //System.out.println(idarticulo);
                }
                cn.close();
            } catch (SQLException e) {
            }
            //Bloquear edición en algunos jTextFields
            jTextFieldIdentificacion.setEditable(false);
            jTextFieldEstado.setEditable(false);

            //Cargo valor de alta de articulo
            est = 1;
            jTextFieldEstado.setText(Integer.toString(est));
            numsupr = 0;
            numr = 0;
            //Genera número aleatorio
            String aleatorio = Integer.toString(NumeroAleatorio());
            //System.out.println(aleatorio);
            codbar = Integer.parseInt(aleatorio);

            //Creo el código de barras
            CodigoBarra barcode = new CodigoBarra();
            barcode.generarcodigo(aleatorio);
            //Creo una imagen para ponerla en pantalla
            String nombrearchivo = aleatorio + ".gif";
            //System.out.println(image);
            //Clase para recuperar 
            ImageIcon imagenexterna = new ImageIcon(""+nombrearchivo+"");
            //Image imagenInterna = new ImageIcon(getClass().getResource("satelite.jpg")).getImage();
            //Icon imagen = new ImageIcon(imagenexterna.getImage().getScaledInstance(jLabelCodigo.getWidth(),jLabelCodigo.getHeight(), Image.SCALE_DEFAULT));
            Icon imagen = new ImageIcon(imagenexterna.getImage());
            jLabelCodigo.setIcon(imagen);
            //imagencodigo = (Blob) imagen; ME CUELGA EL PROGRAMA
        }
        
        if (banderaABM == "2") {
            //Modificación
            //Apagar botones de eliminar y agregar
            jButtonAgregar.setEnabled(false);
            jButtonEliminar.setEnabled(false);

            // Cargar articulo seleccionado en la tabla
            try {
                Connection cn = Conexion.conectar();
                sql = "select * from articulo as ar\n"
                        + "			  LEFT JOIN superrubro as sru ON ar.art_idsuprubro=sru.suprub_idsuprubro\n"
                        + "			  LEFT JOIN rubro as ru       ON ar.art_idrubro=ru.rub_idrubro\n"
                        + "			  LEFT JOIN subrubro as sr    ON ar.art_idsubrubro=sr.subru_idsubrubro\n"
                        + "			  LEFT JOIN marca as ma       ON ar.art_idmarca=ma.mar_idmarca\n"
                        + "			  where  ar.art_idarticulo = " + idarticuloaux;

                System.out.println(sql);
                PreparedStatement pst = cn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                //Cargo los valores del registro en pantalla
                if (rs.next()) {
                    jTextFieldIdentificacion.setText(rs.getString("art_idarticulo"));
                    jTextFieldNombre.setText(rs.getString("art_nombre"));
                    jTextFieldPrecioFinal.setText(rs.getString("art_preciofinal"));
                    jTextFieldPrecioCosto.setText(rs.getString("art_preciocosto"));
                    jTextFieldPorcentajeGanancia.setText(rs.getString("art_porcentaje"));
                    jTextFieldStock.setText(rs.getString("art_stock"));
                    jTextFieldStockMinimo.setText(rs.getString("art_stockmin"));
                    jTextFieldStockMaximo.setText(rs.getString("art_stockmax"));
                    jTextFieldDescripcion.setText(rs.getString("art_descripcion"));
                    jDateChooserFechaAlta.setDateFormatString(rs.getString("art_fechaalta"));
                    jDateChooserFechaBaja.setDateFormatString(rs.getString("art_fechabaja"));
                    jLabelArticulo.setText(rs.getString("art_codigobarras"));
                    jTextFieldEstado.setText(rs.getString("art_estado"));
                    numsupr = rs.getInt("art_idsuprubro");
                    //System.out.println("SUBRUBRO: " + numsupr);
                    jComboBoxSupRubro.setSelectedIndex(numsupr);
                    numr = rs.getInt("art_idrubro");
                    //System.out.println("RUBRO: " + numr);
                    jComboBoxRubro.setSelectedIndex(numr);
                    numsubr = rs.getInt("art_idsubrubro");
                    jComboBoxSubRubro.setSelectedIndex(numsubr);
                    numm = rs.getInt("art_idmarca");
                    jComboBoxMarca.setSelectedIndex(numm);

                    //Bloquear edición en algunos jTextFields
                    jTextFieldIdentificacion.setEditable(false);
                    jTextFieldEstado.setEditable(false);

                    cn.close();
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo cargar el registro");
                }

            } catch (SQLException e) {

            }
        }
        if (banderaABM == "3") {
            //Baja
            //Apagar botones de agregar y modificar
            jButtonAgregar.setEnabled(false);
            jButtonModificar.setEnabled(false);
            try {
                Connection cn = Conexion.conectar();
                sql = "select * from articulo as ar\n"
                        + "			  LEFT JOIN superrubro as sru ON ar.art_idsuprubro=sru.suprub_idsuprubro\n"
                        + "			  LEFT JOIN rubro as ru       ON ar.art_idrubro=ru.rub_idrubro\n"
                        + "			  LEFT JOIN subrubro as sr    ON ar.art_idsubrubro=sr.subru_idsubrubro\n"
                        + "			  LEFT JOIN marca as ma       ON ar.art_idmarca=ma.mar_idmarca\n"
                        + "			  where  ar.art_idarticulo = " + idarticuloaux;

                System.out.println(sql);
                PreparedStatement pst = cn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                //Cargo los valores del registro en pantalla
                if (rs.next()) {
                    jTextFieldIdentificacion.setText(rs.getString("art_idarticulo"));
                    jTextFieldNombre.setText(rs.getString("art_nombre"));
                    jTextFieldPrecioFinal.setText(rs.getString("art_preciofinal"));
                    jTextFieldPrecioCosto.setText(rs.getString("art_preciocosto"));
                    jTextFieldPorcentajeGanancia.setText(rs.getString("art_porcentaje"));
                    jTextFieldStock.setText(rs.getString("art_stock"));
                    jTextFieldStockMinimo.setText(rs.getString("art_stockmin"));
                    jTextFieldStockMaximo.setText(rs.getString("art_stockmax"));
                    jTextFieldDescripcion.setText(rs.getString("art_descripcion"));
                    jDateChooserFechaAlta.setDateFormatString(rs.getString("art_fechaalta"));
                    jDateChooserFechaBaja.setDateFormatString(rs.getString("art_fechabaja"));
                    jLabelArticulo.setText(rs.getString("art_codigobarras"));
                    jTextFieldEstado.setText(rs.getString("art_estado"));
                    numsupr = rs.getInt("art_idsuprubro");
                    //System.out.println("SUBRUBRO: " + numsupr);
                    jComboBoxSupRubro.setSelectedIndex(numsupr);
                    numr = rs.getInt("art_idrubro");
                    //System.out.println("RUBRO: " + numr);
                    jComboBoxRubro.setSelectedIndex(numr);
                    numsubr = rs.getInt("art_idsubrubro");
                    jComboBoxSubRubro.setSelectedIndex(numsubr);
                    numm = rs.getInt("art_idmarca");
                    jComboBoxMarca.setSelectedIndex(numm);

                    //Bloquear edición en algunos jTextFields
                    jTextFieldIdentificacion.setEditable(false);
                    jLabelArticulo.setEnabled(false);
                    jTextFieldNombre.setEditable(false);
                    jTextFieldPrecioFinal.setEditable(false);
                    jTextFieldPrecioCosto.setEditable(false);
                    jTextFieldPorcentajeGanancia.setEditable(false);
                    jTextFieldStock.setEditable(false);
                    jTextFieldStockMinimo.setEditable(false);
                    jTextFieldStockMaximo.setEditable(false);
                    jTextFieldDescripcion.setEditable(false);
                    jDateChooserFechaAlta.setEnabled(false);
                    jDateChooserFechaBaja.setEnabled(false);
                    cn.close();
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo cargar el registro");
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Articulo NO eliminado.");
            }
            //GUARDO LOS VALORES DE PANTALLA EN VARIABLES PARA PODER RESTABLECER LO RECIBIDO DE REGISTRO
            idart = Integer.parseInt(jTextFieldIdentificacion.getText().trim());
            suprubro = jComboBoxSupRubro.getSelectedIndex();
            rubro = jComboBoxRubro.getSelectedIndex();
            subrubro = jComboBoxSubRubro.getSelectedIndex();
            nomart = (jTextFieldNombre.getText().trim());
            marca = jComboBoxMarca.getSelectedIndex();
            prefinal = Float.parseFloat(jTextFieldPrecioFinal.getText().trim());
            precosto = Float.parseFloat(jTextFieldPrecioCosto.getText().trim());
            porganancia = Integer.parseInt(jTextFieldPorcentajeGanancia.getText().trim());
            stock = Integer.parseInt(jTextFieldStock.getText().trim());
            stockmin = Integer.parseInt(jTextFieldStockMinimo.getText().trim());
            stockmax = Integer.parseInt(jTextFieldStockMaximo.getText().trim());
            desc = jTextFieldDescripcion.getText().trim();
            //ACA PASO UN VALOR JDATACHOOSER A STRING
            try {
                String formato1 = jDateChooserFechaAlta.getDateFormatString();
                String formato2 = jDateChooserFechaBaja.getDateFormatString();
                java.util.Date date1 = jDateChooserFechaAlta.getDate();
                java.util.Date date2 = jDateChooserFechaBaja.getDate();
                SimpleDateFormat sdf1 = new SimpleDateFormat(formato1);
                SimpleDateFormat sdf2 = new SimpleDateFormat(formato2);
                fecalta = String.valueOf(sdf1.format(date1));
                fecbaja = String.valueOf(sdf2.format(date2));
            } catch (Exception e) {
            }
            //linkima = "";
            tipima = "";
            codbar = Integer.parseInt(jLabelArticulo.getText().trim());
            est = Integer.parseInt(jTextFieldEstado.getText().trim());

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

        jLabelStockMinimo = new javax.swing.JLabel();
        jLabelStockMaximo = new javax.swing.JLabel();
        jLabelFechaAlta = new javax.swing.JLabel();
        jLabelFechaBaja = new javax.swing.JLabel();
        jLabelArticulo1 = new javax.swing.JLabel();
        jLabelEstado = new javax.swing.JLabel();
        jLabelTitulo = new javax.swing.JLabel();
        jLabelNombre = new javax.swing.JLabel();
        jLabelSupRubro = new javax.swing.JLabel();
        jLabelMarca = new javax.swing.JLabel();
        jLabelRubro = new javax.swing.JLabel();
        jLabelStock = new javax.swing.JLabel();
        jLabelPrecioCosto = new javax.swing.JLabel();
        jLabelSubRubro = new javax.swing.JLabel();
        jLabelPorcentajeGanancia = new javax.swing.JLabel();
        jLabelPrecioFinal = new javax.swing.JLabel();
        jLabelDescripcion = new javax.swing.JLabel();
        jLabelIdentificacion = new javax.swing.JLabel();
        jTextFieldPrecioFinal = new javax.swing.JTextField();
        jTextFieldPorcentajeGanancia = new javax.swing.JTextField();
        jTextFieldNombre = new javax.swing.JTextField();
        jTextFieldStockMinimo = new javax.swing.JTextField();
        jTextFieldEstado = new javax.swing.JTextField();
        jTextFieldStock = new javax.swing.JTextField();
        jTextFieldStockMaximo = new javax.swing.JTextField();
        jTextFieldDescripcion = new javax.swing.JTextField();
        jTextFieldPrecioCosto = new javax.swing.JTextField();
        jTextFieldIdentificacion = new javax.swing.JTextField();
        jButtonAgregar = new javax.swing.JButton();
        jButtonModificar = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();
        jButtonRestablecer = new javax.swing.JButton();
        jComboBoxRubro = new javax.swing.JComboBox<>();
        jComboBoxSubRubro = new javax.swing.JComboBox<>();
        jComboBoxMarca = new javax.swing.JComboBox<>();
        jComboBoxSupRubro = new javax.swing.JComboBox<>();
        jButtonSalir = new javax.swing.JButton();
        jDateChooserFechaAlta = new com.toedter.calendar.JDateChooser();
        jDateChooserFechaBaja = new com.toedter.calendar.JDateChooser();
        jLabelArticulo = new javax.swing.JLabel();
        jLabelCodigoBarras = new javax.swing.JLabel();
        jLabelCodigo = new javax.swing.JLabel();
        jLabelFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelStockMinimo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelStockMinimo.setForeground(new java.awt.Color(51, 153, 255));
        jLabelStockMinimo.setText("Stock Mínimo");
        getContentPane().add(jLabelStockMinimo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 450, -1, 20));

        jLabelStockMaximo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelStockMaximo.setForeground(new java.awt.Color(51, 153, 255));
        jLabelStockMaximo.setText("Stock Máximo");
        getContentPane().add(jLabelStockMaximo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 490, -1, 20));

        jLabelFechaAlta.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelFechaAlta.setForeground(new java.awt.Color(51, 153, 255));
        jLabelFechaAlta.setText("Fecha Alta");
        getContentPane().add(jLabelFechaAlta, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, -1, 20));

        jLabelFechaBaja.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelFechaBaja.setForeground(new java.awt.Color(51, 153, 255));
        jLabelFechaBaja.setText("Fecha Baja");
        getContentPane().add(jLabelFechaBaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, -1, 20));

        jLabelArticulo1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelArticulo1.setForeground(new java.awt.Color(51, 153, 255));
        jLabelArticulo1.setText("Imagen Articulo");
        getContentPane().add(jLabelArticulo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 410, -1, 20));

        jLabelEstado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelEstado.setForeground(new java.awt.Color(51, 153, 255));
        jLabelEstado.setText("Estado");
        getContentPane().add(jLabelEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 530, -1, 20));

        jLabelTitulo.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabelTitulo.setForeground(new java.awt.Color(51, 153, 255));
        jLabelTitulo.setText("Agregar/Modificar/Eliminar Articulo");
        getContentPane().add(jLabelTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, 370, 30));

        jLabelNombre.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelNombre.setForeground(new java.awt.Color(51, 153, 255));
        jLabelNombre.setText("Nombre");
        getContentPane().add(jLabelNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, -1, 20));

        jLabelSupRubro.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelSupRubro.setForeground(new java.awt.Color(51, 153, 255));
        jLabelSupRubro.setText("SupRubro");
        getContentPane().add(jLabelSupRubro, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 80, -1, 20));

        jLabelMarca.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelMarca.setForeground(new java.awt.Color(51, 153, 255));
        jLabelMarca.setText("Marca");
        getContentPane().add(jLabelMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 230, -1, 20));

        jLabelRubro.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelRubro.setForeground(new java.awt.Color(51, 153, 255));
        jLabelRubro.setText("Rubro");
        getContentPane().add(jLabelRubro, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 130, -1, 20));

        jLabelStock.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelStock.setForeground(new java.awt.Color(51, 153, 255));
        jLabelStock.setText("Stock");
        getContentPane().add(jLabelStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 410, -1, 20));

        jLabelPrecioCosto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelPrecioCosto.setForeground(new java.awt.Color(51, 153, 255));
        jLabelPrecioCosto.setText("Precio Costo");
        getContentPane().add(jLabelPrecioCosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, -1, 20));

        jLabelSubRubro.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelSubRubro.setForeground(new java.awt.Color(51, 153, 255));
        jLabelSubRubro.setText("SubRubro");
        getContentPane().add(jLabelSubRubro, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 180, -1, 20));

        jLabelPorcentajeGanancia.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelPorcentajeGanancia.setForeground(new java.awt.Color(51, 153, 255));
        jLabelPorcentajeGanancia.setText("Porc. Ganancia");
        getContentPane().add(jLabelPorcentajeGanancia, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 370, 110, 20));

        jLabelPrecioFinal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelPrecioFinal.setForeground(new java.awt.Color(51, 153, 255));
        jLabelPrecioFinal.setText("Precio Final");
        getContentPane().add(jLabelPrecioFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 330, -1, 20));

        jLabelDescripcion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelDescripcion.setForeground(new java.awt.Color(51, 153, 255));
        jLabelDescripcion.setText("Descripción");
        getContentPane().add(jLabelDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, -1, 20));

        jLabelIdentificacion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelIdentificacion.setForeground(new java.awt.Color(51, 153, 255));
        jLabelIdentificacion.setText("Identificación");
        getContentPane().add(jLabelIdentificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, -1, -1));
        getContentPane().add(jTextFieldPrecioFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 330, 110, -1));
        getContentPane().add(jTextFieldPorcentajeGanancia, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 370, 110, -1));
        getContentPane().add(jTextFieldNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, 194, -1));
        getContentPane().add(jTextFieldStockMinimo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 450, 69, -1));
        getContentPane().add(jTextFieldEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 530, 70, -1));
        getContentPane().add(jTextFieldStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 410, 69, -1));
        getContentPane().add(jTextFieldStockMaximo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 490, 69, -1));
        getContentPane().add(jTextFieldDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 170, 194, -1));
        getContentPane().add(jTextFieldPrecioCosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 290, 110, -1));
        getContentPane().add(jTextFieldIdentificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, 70, -1));

        jButtonAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add_insert_plus_1588.png"))); // NOI18N
        jButtonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 600, 100, 100));

        jButtonModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/editar.png"))); // NOI18N
        jButtonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificarActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 600, 100, 100));

        jButtonEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete_delete_exit_1577.png"))); // NOI18N
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 600, 100, 100));

        jButtonRestablecer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_ok_accept_apply_1582.png"))); // NOI18N
        jButtonRestablecer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRestablecerActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonRestablecer, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 600, 100, 100));

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
        getContentPane().add(jComboBoxRubro, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 130, 220, -1));

        jComboBoxSubRubro.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxSubRubroItemStateChanged(evt);
            }
        });
        jComboBoxSubRubro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSubRubroActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBoxSubRubro, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 180, 220, -1));

        jComboBoxMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxMarcaActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBoxMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 230, 220, -1));

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
        getContentPane().add(jComboBoxSupRubro, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 80, 220, -1));

        jButtonSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit_closethesession_close_6317.png"))); // NOI18N
        jButtonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalirActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 600, 100, 100));

        jDateChooserFechaAlta.setDateFormatString("yyyy-MM-dd");
        getContentPane().add(jDateChooserFechaAlta, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 210, 130, -1));

        jDateChooserFechaBaja.setDateFormatString("yyyy-MM-dd");
        getContentPane().add(jDateChooserFechaBaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 250, 130, -1));

        jLabelArticulo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(jLabelArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 430, 320, 150));

        jLabelCodigoBarras.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelCodigoBarras.setForeground(new java.awt.Color(51, 153, 255));
        jLabelCodigoBarras.setText("Código Barras");
        getContentPane().add(jLabelCodigoBarras, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 280, -1, 20));

        jLabelCodigo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelCodigo.setAlignmentY(0.0F);
        jLabelCodigo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(jLabelCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 310, 320, 80));
        getContentPane().add(jLabelFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 750));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarActionPerformed

        String suprubrocadena = null;
        String rubrocadena = null;
        String subrubrocadena = null;
        String marcacadena = null;
        String sqlagrega;
        // Insertar articulo nuevo
        try {
            Connection cn = Conexion.conectar();
            /*sql = "insert into articulo (art_idarticulo, art_idsuprubro, art_idrubro, art_idsubrubro, art_nombre, art_idmarca, art_preciofinal, art_preciocosto, "
                    + "art_porcentaje, art_stock, art_stockmin, art_stockmax, art_descripcion, art_fechaalta, art_fechabaja, art_linkimagen, art_tipoimagen, art_codigobarras, art_estado) "
                    + "values("+idart+","+rubro+","+suprubro+","+subrubro+","+nomart+","+marca+","+prefinal+","+precosto+","+porganancia+","+stock+","+stockmin+","+stockmax+","+desc+","+fecalta+","+fecbaja+","+imagenarticulo+","+imagencodigo+","+codbar+","+est+")";*/
            sql = "insert into articulo (art_idarticulo, art_idsuprubro, art_idrubro, art_idsubrubro, art_nombre, art_idmarca, art_preciofinal, art_preciocosto, "
                    + "art_porcentaje, art_stock, art_stockmin, art_stockmax, art_descripcion, art_fechaalta, art_fechabaja, art_imagenarticulo, art_imagencodigo, art_codigobarras, art_estado) "
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            //Preparo la consulta
            PreparedStatement pst = cn.prepareStatement(sql);
            System.out.println(sql);

            //Guardo los valores de pantalla en variables temporales para usarlas en restablecer
            idart = Integer.parseInt(jTextFieldIdentificacion.getText().trim());
            suprubro = jComboBoxSupRubro.getSelectedIndex();
            rubro = jComboBoxRubro.getSelectedIndex();
            subrubro = jComboBoxSubRubro.getSelectedIndex();
            nomart = (jTextFieldNombre.getText().trim());
            marca = jComboBoxMarca.getSelectedIndex();
            prefinal = Float.parseFloat(jTextFieldPrecioFinal.getText().trim());
            precosto = Float.parseFloat(jTextFieldPrecioCosto.getText().trim());
            porganancia = Integer.parseInt(jTextFieldPorcentajeGanancia.getText().trim());
            stock = Integer.parseInt(jTextFieldStock.getText().trim());
            stockmin = Integer.parseInt(jTextFieldStockMinimo.getText().trim());
            stockmax = Integer.parseInt(jTextFieldStockMaximo.getText().trim());
            desc = jTextFieldDescripcion.getText().trim();

            //ACA PASO UN VALOR JDATACHOOSER A STRING
            try {
                String formato1 = jDateChooserFechaAlta.getDateFormatString();
                String formato2 = jDateChooserFechaBaja.getDateFormatString();
                java.util.Date date1 = jDateChooserFechaAlta.getDate();
                java.util.Date date2 = jDateChooserFechaBaja.getDate();
                SimpleDateFormat sdf1 = new SimpleDateFormat(formato1);
                SimpleDateFormat sdf2 = new SimpleDateFormat(formato2);
                fecalta = String.valueOf(sdf1.format(date1));
                fecbaja = String.valueOf(sdf2.format(date2));
                System.out.println("FECHA " + fecalta);
                System.out.println("FECHA " + fecbaja);

            } catch (Exception e) {
            }
            //linkima = "";
            tipima = "";
            est = Integer.parseInt(jTextFieldEstado.getText().trim());

            //Validaciones
            if (nomart.equals("")) {
                jTextFieldNombre.setBackground(Color.red);
            }
            if (desc.equals("")) {
                jTextFieldNombre.setBackground(Color.red);
            }
            if (suprubro == 1) {
                suprubrocadena = "Libreria";
            } else if (suprubro == 2) {
                suprubrocadena = "Artistica";
            } else if (suprubro == 3) {
                suprubrocadena = "Computación";
            } else if (suprubro == 4) {
                suprubrocadena = "Regalería";
            } else if (suprubro == 5) {
                suprubrocadena = "Tarjetería";
            } else if (suprubro == 6) {
                suprubrocadena = "Impresiones/Fotocopias";
            } else if (suprubro == 7) {
                suprubrocadena = "Cotillón";
            } else if (suprubro == 8) {
                suprubrocadena = "Mercería";
            }
            if (rubro == 1) {
                rubrocadena = "Accesorios de escritorio";
            } else if (suprubro == 2) {
                rubrocadena = "Adhesivos";
            } else if (suprubro == 3) {
                rubrocadena = "Artículos Patrios";
            } else if (suprubro == 4) {
                rubrocadena = "Calculadoras";
            } else if (suprubro == 5) {
                rubrocadena = "Cartucheras";
            } else if (suprubro == 6) {
                rubrocadena = "Carpetas y archivos";
            } else if (suprubro == 7) {
                rubrocadena = "Marroquineria";
            } else if (suprubro == 8) {
                rubrocadena = "Pizarras y accesorios";
            } else if (suprubro == 9) {
                rubrocadena = "Papelería";
            } else if (suprubro == 10) {
                rubrocadena = "Folios";
            } else if (suprubro == 11) {
                rubrocadena = "Escritura";
            } else if (suprubro == 12) {
                rubrocadena = "Encuadernación";
            } else if (suprubro == 13) {
                rubrocadena = "Embalaje";
            } else if (suprubro == 14) {
                rubrocadena = "Geometría y dibujo";
            } else if (suprubro == 15) {
                rubrocadena = "Didáctico y manualidades";
            } else if (suprubro == 16) {
                rubrocadena = "Cuadernos";
            } else if (suprubro == 17) {
                rubrocadena = "Libros";
            } else if (suprubro == 18) {
                rubrocadena = "Repuestos";
            } else if (suprubro == 19) {
                rubrocadena = "Tijeras";
            } else if (suprubro == 20) {
                rubrocadena = "Resmas";
            } else if (suprubro == 21) {
                rubrocadena = "Accesorios";
            } else if (suprubro == 22) {
                rubrocadena = "Pinturas";
            } else if (suprubro == 23) {
                rubrocadena = "Herramientas";
            } else if (suprubro == 24) {
                rubrocadena = "Pinceles";
            } else if (suprubro == 25) {
                rubrocadena = "Técnicas Secas";
            } else if (suprubro == 26) {
                rubrocadena = "Accesorios Madera";
            } else if (suprubro == 27) {
                rubrocadena = "Masas Manualidades";
            } else if (suprubro == 28) {
                rubrocadena = "Decoopages";
            } else if (suprubro == 29) {
                rubrocadena = "Accesorios y Cables";
            } else if (suprubro == 30) {
                rubrocadena = "Accesorios Celulares";
            }

            if (subrubro == 1) {
                subrubrocadena = "Carpetas escolares";
            } else if (suprubro == 2) {
                subrubrocadena = "Biblioratos";
            } else if (suprubro == 3) {
                subrubrocadena = "Carpetas Universitarias";
            } else if (suprubro == 4) {
                subrubrocadena = "Carpetas Dibujo";
            } else if (suprubro == 5) {
                subrubrocadena = "Tapa dura";
            } else if (suprubro == 6) {
                subrubrocadena = "Tapa flexible";
            } else if (suprubro == 7) {
                subrubrocadena = "Espiralado";
            } else if (suprubro == 8) {
                subrubrocadena = "Block";
            } else if (suprubro == 9) {
                subrubrocadena = "Carpetas Oficina";
            } else if (suprubro == 10) {
                subrubrocadena = "Repuestos Escolares";
            } else if (suprubro == 11) {
                subrubrocadena = "Repuestos Universitarios";
            } else if (suprubro == 12) {
                subrubrocadena = "Repuestos Dibujo";
            }

            if (marca == 1) {
                marcacadena = "Faber Castell";
            } else if (suprubro == 2) {
                marcacadena = "Rivadavia";
            } else if (suprubro == 3) {
                marcacadena = "Rexon";
            } else if (suprubro == 4) {
                marcacadena = "Generica";
            } else if (suprubro == 5) {
                marcacadena = "Pizzini";
            } else if (suprubro == 6) {
                marcacadena = "Laprida";
            } else if (suprubro == 7) {
                marcacadena = "Exito";
            } else if (suprubro == 8) {
                marcacadena = "Señorita";
            } else if (suprubro == 9) {
                marcacadena = "Boligoma";
            } else if (suprubro == 10) {
                marcacadena = "Filgo";
            } else if (suprubro == 11) {
                marcacadena = "Pelikan";
            } else if (suprubro == 12) {
                marcacadena = "Faber";
            } else if (suprubro == 13) {
                marcacadena = "BIC";
            } else if (suprubro == 14) {
                marcacadena = "Micro";
            } else if (suprubro == 15) {
                marcacadena = "Paper Mate";
            } else if (suprubro == 16) {
                marcacadena = "Maped";
            } else if (suprubro == 17) {
                marcacadena = "Tintoretto";
            } else if (suprubro == 18) {
                marcacadena = "Alba";
            } else if (suprubro == 19) {
                marcacadena = "Staedler";
            } else if (suprubro == 20) {
                marcacadena = "Trabi";
            } else if (suprubro == 21) {
                marcacadena = "Playcolor";
            } else if (suprubro == 22) {
                marcacadena = "Pagoda";
            } else if (suprubro == 23) {
                marcacadena = "Ezco";
            } else if (suprubro == 24) {
                marcacadena = "Pampa";
            } else if (suprubro == 25) {
                marcacadena = "Boreal";
            } else if (suprubro == 26) {
                marcacadena = "América";
            } else if (suprubro == 27) {
                marcacadena = "Gloria";
            } else if (suprubro == 28) {
                marcacadena = "Triunfante";
            } else if (suprubro == 29) {
                marcacadena = "Arte";
            } else if (suprubro == 30) {
                marcacadena = "Proarte";
            }

            //Cargo los valores de pantalla en registro de tabla
            pst.setInt(1, idart);
            pst.setInt(2, suprubro);
            pst.setInt(3, rubro);
            pst.setInt(4, subrubro);
            pst.setString(5, nomart);
            pst.setInt(6, marca);
            pst.setFloat(7, prefinal);
            pst.setFloat(8, precosto);
            pst.setInt(9, porganancia);
            pst.setInt(10, stock);
            pst.setInt(11, stockmin);
            pst.setInt(12, stockmax);
            pst.setString(13, desc);
            pst.setString(14, fecalta);
            pst.setString(15, fecbaja);
            //pst.setBlob(16, imagenarticulo);
            //pst.setBlob(17, imagencodigo);
            pst.setInt(18, codbar);
            pst.setInt(19, est);

            //Realizo la consulta
            int resultado = pst.executeUpdate();
            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Articulo insertado.");
            }
            cn.close();
            this.dispose();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Articulo NO insertado.");
        }
        //Blanquear campos luego de insertar
        limpiarcampos();
    }//GEN-LAST:event_jButtonAgregarActionPerformed

    private void jButtonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificarActionPerformed

        String suprubrocadena = null;
        String rubrocadena = null;
        String subrubrocadena = null;
        String marcacadena = null;
        String sqlmodifica;
        // Modificar un articulo:
        try {
            Connection cn = Conexion.conectar();
            sql = "update articulo set art_idarticulo = ?, art_idsuprubro = ?, art_idrubro = ?, "
                    + "art_idsubrubro = ?, art_nombre = ?, art_idmarca = ?, art_preciofinal = ?, art_preciocosto = ?, "
                    + "art_porcentaje = ?, art_stock = ?, art_stockmin = ?, art_stockmax = ?,art_descripcion = ?, "
                    + "art_fechaalta = ?, art_fechabaja = ?, art_imagenarticulo = ?, art_imagencodigo = ?, art_codigobarras = ?, art_estado = ? "
                    + "where art_idarticulo = " + idarticulo;
            PreparedStatement pst = cn.prepareStatement(sql);
            //System.out.println(sql);

            //Guardo los valores de pantalla en variables temporales para usarlas en restablecer
            idart = Integer.parseInt(jTextFieldIdentificacion.getText().trim());
            suprubro = jComboBoxSupRubro.getSelectedIndex();
            rubro = jComboBoxRubro.getSelectedIndex();
            subrubro = jComboBoxSubRubro.getSelectedIndex();
            nomart = (jTextFieldNombre.getText().trim());
            marca = jComboBoxMarca.getSelectedIndex();
            prefinal = Float.parseFloat(jTextFieldPrecioFinal.getText().trim());
            precosto = Float.parseFloat(jTextFieldPrecioCosto.getText().trim());
            porganancia = Integer.parseInt(jTextFieldPorcentajeGanancia.getText().trim());
            stock = Integer.parseInt(jTextFieldStock.getText().trim());
            stockmin = Integer.parseInt(jTextFieldStockMinimo.getText().trim());
            stockmax = Integer.parseInt(jTextFieldStockMaximo.getText().trim());
            desc = jTextFieldDescripcion.getText().trim();
            //ACA PASO UN VALOR JDATACHOOSER A STRING
            try {
                String formato1 = jDateChooserFechaAlta.getDateFormatString();
                String formato2 = jDateChooserFechaBaja.getDateFormatString();
                java.util.Date date1 = jDateChooserFechaAlta.getDate();
                java.util.Date date2 = jDateChooserFechaBaja.getDate();
                SimpleDateFormat sdf1 = new SimpleDateFormat(formato1);
                SimpleDateFormat sdf2 = new SimpleDateFormat(formato2);
                fecalta = String.valueOf(sdf1.format(date1));
                fecbaja = String.valueOf(sdf2.format(date2));
                System.out.println("FECHA ALTA " + fecalta);
                System.out.println("FECHA BAJA " + fecbaja);

            } catch (Exception e) {
            }
            //linkima = "";
            tipima = "";
            codbar = Integer.parseInt(jLabelArticulo.getText().trim());
            
            est = Integer.parseInt(jTextFieldEstado.getText().trim());

            //Validaciones
            if (nomart.equals("")) {
                jTextFieldNombre.setBackground(Color.red);
            }
            if (desc.equals("")) {
                jTextFieldNombre.setBackground(Color.red);
            }
            if (suprubro == 1) {
                suprubrocadena = "Libreria";
            } else if (suprubro == 2) {
                suprubrocadena = "Artistica";
            } else if (suprubro == 3) {
                suprubrocadena = "Computación";
            } else if (suprubro == 4) {
                suprubrocadena = "Regalería";
            } else if (suprubro == 5) {
                suprubrocadena = "Tarjetería";
            } else if (suprubro == 6) {
                suprubrocadena = "Impresiones/Fotocopias";
            } else if (suprubro == 7) {
                suprubrocadena = "Cotillón";
            } else if (suprubro == 8) {
                suprubrocadena = "Mercería";
            }
            if (rubro == 1) {
                rubrocadena = "Accesorios de escritorio";
            } else if (suprubro == 2) {
                rubrocadena = "Adhesivos";
            } else if (suprubro == 3) {
                rubrocadena = "Artículos Patrios";
            } else if (suprubro == 4) {
                rubrocadena = "Calculadoras";
            } else if (suprubro == 5) {
                rubrocadena = "Cartucheras";
            } else if (suprubro == 6) {
                rubrocadena = "Carpetas y archivos";
            } else if (suprubro == 7) {
                rubrocadena = "Marroquineria";
            } else if (suprubro == 8) {
                rubrocadena = "Pizarras y accesorios";
            } else if (suprubro == 9) {
                rubrocadena = "Papelería";
            } else if (suprubro == 10) {
                rubrocadena = "Folios";
            } else if (suprubro == 11) {
                rubrocadena = "Escritura";
            } else if (suprubro == 12) {
                rubrocadena = "Encuadernación";
            } else if (suprubro == 13) {
                rubrocadena = "Embalaje";
            } else if (suprubro == 14) {
                rubrocadena = "Geometría y dibujo";
            } else if (suprubro == 15) {
                rubrocadena = "Didáctico y manualidades";
            } else if (suprubro == 16) {
                rubrocadena = "Cuadernos";
            } else if (suprubro == 17) {
                rubrocadena = "Libros";
            } else if (suprubro == 18) {
                rubrocadena = "Repuestos";
            } else if (suprubro == 19) {
                rubrocadena = "Tijeras";
            } else if (suprubro == 20) {
                rubrocadena = "Resmas";
            } else if (suprubro == 21) {
                rubrocadena = "Accesorios";
            } else if (suprubro == 22) {
                rubrocadena = "Pinturas";
            } else if (suprubro == 23) {
                rubrocadena = "Herramientas";
            } else if (suprubro == 24) {
                rubrocadena = "Pinceles";
            } else if (suprubro == 25) {
                rubrocadena = "Técnicas Secas";
            } else if (suprubro == 26) {
                rubrocadena = "Accesorios Madera";
            } else if (suprubro == 27) {
                rubrocadena = "Masas Manualidades";
            } else if (suprubro == 28) {
                rubrocadena = "Decoopages";
            } else if (suprubro == 29) {
                rubrocadena = "Accesorios y Cables";
            } else if (suprubro == 30) {
                rubrocadena = "Accesorios Celulares";
            }

            if (subrubro == 1) {
                subrubrocadena = "Carpetas escolares";
            } else if (suprubro == 2) {
                subrubrocadena = "Biblioratos";
            } else if (suprubro == 3) {
                subrubrocadena = "Carpetas Universitarias";
            } else if (suprubro == 4) {
                subrubrocadena = "Carpetas Dibujo";
            } else if (suprubro == 5) {
                subrubrocadena = "Tapa dura";
            } else if (suprubro == 6) {
                subrubrocadena = "Tapa flexible";
            } else if (suprubro == 7) {
                subrubrocadena = "Espiralado";
            } else if (suprubro == 8) {
                subrubrocadena = "Block";
            } else if (suprubro == 9) {
                subrubrocadena = "Carpetas Oficina";
            } else if (suprubro == 10) {
                subrubrocadena = "Repuestos Escolares";
            } else if (suprubro == 11) {
                subrubrocadena = "Repuestos Universitarios";
            } else if (suprubro == 12) {
                subrubrocadena = "Repuestos Dibujo";
            }

            if (marca == 1) {
                marcacadena = "Faber Castell";
            } else if (suprubro == 2) {
                marcacadena = "Rivadavia";
            } else if (suprubro == 3) {
                marcacadena = "Rexon";
            } else if (suprubro == 4) {
                marcacadena = "Generica";
            } else if (suprubro == 5) {
                marcacadena = "Pizzini";
            } else if (suprubro == 6) {
                marcacadena = "Laprida";
            } else if (suprubro == 7) {
                marcacadena = "Exito";
            } else if (suprubro == 8) {
                marcacadena = "Señorita";
            } else if (suprubro == 9) {
                marcacadena = "Boligoma";
            } else if (suprubro == 10) {
                marcacadena = "Filgo";
            } else if (suprubro == 11) {
                marcacadena = "Pelikan";
            } else if (suprubro == 12) {
                marcacadena = "Faber";
            } else if (suprubro == 13) {
                marcacadena = "BIC";
            } else if (suprubro == 14) {
                marcacadena = "Micro";
            } else if (suprubro == 15) {
                marcacadena = "Paper Mate";
            } else if (suprubro == 16) {
                marcacadena = "Maped";
            } else if (suprubro == 17) {
                marcacadena = "Tintoretto";
            } else if (suprubro == 18) {
                marcacadena = "Alba";
            } else if (suprubro == 19) {
                marcacadena = "Staedler";
            } else if (suprubro == 20) {
                marcacadena = "Trabi";
            } else if (suprubro == 21) {
                marcacadena = "Playcolor";
            } else if (suprubro == 22) {
                marcacadena = "Pagoda";
            } else if (suprubro == 23) {
                marcacadena = "Ezco";
            } else if (suprubro == 24) {
                marcacadena = "Pampa";
            } else if (suprubro == 25) {
                marcacadena = "Boreal";
            } else if (suprubro == 26) {
                marcacadena = "América";
            } else if (suprubro == 27) {
                marcacadena = "Gloria";
            } else if (suprubro == 28) {
                marcacadena = "Triunfante";
            } else if (suprubro == 29) {
                marcacadena = "Arte";
            } else if (suprubro == 30) {
                marcacadena = "Proarte";
            }

            //Cargo los valores de pantalla en registro de tabla
            pst.setInt(1, idart);
            pst.setInt(2, suprubro);
            pst.setInt(3, rubro);
            pst.setInt(4, subrubro);
            pst.setString(5, nomart);
            pst.setInt(6, marca);
            pst.setFloat(7, prefinal);
            pst.setFloat(8, precosto);
            pst.setInt(9, porganancia);
            pst.setInt(10, stock);
            pst.setInt(11, stockmin);
            pst.setInt(12, stockmax);
            pst.setString(13, desc);
            pst.setString(14, fecalta);
            pst.setString(15, fecbaja);
            //pst.setBlob(16, imagenarticulo);
            //pst.setBlob(17, imagencodigo);
            pst.setInt(18, codbar);
            pst.setInt(19, est);

            int resultado = pst.executeUpdate();
            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Articulo modificado.");
            }
            cn.close();
            this.dispose();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Articulo NO modificado.");
        }
        //Blanquear campos luego de modificar
        limpiarcampos();
    }//GEN-LAST:event_jButtonModificarActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        // Eliminar articulo poniendo unicamente el estado en cero sin borrar registro:
        String sqlelimina;
        //Cargo valor de baja de articulo
        est = 0;
        jTextFieldEstado.setText(Integer.toString(est));
        try {
            Connection cn = Conexion.conectar();
            sql = "update articulo set art_estado = ? where art_idarticulo = " + idarticulo;
            PreparedStatement pst = cn.prepareStatement(sql);
            //System.out.println(sql);
            pst.setInt(19, est);
            int resultado = pst.executeUpdate();
            //System.out.println(pst);

            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Articulo eliminado.");
            }
            cn.close();
            this.dispose();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Articulo NO eliminado.");
        }
        //Blanquear campos luego de eliminar
        limpiarcampos();
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jButtonRestablecerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRestablecerActionPerformed

        //Carga datos en los campos nuevamente por si hubo cambios
        jTextFieldIdentificacion.setText(Integer.toString(idart));
        jComboBoxSupRubro.setSelectedItem(suprubro);
        jComboBoxRubro.setSelectedItem(rubro);
        jComboBoxSubRubro.setSelectedItem(subrubro);
        jTextFieldNombre.setText(nomart);
        jComboBoxMarca.setSelectedItem(marca);
        jTextFieldPrecioFinal.setText(Float.toString(prefinal));
        jTextFieldPrecioCosto.setText(Float.toString(precosto));
        jTextFieldPorcentajeGanancia.setText(Integer.toString(porganancia));
        jTextFieldStock.setText(Integer.toString(stock));
        jTextFieldStockMinimo.setText(Integer.toString(stockmin));
        jTextFieldStockMaximo.setText(Integer.toString(stockmax));
        jTextFieldDescripcion.setText(desc);
        jDateChooserFechaAlta.setDateFormatString(fecalta);
        jDateChooserFechaBaja.setDateFormatString(fecbaja);
        jLabelArticulo.setText(Integer.toString(codbar));
        jTextFieldEstado.setText(Integer.toString(est));

    }//GEN-LAST:event_jButtonRestablecerActionPerformed

    private void jComboBoxRubroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxRubroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxRubroActionPerformed

    private void jComboBoxSubRubroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSubRubroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxSubRubroActionPerformed

    private void jComboBoxMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxMarcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxMarcaActionPerformed

    private void jComboBoxSupRubroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSupRubroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxSupRubroActionPerformed

    private void jComboBoxSupRubroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxSupRubroItemStateChanged

        if (evt.getStateChange() == ItemEvent.SELECTED) {
            SupRubro supr = (SupRubro) jComboBoxSupRubro.getSelectedItem();
            Rubro rubr = new Rubro();
            DefaultComboBoxModel modelRubro = new DefaultComboBoxModel(rubr.mostrarRubro(supr.getSuprubidsuprubro()));
            jComboBoxRubro.setModel(modelRubro);
            jComboBoxSubRubro.removeAllItems();
        }

    }//GEN-LAST:event_jComboBoxSupRubroItemStateChanged

    private void jComboBoxRubroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxRubroItemStateChanged

        if (evt.getStateChange() == ItemEvent.SELECTED) {
            Rubro rubr = (Rubro) jComboBoxRubro.getSelectedItem();
            SubRubro subr = new SubRubro();
            DefaultComboBoxModel modelSubrubro = new DefaultComboBoxModel(subr.mostrarSubrubro(rubr.getRubidrubro()));
            jComboBoxSubRubro.setModel(modelSubrubro);
        }

    }//GEN-LAST:event_jComboBoxRubroItemStateChanged

    private void jButtonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalirActionPerformed
        //Volver de sonde vino Administrador Gerente o Usuario
        Articulo Art = new Articulo();
        Art.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonSalirActionPerformed

    private void jComboBoxSubRubroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxSubRubroItemStateChanged


    }//GEN-LAST:event_jComboBoxSubRubroItemStateChanged

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
            java.util.logging.Logger.getLogger(ArticuloABM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ArticuloABM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ArticuloABM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ArticuloABM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ArticuloABM().setVisible(true);
                } catch (DocumentException ex) {
                    Logger.getLogger(ArticuloABM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAgregar;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonModificar;
    private javax.swing.JButton jButtonRestablecer;
    private javax.swing.JButton jButtonSalir;
    private javax.swing.JComboBox<String> jComboBoxMarca;
    private javax.swing.JComboBox<String> jComboBoxRubro;
    private javax.swing.JComboBox<String> jComboBoxSubRubro;
    private javax.swing.JComboBox<String> jComboBoxSupRubro;
    private com.toedter.calendar.JDateChooser jDateChooserFechaAlta;
    private com.toedter.calendar.JDateChooser jDateChooserFechaBaja;
    private javax.swing.JLabel jLabelArticulo;
    private javax.swing.JLabel jLabelArticulo1;
    private javax.swing.JLabel jLabelCodigo;
    private javax.swing.JLabel jLabelCodigoBarras;
    private javax.swing.JLabel jLabelDescripcion;
    private javax.swing.JLabel jLabelEstado;
    private javax.swing.JLabel jLabelFechaAlta;
    private javax.swing.JLabel jLabelFechaBaja;
    private javax.swing.JLabel jLabelFondo;
    private javax.swing.JLabel jLabelIdentificacion;
    private javax.swing.JLabel jLabelMarca;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelPorcentajeGanancia;
    private javax.swing.JLabel jLabelPrecioCosto;
    private javax.swing.JLabel jLabelPrecioFinal;
    private javax.swing.JLabel jLabelRubro;
    private javax.swing.JLabel jLabelStock;
    private javax.swing.JLabel jLabelStockMaximo;
    private javax.swing.JLabel jLabelStockMinimo;
    private javax.swing.JLabel jLabelSubRubro;
    private javax.swing.JLabel jLabelSupRubro;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JTextField jTextFieldDescripcion;
    private javax.swing.JTextField jTextFieldEstado;
    private javax.swing.JTextField jTextFieldIdentificacion;
    private javax.swing.JTextField jTextFieldNombre;
    private javax.swing.JTextField jTextFieldPorcentajeGanancia;
    private javax.swing.JTextField jTextFieldPrecioCosto;
    private javax.swing.JTextField jTextFieldPrecioFinal;
    private javax.swing.JTextField jTextFieldStock;
    private javax.swing.JTextField jTextFieldStockMaximo;
    private javax.swing.JTextField jTextFieldStockMinimo;
    // End of variables declaration//GEN-END:variables

    public void limpiarcampos() {
        jTextFieldIdentificacion.setText("");
        jTextFieldNombre.setText("");
        jTextFieldDescripcion.setText("");
        jDateChooserFechaAlta.setDateFormatString("");
        jDateChooserFechaBaja.setDateFormatString("");
        jTextFieldPrecioCosto.setText("");
        jTextFieldPorcentajeGanancia.setText("");
        jTextFieldPrecioFinal.setText("");
        jTextFieldStock.setText("");
        jTextFieldStockMinimo.setText("");
        jTextFieldStockMaximo.setText("");
        jTextFieldEstado.setText("");
        jLabelArticulo.setText("");
        jComboBoxSupRubro.setSelectedIndex(1);
        jComboBoxRubro.setSelectedIndex(1);
        jComboBoxSubRubro.setSelectedIndex(1);
        jComboBoxMarca.setSelectedIndex(1);
    }
}
