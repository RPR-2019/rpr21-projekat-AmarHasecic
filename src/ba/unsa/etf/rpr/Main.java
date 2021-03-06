package ba.unsa.etf.rpr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
	    launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        MainController ctrl;
        DAO dao = DAO.getInstance();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
        ctrl = new MainController(false);
        loader.setController(ctrl);
        Parent root = loader.load();
        Scene scena = new Scene(root);
        stage.setScene(scena);
        stage.toFront();
        stage.setTitle("Vote!");
        stage.show();
    }
}
