/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author USUARIO
 */
public class TablaArticulo extends DefaultTableCellRenderer {

    private Component componente;

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        componente = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
        /*Color de letra blanco para las letras de la tabla
        componente.setForeground(Color.white);
        Las filas pares color de fondo rojo y las impares azules
        if (row % 2==0){
            componente.setBackground(Color.red);
        }else{
            componente.setBackground(Color.blue);
        }*/
        //La fila que seleccione se pinta las letras de blanco y al deseleccionarlas de negro
        if (isSelected){
            componente.setForeground(Color.white);
        }else{
            componente.setForeground(Color.black);
        }
                
        return componente;
    }
    
    
}





