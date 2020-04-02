/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.Barcode39;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import net.sourceforge.jbarcodebean.JBarcodeBean;
import net.sourceforge.jbarcodebean.model.Code39;
/**
 *
 * @author USUARIO
 */
public class CodigoBarra {

    public void generarcodigo(String codigo) {
        try {
            JBarcodeBean barcode = new JBarcodeBean();
            barcode.setCodeType(new Code39());
            barcode.setCode(codigo);
            BufferedImage bufferedImage = barcode.draw(new BufferedImage(400, 400, BufferedImage.TYPE_INT_RGB));
            //Genero nombre de archivo y lo guardo como archivo de imagen en disco
            File archivo = new File("" + codigo + ".gif");
            ImageIO.write(bufferedImage, "gif", archivo);
        } catch (IOException ex) {
            Logger.getLogger(CodigoBarra.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void generarcodigopdf(String codigo) {
        try {
            //Abrir base de datos
            
            //Generar c{odigo y Colocarlo en archivo PDF
            Document doc = new Document();
            PdfWriter pdf = null;
            try {
                pdf = PdfWriter.getInstance(doc, new FileOutputStream("codigos.pdf"));
            } catch (DocumentException ex) {
                Logger.getLogger(CodigoBarra.class.getName()).log(Level.SEVERE, null, ex);
            }
            doc.open();
            Barcode39 code = new Barcode39();
            //code.setCode("art_codigobarras");
            code.setCode(codigo);
            Image icon = code.createImageWithBarcode(pdf.getDirectContent(), BaseColor.BLACK, BaseColor.BLACK);
            try {
                doc.add(icon);
            } catch (DocumentException ex) {
                Logger.getLogger(CodigoBarra.class.getName()).log(Level.SEVERE, null, ex);
            }
            doc.close();  
        } catch (IOException ex) {
            Logger.getLogger(CodigoBarra.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
