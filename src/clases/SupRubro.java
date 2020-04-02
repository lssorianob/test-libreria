/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import clases.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author USUARIO
 */
public class SupRubro {
    
   private int suprubidsuprubro;
   private String suprubnombre;

    public int getSuprubidsuprubro() {
        return suprubidsuprubro;
    }

    public void setSuprubidsuprubro(int suprubidsuprubro) {
        this.suprubidsuprubro = suprubidsuprubro;
    }

    public String getSuprubnombre() {
        return suprubnombre;
    }

    public void setSuprubnombre(String suprubnombre) {
        this.suprubnombre = suprubnombre;
    }
    
    @Override
    public String toString() {
        return this.suprubnombre;
    }

    public Vector<SupRubro> mostrarSuprubro() {

        PreparedStatement ps = null;
        ResultSet rs = null;
        //Conexión a la base de datos
        Connection cn;
        cn = Conexion.conectar();
        //Creación de objeto de tipo vector
        Vector<SupRubro> datos = new Vector<SupRubro>();
        //Creación variable de tipo SupRubro
        SupRubro dat = null;
        try {

            String sql = "SELECT * FROM superrubro";
            //ps = con.prepareStatement(sql);
            PreparedStatement pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();

            dat = new SupRubro();
            dat.setSuprubidsuprubro(0);
            dat.setSuprubnombre("Selecciona suprubro");
            datos.add(dat);

            while (rs.next()) {
                dat = new SupRubro();
                dat.setSuprubidsuprubro(rs.getInt("suprub_idsuprubro"));
                dat.setSuprubnombre(rs.getString("suprub_nombre"));
                datos.add(dat);
            }
            rs.close();
        } catch (SQLException ex) {
            System.err.println("Error consulta :" + ex.getMessage());
        }
        return datos;
    }
}
