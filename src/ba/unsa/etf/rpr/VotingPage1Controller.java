package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;


public class VotingPage1Controller {

    public ListView listViewParties;
    public DAO model;
    private Voter voter;

    public VotingPage1Controller(Voter voter) {
        model = DAO.getInstance();
        this.voter=voter;

    }

    public void initialize(){
        listViewParties.setItems(model.partiesObs());
    }

    public void nextAction(ActionEvent actionEvent){

    }

    public void backAction(ActionEvent actionEvent){

        Stage stage = new Stage();
        Parent root = null;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/voterMainPage.fxml"));
        VoterController ctrl = new VoterController(voter);
        loader.setController(ctrl);
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Welcome page");
        stage.show();

        Stage currentStage = (Stage) listViewParties.getScene().getWindow();
        currentStage.close();

    }


}
