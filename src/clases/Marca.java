/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author USUARIO
 */
public class Marca {
    
    private int maridmarca;
    private String marnombremarca;

    public int getMaridmarca() {
        return maridmarca;
    }

    public void setMaridmarca(int maridmarca) {
        this.maridmarca = maridmarca;
    }

    public String getMarnombremarca() {
        return marnombremarca;
    }

    public void setMarnombremarca(String marnombremarca) {
        this.marnombremarca = marnombremarca;
    }
    
    @Override
    public String toString() {
        return this.marnombremarca;
    }

    public Vector<Marca> mostrarMarca() {

        PreparedStatement ps = null;
        ResultSet rs = null;
        //Conexión a la base de datos
        Connection cn;
        cn = Conexion.conectar();
        
        Vector<Marca> datos = new Vector<Marca>();
        Marca dat = null;
        try {

            String sql = "SELECT * FROM marca";
            //ps = con.prepareStatement(sql);
            PreparedStatement pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();

            dat = new Marca();
            dat.setMaridmarca(0);
            dat.setMarnombremarca("Selecciona marca");
            datos.add(dat);

            while (rs.next()) {
                dat = new Marca();
                dat.setMaridmarca(rs.getInt("mar_idmarca"));
                dat.setMarnombremarca(rs.getString("mar_nombremarca"));
                datos.add(dat);
            }
            rs.close();
        } catch (SQLException ex) {
            System.err.println("Error consulta :" + ex.getMessage());
        }
        return datos;
    }
    
}
