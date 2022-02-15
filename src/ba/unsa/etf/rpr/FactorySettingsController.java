package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

public class FactorySettingsController {

    private DAO model = DAO.getInstance();

public void testSettingsAction(ActionEvent actionEvent){
    model.setDefaultDatabaseTest();
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setHeaderText("Database test mode on.");
    alert.setContentText("Log in again to see changes.");
    alert.showAndWait();
}

public void deleteDataAction(ActionEvent actionEvent){
    model.setDefaultDatabase();
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setHeaderText("Database default mode on.");
    alert.setContentText("Log in again to see changes.");
    alert.showAndWait();
}

}
