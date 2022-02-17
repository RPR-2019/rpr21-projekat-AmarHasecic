package ba.unsa.etf.rpr;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
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


        firstName.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                firstName.getStyleClass().removeAll("poljeNijeIspravno");
                firstName.getStyleClass().add("poljeIspravno");
            } else {
                firstName.getStyleClass().removeAll("poljeIspravno");
                firstName.getStyleClass().add("poljeNijeIspravno");
            }
        });

        lastName.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                lastName.getStyleClass().removeAll("poljeNijeIspravno");
                lastName.getStyleClass().add("poljeIspravno");
            } else {
                lastName.getStyleClass().removeAll("poljeIspravno");
                lastName.getStyleClass().add("poljeNijeIspravno");
            }
        });

        party.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                party.getStyleClass().removeAll("poljeNijeIspravno");
                party.getStyleClass().add("poljeIspravno");
            } else {
                party.getStyleClass().removeAll("poljeIspravno");
                party.getStyleClass().add("poljeNijeIspravno");
            }
        });

    }
    public void okAction(ActionEvent actionEvent){

        if(firstName.getText().isBlank() || lastName.getText().isBlank() || party.getText().isBlank())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Field empty");
            alert.setContentText("You have to fill every field in this form");
            alert.showAndWait();
        }
        else {

            if (candidate == null) {
                candidate = new Candidate();
            }

            candidate.setFirstName(firstName.getText());
            candidate.setLastName(lastName.getText());
            candidate.setPoliticalPartyId(Integer.parseInt(party.getText()));

            Stage stage = (Stage) firstName.getScene().getWindow();
            stage.close();
        }

    }

    public void cancelAction(ActionEvent actionEvent){
        Stage currentStage = (Stage) firstName.getScene().getWindow();
        currentStage.close();

    }

}
