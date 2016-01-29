package shop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("view/main.fxml"));
        loader.setController(new HomeController());
        Parent root = loader.load();
        primaryStage.setTitle("JavaShop");
        Scene scene = new Scene(root, 1000, 800);
        scene.getStylesheets().addAll(this.getClass().getResource("resources/css/main.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
