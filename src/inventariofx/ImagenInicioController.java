
package inventariofx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;


public class ImagenInicioController implements Initializable {

    @FXML
    private ImageView ImagenCentroLimpiar;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           FXMLDocumentController.variableControlVentana = "/inventariodx/ImagenInicio";
        new animatefx.animation.BounceInDown(ImagenCentroLimpiar).setSpeed(0.65).play();
    }    
    
}
