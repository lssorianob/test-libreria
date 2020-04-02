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
public class Rubro {
    
   private int rubidrubro;
   private String rubnombre;

    public int getRubidrubro() {
        return rubidrubro;
    }

    public void setRubidrubro(int rubidrubro) {
        this.rubidrubro = rubidrubro;
    }

    public String getRubnombre() {
        return rubnombre;
    }

    public void setRubnombre(String rubnombre) {
        this.rubnombre = rubnombre;
    }
   
    @Override
    public String toString() {
        return this.rubnombre;
    }

    public Vector<Rubro> mostrarRubro(Integer idsuprubro) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        //Conexión a la base de datos
        Connection cn;
        cn = Conexion.conectar();
        
        Vector<Rubro> datos = new Vector<Rubro>(); //Creo el objeto vector datos de la clase Rubro
        Rubro dat = null; //Creo variable de la clase Rubro hago esto para poder crear el objeto en 2 lugares distintos
        try {

            String sql = "SELECT * FROM rubro WHERE rub_idsuprubro=" + idsuprubro;
            //ps = con.prepareStatement(sql);
            PreparedStatement pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();

            dat = new Rubro(); //Creo el objeto de clase Rubro
            dat.setRubidrubro(0);
            dat.setRubnombre("Selecciona rubro");
            datos.add(dat);

            while (rs.next()) {
                dat = new Rubro(); //Creo el objeto de clase Rubro
                dat.setRubidrubro(rs.getInt("rub_idrubro"));
                dat.setRubnombre(rs.getString("rub_nombre"));
                datos.add(dat);
            }
            rs.close();
        } catch (SQLException ex) {
            System.err.println("Error consulta :" + ex.getMessage());
        }
        return datos; //retorno el objeto vector
    }

    
    
}
