package PaginaReportes;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import com.mysql.jdbc.Connection;
import java.awt.HeadlessException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class ModalAgregarReporteController implements Initializable {

    GetSetReportes ObjetoGetSetReportes = new GetSetReportes();
    GetSetReportes ObjetoOperaciones = new GetSetReportes();
    SentenciasVales ObjetoSentenciasReportes = new SentenciasVales();

    String codigoTemporal;
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    @FXML
    private JFXButton btnAceptar;
    @FXML
    private JFXButton btnCancelar;
    @FXML
    private JFXRadioButton radioBtnEntrada;
    @FXML
    private JFXRadioButton radioBtnSalida;
    @FXML
    private JFXTextArea txtValeDescripcion;
    @FXML
    private JFXTextArea txtValeObservacion;
    @FXML
    private JFXTextField txtValeEntrego;
    @FXML
    private JFXTextField txtValeRecibio;
    @FXML
    private JFXDatePicker txtValeFecha;
    @FXML
    private JFXTextField txtValeCodigoInsumo;
    @FXML
    private JFXTextField txtValeCantidad;
    @FXML
    private ToggleGroup grupoSeleccionTipo;
    @FXML
    private JFXTextField txtUnidadPorCodigo;
    private JFXButton btnComprobarCodigo;
    @FXML
    private JFXTextField txtNombrePorCodigo;
    @FXML
    private Label lblTipoVale;
    @FXML
    private JFXTextField txtFolio;
    @FXML
    private JFXTextField txtValeCantidad3;
    @FXML
    private JFXTextField txtValeCantidad2;
    @FXML
    private JFXTextField txtValeCantidad4;
    @FXML
    private JFXTextField txtValeCantidad5;
    @FXML
    private JFXTextField txtUnidadPorCodigo2;
    @FXML
    private JFXTextField txtNombrePorCodigo3;
    @FXML
    private JFXTextField txtNombrePorCodigo4;
    @FXML
    private JFXTextField txtNombrePorCodigo5;
    @FXML
    private JFXTextField txtUnidadPorCodigo5;
    @FXML
    private JFXTextField txtNombrePorCodigo2;
    @FXML
    private JFXTextField txtUnidadPorCodigo4;
    @FXML
    private JFXTextField txtUnidadPorCodigo3;
    @FXML
    private JFXTextField txtValeCodigoInsumo2;
    @FXML
    private JFXTextField txtValeCodigoInsumo3;
    @FXML
    private JFXTextField txtValeCodigoInsumo4;
    @FXML
    private JFXTextField txtValeCodigoInsumo5;
    @FXML
    private JFXButton btnComprobarInsumos;
    @FXML
    private JFXRadioButton radiobtnEnable2;
    @FXML
    private JFXRadioButton radiobtnEnable3;
    @FXML
    private JFXRadioButton radiobtnEnable4;
    @FXML
    private JFXRadioButton radiobtnEnable5;

    int controlCampo = 1;
    int controlAgregado = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObjetoSentenciasReportes.acomodarDatePicker(txtValeFecha);

    }

    void llenarGettersSetters(JFXTextField codigoInsumo, JFXTextField cantidadInsumo, JFXTextField unidadInsumo) {
        JFXRadioButton radioBtnSeleccionado = (JFXRadioButton) grupoSeleccionTipo.getSelectedToggle();
        ObjetoGetSetReportes.setTipoVale(radioBtnSeleccionado.getText());

        ObjetoGetSetReportes.setDescripcion(txtValeDescripcion.getText());
        ObjetoGetSetReportes.setObservacion(txtValeObservacion.getText());
        ObjetoGetSetReportes.setEntrego(txtValeEntrego.getText());
        ObjetoGetSetReportes.setRecibio(txtValeRecibio.getText());
        ObjetoGetSetReportes.setFechaDate(Date.valueOf(txtValeFecha.getValue()));
        ObjetoGetSetReportes.setFechaString(String.valueOf(txtValeFecha.getValue()));
        ObjetoGetSetReportes.setFolio(txtFolio.getText());

        ObjetoGetSetReportes.setCodigoInsumoModificar(codigoInsumo.getText());
        ObjetoGetSetReportes.setCantidad(Double.valueOf(cantidadInsumo.getText()));
        ObjetoGetSetReportes.setUnidad(unidadInsumo.getText());

        ObjetoOperaciones.setCantidad(Double.valueOf(cantidadInsumo.getText()));
        ObjetoOperaciones.setCodigoInsumoModificar(codigoInsumo.getText());
    }

    @FXML
    private void btnAceptar(ActionEvent event) {
        if (txtFolio.getText().equals("") || txtValeDescripcion.getText().equals("") || txtValeObservacion.equals("") || txtValeEntrego.getText().equals("")
                || txtValeRecibio.getText().equals("") || txtValeFecha == null || txtValeCantidad.getText().equals("")
                || txtValeCodigoInsumo.getText().equals("") || txtNombrePorCodigo.getText().equals("") || txtUnidadPorCodigo.getText().equals("")
                || txtValeCodigoInsumo2.isDisabled() == false && txtValeCodigoInsumo2.getText().equals("")
                || txtValeCodigoInsumo3.isDisabled() == false && txtValeCodigoInsumo3.getText().equals("")
                || txtValeCodigoInsumo4.isDisabled() == false && txtValeCodigoInsumo4.getText().equals("")
                || txtValeCodigoInsumo5.isDisabled() == false && txtValeCodigoInsumo5.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Debes llenar todos los campos");
        } else {

            if (radioBtnEntrada.isSelected()) {

                llenarGettersSetters(txtValeCodigoInsumo, txtValeCantidad, txtUnidadPorCodigo);
                if (ObjetoSentenciasReportes.AgregarVale(ObjetoGetSetReportes) && ObjetoSentenciasReportes.SumarCantidades(ObjetoOperaciones)) {
                    controlAgregado++;
                }

                if (txtValeCodigoInsumo2.isDisabled() == false && !txtValeCodigoInsumo2.getText().isEmpty()) {
                    llenarGettersSetters(txtValeCodigoInsumo2, txtValeCantidad2, txtUnidadPorCodigo2);

                    if (ObjetoSentenciasReportes.AgregarVale(ObjetoGetSetReportes) && ObjetoSentenciasReportes.SumarCantidades(ObjetoOperaciones)) {
                        controlAgregado++;
                    }
                }
                if (txtValeCodigoInsumo3.isDisabled() == false && !txtValeCodigoInsumo3.getText().isEmpty()) {
                    llenarGettersSetters(txtValeCodigoInsumo3, txtValeCantidad3, txtUnidadPorCodigo3);

                    if (ObjetoSentenciasReportes.AgregarVale(ObjetoGetSetReportes) && ObjetoSentenciasReportes.SumarCantidades(ObjetoOperaciones)) {
                        controlAgregado++;
                    }
                }
                if (txtValeCodigoInsumo4.isDisabled() == false && !txtValeCodigoInsumo4.getText().isEmpty()) {
                    llenarGettersSetters(txtValeCodigoInsumo4, txtValeCantidad4, txtUnidadPorCodigo4);

                    if (ObjetoSentenciasReportes.AgregarVale(ObjetoGetSetReportes) && ObjetoSentenciasReportes.SumarCantidades(ObjetoOperaciones)) {
                        controlAgregado++;
                    }
                }
                if (txtValeCodigoInsumo5.isDisabled() == false && !txtValeCodigoInsumo5.getText().isEmpty()) {
                    llenarGettersSetters(txtValeCodigoInsumo5, txtValeCantidad5, txtUnidadPorCodigo5);

                    if (ObjetoSentenciasReportes.AgregarVale(ObjetoGetSetReportes) && ObjetoSentenciasReportes.SumarCantidades(ObjetoOperaciones)) {
                        controlAgregado++;
                    }
                }
////////////////////////////////////////////////
                if (controlAgregado > 0) {

                    JOptionPane.showMessageDialog(null, "El vale se agrego correctamente y se actualizo el inventario");
                    Stage stage = (Stage) btnAceptar.getScene().getWindow();
                    stage.close();

                } else {
                    JOptionPane.showMessageDialog(null, "Hubo un error");
                    Stage stage = (Stage) btnAceptar.getScene().getWindow();
                    stage.close();
                }

            } else if (radioBtnSalida.isSelected()) {

                llenarGettersSetters(txtValeCodigoInsumo, txtValeCantidad, txtUnidadPorCodigo);
                if (ObjetoSentenciasReportes.controlRestas(ObjetoOperaciones) && ObjetoSentenciasReportes.AgregarVale(ObjetoGetSetReportes) && ObjetoSentenciasReportes.restarCantidades(ObjetoOperaciones)) {
                    controlAgregado++;
                }

                if (txtValeCodigoInsumo2.isDisabled() == false && !txtValeCodigoInsumo2.getText().isEmpty()) {
                    llenarGettersSetters(txtValeCodigoInsumo2, txtValeCantidad2, txtUnidadPorCodigo2);

                    if (ObjetoSentenciasReportes.controlRestas(ObjetoOperaciones) && ObjetoSentenciasReportes.AgregarVale(ObjetoGetSetReportes) && ObjetoSentenciasReportes.restarCantidades(ObjetoOperaciones)) {
                        controlAgregado++;
                    }
                }
                if (txtValeCodigoInsumo3.isDisabled() == false && !txtValeCodigoInsumo3.getText().isEmpty()) {
                    llenarGettersSetters(txtValeCodigoInsumo3, txtValeCantidad3, txtUnidadPorCodigo3);

                    if (ObjetoSentenciasReportes.controlRestas(ObjetoOperaciones) && ObjetoSentenciasReportes.AgregarVale(ObjetoGetSetReportes) && ObjetoSentenciasReportes.restarCantidades(ObjetoOperaciones)) {
                        controlAgregado++;
                    }
                }
                if (txtValeCodigoInsumo4.isDisabled() == false && !txtValeCodigoInsumo4.getText().isEmpty()) {
                    llenarGettersSetters(txtValeCodigoInsumo4, txtValeCantidad4, txtUnidadPorCodigo4);

                    if (ObjetoSentenciasReportes.controlRestas(ObjetoOperaciones) && ObjetoSentenciasReportes.AgregarVale(ObjetoGetSetReportes) && ObjetoSentenciasReportes.restarCantidades(ObjetoOperaciones)) {
                        controlAgregado++;
                    }
                }
                if (txtValeCodigoInsumo5.isDisabled() == false && !txtValeCodigoInsumo5.getText().isEmpty()) {
                    llenarGettersSetters(txtValeCodigoInsumo5, txtValeCantidad5, txtUnidadPorCodigo5);

                    if (ObjetoSentenciasReportes.controlRestas(ObjetoOperaciones) && ObjetoSentenciasReportes.AgregarVale(ObjetoGetSetReportes) && ObjetoSentenciasReportes.restarCantidades(ObjetoOperaciones)) {
                        controlAgregado++;
                    }
                }
                if (controlAgregado > 0) {
                    JOptionPane.showMessageDialog(null, "El vale se agrego correctamente y el insumo de actualizo");
                    Stage stage = (Stage) btnAceptar.getScene().getWindow();
                    stage.close();
                } else {
                    Stage stage = (Stage) btnAceptar.getScene().getWindow();
                    stage.close();
                }
            }
        }
    }

    @FXML
    private void btnCancelar(ActionEvent event) {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void radioBtnEntrada(ActionEvent event) {
        lblTipoVale.setText("(Entrada)");
        deshabilitarHastaClickRadio();
    }

    void deshabilitarHastaClickRadio() {
        txtValeDescripcion.setDisable(false);
        txtValeObservacion.setDisable(false);
        txtValeCantidad.setDisable(false);
        txtValeCodigoInsumo.setDisable(false);
        txtValeRecibio.setDisable(false);
        txtValeEntrego.setDisable(false);
        txtValeFecha.setDisable(false);
        txtUnidadPorCodigo.setDisable(false);
        txtNombrePorCodigo.setDisable(false);
        btnAceptar.setDisable(false);
        txtFolio.setDisable(false);
        radiobtnEnable2.setDisable(false);
        radiobtnEnable3.setDisable(false);
        radiobtnEnable4.setDisable(false);
        radiobtnEnable5.setDisable(false);
        btnComprobarInsumos.setDisable(false);
    }

    @FXML
    private void radioBtnSalida(ActionEvent event) {
        lblTipoVale.setText("(Salida)");
        deshabilitarHastaClickRadio();
    }

    void comprobarInsumo(JFXTextField campo, JFXTextField nombre, JFXTextField unidad) {
        conn = Conexion.ConexionBD.SubConexion();
        try {
            ps = conn.prepareStatement("select nombre, unidad from productos where codigo = '" + campo.getText() + "'");
            rs = ps.executeQuery();
            if (rs.next()) {
                nombre.setText(rs.getString("nombre"));
//                txtNombrePorCodigo.setText(rs.getString("nombre"));
                unidad.setText(rs.getString("unidad"));
//                txtUnidadPorCodigo.setText(rs.getString("unidad"));
            } else {
                JOptionPane.showMessageDialog(null, "Hubo un error al buscar el insumo");
            }
        } catch (HeadlessException | SQLException e) {
            System.out.println(e);
        }
    }

    @FXML
    private void btnComprobarInsumos(ActionEvent event) {
        int control;
        if (txtValeCodigoInsumo.getText().equals("")
                || txtValeCodigoInsumo2.isDisabled() == false && txtValeCodigoInsumo2.getText().equals("")
                || txtValeCodigoInsumo3.isDisabled() == false && txtValeCodigoInsumo3.getText().equals("")
                || txtValeCodigoInsumo4.isDisabled() == false && txtValeCodigoInsumo4.getText().equals("")
                || txtValeCodigoInsumo5.isDisabled() == false && txtValeCodigoInsumo5.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Debes escribir el codigo del insumo para poder comprobar o desactivar los campos que no se estan ocupando");
        } else {
            comprobarInsumo(txtValeCodigoInsumo, txtNombrePorCodigo, txtUnidadPorCodigo);
            if (txtValeCodigoInsumo2.isDisabled() == false) {
                comprobarInsumo(txtValeCodigoInsumo2, txtNombrePorCodigo2, txtUnidadPorCodigo2);
            }
            if (txtValeCodigoInsumo3.isDisabled() == false) {
                comprobarInsumo(txtValeCodigoInsumo3, txtNombrePorCodigo3, txtUnidadPorCodigo3);
            }
            if (txtValeCodigoInsumo4.isDisabled() == false) {
                comprobarInsumo(txtValeCodigoInsumo4, txtNombrePorCodigo4, txtUnidadPorCodigo4);
            }
            if (txtValeCodigoInsumo5.isDisabled() == false) {
                comprobarInsumo(txtValeCodigoInsumo5, txtNombrePorCodigo5, txtUnidadPorCodigo5);
            }
        }
    }

    @FXML
    private void radiobtnEnable2(ActionEvent event) {
        if (radiobtnEnable2.isSelected()) {
            txtValeCodigoInsumo2.setDisable(false);
            txtNombrePorCodigo2.setDisable(false);
            txtUnidadPorCodigo2.setDisable(false);
            txtValeCantidad2.setDisable(false);
        } else {
            txtValeCodigoInsumo2.setDisable(true);
            txtValeCodigoInsumo2.setText("");
            txtNombrePorCodigo2.setDisable(true);
            txtNombrePorCodigo2.setText("");
            txtUnidadPorCodigo2.setDisable(true);
            txtUnidadPorCodigo2.setText("");
            txtValeCantidad2.setDisable(true);
            txtValeCantidad2.setText(codigoTemporal);
        }
    }

    @FXML
    private void radiobtnEnable3(ActionEvent event) {
        if (radiobtnEnable3.isSelected()) {
            txtValeCodigoInsumo3.setDisable(false);
            txtNombrePorCodigo3.setDisable(false);
            txtUnidadPorCodigo3.setDisable(false);
            txtValeCantidad3.setDisable(false);
        } else {
            txtValeCodigoInsumo3.setDisable(true);
            txtValeCodigoInsumo3.setText("");
            txtNombrePorCodigo3.setDisable(true);
            txtNombrePorCodigo3.setText("");
            txtUnidadPorCodigo3.setDisable(true);
            txtUnidadPorCodigo3.setText("");
            txtValeCantidad3.setDisable(true);
            txtValeCantidad3.setText("");
        }
    }

    @FXML
    private void radiobtnEnable4(ActionEvent event) {
        if (radiobtnEnable4.isSelected()) {
            txtValeCodigoInsumo4.setDisable(false);
            txtNombrePorCodigo4.setDisable(false);
            txtUnidadPorCodigo4.setDisable(false);
            txtValeCantidad4.setDisable(false);
        } else {
            txtValeCodigoInsumo4.setDisable(true);
            txtValeCodigoInsumo4.setText("");
            txtNombrePorCodigo4.setDisable(true);
            txtNombrePorCodigo4.setText("");
            txtUnidadPorCodigo4.setDisable(true);
            txtUnidadPorCodigo4.setText("");
            txtValeCantidad4.setDisable(true);
            txtValeCantidad4.setText("");
        }
    }

    @FXML
    private void radiobtnEnable5(ActionEvent event) {
        if (radiobtnEnable5.isSelected()) {
            txtValeCodigoInsumo5.setDisable(false);
            txtNombrePorCodigo5.setDisable(false);
            txtUnidadPorCodigo5.setDisable(false);
            txtValeCantidad5.setDisable(false);
        } else {
            txtValeCodigoInsumo5.setDisable(true);
            txtValeCodigoInsumo5.setText("");
            txtNombrePorCodigo5.setDisable(true);
            txtNombrePorCodigo5.setText("");
            txtUnidadPorCodigo5.setDisable(true);
            txtUnidadPorCodigo5.setText("");
            txtValeCantidad5.setDisable(true);
            txtValeCantidad5.setText("");
        }
    }

    @FXML
    private void txtLimiteDescripcion(KeyEvent event) {
        if (txtValeDescripcion.getText().length() >= 130) {
            event.consume();
            JOptionPane.showMessageDialog(null, "Alcanzaste el limite de 130 caracteres");
        }
    }

    @FXML
    private void txtLimiteObservacion(KeyEvent event) {
        if (txtValeObservacion.getText().length() >= 130) {
            event.consume();
            JOptionPane.showMessageDialog(null, "Alcanzaste el limite de 130 caracteres");
        }
    }

    @FXML
    private void validarTxtFecha(KeyEvent event) {
        int escribirFecha = 0;
        for (int i = 0; i <= 11; i++) {
            if (SentenciasVales.fecha[i] == event.getCharacter().charAt(0)) {
                escribirFecha = 1;
            }
        }
        if (escribirFecha == 0) {
            event.consume();
        }
    }

    @FXML
    private void validarTxtCantiad(KeyEvent event) {
        int escribirCantidad = 0;
        for (int i = 0; i <= 10; i++) {
            if (SentenciasVales.decimal[i] == event.getCharacter().charAt(0)) {
                escribirCantidad = 1;
            }
        }
        if (escribirCantidad == 0) {
            event.consume();
        }
    }

}
