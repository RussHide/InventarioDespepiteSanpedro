package PaginaReportes;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.jfoenix.controls.JFXRadioButton;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ModalCrearInformeController implements Initializable {

    String currentDir = System.getProperty("user.dir");

    GetSetReportes ObjetoGetSetGenerarReportes = new GetSetReportes();
    SentenciasVales ObjetoSentenciasGenerarReportes = new SentenciasVales();

    @FXML
    private TableView<GetSetReportes> tablaGenerarPdf;
    public static TableView<GetSetReportes> tablaGenerarPdf1;
    @FXML
    private TableColumn<GetSetReportes, Integer> colNumValePdf;
    public static TableColumn<GetSetReportes, Integer> colNumValePdf1;
    @FXML
    private TableColumn<GetSetReportes, String> colRecibioPdf;
    public static TableColumn<GetSetReportes, String> colRecibioPdf1;
    @FXML
    private TableColumn<GetSetReportes, String> colEntregoPdf;
    public static TableColumn<GetSetReportes, String> colEntregoPdf1;
    @FXML
    private TableColumn<GetSetReportes, String> colFechaPdf;
    public static TableColumn<GetSetReportes, String> colFechaPdf1;
    @FXML
    private TableColumn<GetSetReportes, Double> colCantidadPdf;
    public static TableColumn<GetSetReportes, Double> colCantidadPdf1;
    @FXML
    private TableColumn<GetSetReportes, String> colTipoValePdf;
    public static TableColumn<GetSetReportes, String> colTipoValePdf1;
    @FXML
    private TableColumn<GetSetReportes, String> colCodigoPdf;
    public static TableColumn<GetSetReportes, String> colCodigoPdf1;
    @FXML
    private TableColumn<GetSetReportes, String> colUnidadPdf;
    public static TableColumn<GetSetReportes, String> colUnidadPdf1;

    @FXML
    private JFXDatePicker txtFechaInicial;
    public static JFXDatePicker txtFechaInicial1;
    @FXML
    private JFXDatePicker txtFechaFinal;
    public static JFXDatePicker txtFechaFinal1;
    @FXML
    private JFXButton btnBuscar;
    @FXML
    private JFXButton btnGenerarPdf;
    @FXML
    private JFXButton btnCerrarPdf;
    @FXML
    private JFXTextArea txtAreaDescripcionPdf;
    @FXML
    private JFXTextArea txtAreaObservacionPdf;
    static ObservableList<GetSetReportes> listaGenerarReporte;
    @FXML
    private JFXRadioButton radioBtnPDF;
    @FXML
    private ToggleGroup grupoExportar;
    @FXML
    private JFXRadioButton radiobtnExcel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObjetoSentenciasGenerarReportes.acomodarDatePicker(txtFechaFinal);
        ObjetoSentenciasGenerarReportes.acomodarDatePicker(txtFechaInicial);

        txtFechaInicial1 = txtFechaInicial;
        txtFechaFinal1 = txtFechaFinal;
        tablaGenerarPdf1 = tablaGenerarPdf;
        colCantidadPdf1 = colCantidadPdf;
        colCodigoPdf1 = colCodigoPdf;
        colEntregoPdf1 = colEntregoPdf;
        colNumValePdf1 = colNumValePdf;
        colTipoValePdf1 = colTipoValePdf;
        colRecibioPdf1 = colRecibioPdf;
        colFechaPdf1 = colFechaPdf;
        colUnidadPdf1 = colUnidadPdf;

    }

    @FXML
    private void btnBuscar(ActionEvent event) {
        if (txtFechaInicial.getValue() == null) {
            JOptionPane.showMessageDialog(null, "Debes llenar los campos de las fechas");
        } else if (txtFechaFinal.getValue() == null) {
            JOptionPane.showMessageDialog(null, "Debes llenar los campos de las fechas");

        } else {
            ObjetoSentenciasGenerarReportes.datosTablaGenerarReporte();
            if (listaGenerarReporte.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No se encontraron resultadods");
            }
        }
    }

    @FXML
    private void btnGenerarPdf(ActionEvent event) throws FileNotFoundException {

        if (tablaGenerarPdf.getItems().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debes llenar la tabla");

        } else {
            if (radioBtnPDF.isSelected()) {
                if (tablaGenerarPdf.getItems().isEmpty()) {
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
                        PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/Reporte del " + currentTime + ".pdf"));
                        documento.open();
                        Paragraph titulo = new Paragraph("Reporte inventario despepite\n\n", FontFactory.getFont("arial", // fuente
                                22, // tamaño
                                Font.ITALIC, // estilo
                                BaseColor.CYAN));
                        titulo.setAlignment(1);
                        documento.add(titulo);
                        try {
                            Image foto = Image.getInstance(
                                    currentDir + "//img//logo.png");
                            foto.scaleToFit(100, 100);

                            foto.setAbsolutePosition(0, 750);
                            documento.add(foto);
                        } catch (Exception e) {
                            System.out.println(e);
                        }

                        PdfPTable tabla = new PdfPTable(8);
                        tabla.setWidths(new float[]{30, 50, 50, 45, 50, 25, 30, 45});
                        tabla.addCell(new PdfPCell(new Phrase("No. Vale", fuenteBlod)));
                        tabla.addCell(new PdfPCell(new Phrase("Recibió", fuenteBlod)));
                        tabla.addCell(new PdfPCell(new Phrase("Entregó", fuenteBlod)));
                        tabla.addCell(new PdfPCell(new Phrase("Fecha", fuenteBlod)));
                        tabla.addCell(new PdfPCell(new Phrase("Insumo", fuenteBlod)));
                        tabla.addCell(new PdfPCell(new Phrase("Unidad", fuenteBlod)));
                        tabla.addCell(new PdfPCell(new Phrase("Cantidad", fuenteBlod)));
                        tabla.addCell(new PdfPCell(new Phrase("Tipo vale", fuenteBlod)));
                        tabla.setWidthPercentage(110);

                        int numTablas = 0;
                        tablaGenerarPdf.getItems().size();
                        for (int i = 0; i < tablaGenerarPdf.getItems().size(); i++) {
                            tabla.addCell(new PdfPCell(new Phrase(String.valueOf(tablaGenerarPdf.getItems().get(i).getFolio()), fuenteNormal)));

                            tabla.addCell(new PdfPCell(new Phrase(tablaGenerarPdf.getItems().get(i).getRecibio(), fuenteNormal)));
                            tabla.addCell(new PdfPCell(new Phrase(tablaGenerarPdf.getItems().get(i).getEntrego(), fuenteNormal)));
                            tabla.addCell(new PdfPCell(new Phrase(tablaGenerarPdf.getItems().get(i).getFechaString(), fuenteNormal)));
                            tabla.addCell(new PdfPCell(new Phrase(tablaGenerarPdf.getItems().get(i).getCodigoInsumoModificar(), fuenteNormal)));
                            tabla.addCell(new PdfPCell(new Phrase(tablaGenerarPdf.getItems().get(i).getUnidad(), fuenteNormal)));
                            tabla.addCell(new PdfPCell(new Phrase(String.valueOf(tablaGenerarPdf.getItems().get(i).getCantidad()), fuenteNormal)));
                            tabla.addCell(new PdfPCell(new Phrase(tablaGenerarPdf.getItems().get(i).getTipoVale(), fuenteNormal)));
                        }
                        documento.add(tabla);
                        documento.close();
                        JOptionPane.showMessageDialog(null, "Reporte creado.");
                    } catch (DocumentException
                            | FileNotFoundException e) {
                    }
                }

            } else if (radiobtnExcel.isSelected()) {
                ///////AQUIIIIIIIIIIIIIIIII
                if (tablaGenerarPdf.getItems().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Debes llenar la tabla");
                } else {
                    XSSFWorkbook book = new XSSFWorkbook();
                    XSSFSheet sheet = book.createSheet("Vales"); //nombre de la hoja
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
//                sheet.setColumnWidth(1, 16 * 256);
                    sheet.setColumnWidth(0, 16 * 256);
                    sheet.setColumnWidth(1, 16 * 256);
                    sheet.setColumnWidth(2, 16 * 256);
                    sheet.setColumnWidth(3, 16 * 256);
                    sheet.setColumnWidth(4, 16 * 256);
//                sheet.setColumnWidth(6, 16 * 256);
//                sheet.setColumnWidth(7, 16 * 256);
                    sheet.setColumnWidth(7, 16 * 256);
                    sheet.setColumnWidth(8, 20 * 256);
                    sheet.setColumnWidth(9, 20 * 256);

                    //Crear Row 0 del titulo
                    XSSFRow rowTitulo = sheet.createRow(0);
                    rowTitulo.createCell(0).setCellValue("Reporte de vales despepite");
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
                    rowTop.createCell(0).setCellValue("No. Vale");
                    rowTop.createCell(1).setCellValue("Recibió");
                    rowTop.createCell(2).setCellValue("Entregó");
                    rowTop.createCell(3).setCellValue("Fecha");
                    rowTop.createCell(4).setCellValue("Cod. Insumo");
                    rowTop.createCell(5).setCellValue("Unidad");
                    rowTop.createCell(6).setCellValue("Cantidad");
                    rowTop.createCell(7).setCellValue("Tipo vale");
                    rowTop.createCell(8).setCellValue("Descripcion");
                    rowTop.createCell(9).setCellValue("Observacion");
                    //row.setHeight((short) (row.getHeight() * 1.10));

                    //Darle estilo a la primer Row 4 de informacion 
                    for (int i = 0; i < 10; i++) {
                        XSSFCell celda = rowTop.getCell(i);
                        celda.setCellStyle(borderAll);
                    }
                    //Crar Row 5
                    int controlExterno = 0;
                    for (int i = 5; controlExterno < tablaGenerarPdf.getItems().size(); i++) {
                        XSSFRow rowInformacion = sheet.createRow(i);
                        rowInformacion.createCell(0).setCellValue(tablaGenerarPdf.getItems().get(controlExterno).getFolio());
                        rowInformacion.createCell(1).setCellValue(tablaGenerarPdf.getItems().get(controlExterno).getRecibio());
                        rowInformacion.createCell(2).setCellValue(tablaGenerarPdf.getItems().get(controlExterno).getEntrego());
                        rowInformacion.createCell(3).setCellValue(tablaGenerarPdf.getItems().get(controlExterno).getFechaString());
                        rowInformacion.createCell(4).setCellValue(tablaGenerarPdf.getItems().get(controlExterno).getCodigoInsumoModificar());
                        rowInformacion.createCell(5).setCellValue(tablaGenerarPdf.getItems().get(controlExterno).getUnidad());
                        rowInformacion.createCell(6).setCellValue(tablaGenerarPdf.getItems().get(controlExterno).getCantidad());
                        rowInformacion.createCell(7).setCellValue(tablaGenerarPdf.getItems().get(controlExterno).getTipoVale());
                        rowInformacion.createCell(8).setCellValue(tablaGenerarPdf.getItems().get(controlExterno).getDescripcion());
                        rowInformacion.createCell(9).setCellValue(tablaGenerarPdf.getItems().get(controlExterno).getObservacion());
                        controlExterno++;
                    }

                    try {
                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH.mm.ss");
                        Calendar calendar = Calendar.getInstance();
                        String currentTime = String.valueOf(formatter.format(calendar.getTime()));
                        String ruta = System.getProperty("user.home");

                        FileOutputStream fileout = new FileOutputStream(ruta + "/Desktop/Reporte de vales del" + currentTime + ".xlsx"); //nombre del archivo
                        book.write(fileout);
                        fileout.close();
                        JOptionPane.showMessageDialog(null, "Reporte creado.");
                    } catch (IOException e) {
                        System.out.println("Error" + e);
                    }
                }

                //////////AQUI TERMINAAAAAAAAAAAA
            }
        }

    }

    @FXML
    private void btnCerrarPdf(ActionEvent event) {
        Stage stage = (Stage) btnCerrarPdf.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void mostrarInformacionDescripcionObservacion(MouseEvent event) {
        if (tablaGenerarPdf.getSelectionModel().getSelectedIndex() == -1) {
        } else {
            txtAreaDescripcionPdf.setText(tablaGenerarPdf.getSelectionModel().getSelectedItem().getDescripcion());
            txtAreaObservacionPdf.setText(tablaGenerarPdf.getSelectionModel().getSelectedItem().getObservacion());
        }
    }

    @FXML
    private void validarTxtFechaInicial(KeyEvent event) {
        int escribirFechaInicial = 0;
        for (int i = 0; i <= 10; i++) {
            if (SentenciasVales.fecha[i] == event.getCharacter().charAt(0)) {
                escribirFechaInicial = 1;
            }
        }
        if (escribirFechaInicial == 0) {
            event.consume();
        }
    }

    @FXML
    private void validarTxtFechaFinal(KeyEvent event) {
        int escribirFechaFinal = 0;
        for (int i = 0; i <= 10; i++) {
            if (SentenciasVales.fecha[i] == event.getCharacter().charAt(0)) {
                escribirFechaFinal = 1;
            }
        }
        if (escribirFechaFinal == 0) {
            event.consume();
        }
    }
}
