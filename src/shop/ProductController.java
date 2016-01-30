package shop;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import shop.products.Catalog;
import shop.products.Product;
import shop.products.parameters.Price;
import shop.products.parameters.Type;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
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
    private static Product editedProduct = null;

    public ProductController() {

    }

    public static Product getEditedProduct() {
        return editedProduct;
    }

    public static void setEditedProduct(Product editedProduct) {
        ProductController.editedProduct = editedProduct;
    }

    public Product getProduct() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        if(getEditedProduct() != null) {
            System.out.println("editing existing product");
            return getEditedProduct();
        }
        System.out.println("creating new product");
        Product product = (Product) Class.forName("shop.products."+productType.getValue().toString()).newInstance();
        Catalog.add(product);
        return product;
    }

    public void fillFields(){
        Product product = getEditedProduct();
        if (product == null) return;
        productName.setText(product.getName());
        productType.setValue(Type.valueOf(product.getClass().getSimpleName()));
        productBrand.setText(product.getBrand());
        productPrice.setText(product.getPrice().getFinalPrice().toString());
        productPriceTax.setSelected(false);
        if(product.getColor() != null)
            productColor.setValue(Color.web(product.getColor()));
    }

    @FXML
    public void pickImage(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );
        File file = fileChooser.showOpenDialog(Main.getPrimaryStage());
        if(file != null){
            File dest = new File("src/shop/resources/img/product" + Catalog.getLastProductId() + "." + getFileExtension(file));
            try {
                Files.copy(file.toPath(), dest.toPath());
            } catch(IOException e) {
                System.out.println("Can not copy " + file + " to " + dest);
                e.printStackTrace();
            }
        }

    }

    @FXML
    public void save(){
        System.out.println("saving product data:");
        System.out.println("name: " + productName.getText());
        System.out.println("type: " + productType.getValue());
        System.out.println("brand: " + productBrand.getText());
        System.out.println("price: " + productPrice.getText());
        System.out.println("tax: " + productPriceTax.isSelected());
        System.out.println("color: " + productColor.getValue());
        try {
            Product product = this.getProduct();
            product.setName(productName.getText());
            product.setBrand(productBrand.getText());
            product.setPrice(new Price(Double.parseDouble(productPrice.getText()), productPriceTax.isSelected()));
            product.setColor(productColor.getValue().toString());
            Catalog.set(product);
        } catch(Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("We got a problem!");
            alert.setHeaderText("Ooops, something went wrong...");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        productType.getItems().setAll(Type.values());
        if(getEditedProduct() != null) {
            this.fillFields();
        }
    }

    private String getFileExtension(File file) {
        String name = file.getName();
        try {
            return name.substring(name.lastIndexOf(".") + 1);
        } catch (Exception e) {
            return "";
        }
    }


}
