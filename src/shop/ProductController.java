package shop;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import shop.products.Catalog;
import shop.products.Product;
import shop.products.parameters.Type;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created on 2015-11-19
 *
 * @author Daniel Kucal
 *         email: dkucal@gmail.com
 *         www: danielkucal.com
 */
public class ProductController implements Initializable {
    @FXML TextField productName;
    @FXML TextField productBrand;
    @FXML TextField productPrice;
    @FXML ToggleButton productPriceTax;
    @FXML ColorPicker productColor;
    @FXML ComboBox<Type> productType;
    private ArrayList<Product> catalog = Catalog.getProducts();

    public ProductController() {

    }

    @FXML
    public void pickImage(){
        System.out.println("pick image");
    }

    @FXML
    public void save(){
        System.out.println("saving product data:");
        System.out.println("name: " + productName.getText());
        System.out.println("brand: " + productBrand.getText());
        System.out.println("price: " + productPrice.getText());
        System.out.println("tax: " + productPriceTax.isSelected());
        System.out.println("color: " + productColor.getValue());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        productType.getItems().setAll(Type.values());
    }
}
