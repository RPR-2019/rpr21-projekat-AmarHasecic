package ba.unsa.etf.rpr;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class VotingPage1Controller {

    public ListView listViewParties;
    public DAO model;
    private Voter voter;
    public TextField fldSearch;

    public VotingPage1Controller(Voter voter) {
        model = DAO.getInstance();
        this.voter=voter;

    }

    public void initialize(){
        listViewParties.setItems(model.partiesObs());
    }

    public void nextAction(ActionEvent actionEvent){

        if(listViewParties.getSelectionModel().getSelectedItem()==null) return;
        Stage stage = new Stage();
        Parent root = null;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/voting-page2.fxml"));
        VotingPage2Controller ctrl = new VotingPage2Controller((PoliticalParty)listViewParties.getSelectionModel().getSelectedItem(),voter);
        loader.setController(ctrl);
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);

        scene.getStylesheets().add(this.getClass().getResource("/css/list.css").toExternalForm());
        stage.setScene(scene);

        stage.setTitle("Choose Candidates");
        stage.show();

        Stage currentStage = (Stage) listViewParties.getScene().getWindow();
        currentStage.close();
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

    public void searchAction(ActionEvent actionEvent){

        ObservableList listParties = listViewParties.getItems();
        for(int i = 0; i<listParties.size(); i++)
        {
            if(listParties.get(i).toString().equals(fldSearch.getText()))
            {
                listViewParties.getSelectionModel().select(listParties.get(i));
                break;
            }
        }

    }


}
