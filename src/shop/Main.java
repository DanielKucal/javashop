package shop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import shop.controllers.HomeController;

public class Main extends Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("view/main.fxml"));
        loader.setController(new HomeController());
        Parent root = loader.load();
        primaryStage.setTitle("JavaShop");
        Scene scene = new Scene(root, 1000, 630);
        scene.getStylesheets().addAll(this.getClass().getResource("resources/css/main.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
        Main.primaryStage = primaryStage;
    }

    public static Stage getPrimaryStage(){
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
