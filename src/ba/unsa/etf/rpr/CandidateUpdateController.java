package ba.unsa.etf.rpr;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CandidateUpdateController {

    public TextField firstName;
    public TextField lastName;
    public TextField party;


    private ObservableList candidates;
    private Candidate candidate;

    public CandidateUpdateController(ObservableList candidates, Candidate candidate) {
        this.candidates = candidates;
        this.candidate = candidate;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public void initialize(){

        if(candidate!=null)
        {
          firstName.setText(candidate.getFirstName());
          lastName.setText(candidate.getLastName());
          party.setText(String.valueOf(candidate.getPoliticalPartyId()));

        }


    }
    public void okAction(ActionEvent actionEvent){
        if (candidate == null) {
            candidate = new Candidate();
        }

        candidate.setFirstName(firstName.getText());
        candidate.setLastName(lastName.getText());
        candidate.setPoliticalPartyId(Integer.parseInt(party.getText()));

        Stage stage = (Stage) firstName.getScene().getWindow();
        stage.close();

    }

    public void cancelAction(ActionEvent actionEvent){
        Stage currentStage = (Stage) firstName.getScene().getWindow();
        currentStage.close();

    }

}
