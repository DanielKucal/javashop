package shop;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import shop.interfaces.Materialized;
import shop.interfaces.Promotional;
import shop.interfaces.Sizeable;
import shop.products.*;
import shop.products.parameters.*;

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
    @FXML ComboBox<Currency> productPriceCurrency;
    @FXML ColorPicker productColor;
    @FXML ComboBox<Type> productType;
    @FXML ComboBox<Gender> productGender;
    @FXML VBox additionalForm;
    private static Product editedProduct = null;
    private Product product = null;

    public ProductController() {

    }

    public static Product getEditedProduct() {
        return editedProduct;
    }

    public static void setEditedProduct(Product editedProduct) {
        ProductController.editedProduct = editedProduct;
    }

    public Product getProduct() {
        if (product != null && product.getClass().getSimpleName().equals(productType.getValue().toString())) {
            return product;
        }

        if(getEditedProduct() != null) {
            System.out.println("editing existing product");
            product = getEditedProduct();
            return getEditedProduct();
        }
        System.out.println("creating new product");
        Product product = null;
        try {
            product = (Product) Class.forName("shop.products." + productType.getValue().toString()).newInstance();
            System.out.println(product);
        } catch (Exception e) {
            PopupController.alert(null, "Can not create instance of class " + productType.getValue(), e.getMessage());
            e.printStackTrace();
        } finally {
            //Catalog.add(product);
            this.product = product;
        }
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
        productPriceCurrency.setValue(product.getPrice().getCurrency());
        productGender.setValue(product.getGender());
        if(product.getColor() != null)
            productColor.setValue(Color.web(product.getColor()));
    }

    @FXML
    public void updateForm(){
        System.out.println(productType.getValue());
        additionalForm.getChildren().clear();

        if (this.getProduct() instanceof Materialized) {
            ComboBox<Material> material = new ComboBox<>();
            material.setPromptText("Material");
            material.getItems().setAll(Material.values());
            additionalForm.getChildren().add(material);
        }
        if (this.getProduct() instanceof Sizeable) {
            ComboBox<Size> size = new ComboBox<>();
            size.setPromptText("Size");
            size.getItems().setAll(Size.values());
            additionalForm.getChildren().add(size);
        }
        if (this.getProduct() instanceof Promotional){
            Text text = new Text("Promotion");
            TextField discount = new TextField();
            discount.setPromptText("Discount between 10-70%");
            DatePicker fromDate = new DatePicker();
            fromDate.setPromptText("From");
            DatePicker toDate = new DatePicker();
            toDate.setPromptText("To");
            additionalForm.getChildren().addAll(text, discount, fromDate, toDate);
        }

        switch (productType.getValue()) {
            case Jacket:
                ComboBox<ClaspType> clasp = new ComboBox<>();
                clasp.setPromptText("Clasp type");
                clasp.getItems().setAll(ClaspType.values());
                ComboBox<Season> season = new ComboBox<>();
                season.setPromptText("Season");
                season.getItems().setAll(Season.values());
                additionalForm.getChildren().addAll(clasp, season);
                break;

            case Pants:
                TextField width = new TextField();
                width.setPromptText("Width");
                TextField height = new TextField();
                height.setPromptText("Height");
                additionalForm.getChildren().addAll(width, height);
                break;

            case Shirt:
                TextField collar = new TextField();
                collar.setPromptText("Collar size");
                CheckBox isTieIncluded = new CheckBox("Is tie included?");
                additionalForm.getChildren().addAll(collar, isTieIncluded);
                break;

            case Shoes:
                CheckBox hasHeel = new CheckBox("Has heel?");
                TextField size = new TextField();
                size.setPromptText("Size");
                additionalForm.getChildren().addAll(hasHeel, size);
                break;
        }
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
            product.setPrice(
                    new Price(
                            Double.parseDouble(productPrice.getText()),
                            productPriceTax.isSelected(),
                            productPriceCurrency.getValue()
                    )
            );
            product.setColor(productColor.getValue().toString());
            product.setGender(productGender.getValue());
            Catalog.set(product);
            this.product = null;
            HomeController.getInstance().openProducts();
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
        productGender.getItems().setAll(Gender.values());
        productPriceCurrency.getItems().setAll(Currency.values());

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
