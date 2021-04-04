package inventariofx;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class FXMLDocumentController implements Initializable {

    
    //FERZI DE LA LAGUNA SPR DE RL
    
    
    public static String variableControlVentana = "";

    @FXML
    private JFXButton btnLimpiar;
    @FXML
    private JFXButton btnInventario;
    @FXML
    private JFXButton btnGastos;
    @FXML
    private JFXButton btnSalir;
    @FXML
    private ImageView imgInicio;
    @FXML
    private BorderPane borderPaneInicio;
    @FXML
    private JFXButton btnReportes;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void btnLimpiar(ActionEvent event) {
        if (variableControlVentana.equals("/inventariodx/ImagenInicio")) {
            System.out.println("Ya esta abierta la ventana imagen");
        } else {
            loadUI("/inventariofx/ImagenInicio");
        }
    }

    @FXML
    private void btnInventario(ActionEvent event) {

        if (variableControlVentana.equals("/PaginaInventario/PaginaInventario")) {
            System.out.println("Ya esta abierta la ventana imagen");
        } else {
            loadUI("/PaginaInventario/PaginaInventario");
        }
    }

    @FXML
    private void btnGastos(ActionEvent event) {

//        if (variableControlVentana.equals("/PaginaGastos/PaginaGastos")) {
//            System.out.println("Ya esta abierta la ventana imagen");
//        } else {
//            loadUI("/PaginaGastos/PaginaGastos");
//        }
    }

    @FXML
    private void btnReportes(ActionEvent event) {

        if (variableControlVentana.equals("/PaginaReportes/PaginaReportes")) {
            System.out.println("Ya esta abierta la ventana imagen");
        } else {
            loadUI("/PaginaReportes/PaginaReportes");
        }
    }

    @FXML
    private void btnSalir(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");

        alert.setHeaderText(null);

        alert.setContentText("¿Estas seguro que quieres salir?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Stage stage = (Stage) borderPaneInicio.getScene().getWindow();
            stage.close();
        }
    }

    private void loadUI(String ui) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(ui + ".fxml"));
//   new animatefx.animation.BounceInDown(root).setSpeed(0.65).play();
        } catch (IOException ex) {
            System.out.println("Error al abrir LoadUI:" + ex);
        }
        borderPaneInicio.setCenter(root);

    }

}
