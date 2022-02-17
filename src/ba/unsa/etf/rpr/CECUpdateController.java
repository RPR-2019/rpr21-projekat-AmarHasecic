package ba.unsa.etf.rpr;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class CECUpdateController {

    private ObservableList members;
    private CECMember member;

    public TextField usernameCEC;
    public TextField firstName;
    public TextField lastName;


    public CECUpdateController(ObservableList members, CECMember member) {
        this.members = members;
        this.member = member;
    }

    public CECMember getMember() {
        return member;
    }

    public void setMember(CECMember member) {
        this.member = member;
    }

    public void initialize() {

        if(member!=null){
            usernameCEC.setText(member.getUsername());
            firstName.setText(member.getFirstName());
            lastName.setText(member.getLastName());
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

        usernameCEC.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                usernameCEC.getStyleClass().removeAll("poljeNijeIspravno");
                usernameCEC.getStyleClass().add("poljeIspravno");
            } else {
                usernameCEC.getStyleClass().removeAll("poljeIspravno");
                usernameCEC.getStyleClass().add("poljeNijeIspravno");
            }
        });

    }

    public void okAction(ActionEvent actionEvent){
        if(firstName.getText().isBlank() || lastName.getText().isBlank() || usernameCEC.getText().isBlank())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Field empty");
            alert.setContentText("You have to fill every field in this form");
            alert.showAndWait();
        }
        else {

            if (member == null) {
                member = new CECMember();

            }
            member.setUsername(usernameCEC.getText());
            member.setFirstName(firstName.getText());
            member.setLastName(lastName.getText());

            Stage stage = (Stage) usernameCEC.getScene().getWindow();
            stage.close();
        }

    }

    public void cancelAction(ActionEvent actionEvent){
        Stage currentStage = (Stage) usernameCEC.getScene().getWindow();
        currentStage.close();

    }


}
