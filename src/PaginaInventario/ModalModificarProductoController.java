package PaginaInventario;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class ModalModificarProductoController implements Initializable {

    GetSetInventario ObjetoGetSetInventario = new GetSetInventario();
    SentenciasInventario ObjetoSentenciasInventario = new SentenciasInventario();

    @FXML
    private JFXTextField txtModificarCodigo;
    public static JFXTextField txtModificarCodigo1;
    @FXML
    private JFXTextField txtModificarNombre;
    public static JFXTextField txtModificarNombre1;
    @FXML
    private JFXTextField txtModificarDescripcion;
    public static JFXTextField txtModificarDescripcion1;
    @FXML
    private JFXTextField txtModificarCantidad;
    public static JFXTextField txtModificarCantidad1;
    @FXML
    private JFXComboBox<String> txtModificarUnidad;
    public static JFXComboBox<String> txtModificarUnidad1;
    @FXML
    private JFXButton btnAceptar;
    @FXML
    private JFXButton btnCancelar;
    @FXML
    private Label lblControlId;
    public static Label lblControlId1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        txtModificarUnidad.getItems().addAll("Kg", "L","Bulto");
        txtModificarCodigo1 = txtModificarCodigo;
        txtModificarNombre1 = txtModificarNombre;
        txtModificarDescripcion1 = txtModificarDescripcion;
        txtModificarCantidad1 = txtModificarCantidad;
        txtModificarUnidad1 = txtModificarUnidad;
        lblControlId1 = lblControlId;
    }

    @FXML
    private void btnAceptar(ActionEvent event) {

        if (txtModificarCantidad.getText().equals("") || txtModificarCodigo.getText().equals("") || txtModificarDescripcion.getText().equals("")
                || txtModificarNombre.getText().equals("") || txtModificarUnidad.getSelectionModel().getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Debes llenar todos los campos");

        } else {
            ObjetoGetSetInventario.setCodigo(txtModificarCodigo.getText());
            ObjetoGetSetInventario.setNombre(txtModificarNombre.getText());
            ObjetoGetSetInventario.setDescripcion(txtModificarDescripcion.getText());
            ObjetoGetSetInventario.setCantidad(Double.valueOf(txtModificarCantidad.getText()));
            ObjetoGetSetInventario.setUnidad(txtModificarUnidad.getValue());

            if (ObjetoSentenciasInventario.ModificarProducto(ObjetoGetSetInventario)) {
                JOptionPane.showMessageDialog(null, "El producto se modifico exitosamente");
                Stage stage = (Stage) btnAceptar.getScene().getWindow();
                stage.close();
            } else {
                JOptionPane.showMessageDialog(null, "El producto fallo al modificarse");
                Stage stage = (Stage) btnAceptar.getScene().getWindow();
                stage.close();
            }
        }
    }

    @FXML
    private void btnCancelar(ActionEvent event) {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

}
