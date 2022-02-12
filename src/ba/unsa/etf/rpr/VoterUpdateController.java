package ba.unsa.etf.rpr;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class VoterUpdateController {

    public TextField pass;
    public TextField firstName;
    public TextField lastName;
    public TextField username;
    public TextField jmbg;
    public TextField date;
    public TextField city;
    public TextField adress;
    public TextField email;
    public TextField phone;

    private ObservableList voters;
    private Voter voter;

    public VoterUpdateController(ObservableList voters, Voter voter) {
        this.voters = voters;
        this.voter = voter;
    }

    public void setVoter(Voter voter) {
        this.voter = voter;
    }

    public Voter getVoter() {
        return voter;
    }

    public void initialize() {
        if(voter!=null)
        {
            pass.setText(voter.getPassword());
            firstName.setText(voter.getFirstName());
            lastName.setText(voter.getLastName());
            username.setText(voter.getUsername());
            jmbg.setText(voter.getJmbg());
            date.setText(voter.getDate());
            city.setText(voter.getCity());
            adress.setText(voter.getAdress());
            email.setText(voter.getEmail());
            phone.setText(voter.getPhone());

        }


    }

    public void okAction(ActionEvent actionEvent){

        if (voter == null) {
            voter = new Voter();

        }

        voter.setFirstName(firstName.getText());
        voter.setLastName(lastName.getText());
        voter.setAdress(adress.getText());
        voter.setCity(city.getText());
        voter.setDate(date.getText());
        voter.setJmbg(jmbg.getText());
        voter.setEmail(email.getText());
        voter.setPhone(phone.getText());
        voter.setPassword(pass.getText());
        voter.setUsername(username.getText());


        Stage stage = (Stage) username.getScene().getWindow();
        stage.close();

    }

    public void cancelAction(ActionEvent actionEvent){
        Stage currentStage = (Stage) username.getScene().getWindow();
        currentStage.close();

    }

}
