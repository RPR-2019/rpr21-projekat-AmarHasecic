package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;

public class FactorySettingsController {

    private DAO model = DAO.getInstance();

public void testSettingsAction(ActionEvent actionEvent){
    model.setDefaultDatabaseTest();
}

public void deleteDataAction(ActionEvent actionEvent){
    model.setDefaultDatabase();
}

}
