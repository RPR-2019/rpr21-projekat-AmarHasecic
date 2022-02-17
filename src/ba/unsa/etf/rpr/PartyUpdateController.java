package ba.unsa.etf.rpr;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PartyUpdateController {
    private ObservableList parties;
    private PoliticalParty party;

    public TextField name;


    public PartyUpdateController(ObservableList parties, PoliticalParty party) {
        this.parties = parties;
        this.party = party;
    }

    public PoliticalParty getParty() {
        return party;
    }

    public void setParty(PoliticalParty party) {
        this.party = party;
    }

    public void initialize() {

        if(party!=null){
            name.setText(party.getName());

        }
        name.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                name.getStyleClass().removeAll("poljeNijeIspravno");
                name.getStyleClass().add("poljeIspravno");
            } else {
                name.getStyleClass().removeAll("poljeIspravno");
                name.getStyleClass().add("poljeNijeIspravno");
            }
        });

    }

    public void okAction(ActionEvent actionEvent){
        if(name.getText().isBlank())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Field empty");
            alert.setContentText("You have to fill every field in this form");
            alert.showAndWait();
        }
        else {
            if (party == null) {
                party = new PoliticalParty();

            }
            party.setName(name.getText());


            Stage stage = (Stage) name.getScene().getWindow();
            stage.close();
        }

    }

    public void cancelAction(ActionEvent actionEvent){
        Stage currentStage = (Stage) name.getScene().getWindow();
        currentStage.close();

    }


}
