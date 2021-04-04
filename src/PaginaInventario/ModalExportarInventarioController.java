package PaginaInventario;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

public class ModalExportarInventarioController implements Initializable {

        String currentDir = System.getProperty("user.dir");

    SentenciasInventario ObjetoSentenciasInventario = new SentenciasInventario();
    GetSetInventario ObjetoGetSetInventario = new GetSetInventario();

    @FXML
    private TableView<GetSetInventario> tablaReporteInventario;
    public static TableView<GetSetInventario> tablaReporteInventario1;
    @FXML
    private TableColumn<GetSetInventario, String> colCodigo;
    public static TableColumn<GetSetInventario, String> colCodigo1;
    @FXML
    private TableColumn<GetSetInventario, String> colNombre;
    public static TableColumn<GetSetInventario, String> colNombre1;
    @FXML
    private TableColumn<GetSetInventario, String> colDescripcion;
    public static TableColumn<GetSetInventario, String> colDescripcion1;
    @FXML
    private TableColumn<GetSetInventario, Double> colCantidad;
    public static TableColumn<GetSetInventario, Double> colCantidad1;
    @FXML
    private TableColumn<GetSetInventario, String> colUnidades;
    public static TableColumn<GetSetInventario, String> colUnidades1;

    static ObservableList<GetSetInventario> listaGenerarInventarioXD;

    @FXML
    private JFXDatePicker txtFechaReporte;
    public static JFXDatePicker txtFechaReporte1;
    @FXML
    private JFXButton btnGenerar;
    @FXML
    private JFXButton btnCancelar;
    @FXML
    private JFXButton btnBuscar;
    @FXML
    private JFXRadioButton radioBtnExcel;
    @FXML
    private JFXRadioButton radioBtnPDF;
    @FXML
    private ToggleGroup grupoExportarInventario;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObjetoSentenciasInventario.acomodarDatePicker(txtFechaReporte);
        colCantidad1 = colCantidad;
        colCodigo1 = colCodigo;
        colDescripcion1 = colDescripcion;
        colUnidades1 = colUnidades;
        colNombre1 = colNombre;
        txtFechaReporte1 = txtFechaReporte;
        tablaReporteInventario1 = tablaReporteInventario;

    }

    @FXML
    private void btnGenerar(ActionEvent event) {
        if (radioBtnPDF.isSelected()) {
            if (tablaReporteInventario.getItems().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debes llenar la tabla");
            } else {
                Document documento = new Document();
                try {
                    Font fuenteBlod = new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD);
                    Font fuenteNormal = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL);
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH.mm.ss");
                    String currentTime = String.valueOf(formatter.format(calendar.getTime()));
                    String ruta = System.getProperty("user.home");
                    PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/Reporte de inventario del " + currentTime + ".pdf"));

                    documento.open();
                    Paragraph titulo = new Paragraph("Reporte inventario despepite\n\n",
                            FontFactory.getFont("arial", // fuente
                                    22, // tamaño
                                    Font.ITALIC, // estilo
                                    BaseColor.CYAN));
                    titulo.setAlignment(1);
                    documento.add(titulo);
                    try {
                        Image foto = Image.getInstance(
                                currentDir + "//img//logo.png"
                        );

                        foto.scaleToFit(100, 100);

                        foto.setAbsolutePosition(0, 750);
                        documento.add(foto);
                    } catch (Exception e) {
                        System.out.println(e);
                    }

                    PdfPTable tabla = new PdfPTable(5);
                    tabla.setWidths(new float[]{35, 35, 35, 35, 35});
                    tabla.addCell(new PdfPCell(new Phrase("Codigo", fuenteBlod)));
                    tabla.addCell(new PdfPCell(new Phrase("Nombre", fuenteBlod)));
                    tabla.addCell(new PdfPCell(new Phrase("Descripcion", fuenteBlod)));
                    tabla.addCell(new PdfPCell(new Phrase("Cantidad", fuenteBlod)));
                    tabla.addCell(new PdfPCell(new Phrase("Unidades", fuenteBlod)));
                    tabla.setWidthPercentage(110);

                    for (int i = 0; i < tablaReporteInventario.getItems().size(); i++) {
                        tabla.addCell(new PdfPCell(new Phrase(tablaReporteInventario.getItems().get(i).getCodigo(), fuenteNormal)));
                        tabla.addCell(new PdfPCell(new Phrase(tablaReporteInventario.getItems().get(i).getNombre(), fuenteNormal)));

                        tabla.addCell(new PdfPCell(new Phrase(tablaReporteInventario.getItems().get(i).getDescripcion(), fuenteNormal)));
                        tabla.addCell(new PdfPCell(new Phrase(String.valueOf(tablaReporteInventario.getItems().get(i).getCantidad()), fuenteNormal)));
                        tabla.addCell(new PdfPCell(new Phrase(tablaReporteInventario.getItems().get(i).getUnidad(), fuenteNormal)));
                    }
                    documento.add(tabla);
                    documento.close();
                    JOptionPane.showMessageDialog(null, "Reporte creado.");
                } catch (DocumentException
                        | FileNotFoundException e) {
                }
            }
        } else if (radioBtnExcel.isSelected()) {
            if (tablaReporteInventario.getItems().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debes llenar la tabla");
            } else {
                XSSFWorkbook book = new XSSFWorkbook();
                XSSFSheet sheet = book.createSheet("Inventario"); //nombre de la hoja
                //ESTILO BORDES
                XSSFCellStyle borderAll = book.createCellStyle();
                borderAll.setBorderTop(BorderStyle.MEDIUM);
                borderAll.setBorderBottom(BorderStyle.MEDIUM);
                borderAll.setBorderLeft(BorderStyle.MEDIUM);
                borderAll.setBorderRight(BorderStyle.MEDIUM);
                //ESTILO CENTRAR AL MEDIO
                XSSFCellStyle centrarAlign = book.createCellStyle();
                centrarAlign.setAlignment(HorizontalAlignment.CENTER);
                centrarAlign.setVerticalAlignment(VerticalAlignment.CENTER);

                //Tamaño de columnas
                sheet.setColumnWidth(1, 16 * 256);
                sheet.setColumnWidth(2, 16 * 256);
                sheet.setColumnWidth(3, 16 * 256);
                sheet.setColumnWidth(4, 16 * 256);
                sheet.setColumnWidth(5, 16 * 256);
                //Crear Row 0 del titulo
                XSSFRow rowTitulo = sheet.createRow(0);
                rowTitulo.createCell(0).setCellValue("Reporte de inventario despepite");
                //Darle estilo a la Row 0 del titulo
                XSSFCell celdaEstilo = rowTitulo.getCell(0);
                celdaEstilo.setCellStyle(centrarAlign);
                //Combinar las celdas de Row 0
                int firstRow = 0;
                int lastRow = 3;
                int firstCol = 0;
                int lastCol = 6;
                sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));
                //Crar Row 4
                XSSFRow rowTop = sheet.createRow(4);
                //Crear celdas de Row 4
                rowTop.createCell(1).setCellValue("Codigo");
                rowTop.createCell(2).setCellValue("Nombre");
                rowTop.createCell(3).setCellValue("Descripcion");
                rowTop.createCell(4).setCellValue("Cantidad");
                rowTop.createCell(5).setCellValue("Unidad");
                //row.setHeight((short) (row.getHeight() * 1.10));
                //Darle estilo a la primer Row 4 de informacion 
                for (int i = 1; i < 6; i++) {
                    XSSFCell celda = rowTop.getCell(i);
                    celda.setCellStyle(borderAll);
                }
                //Crar Row 5
                int controlExterno = 0;
                for (int i = 5; controlExterno < tablaReporteInventario.getItems().size(); i++) {
                    XSSFRow rowInformacion = sheet.createRow(i);
                    rowInformacion.createCell(1).setCellValue(tablaReporteInventario.getItems().get(controlExterno).getCodigo());
                    rowInformacion.createCell(2).setCellValue(tablaReporteInventario.getItems().get(controlExterno).getNombre());
                    rowInformacion.createCell(3).setCellValue(tablaReporteInventario.getItems().get(controlExterno).getDescripcion());
                    rowInformacion.createCell(4).setCellValue(tablaReporteInventario.getItems().get(controlExterno).getCantidad());
                    rowInformacion.createCell(5).setCellValue(tablaReporteInventario.getItems().get(controlExterno).getUnidad());
                    controlExterno++;
                }

                try {
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH.mm.ss");
                    Calendar calendar = Calendar.getInstance();
                    String currentTime = String.valueOf(formatter.format(calendar.getTime()));
                    String ruta = System.getProperty("user.home");

                    FileOutputStream fileout = new FileOutputStream(ruta + "/Desktop/Reporte del " + currentTime + ".xlsx"); //nombre del archivo
                    book.write(fileout);
                    fileout.close();
                    JOptionPane.showMessageDialog(null, "Reporte creado.");
                } catch (IOException e) {
                    System.out.println("Error" + e);
                }

            }

        }
    }

    @FXML
    private void btnBuscar(ActionEvent event) {
        ObjetoSentenciasInventario.datosTablaBuscarGenerar();
        if (listaGenerarInventarioXD.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se encontraron resultadods");
        }
    }

    @FXML
    private void validarTxtFecha(KeyEvent event) {
        int escribirFecha = 0;
        for (int i = 0; i <= 10; i++) {
            if (SentenciasInventario.fecha[i] == event.getCharacter().charAt(0)) {
                escribirFecha = 1;
            }
        }
        if (escribirFecha == 0) {
            event.consume();
        }
    }

    @FXML
    private void btnCancelar(ActionEvent event) {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }
}
