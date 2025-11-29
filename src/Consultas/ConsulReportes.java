/*package Consultas;

import Controlador.Conexion;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.cj.jdbc.result.ResultSetMetaData;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.text.Document;

public class ConsulReportes {

    PreparedStatement consultar = null;
    ResultSet resultado = null;

    public void reporte() throws SQLException {

    Document documento = new Document(PageSize.A3);
        try {
            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/Reporte Clientes.pdf"));
            documento.open();

            Paragraph titulo = new Paragraph("Reportes", new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD));
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);

            documento.add(new Paragraph("\n"));

            PdfPTable tabla;

            try (Connection conexion = Conexion.conector()) {
                PreparedStatement psm = conexion.prepareStatement("SELECT * FROM CLIENTES LIMIT 1"); // Limitamos a 1 para obtener solo la estructura de la tabla
                ResultSet rs = psm.executeQuery();
                ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();

                int columnCount = rsmd.getColumnCount();

                tabla = new PdfPTable(columnCount);
                tabla.setWidthPercentage(100);
                Font font = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD);
                PdfPCell cell;

                for (int i = 1; i <= columnCount; i++) {
                    cell = new PdfPCell(new Phrase(rsmd.getColumnName(i), font));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    tabla.addCell(cell);
                }

                // Obtener los datos
                psm = conexion.prepareStatement("SELECT * FROM CLIENTES");
                rs = psm.executeQuery();

                while (rs.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        tabla.addCell(rs.getString(i)); // Suponiendo que todas las columnas son de tipo String
                    }
                }
                documento.add(tabla);
            } catch (SQLException e) {
            }

            documento.close();
            JOptionPane.showMessageDialog(null, "El PDF se ha creado correctamente.");
        } catch (DocumentException | FileNotFoundException e) {}}
}*/
