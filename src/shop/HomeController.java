package shop;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created on 2015-11-18
 *
 * @author Daniel Kucal
 *         email: dkucal@gmail.com
 *         www: danielkucal.com
 */
public class HomeController implements Initializable {
    @FXML private AnchorPane content;
    @FXML private HBox buttonContainer;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        Text text = new Text();
        text.setText("test");
        AnchorPane.setTopAnchor(text, 10.0);
        content.getChildren().add(text);

        TextField searchText = new TextField();
        searchText.setId("searchInput");
        searchText.setPromptText("Filter results");
        Button searchButton = new Button("Search");
        buttonContainer.getChildren().add(searchText);
        buttonContainer.getChildren().add(searchButton);
    }

    public HomeController setContent(Node content){
        this.content.getChildren().setAll(content);
        return this;
    }
}
