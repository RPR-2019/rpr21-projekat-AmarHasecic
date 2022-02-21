package ba.unsa.etf.rpr;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
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

        pass.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                pass.getStyleClass().removeAll("poljeNijeIspravno");
                pass.getStyleClass().add("poljeIspravno");
            } else {
                pass.getStyleClass().removeAll("poljeIspravno");
                pass.getStyleClass().add("poljeNijeIspravno");
            }
        });

        username.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                username.getStyleClass().removeAll("poljeNijeIspravno");
                username.getStyleClass().add("poljeIspravno");
            } else {
                username.getStyleClass().removeAll("poljeIspravno");
                username.getStyleClass().add("poljeNijeIspravno");
            }
        });

        jmbg.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                jmbg.getStyleClass().removeAll("poljeNijeIspravno");
                jmbg.getStyleClass().add("poljeIspravno");
            } else {
                jmbg.getStyleClass().removeAll("poljeIspravno");
                jmbg.getStyleClass().add("poljeNijeIspravno");
            }
        });

        date.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                date.getStyleClass().removeAll("poljeNijeIspravno");
                date.getStyleClass().add("poljeIspravno");
            } else {
                date.getStyleClass().removeAll("poljeIspravno");
                date.getStyleClass().add("poljeNijeIspravno");
            }
        });

        city.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                city.getStyleClass().removeAll("poljeNijeIspravno");
                city.getStyleClass().add("poljeIspravno");
            } else {
                city.getStyleClass().removeAll("poljeIspravno");
                city.getStyleClass().add("poljeNijeIspravno");
            }
        });

        adress.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                adress.getStyleClass().removeAll("poljeNijeIspravno");
                adress.getStyleClass().add("poljeIspravno");
            } else {
                adress.getStyleClass().removeAll("poljeIspravno");
                adress.getStyleClass().add("poljeNijeIspravno");
            }
        });

        email.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                email.getStyleClass().removeAll("poljeNijeIspravno");
                email.getStyleClass().add("poljeIspravno");
            } else {
                email.getStyleClass().removeAll("poljeIspravno");
                email.getStyleClass().add("poljeNijeIspravno");
            }
        });

        phone.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                phone.getStyleClass().removeAll("poljeNijeIspravno");
                phone.getStyleClass().add("poljeIspravno");
            } else {
                phone.getStyleClass().removeAll("poljeIspravno");
                phone.getStyleClass().add("poljeNijeIspravno");
            }
        });



    }

    boolean checkPassword(String password) throws PasswordFormatException {
        if (password == null) {
            throw new PasswordFormatException("Password must contain at least one letter");
        }
        try {
            double d = Integer.parseInt(password);
        } catch (NumberFormatException nfe) {
            return true;
        }
        throw new PasswordFormatException("Password must contain at least one letter");
    }

    public void okAction(ActionEvent actionEvent){

        if(firstName.getText().isBlank() || lastName.getText().isBlank() || username.getText().isBlank() || adress.getText().isBlank() ||
         city.getText().isBlank() ||date.getText().isBlank() || jmbg.getText().isBlank() || email.getText().isBlank() || phone.getText().isBlank() ||
        pass.getText().isBlank())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Field empty");
            alert.setContentText("You have to fill every field in this form");
            alert.showAndWait();
        }
        else {

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

            try {
                if(checkPassword(pass.getText())) {
                    Stage stage = (Stage) username.getScene().getWindow();
                    stage.close();
                }
            } catch (PasswordFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Password format not acceptable");
                alert.setContentText(e.getMessage());
                alert.showAndWait();

            }
        }

    }

    public void cancelAction(ActionEvent actionEvent){
        voter=null;
        Stage currentStage = (Stage) username.getScene().getWindow();
        currentStage.close();

    }

}
