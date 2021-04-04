package PaginaInventario;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class ModalAgregarProductoController implements Initializable {

    GetSetInventario ObjetoGetSetInventario = new GetSetInventario();
    SentenciasInventario ObjetoSentenciasInventario = new SentenciasInventario();

    @FXML
    private JFXTextField txtRegistrarCodio;
    @FXML
    private JFXTextField txtRegistrarNombre;
    @FXML
    private JFXTextField txtRegistrarDescripcion;
    @FXML
    private JFXTextField txtRegistrarCantidad;
    @FXML
    private JFXComboBox<String> txtRegistrarUnidad;
    @FXML
    private JFXButton btnAceptar;
    @FXML
    private JFXButton btnCancelar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtRegistrarUnidad.getItems().addAll("Kg", "L","Bulto");
    }

    @FXML
    private void btnAceptar(ActionEvent event) {
        if (txtRegistrarCodio.getText().equals("null") || txtRegistrarNombre.getText().equals("") || txtRegistrarDescripcion.getText().equals("")
                || txtRegistrarCantidad.getText().equals("") || txtRegistrarUnidad.getSelectionModel().getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Debes llenar todos los campos");
        } else {
            ObjetoGetSetInventario.setCodigo(txtRegistrarCodio.getText());
            ObjetoGetSetInventario.setNombre(txtRegistrarNombre.getText());
            ObjetoGetSetInventario.setDescripcion(txtRegistrarDescripcion.getText());
            ObjetoGetSetInventario.setCantidad(Double.valueOf(txtRegistrarCantidad.getText()));
            ObjetoGetSetInventario.setUnidad(txtRegistrarUnidad.getValue());

            if (ObjetoSentenciasInventario.AgregarProducto(ObjetoGetSetInventario)) {
                JOptionPane.showMessageDialog(null, "El producto se agrego exitosamente");
                Stage stage = (Stage) btnAceptar.getScene().getWindow();
                stage.close();

            } else {
                JOptionPane.showMessageDialog(null, "El producto fallo al agregarse");
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

    @FXML
    private void validarTxtCantidad(KeyEvent event) {
        int escribirCantidad = 0;
        for (int i = 0; i <= 10; i++) {
            if (SentenciasInventario.decimal[i] == event.getCharacter().charAt(0)) {
                escribirCantidad = 1;
            }
        }
        if (escribirCantidad == 0) {
            event.consume();
        }
    }
}
