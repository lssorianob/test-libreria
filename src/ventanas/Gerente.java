/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ventanas;

import java.awt.Image;
import java.util.*;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author USUARIO
 */
public class Gerente extends javax.swing.JFrame {

    /**
     * Creates new form Administrador
     */
    String usuario, perfil, fechacompleta, nombre_usuario;
    public static int sesion_usuario;
    public Gerente() {
        initComponents();
        setSize(845, 630);
        //Cargo usuario y perfil
        usuario = Login.user;
        perfil = Login.perfil;
        jLabelUsuario1.setText(usuario); 
        jLabelPerfil1.setText(perfil);
        //Cargo nombre de libreria
        jLabelLibreria1.setText("BLOCKEARTE");
        //Cargo fecha
        Calendar fecha = new GregorianCalendar();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        fechacompleta = dia + "/" + (mes+1) + "/" + año;
        jLabelFecha1.setText(fechacompleta);
        //Bandera
        sesion_usuario = 1;
        setResizable(false);
        setTitle("Gerente - Sesión de " + usuario);
        setLocationRelativeTo(null);
        ImageIcon wallpaper = new ImageIcon("src/images/FondoAzul.png");// Tapis de fondo
        Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(jLabelFondo.getWidth(),
                jLabelFondo.getHeight(), Image.SCALE_DEFAULT));
        jLabelFondo.setIcon(icono);
        this.repaint();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonArticulos = new javax.swing.JButton();
        jButtonClientes = new javax.swing.JButton();
        jButtonProveedores = new javax.swing.JButton();
        jButtonVentaCaja = new javax.swing.JButton();
        jButtonCompras = new javax.swing.JButton();
        jButtonReportes = new javax.swing.JButton();
        jButtonAcercade = new javax.swing.JButton();
        jLabelUsuario = new javax.swing.JLabel();
        jLabelPerfil = new javax.swing.JLabel();
        jLabelLibreria = new javax.swing.JLabel();
        jLabelFecha = new javax.swing.JLabel();
        jButtonSalir = new javax.swing.JButton();
        jLabelArticulos = new javax.swing.JLabel();
        jLabelArticulos1 = new javax.swing.JLabel();
        jLabelArticulos2 = new javax.swing.JLabel();
        jLabelArticulos3 = new javax.swing.JLabel();
        jLabelArticulos4 = new javax.swing.JLabel();
        jLabelArticulos6 = new javax.swing.JLabel();
        jLabelArticulos7 = new javax.swing.JLabel();
        jLabelUsuario1 = new javax.swing.JLabel();
        jLabelFecha1 = new javax.swing.JLabel();
        jLabelPerfil1 = new javax.swing.JLabel();
        jLabelLibreria1 = new javax.swing.JLabel();
        jLabelFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButtonArticulos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/articulo.png"))); // NOI18N
        jButtonArticulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonArticulosActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonArticulos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 180, 130));

        jButtonClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Proveedores.png"))); // NOI18N
        jButtonClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClientesActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 130, 180, 130));

        jButtonProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/usuarios (3).png"))); // NOI18N
        jButtonProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProveedoresActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 130, 180, 130));

        jButtonVentaCaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ventas.png"))); // NOI18N
        jButtonVentaCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVentaCajaActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonVentaCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 180, 130));

        jButtonCompras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/compras.png"))); // NOI18N
        jButtonCompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonComprasActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonCompras, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 330, 180, 130));

        jButtonReportes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/acercade.png"))); // NOI18N
        jButtonReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReportesActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonReportes, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 330, 180, 130));

        jButtonAcercade.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reportes.png"))); // NOI18N
        jButtonAcercade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAcercadeActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonAcercade, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 330, 180, 130));

        jLabelUsuario.setFont(new java.awt.Font("Perkeo", 1, 18)); // NOI18N
        jLabelUsuario.setForeground(new java.awt.Color(51, 153, 255));
        jLabelUsuario.setText("Usuario:");
        getContentPane().add(jLabelUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 70, 40));

        jLabelPerfil.setFont(new java.awt.Font("Perkeo", 1, 18)); // NOI18N
        jLabelPerfil.setForeground(new java.awt.Color(51, 153, 255));
        jLabelPerfil.setText("Perfil:");
        getContentPane().add(jLabelPerfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, 70, 40));

        jLabelLibreria.setFont(new java.awt.Font("Perkeo", 1, 18)); // NOI18N
        jLabelLibreria.setForeground(new java.awt.Color(51, 153, 255));
        jLabelLibreria.setText("Librería Escolar:");
        getContentPane().add(jLabelLibreria, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, 140, 40));

        jLabelFecha.setFont(new java.awt.Font("Perkeo", 1, 18)); // NOI18N
        jLabelFecha.setForeground(new java.awt.Color(51, 153, 255));
        jLabelFecha.setText("Fecha:");
        getContentPane().add(jLabelFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 0, 60, 40));

        jButtonSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit_closethesession_close_6317.png"))); // NOI18N
        jButtonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalirActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 490, 110, 100));

        jLabelArticulos.setFont(new java.awt.Font("A Perfect Circle", 1, 18)); // NOI18N
        jLabelArticulos.setForeground(new java.awt.Color(51, 153, 255));
        jLabelArticulos.setText("Proveedores");
        getContentPane().add(jLabelArticulos, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 110, 120, 20));

        jLabelArticulos1.setFont(new java.awt.Font("A Perfect Circle", 1, 18)); // NOI18N
        jLabelArticulos1.setForeground(new java.awt.Color(51, 153, 255));
        jLabelArticulos1.setText("Clientes");
        getContentPane().add(jLabelArticulos1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 110, 90, 20));

        jLabelArticulos2.setFont(new java.awt.Font("A Perfect Circle", 1, 18)); // NOI18N
        jLabelArticulos2.setForeground(new java.awt.Color(51, 153, 255));
        jLabelArticulos2.setText("Venta - Caja");
        getContentPane().add(jLabelArticulos2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 310, 120, 20));

        jLabelArticulos3.setFont(new java.awt.Font("A Perfect Circle", 1, 18)); // NOI18N
        jLabelArticulos3.setForeground(new java.awt.Color(51, 153, 255));
        jLabelArticulos3.setText("Compras");
        getContentPane().add(jLabelArticulos3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 310, 90, 20));

        jLabelArticulos4.setFont(new java.awt.Font("A Perfect Circle", 1, 18)); // NOI18N
        jLabelArticulos4.setForeground(new java.awt.Color(51, 153, 255));
        jLabelArticulos4.setText("Reportes");
        getContentPane().add(jLabelArticulos4, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 310, 90, 20));

        jLabelArticulos6.setFont(new java.awt.Font("A Perfect Circle", 1, 18)); // NOI18N
        jLabelArticulos6.setForeground(new java.awt.Color(51, 153, 255));
        jLabelArticulos6.setText("Acerca de...");
        getContentPane().add(jLabelArticulos6, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 310, 120, 20));

        jLabelArticulos7.setFont(new java.awt.Font("A Perfect Circle", 1, 18)); // NOI18N
        jLabelArticulos7.setForeground(new java.awt.Color(51, 153, 255));
        jLabelArticulos7.setText("Articulos");
        getContentPane().add(jLabelArticulos7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 90, 20));

        jLabelUsuario1.setFont(new java.awt.Font("Perkeo", 1, 18)); // NOI18N
        jLabelUsuario1.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jLabelUsuario1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 90, 40));

        jLabelFecha1.setFont(new java.awt.Font("Perkeo", 1, 18)); // NOI18N
        jLabelFecha1.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jLabelFecha1, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 0, 80, 40));

        jLabelPerfil1.setFont(new java.awt.Font("Perkeo", 1, 18)); // NOI18N
        jLabelPerfil1.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jLabelPerfil1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 120, 40));

        jLabelLibreria1.setFont(new java.awt.Font("Perkeo", 1, 18)); // NOI18N
        jLabelLibreria1.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jLabelLibreria1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 0, 160, 40));
        getContentPane().add(jLabelFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, 630));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonArticulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonArticulosActionPerformed
        // TODO add your handling code here:
        Articulo Art = new Articulo();
        Art.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonArticulosActionPerformed

    private void jButtonClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClientesActionPerformed
        // TODO add your handling code here:
        ClienteABM Cli = new ClienteABM();
        Cli.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonClientesActionPerformed

    private void jButtonProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProveedoresActionPerformed
        // TODO add your handling code here:
        ProveedorABM Pro = new ProveedorABM();
        Pro.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonProveedoresActionPerformed

    private void jButtonVentaCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVentaCajaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonVentaCajaActionPerformed

    private void jButtonComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonComprasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonComprasActionPerformed

    private void jButtonReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReportesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonReportesActionPerformed

    private void jButtonAcercadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAcercadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonAcercadeActionPerformed

    private void jButtonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalirActionPerformed
        // TODO add your handling code here:
        // Boton para salir del sistema :
        System.exit(0);
    }//GEN-LAST:event_jButtonSalirActionPerformed

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
            java.util.logging.Logger.getLogger(Gerente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Gerente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Gerente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gerente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Gerente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAcercade;
    private javax.swing.JButton jButtonArticulos;
    private javax.swing.JButton jButtonClientes;
    private javax.swing.JButton jButtonCompras;
    private javax.swing.JButton jButtonProveedores;
    private javax.swing.JButton jButtonReportes;
    private javax.swing.JButton jButtonSalir;
    private javax.swing.JButton jButtonVentaCaja;
    private javax.swing.JLabel jLabelArticulos;
    private javax.swing.JLabel jLabelArticulos1;
    private javax.swing.JLabel jLabelArticulos2;
    private javax.swing.JLabel jLabelArticulos3;
    private javax.swing.JLabel jLabelArticulos4;
    private javax.swing.JLabel jLabelArticulos6;
    private javax.swing.JLabel jLabelArticulos7;
    private javax.swing.JLabel jLabelFecha;
    private javax.swing.JLabel jLabelFecha1;
    private javax.swing.JLabel jLabelFondo;
    private javax.swing.JLabel jLabelLibreria;
    private javax.swing.JLabel jLabelLibreria1;
    private javax.swing.JLabel jLabelPerfil;
    private javax.swing.JLabel jLabelPerfil1;
    private javax.swing.JLabel jLabelUsuario;
    private javax.swing.JLabel jLabelUsuario1;
    // End of variables declaration//GEN-END:variables

}
