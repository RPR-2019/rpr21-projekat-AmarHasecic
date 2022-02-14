package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;


public class VotingPage2Controller {

    public ListView listViewCandidates;
    public DAO model;
    private PoliticalParty party;
    private Voter voter;
    private ObservableList<Candidate> selectedCandidates;
    private ObservableList<Candidate> candidates;
    public TextField fldSearch;

    public VotingPage2Controller(PoliticalParty party, Voter voter) {
        model = DAO.getInstance();
        this.party=party;
        this.voter=voter;
        selectedCandidates= FXCollections.observableArrayList();
        candidates = FXCollections.observableArrayList(model.getCandidatesFromParty(party));
    }

    public void initialize() {
        listViewCandidates.setItems(candidates);
    }



    public void finishAction(ActionEvent actionEvent){
        if(selectedCandidates.isEmpty())
        {
            for(int i=0; i<candidates.size();i++)
            {
                candidates.get(i).setNumOfVotes(candidates.get(i).getNumOfVotes()+1);
                model.updateVote(candidates.get(i));
                selectedCandidates.add(candidates.get(i));
            }

        }
        else{
            for(int i=0; i<selectedCandidates.size(); i++)
            {
                selectedCandidates.get(i).setNumOfVotes(selectedCandidates.get(i).getNumOfVotes()+1);
                model.updateVote(selectedCandidates.get(i));
            }
        }

        model.addVote(new VotingForm(0,party.getName(), selectedCandidates));
        voter.setBadge("/jpg/IVotedSticker.jpg");
        model.setBadge(voter);

        Stage currentStage = (Stage) listViewCandidates.getScene().getWindow();
        currentStage.close();

    }

    public void backAction(ActionEvent actionEvent){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Pay attention");
        alert.setContentText("If youur choice will be deleted because you can only vote for candidates from one Political Party.\n Are you sure you want to go back?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {

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

    public void chooseAction(ActionEvent actionEvent){

        Candidate candidate = (Candidate)listViewCandidates.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Woting for "+candidate.toString());
        alert.setContentText("Are you sure you want to give your vote to " +candidate.toString()+"?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            selectedCandidates.add(candidate);

            for(int i=0; i< candidates.size();i++)
            {
                if(candidates.get(i).getId() == candidate.getId())
                {
                    candidates.remove(candidate);
                }
            }
        }

    }

    public void searchAction(ActionEvent actionEvent){

        ObservableList listCandidates = listViewCandidates.getItems();
        for(int i = 0; i<listCandidates.size(); i++)
        {
            if(listCandidates.get(i).toString().equals(fldSearch.getText()))
            {
                listViewCandidates.getSelectionModel().select(listCandidates.get(i));
                break;
            }
        }

    }



}
