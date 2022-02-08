package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

public class MainController{

    public TextField fieldUsername;
    public TextField fieldPassword;


    public MainController() {

    }

    public void initialize() {

    }


    public void loginAction(ActionEvent actionEvent) {


        //ulaz admina
        if(fieldUsername.getText().equals("admin") && fieldPassword.getText().equals("admin"))
        {
            Stage stage = new Stage();
            Parent root = null;

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/admin.fxml"));
            try {
            root = loader.load();
            } catch (IOException e) {
            e.printStackTrace();
            }

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.show();
        }
    }
}
