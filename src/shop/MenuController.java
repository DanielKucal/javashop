package shop;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;

/**
 * Created on 2015-11-19
 *
 * @author Daniel Kucal
 *         email: dkucal@gmail.com
 *         www: danielkucal.com
 */
public class MenuController extends VBox {
    public MenuController() {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("view/menu.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    public void openProducts(){
        Text text = new Text();
        text.setText("produkty");
        AnchorPane.setTopAnchor(text, 10.0);

        // here i would need to call method from another controller
    }

    @FXML
    public void openCart(){

    }
}
