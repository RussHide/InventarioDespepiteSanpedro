package PaginaInventario;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class PaginaInventarioController implements Initializable {

    SentenciasInventario ObjetoSentenciasInventario = new SentenciasInventario();
    GetSetInventario ObjetoGetSetInventario = new GetSetInventario();

    @FXML
    private JFXButton btnNuevoProducto;
    @FXML
    private JFXButton btnModificarProducto;
    @FXML
    private JFXButton btnEliminarProducto;
    @FXML
    private JFXButton btnEliminarTodoProducto;
    @FXML
    private JFXTextField txtBuscarProdcto;
    @FXML
    private TableView<GetSetInventario> tablaInventario;
    public static TableView<GetSetInventario> tablaInventario1;
    @FXML
    private TableColumn<GetSetInventario, String> colCodigoBarrasProducto;
    public static TableColumn<GetSetInventario, String> colCodigoBarrasProducto1;
    @FXML
    private TableColumn<GetSetInventario, String> colNombreProducto;
    public static TableColumn<GetSetInventario, String> colNombreProducto1;
    @FXML
    private TableColumn<GetSetInventario, String> colDescripcionProducto;
    public static TableColumn<GetSetInventario, String> colDescripcionProducto1;
    @FXML
    private TableColumn<GetSetInventario, Double> colCantidadProducto;
    public static TableColumn<GetSetInventario, Double> colCantidadProducto1;
    @FXML
    private TableColumn<GetSetInventario, String> colUnidadProducto;
    public static TableColumn<GetSetInventario, String> colUnidadProducto1;

    static ObservableList<GetSetInventario> listaPaginaProductos;
    @FXML
    private JFXButton btnExportarInventarioPDF;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inventariofx.FXMLDocumentController.variableControlVentana = "/PaginaInventario/PaginaInventario";
        colCodigoBarrasProducto1 = colCodigoBarrasProducto;
        colNombreProducto1 = colNombreProducto;
        colDescripcionProducto1 = colDescripcionProducto;
        colCantidadProducto1 = colCantidadProducto;
        colUnidadProducto1 = colUnidadProducto;
        tablaInventario1 = tablaInventario;
        ObjetoSentenciasInventario.datosTablaProductos();
    }

    @FXML
    private void btnNuevoProducto(ActionEvent event) {
        try {
            FXMLLoader Loader = new FXMLLoader(getClass().getResource("/PaginaInventario/ModalAgregarProducto.fxml"));
            Parent root = Loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
//             stage.initStyle(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            //new animatefx.animation.FadeInDown(root).setSpeed(1.50).play();
            stage.showAndWait();
        } catch (Exception e) {
            System.out.println("No se puede abrir modal: " + e);
        }
    }

    @FXML
    private void btnModificarProducto(ActionEvent event) {
        if (tablaInventario.getSelectionModel().getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Debes seleccionar el insumo a modificar");
        } else {
            try {
                FXMLLoader Loader = new FXMLLoader(getClass().getResource("/PaginaInventario/ModalModificarProducto.fxml"));
                Parent root = Loader.load();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                //  stage.initStyle(StageStyle.TRANSPARENT);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                //new animatefx.animation.FadeInDown(root).setSpeed(1.50).play();
                ModalModificarProductoController.lblControlId1.setText(String.valueOf(tablaInventario.getSelectionModel().getSelectedItem().getId()));
                ModalModificarProductoController.txtModificarCodigo1.setText(tablaInventario.getSelectionModel().getSelectedItem().getCodigo());
                ModalModificarProductoController.txtModificarNombre1.setText(tablaInventario.getSelectionModel().getSelectedItem().getNombre());
                ModalModificarProductoController.txtModificarDescripcion1.setText(tablaInventario.getSelectionModel().getSelectedItem().getDescripcion());
                ModalModificarProductoController.txtModificarCantidad1.setText(String.valueOf(tablaInventario.getSelectionModel().getSelectedItem().getCantidad()));
                ModalModificarProductoController.txtModificarUnidad1.setValue(tablaInventario.getSelectionModel().getSelectedItem().getUnidad());
                stage.showAndWait();
            } catch (Exception e) {
                System.out.println("No se puede abrir modal: " + e);
            }
        }
    }

    @FXML
    private void btnEliminarProducto(ActionEvent event) {
        ObjetoGetSetInventario.setId(tablaInventario.getSelectionModel().getSelectedItem().getId());
    }

    @FXML
    private void btnEliminarTodoProducto(ActionEvent event) {
    }

    @FXML
    private void btnExportarInventarioPDF(ActionEvent event) {
        try {
            FXMLLoader Loader = new FXMLLoader(getClass().getResource("/PaginaInventario/ModalExportarInventario.fxml"));
            Parent root = Loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            //  stage.initStyle(StageStyle.TRANSPARENT);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (Exception e) {
            System.out.println("No se puede abrir modal: " + e);
        }

    }

}
