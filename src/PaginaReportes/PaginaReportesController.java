package PaginaReportes;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class PaginaReportesController implements Initializable {

    SentenciasVales ObjetoSentenciasVales = new SentenciasVales();
    GetSetReportes ObjetoGetSetReportes = new GetSetReportes();
    static ObservableList<GetSetReportes> listaPaginaVales;

    @FXML
    private JFXButton btnEliminarReporte;
    @FXML
    private JFXButton btnEliminarTodoReporte;
    @FXML
    private JFXTextField txtBuscarReporte;
    @FXML
    private TableView<GetSetReportes> tablaReportes;
    public static TableView<GetSetReportes> tablaReportes1;
    @FXML
    private TableColumn<GetSetReportes, String> colRecibio;
    public static TableColumn<GetSetReportes, String> colRecibio1;
    @FXML
    private TableColumn<GetSetReportes, String> colEntrego;
    public static TableColumn<GetSetReportes, String> colEntrego1;
    @FXML
    private TableColumn<GetSetReportes, String> colFecha;
    public static TableColumn<GetSetReportes, String> colFecha1;
    @FXML
    private TableColumn<GetSetReportes, Double> colCantidad;
    public static TableColumn<GetSetReportes, Double> colCantidad1;
    @FXML
    private TableColumn<GetSetReportes, String> colFolio;
    public static TableColumn<GetSetReportes, String> colFolio1;
    @FXML
    private TableColumn<GetSetReportes, String> colTipoVale;
    public static TableColumn<GetSetReportes, String> colTipoVale1;
    @FXML
    private TableColumn<GetSetReportes, String> colInsumo;
    public static TableColumn<GetSetReportes, String> colInsumo1;
    @FXML
    private TableColumn<GetSetReportes, String> colUnidad;
    public static TableColumn<GetSetReportes, String> colUnidad1;

    @FXML
    private JFXTextArea txtAreaDescripcion;
    @FXML
    private JFXTextArea txtAreaObservacion;
    @FXML
    private JFXButton btnAgregarVale;

    @FXML
    private JFXButton btnValesCrearInforme;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        inventariofx.FXMLDocumentController.variableControlVentana = "/PaginaReportes/PaginaReportes";

        colCantidad1 = colCantidad;
        colEntrego1 = colEntrego;
        colFecha1 = colFecha;
        colInsumo1 = colInsumo;
        colFolio1 = colFolio;
        colRecibio1 = colRecibio;
        colTipoVale1 = colTipoVale;
        colUnidad1 = colUnidad;
        tablaReportes1 = tablaReportes;
        ObjetoSentenciasVales.datosTablaVales();
        Seleccionar();

    }

    @FXML
    private void btnEliminarReporte(ActionEvent event) {
        if (tablaReportes.getSelectionModel().getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Debes seleccionar el reporte a eliminar");
        } else {
            ObjetoGetSetReportes.setTipoVale(tablaReportes.getSelectionModel().getSelectedItem().getTipoVale());
            ObjetoGetSetReportes.setCantidad(tablaReportes.getSelectionModel().getSelectedItem().getCantidad());
            ObjetoGetSetReportes.setCodigoInsumoModificar(tablaReportes.getSelectionModel().getSelectedItem().getCodigoInsumoModificar());
            ObjetoGetSetReportes.setIdVale(tablaReportes.getSelectionModel().getSelectedItem().getIdVale());
            if (ObjetoSentenciasVales.EliminarReporte(ObjetoGetSetReportes)) {
                JOptionPane.showMessageDialog(null, "El reporte se elmino y las cantidades del producto se reestablecieron");
            } else {
                JOptionPane.showMessageDialog(null, "Hubo un error");
            }
        }
    }

    @FXML
    private void btnEliminarTodoReporte(ActionEvent event) {
    }

    @FXML
    private void btnAgregarVale(ActionEvent event) {
        try {
            FXMLLoader Loader = new FXMLLoader(getClass().getResource("/PaginaReportes/ModalAgregarReporte.fxml"));
            Parent root = Loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            System.out.println("No se puede abrir modal: " + e);

        }
    }

    @FXML
    private void btnValesCrearInforme(ActionEvent event) {
        try {
            FXMLLoader Loader = new FXMLLoader(getClass().getResource("/PaginaReportes/ModalCrearInforme.fxml"));
            Parent root = Loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            System.out.println("No se puede abrir modal: " + e);
        }
    }

    @FXML
    private void mostrarContenidoDescripcionObservacion(MouseEvent event) {
        if (tablaReportes.getSelectionModel().getSelectedIndex() == -1) {
        } else {
            txtAreaDescripcion.setText(tablaReportes.getSelectionModel().getSelectedItem().getDescripcion());
            txtAreaObservacion.setText(tablaReportes.getSelectionModel().getSelectedItem().getObservacion());
        }
    }

    public void Seleccionar() {
        FilteredList<GetSetReportes> filteredData = new FilteredList<>(listaPaginaVales, b -> true);
        txtBuscarReporte.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(GetSetReportes -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (GetSetReportes.getFolio().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (GetSetReportes.getFechaString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<GetSetReportes> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tablaReportes.comparatorProperty());
        tablaReportes.setItems(sortedData);
    }

}
