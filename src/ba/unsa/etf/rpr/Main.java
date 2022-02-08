package ba.unsa.etf.rpr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
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
        ctrl = new MainController();
        loader.setController(ctrl);
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.toFront();
        stage.show();
    }
}
