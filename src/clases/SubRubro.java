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
public class SubRubro {
    
   private int subruidsubrubro;
   private String subrunombre;

    public int getSubruidsubrubro() {
        return subruidsubrubro;
    }

    public void setSubruidsubrubro(int subruidsubrubro) {
        this.subruidsubrubro = subruidsubrubro;
    }

    public String getSubrunombre() {
        return subrunombre;
    }

    public void setSubrunombre(String subrunombre) {
        this.subrunombre = subrunombre;
    }
    
    @Override
    public String toString() {
        return this.subrunombre;
    }

    public Vector<SubRubro> mostrarSubrubro(Integer idrubro) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        //Conexión a la base de datos
        Connection cn;
        cn = Conexion.conectar();
        
        Vector<SubRubro> datos = new Vector<SubRubro>();
        SubRubro dat = null;
        try {

            String sql = "SELECT * FROM subrubro WHERE subru_idrubro=" + idrubro;
            //ps = con.prepareStatement(sql);
            PreparedStatement pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();

            dat = new SubRubro();
            dat.setSubruidsubrubro(0);
            dat.setSubrunombre("Selecciona subrubro");
            datos.add(dat);

            while (rs.next()) {
                dat = new SubRubro();
                dat.setSubruidsubrubro(rs.getInt("subru_idsubrubro"));
                dat.setSubrunombre(rs.getString("subru_nombre"));
                datos.add(dat);
            }
            rs.close();
        } catch (SQLException ex) {
            System.err.println("Error consulta :" + ex.getMessage());
        }
        return datos;
    }

    
   
}
