/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

/**
 *
 * @author USUARIO
 */
public class UsuarioABM extends javax.swing.JFrame {

    /**
     * Creates new form UsuarioABM
     */
    public UsuarioABM() {
        initComponents();
        setSize(801, 727);
        setResizable(false);
        setTitle("Usuarios");
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonSalir = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();
        jButtonAgregar = new javax.swing.JButton();
        jButtonModificar = new javax.swing.JButton();
        jButtonRestablecer = new javax.swing.JButton();
        jButtonBuscar = new javax.swing.JButton();
        jTextFieldBuscar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabelTitulo = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        jTextField17 = new javax.swing.JTextField();
        jTextField18 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jTextField19 = new javax.swing.JTextField();
        jTextField20 = new javax.swing.JTextField();
        jTextField21 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jTextField22 = new javax.swing.JTextField();
        jTextField23 = new javax.swing.JTextField();
        jTextField24 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jTextField26 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jTextField27 = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jTextField28 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButtonSalir.setText("Salir");
        jButtonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalirActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(724, 17, 68, 30));

        jButtonEliminar.setText("Eliminar");
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(393, 516, -1, -1));

        jButtonAgregar.setText("Agregar");
        jButtonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(193, 516, -1, -1));

        jButtonModificar.setText("Modificar");
        jButtonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificarActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(292, 516, -1, -1));

        jButtonRestablecer.setText("Restablecer");
        jButtonRestablecer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRestablecerActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonRestablecer, new org.netbeans.lib.awtextra.AbsoluteConstraints(73, 516, -1, -1));

        jButtonBuscar.setText("Buscar");
        jButtonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 516, -1, -1));
        getContentPane().add(jTextFieldBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(524, 517, 88, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Busqueda po Nº de Usuario");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(524, 486, 171, 24));

        jLabelTitulo.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabelTitulo.setForeground(new java.awt.Color(51, 153, 255));
        jLabelTitulo.setText("Agregar/Modificar/Eliminar Usuario");
        getContentPane().add(jLabelTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(292, 11, 370, 30));

        jTextField9.setText("jTextField9");
        getContentPane().add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(191, 130, -1, -1));

        jTextField10.setText("jTextField10");
        getContentPane().add(jTextField10, new org.netbeans.lib.awtextra.AbsoluteConstraints(191, 171, -1, -1));

        jLabel3.setText("jLabel3");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(602, 174, -1, -1));

        jTextField11.setText("jTextField11");
        getContentPane().add(jTextField11, new org.netbeans.lib.awtextra.AbsoluteConstraints(461, 218, -1, -1));

        jLabel4.setText("jLabel4");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(409, 306, -1, -1));

        jTextField12.setText("jTextField12");
        getContentPane().add(jTextField12, new org.netbeans.lib.awtextra.AbsoluteConstraints(191, 261, -1, -1));

        jLabel5.setText("jLabel5");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(409, 349, -1, -1));

        jTextField13.setText("jTextField13");
        getContentPane().add(jTextField13, new org.netbeans.lib.awtextra.AbsoluteConstraints(461, 346, -1, -1));

        jLabel6.setText("jLabel6");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(596, 306, -1, -1));

        jTextField14.setText("jTextField14");
        getContentPane().add(jTextField14, new org.netbeans.lib.awtextra.AbsoluteConstraints(461, 433, -1, -1));

        jTextField2.setText("jTextField2");
        getContentPane().add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(654, 218, -1, -1));

        jTextField3.setText("jTextField3");
        getContentPane().add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(461, 303, -1, -1));

        jTextField6.setText("jTextField6");
        getContentPane().add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(654, 407, -1, -1));

        jLabel7.setText("jLabel7");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(409, 174, -1, -1));

        jLabel8.setText("jLabel8");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(409, 221, -1, -1));

        jLabel9.setText("jLabel9");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(602, 133, -1, -1));

        jLabel10.setText("jLabel10");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(133, 174, -1, -1));

        jLabel11.setText("jLabel11");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(411, 410, -1, -1));

        jLabel12.setText("jLabel12");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(596, 375, -1, -1));

        jLabel14.setText("jLabel14");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 85, -1, -1));

        jTextField7.setText("jTextField7");
        getContentPane().add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(197, 407, -1, -1));

        jTextField8.setText("jTextField8");
        getContentPane().add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(461, 82, -1, -1));

        jLabel2.setText("jLabel1");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(133, 85, -1, -1));

        jLabel15.setText("jLabel2");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(133, 133, -1, -1));

        jLabel17.setText("jLabel14");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(596, 410, -1, -1));

        jLabel18.setText("jLabel6");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(596, 264, -1, -1));

        jTextField15.setText("jTextField10");
        getContentPane().add(jTextField15, new org.netbeans.lib.awtextra.AbsoluteConstraints(461, 171, -1, -1));

        jTextField16.setText("jTextField7");
        getContentPane().add(jTextField16, new org.netbeans.lib.awtextra.AbsoluteConstraints(197, 346, -1, -1));

        jTextField17.setText("jTextField6");
        getContentPane().add(jTextField17, new org.netbeans.lib.awtextra.AbsoluteConstraints(654, 372, -1, -1));

        jTextField18.setText("jTextField8");
        getContentPane().add(jTextField18, new org.netbeans.lib.awtextra.AbsoluteConstraints(191, 82, -1, -1));

        jLabel19.setText("jLabel1");
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(409, 85, -1, -1));

        jLabel20.setText("jLabel12");
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(133, 349, -1, -1));

        jLabel22.setText("jLabel8");
        getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(409, 264, -1, -1));

        jTextField19.setText("jTextField2");
        getContentPane().add(jTextField19, new org.netbeans.lib.awtextra.AbsoluteConstraints(654, 171, -1, -1));

        jTextField20.setText("jTextField1");
        getContentPane().add(jTextField20, new org.netbeans.lib.awtextra.AbsoluteConstraints(654, 130, -1, -1));

        jTextField21.setText("jTextField12");
        getContentPane().add(jTextField21, new org.netbeans.lib.awtextra.AbsoluteConstraints(654, 303, -1, -1));

        jLabel25.setText("jLabel10");
        getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(133, 264, -1, -1));

        jTextField22.setText("jTextField4");
        getContentPane().add(jTextField22, new org.netbeans.lib.awtextra.AbsoluteConstraints(654, 256, -1, -1));

        jTextField23.setText("jTextField13");
        getContentPane().add(jTextField23, new org.netbeans.lib.awtextra.AbsoluteConstraints(654, 82, -1, -1));

        jTextField24.setText("jTextField5");
        getContentPane().add(jTextField24, new org.netbeans.lib.awtextra.AbsoluteConstraints(461, 261, -1, -1));

        jLabel26.setText("jLabel11");
        getContentPane().add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(133, 410, -1, -1));

        jTextField26.setText("jTextField9");
        getContentPane().add(jTextField26, new org.netbeans.lib.awtextra.AbsoluteConstraints(461, 130, -1, -1));

        jLabel27.setText("jLabel13");
        getContentPane().add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(411, 436, -1, -1));

        jLabel28.setText("jLabel7");
        getContentPane().add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(409, 133, -1, -1));

        jLabel29.setText("jLabel9");
        getContentPane().add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(133, 221, -1, -1));

        jTextField27.setText("jTextField14");
        getContentPane().add(jTextField27, new org.netbeans.lib.awtextra.AbsoluteConstraints(461, 407, -1, -1));

        jLabel30.setText("jLabel5");
        getContentPane().add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(602, 221, -1, -1));

        jTextField28.setText("jTextField11");
        getContentPane().add(jTextField28, new org.netbeans.lib.awtextra.AbsoluteConstraints(191, 218, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalirActionPerformed
        // Boton para salir del sistema :
        //System.exit(0);
        //Volver de sonde vino Administrador Gerente o Usuario
        Administrador Adm = new Administrador();
        Adm.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonSalirActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        // Eliminar articulo poniendo unicamente el estado en cero sin borrar registro:
        
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jButtonAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarActionPerformed
        // Insertar articulo nuevo:
        
    }//GEN-LAST:event_jButtonAgregarActionPerformed

    private void jButtonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificarActionPerformed
        // Modificar un articulo:
        
    }//GEN-LAST:event_jButtonModificarActionPerformed

    private void jButtonRestablecerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRestablecerActionPerformed
        // Carga los datos guardados en BD del articulo en pantalla - Igual que Busqueda:
        
    }//GEN-LAST:event_jButtonRestablecerActionPerformed

    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed
        // Buscar un articulo:
        
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
            java.util.logging.Logger.getLogger(UsuarioABM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UsuarioABM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UsuarioABM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UsuarioABM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UsuarioABM().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAgregar;
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonModificar;
    private javax.swing.JButton jButtonRestablecer;
    private javax.swing.JButton jButtonSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField28;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JTextField jTextFieldBuscar;
    // End of variables declaration//GEN-END:variables
}
