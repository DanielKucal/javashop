package shop;

import javafx.scene.control.Alert;

public class PopupController {
    private static Alert.AlertType alertType = Alert.AlertType.ERROR;
    private static String title = "We got a problem!";
    private static String header = "Ooops, something went wrong...";
    private static String content = "Default content";

    public static void alert(String title, String header, String content){
        if (title == null) {
            title = getTitle();
        }
        if (header == null) {
            header = getHeader();
        }
        if (content == null) {
            content = getContent();
        }

        Alert alert = new Alert(getAlertType());
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static Alert.AlertType getAlertType() {
        return alertType;
    }

    public static void setAlertType(Alert.AlertType alertType) {
        PopupController.alertType = alertType;
    }

    public static String getTitle() {
        return title;
    }

    public static void setTitle(String title) {
        PopupController.title = title;
    }

    public static String getHeader() {
        return header;
    }

    public static void setHeader(String header) {
        PopupController.header = header;
    }

    public static String getContent() {
        return content;
    }

    public static void setContent(String content) {
        PopupController.content = content;
    }
}
