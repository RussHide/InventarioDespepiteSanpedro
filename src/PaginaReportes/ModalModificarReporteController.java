package PaginaReportes;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class ModalModificarReporteController implements Initializable {


    GetSetReportes ObjetoGetSetReportes = new GetSetReportes();
    GetSetReportes ObjetoOperaciones = new GetSetReportes();
    SentenciasVales ObjetoSentenciasReportes = new SentenciasVales();

    @FXML
    private JFXTextArea txtModificarDescripcion;
    public static JFXTextArea txtModificarDescripcion1;
    @FXML
    private JFXTextArea txtModificarObservacion;
    public static JFXTextArea txtModificarObservacion1;
    @FXML
    private JFXTextField txtModificarEntrego;
    public static JFXTextField txtModificarEntrego1;
    @FXML
    private JFXTextField txtModificarRecibio;
    public static JFXTextField txtModificarRecibio1;
    @FXML
    private JFXDatePicker txtModificarFecha;
    public static JFXDatePicker txtModificarFecha1;
    @FXML
    private JFXTextField txtModificarCodigoInsumo;
    public static JFXTextField txtModificarCodigoInsumo1;
    @FXML
    private JFXTextField txtModificarCantidad;
    public static JFXTextField txtModificarCantidad1;
    @FXML
    private JFXButton btnModificarAceptar;
    @FXML
    private JFXButton btnModificarCancelar;
    @FXML
    private JFXTextField txtModificarNombreInsumo;
    public static JFXTextField txtModificarNombreInsumo1;
    @FXML
    private JFXTextField txtModificarUnidad;
    public static JFXTextField txtModificarUnidad1;
    @FXML
    private JFXButton btnModificarComprobar;
    @FXML
    private JFXRadioButton radioBtnModificarEntrada;
    public static JFXRadioButton radioBtnModificarEntrada1;
    @FXML
    private ToggleGroup grupoModificarSeleccion;
    @FXML
    private JFXRadioButton radiobtnModificarSalida;
    public static JFXRadioButton radiobtnModificarSalida1;
    @FXML
    private Label lblControlModificar;
    public static Label lblControlModificar1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObjetoSentenciasReportes.acomodarDatePicker(txtModificarFecha);
        txtModificarCantidad1 = txtModificarCantidad;
        txtModificarFecha1 = txtModificarFecha;
        txtModificarEntrego1 = txtModificarEntrego;
        txtModificarObservacion1 = txtModificarObservacion;
        txtModificarDescripcion1 = txtModificarDescripcion;
        txtModificarCodigoInsumo1 = txtModificarCodigoInsumo;
        txtModificarNombreInsumo1 = txtModificarNombreInsumo;
        txtModificarRecibio1 = txtModificarRecibio;
        txtModificarUnidad1 = txtModificarUnidad;
        txtModificarFecha1 = txtModificarFecha;
        radiobtnModificarSalida1 = radiobtnModificarSalida;
        radioBtnModificarEntrada1 = radioBtnModificarEntrada;
        lblControlModificar1 = lblControlModificar;

    }

    @FXML
    private void btnModificarAceptar(ActionEvent event) {
//        JFXRadioButton radioBtnSeleccionado = (JFXRadioButton) grupoModificarSeleccion.getSelectedToggle();
////        int dialogResult = JOptionPane.showConfirmDialog (null, "Would You Like to Save your Previous Note First?");
////        if(dialogResult == JOptionPane.YES_OPTION){
//        ObjetoGetSetReportes.setTipoVale(radioBtnSeleccionado.getText());
//        ObjetoGetSetReportes.setDescripcion(txtModificarDescripcion.getText());
//        ObjetoGetSetReportes.setObservacion(txtModificarObservacion.getText());
//        ObjetoGetSetReportes.setEntrego(txtModificarEntrego.getText());
//        ObjetoGetSetReportes.setRecibio(txtModificarRecibio.getText());
//        ObjetoGetSetReportes.setFechaDate(Date.valueOf(txtModificarFecha.getValue()));
//        ObjetoGetSetReportes.setFechaString(String.valueOf(txtModificarFecha.getValue()));
//        ObjetoGetSetReportes.setCodigoInsumoModificar(txtModificarCodigoInsumo.getText());
//        ObjetoGetSetReportes.setCantidad(Double.valueOf(txtModificarCantidad.getText()));
//        ObjetoGetSetReportes.setUnidad(txtModificarUnidad.getText());
//
//        ObjetoOperaciones.setCantidad(Double.valueOf(txtModificarCantidad.getText()));
//        ObjetoOperaciones.setCodigoInsumoModificar(txtModificarCodigoInsumo.getText());
//
//        if (radioBtnModificarEntrada.isSelected()) {
//            if (ObjetoSentenciasReportes.modificarReporte(ObjetoGetSetReportes) && ObjetoSentenciasReportes.SumarCantidades(ObjetoOperaciones)) {
//                JOptionPane.showMessageDialog(null, "El vale se modifico correctamente y el insumo de actualizo");
//                Stage stage = (Stage) btnModificarAceptar.getScene().getWindow();
//                stage.close();
//            } else {
//                JOptionPane.showMessageDialog(null, "Hubo un error");
//                Stage stage = (Stage) btnModificarAceptar.getScene().getWindow();
//                stage.close();
//            }
//
//        } else if (radiobtnModificarSalida.isSelected()) {
//
//            if (ObjetoSentenciasReportes.controlRestas(ObjetoGetSetReportes) && ObjetoSentenciasReportes.modificarReporte(ObjetoGetSetReportes) && ObjetoSentenciasReportes.restarCantidades(ObjetoOperaciones)) {
//                JOptionPane.showMessageDialog(null, "El vale se modifico correctamente y el insumo de actualizo");
//                Stage stage = (Stage) btnModificarAceptar.getScene().getWindow();
//                stage.close();
//            } else {
//                JOptionPane.showMessageDialog(null, "Hubo un error");
//                Stage stage = (Stage) btnModificarAceptar.getScene().getWindow();
//                stage.close();
//            }
//        }

    }

    @FXML
    private void btnModificarCancelar(ActionEvent event) {
        Stage stage = (Stage) btnModificarCancelar.getScene().getWindow();
        stage.close();

    }

    @FXML
    private void radioBtnModificarEntrada(ActionEvent event) {
//        lblControlModificar.setText("(Entrada)");
//        txtModificarDescripcion.setDisable(false);
//        txtModificarObservacion.setDisable(false);
//        txtModificarCantidad.setDisable(false);
//        txtModificarCodigoInsumo.setDisable(false);
//        txtModificarRecibio.setDisable(false);
//        txtModificarEntrego.setDisable(false);
//        txtModificarFecha.setDisable(false);
//        txtModificarUnidad.setDisable(false);
//        txtModificarNombreInsumo.setDisable(false);
//        btnModificarAceptar.setDisable(false);
//        btnModificarComprobar.setDisable(false);
    }

    @FXML
    private void radiobtnModificarSalida(ActionEvent event) {
//        lblControlModificar.setText("(Salida)");
//        txtModificarDescripcion.setDisable(false);
//        txtModificarObservacion.setDisable(false);
//        txtModificarCantidad.setDisable(false);
//        txtModificarCodigoInsumo.setDisable(false);
//        txtModificarRecibio.setDisable(false);
//        txtModificarEntrego.setDisable(false);
//        txtModificarFecha.setDisable(false);
//        txtModificarUnidad.setDisable(false);
//        txtModificarNombreInsumo.setDisable(false);
//        btnModificarAceptar.setDisable(false);
//        btnModificarComprobar.setDisable(false);
    }

}
