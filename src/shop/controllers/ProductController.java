package shop.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import shop.Main;
import shop.products.*;
import shop.products.parameters.*;
import shop.view.ViewGenerator;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.LinkedHashMap;
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
    private LinkedHashMap<String, Control> additionalInfo;
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
            Class cls = Class.forName("shop.products." + productType.getValue().toString());
            product = (Product) Class.forName("shop.products." + productType.getValue().toString()).newInstance();
            cls.cast(product);
        } catch (Exception e) {
            PopupController.alert(null, "Can not create instance of class " + productType.getValue(), e.getMessage());
            e.printStackTrace();
        }

        if (product instanceof Jacket) {
            Jacket castedProduct = (Jacket) product;
            this.product = castedProduct;
            return castedProduct;
        } else if (product instanceof Pants) {
            Pants castedProduct = (Pants) product;
            this.product = castedProduct;
            return castedProduct;
        } else if (product instanceof Shirt) {
            Shirt castedProduct = (Shirt) product;
            this.product = castedProduct;
            return castedProduct;
        } else if (product instanceof TShirt) {
            TShirt castedProduct = (TShirt) product;
            this.product = castedProduct;
            return castedProduct;
        } else if (product instanceof Shoes) {
            Shoes castedProduct = (Shoes) product;
            this.product = castedProduct;
            return castedProduct;
        }
        this.product = product;
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
        ViewGenerator generator = new ViewGenerator(product);
    }

    @FXML
    public void updateForm(){
        System.out.println(productType.getValue());
        additionalForm.getChildren().clear();
        ViewGenerator generator = new ViewGenerator(this.getProduct());
        additionalInfo = generator.generateForm(true);
        additionalForm.getChildren().setAll(additionalInfo.values());
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

            Method[] methods = product.getClass().getMethods();
            for (Method m : methods) {
                switch (m.getName()) {
                    case "setMaterial":
                        m.invoke(product, this.getAdditionalValue("productMaterial"));
                        break;
                    case "setSize":
                        m.invoke(product, this.getAdditionalValue("productSize"));
                        break;
                    case "setPromotion":
                        if (this.getAdditionalText("productPromotionDiscount").isEmpty())
                            break;
                        Integer discount = Integer.parseInt(this.getAdditionalText("productPromotionDiscount"));
                        LocalDate dateFrom = (LocalDate) this.getAdditionalValue("productPromotionDateFrom");
                        LocalDate dateTo = (LocalDate) this.getAdditionalValue("productPromotionDateTo");
                        m.invoke(product, new Promotion(discount, dateFrom, dateTo));
                        break;
                    case "setClasp":
                        m.invoke(product, this.getAdditionalValue("productClasp"));
                        break;
                    case "setSeason":
                        m.invoke(product, this.getAdditionalValue("productSeason"));
                        break;
                    case "setWidth":
                        if (this.getAdditionalText("productWidth").isEmpty()) {
                            throw new IOException("Please fill product width.");
                        }
                        Integer width = Integer.parseInt(this.getAdditionalText("productWidth"));
                        m.invoke(product, width);
                        break;
                    case "setHeight":
                        if (this.getAdditionalText("productHeight").isEmpty()) {
                            throw new IOException("Please fill product height.");
                        }
                        Integer height = Integer.parseInt(this.getAdditionalText("productHeight"));
                        m.invoke(product, height);
                        break;
                    case "setCollarSize":
                        Integer collarSize = Integer.parseInt(this.getAdditionalText("productCollar"));
                        m.invoke(product, collarSize);
                        break;
                    case "setTieIncluded":
                        m.invoke(product, this.getAdditionalSelected("productTie"));
                        break;
                    case "setHasHeel":
                        m.invoke(product, this.getAdditionalSelected("productHeel"));
                        break;
                    case "setSizeNum":
                        Integer size = Integer.parseInt(this.getAdditionalText("productSizeNum"));
                        m.invoke(product, size);
                        break;
                }
            }

            Catalog.set(product);
            this.product = null;
            HomeController.getInstance().openProducts();
        } catch(Exception e) {
            PopupController.alert(null, null, e.getMessage());
            e.printStackTrace();
        }
    }

    public Object getAdditionalValue(String hash){
        if (additionalInfo.get(hash) instanceof DatePicker) {
            DatePicker object = (DatePicker) additionalInfo.get(hash);
            return object.getValue();
        } else {
            ComboBox<Object> object = (ComboBox<Object>) additionalInfo.get(hash);
            return object.getValue();
        }
    }
    public String getAdditionalText(String hash){
        TextField text = (TextField) additionalInfo.get(hash);
        return text.getText();
    }
    public Boolean getAdditionalSelected(String hash){
        CheckBox checkBox = (CheckBox) additionalInfo.get(hash);
        return checkBox.isSelected();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        productType.getItems().setAll(Type.values());
        productGender.getItems().setAll(Gender.values());
        productPriceCurrency.getItems().setAll(Currency.values());

        if(getEditedProduct() != null) {
            this.fillFields();
            this.updateForm();
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
