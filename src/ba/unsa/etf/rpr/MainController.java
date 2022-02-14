package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class MainController{

    public TextField fieldUsername;
    public TextField fieldPassword;
    public DAO model;
    public ObservableList<Voter> listVoters;
    public ObservableList<CECMember> cecMembers;



    public MainController() {
        model = DAO.getInstance();
        listVoters = FXCollections.observableArrayList(model.voters());
        cecMembers = FXCollections.observableArrayList(model.cecMembersObs());
    }

    public void initialize() {

    }


    public void loginAction(ActionEvent actionEvent) {

        if(fieldUsername.getText().equals("admin") && fieldPassword.getText().equals("admin"))
        {
            Stage stage = new Stage();
            Parent root = null;

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/admin.fxml"));
            AdminController ctrl = new AdminController();
            loader.setController(ctrl);
            try {
            root = loader.load();
            } catch (IOException e) {
            e.printStackTrace();
            }

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.show();

            Stage currentStage = (Stage) fieldUsername.getScene().getWindow();
            currentStage.close();
        }

        boolean flagVoter = false;
        Voter voter = null;

        for(int i=0; i< listVoters.size(); i++)
        {
            System.out.println(listVoters.get(i).getBadge());
            if(fieldUsername.getText().equals(listVoters.get(i).getEmail()) && fieldPassword.getText().equals(listVoters.get(i).getPassword())){
                flagVoter=true;
                voter = listVoters.get(i);
                break;
            }

            if(fieldUsername.getText().equals(listVoters.get(i).getUsername()) && fieldPassword.getText().equals(listVoters.get(i).getPassword())){
                flagVoter=true;
                voter = listVoters.get(i);
                break;
            }
        }

        if(flagVoter==true){
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

            Stage currentStage = (Stage) fieldUsername.getScene().getWindow();
            currentStage.close();
        }

        boolean flagCec = false;
        CECMember member = null;

        for(int i=0; i<cecMembers.size(); i++)
        {

            if(fieldUsername.getText().equals(cecMembers.get(i).getUsername()) && Integer.parseInt(fieldPassword.getText())==(cecMembers.get(i).getCode())){
                flagCec=true;
                member = cecMembers.get(i);
                break;
            }
        }

        if(flagCec==true){
            Stage stage = new Stage();
            Parent root = null;

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/cec-member.fxml"));
            CECController ctrl = new CECController();
            loader.setController(ctrl);
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Statistics");
            stage.show();

            Stage currentStage = (Stage) fieldUsername.getScene().getWindow();
            currentStage.close();
        }

    }

}
