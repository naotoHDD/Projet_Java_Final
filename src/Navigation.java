import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;

import java.io.File;

public class Navigation {
    public static void changerPage(ActionEvent event, String fichierFXML) {
        try {
            Parent root = FXMLLoader.load(new File("resources/" + fichierFXML).toURI().toURL());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 1050, 680));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
