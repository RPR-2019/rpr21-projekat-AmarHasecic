package ba.unsa.etf.rpr;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

    }

    public void okAction(ActionEvent actionEvent){
        if (member == null) {
            member = new CECMember();

        }
        member.setUsername(usernameCEC.getText());
        member.setFirstName(firstName.getText());
        member.setLastName(lastName.getText());

        Stage stage = (Stage) usernameCEC.getScene().getWindow();
        stage.close();

    }

    public void cancelAction(ActionEvent actionEvent){
        Stage currentStage = (Stage) usernameCEC.getScene().getWindow();
        currentStage.close();

    }


}
