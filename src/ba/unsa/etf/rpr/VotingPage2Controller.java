package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;


public class VotingPage2Controller {

    public ListView listViewCandidates;
    public DAO model;
    private PoliticalParty party;
    private Voter voter;

    public VotingPage2Controller(PoliticalParty party, Voter voter) {
        model = DAO.getInstance();
        this.party=party;
        this.voter=voter;
    }

    public void initialize(){
        listViewCandidates.setItems(model.getCandidatesFromParty(party));
    }

    public void finishAction(ActionEvent actionEvent){

    }

    public void backAction(ActionEvent actionEvent){

        Stage stage = new Stage();
        Parent root = null;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/voting-page1.fxml"));
        VotingPage1Controller ctrl;
        ctrl = new VotingPage1Controller(voter);

        loader.setController(ctrl);
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Choose Political Party");
        stage.show();

        Stage currentStage = (Stage) listViewCandidates.getScene().getWindow();
        currentStage.close();

    }


}
