package PaginaReportes;

import com.jfoenix.controls.JFXDatePicker;
import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import javax.swing.JOptionPane;

public class SentenciasVales {

    Connection conn = null;
    //Conexion.ConexionBD.SubConexion();
    PreparedStatement ps = null;
    ResultSet rs = null;
    static char[] decimal = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '.'};
    static char[] entero = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
    static char[] fecha = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '/'};

    public ObservableList<GetSetReportes> GetDatosTablaVales() {
        conn = Conexion.ConexionBD.SubConexion();
        ObservableList<GetSetReportes> listaPaginaVales = FXCollections.observableArrayList();
        try {
            ps = conn.prepareStatement("select * from Vales");
            rs = ps.executeQuery();
            while (rs.next()) {
                listaPaginaVales.add(new GetSetReportes(rs.getInt("idVale"),
                        rs.getString("folio"),
                        rs.getString("descripcion"),
                        rs.getString("observacion"),
                        rs.getString("tipoVale"),
                        rs.getString("entrego"),
                        rs.getString("recibio"),
                        rs.getString("fechaString"),
                        rs.getString("codigoInsumo"),
                        rs.getDouble("cantidad"),
                        rs.getString("unidad")
                ));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return listaPaginaVales;
    }

    public void datosTablaVales() {
        PaginaReportesController.colFolio1.setCellValueFactory(new PropertyValueFactory<>("folio"));
        PaginaReportesController.colRecibio1.setCellValueFactory(new PropertyValueFactory<>("recibio"));
        PaginaReportesController.colEntrego1.setCellValueFactory(new PropertyValueFactory<>("entrego"));
        PaginaReportesController.colFecha1.setCellValueFactory(new PropertyValueFactory<>("fechaString"));
        PaginaReportesController.colInsumo1.setCellValueFactory(new PropertyValueFactory<>("codigoInsumoModificar"));
        PaginaReportesController.colCantidad1.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        PaginaReportesController.colUnidad1.setCellValueFactory(new PropertyValueFactory<>("unidad"));
        PaginaReportesController.colTipoVale1.setCellValueFactory(new PropertyValueFactory<>("tipoVale"));

        PaginaReportesController.listaPaginaVales = GetDatosTablaVales();
        PaginaReportesController.tablaReportes1.setItems(PaginaReportesController.listaPaginaVales);
    }

    ///////////////////////
    public static ObservableList<GetSetReportes> GetDatosTablaGenerarReporte() {
        java.sql.Connection conn = Conexion.ConexionBD.SubConexion();
        ObservableList<GetSetReportes> listaGenerarReporte = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select r.*, p.nombre from Vales as r INNER JOIN productos as p on r.CODIGOINSUMO = p.codigo where r.fechaDate between '" + ModalCrearInformeController.txtFechaInicial1.getValue() + "' AND '" + ModalCrearInformeController.txtFechaFinal1.getValue() + "'");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listaGenerarReporte.add(new GetSetReportes(rs.getString("folio"),
                        rs.getString("descripcion"),
                        rs.getString("observacion"),
                        rs.getString("tipoVale"),
                        rs.getString("entrego"),
                        rs.getString("recibio"),
                        rs.getString("fechaString"),
                        rs.getString("codigoInsumo"),
                        rs.getDouble("cantidad"),
                        rs.getString("unidad"),
                        rs.getString("nombre")
                ));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return listaGenerarReporte;
    }

    public void datosTablaGenerarReporte() {

        ModalCrearInformeController.colNumValePdf1.setCellValueFactory(new PropertyValueFactory<>("folio"));
        ModalCrearInformeController.colRecibioPdf1.setCellValueFactory(new PropertyValueFactory<>("recibio"));
        ModalCrearInformeController.colEntregoPdf1.setCellValueFactory(new PropertyValueFactory<>("entrego"));
        ModalCrearInformeController.colFechaPdf1.setCellValueFactory(new PropertyValueFactory<>("fechaString"));
        ModalCrearInformeController.colCodigoPdf1.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
        ModalCrearInformeController.colCantidadPdf1.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        ModalCrearInformeController.colUnidadPdf1.setCellValueFactory(new PropertyValueFactory<>("unidad"));
        ModalCrearInformeController.colTipoValePdf1.setCellValueFactory(new PropertyValueFactory<>("tipoVale"));

        ModalCrearInformeController.listaGenerarReporte = GetDatosTablaGenerarReporte();
        ModalCrearInformeController.tablaGenerarPdf1.setItems(ModalCrearInformeController.listaGenerarReporte);
    }
////////////////////

    public boolean AgregarVale(GetSetReportes ObjetoReportes) {

        try {
            conn = Conexion.ConexionBD.SubConexion();
            ps = conn.prepareStatement("insert into Vales (tipoVale, descripcion, observacion, entrego, recibio, fechaDate, fechaString, codigoInsumo, cantidad, unidad, folio) values (?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, ObjetoReportes.getTipoVale());
            ps.setString(2, ObjetoReportes.getDescripcion());
            ps.setString(3, ObjetoReportes.getObservacion());
            ps.setString(4, ObjetoReportes.getEntrego());
            ps.setString(5, ObjetoReportes.getRecibio());
            ps.setDate(6, ObjetoReportes.getFechaDate());
            String fechaString = String.valueOf(ObjetoReportes.getFechaString());
            String año = fechaString.substring(0, 4);
            String mes = fechaString.substring(5, 7);
            String dia = fechaString.substring(8, 10);
            String resultado = dia + "-" + mes + "-" + año;
            ps.setString(7, resultado);
            ps.setString(8, ObjetoReportes.getCodigoInsumoModificar());
            ps.setDouble(9, ObjetoReportes.getCantidad());
            ps.setString(10, ObjetoReportes.getUnidad());
            ps.setString(11, ObjetoReportes.getFolio());
            ps.executeUpdate();
            datosTablaVales();

        } catch (Exception e) {
            System.out.println("Error al agregar" + e);
            return false;
        }
        return true;
    }

    public boolean SumarCantidades(GetSetReportes ObjetoOperaciones) {
        try {
            conn = Conexion.ConexionBD.SubConexion();
            ps = conn.prepareStatement("update productos set cantidad = cantidad +" + ObjetoOperaciones.getCantidad() + " where codigo = '" + ObjetoOperaciones.getCodigoInsumoModificar() + "'");
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error al sumar" + e);
            return false;
        }
        return true;
    }

    public boolean restarCantidades(GetSetReportes ObjetoOperaciones) {
        try {
            conn = Conexion.ConexionBD.SubConexion();
            ps = conn.prepareStatement("update productos set cantidad = cantidad -" + ObjetoOperaciones.getCantidad() + " where codigo = '" + ObjetoOperaciones.getCodigoInsumoModificar() + "'");
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al sumar" + e);
            return false;
        }
        return true;
    }

    public boolean controlRestas(GetSetReportes ObjetoOperaciones) {
        double cantidadActual = 0;
        try {
            conn = Conexion.ConexionBD.SubConexion();
            ps = conn.prepareStatement("select cantidad from productos where codigo = '" + ObjetoOperaciones.getCodigoInsumoModificar() + "'");
            rs = ps.executeQuery();
            if (rs.next()) {
                cantidadActual = rs.getDouble("cantidad");
            }
            System.out.println("ANTES DE ENTRAR " + cantidadActual);

            if (cantidadActual - ObjetoOperaciones.getCantidad() >= 0) {

                System.out.println("True");
                return true;
            }
            JOptionPane.showMessageDialog(null, "No queda mas stock");
        } catch (Exception e) {
            System.out.println("Error es menos de 0" + e);
            return false;
        }
        System.out.println("False");
        return false;

    }

    public boolean EliminarReporte(GetSetReportes ObjetoReportes) {
        try {
            conn = Conexion.ConexionBD.SubConexion();
            if (ObjetoReportes.getTipoVale().equals("Salida")) {
                ps = conn.prepareStatement("update productos set cantidad = cantidad +" + ObjetoReportes.getCantidad() + " where codigo = '" + ObjetoReportes.getCodigoInsumoModificar() + "'");
                ps.executeUpdate();
                ps = conn.prepareStatement("delete from Vales where idVale= " + ObjetoReportes.getIdVale());
                ps.executeUpdate();
                datosTablaVales();
                return true;
            } else if (ObjetoReportes.getTipoVale().equals("Entrada")) {
                ps = conn.prepareStatement("update productos set cantidad = cantidad -" + ObjetoReportes.getCantidad() + " where codigo = '" + ObjetoReportes.getCodigoInsumoModificar() + "'");
                ps.executeUpdate();
                ps = conn.prepareStatement("delete from Vales where idVale= " + ObjetoReportes.getIdVale());
                ps.executeUpdate();
                datosTablaVales();
                return true;
            }

        } catch (Exception e) {
            System.out.println("Error al eliminar" + e);
            return false;
        }
        return true;
    }

    void acomodarDatePicker(JFXDatePicker datep) {
        datep.setConverter(new StringConverter<LocalDate>() {
            private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            @Override
            public String toString(LocalDate localDate) {
                if (localDate == null) {
                    return "";
                }
                return dateTimeFormatter.format(localDate);
            }

            @Override
            public LocalDate fromString(String dateString) {
                if (dateString == null || dateString.trim().isEmpty()) {
                    return null;
                }
                return LocalDate.parse(dateString, dateTimeFormatter);
            }
        });
    }

}
