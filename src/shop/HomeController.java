package shop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import shop.products.Catalog;
import shop.products.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created on 2015-11-18
 *
 * @author Daniel Kucal
 *         email: dkucal@gmail.com
 *         www: danielkucal.com
 */
public class HomeController implements Initializable {
    @FXML AnchorPane content;
    @FXML TextField searchInput;
    private ListView<Product> listView = new ListView<>();
    private static HomeController instance;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        this.setContent(new Text("test"));
        instance = this;
    }

    public HomeController setContent(Node content){
        return this.setContent(content, false);
    }

    public HomeController setContent(Node content, boolean full) {
        if (!full) {
            AnchorPane.setTopAnchor(content, 10.0);
            AnchorPane.setLeftAnchor(content, 10.0);
        }
        this.content.getChildren().setAll(content);
        return this;
    }

    @FXML
    public void openProducts(){
        listView.setItems(null);
        ObservableList<Product> list = FXCollections.observableArrayList(Catalog.getProducts().values());
        listView.setItems(list);
        listView.setId("productList");
        listView.setMinHeight(content.getHeight());
        listView.setMinWidth(content.getWidth());
        ContextMenu contextMenu = new ContextMenu();
        MenuItem editItem = new MenuItem("edit");
        MenuItem deleteItem = new MenuItem("delete");
        contextMenu.getItems().addAll(editItem, deleteItem);

        editItem.setOnAction(
                event -> this.editProduct()
        );
        deleteItem.setOnAction(
                event -> this.removeProduct()
        );

        listView.setContextMenu(contextMenu);
        this.setContent(listView, true);
    }

    @FXML
    public void openCart(){
        this.setContent(new Text("cart"));
    }

    @FXML
    public void addProduct(){
        ProductController.setEditedProduct(null);
        openEditForm();
    }
    public void editProduct(){
        ProductController.setEditedProduct(this.getSelectedProduct());
        openEditForm();
    }

    public void openEditForm(){
        AnchorPane editProduct;
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("view/editProduct.fxml"));
        try {
            editProduct = fxmlLoader.load();
        } catch(IOException e) {
            System.out.println("Can not load view/editProduct.fxml:");
            e.printStackTrace();
            editProduct = new AnchorPane();
        }
        this.setContent(editProduct, true);
    }


    @FXML
    public void removeProduct(){
        Catalog.remove(this.getSelectedProduct());
        this.openProducts();
    }

    @FXML
    public void filterResults(){
        String searchText = searchInput.getText().toLowerCase();
        ArrayList<Product> results = new ArrayList<>(Catalog.getProducts().values());
        results.removeIf(product -> !product.toString().toLowerCase().contains(searchText));
        listView.setItems(FXCollections.observableArrayList(results));
    }

    @FXML
    public void saveProducts(){
        Catalog.save();
    }

    private Product getSelectedProduct(){
        return listView.getSelectionModel().getSelectedItem();
    }

    public static HomeController getInstance(){
        return instance;
    }
}
