package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController{

    public TextField fieldUsername;
    public TextField fieldPassword;
    public DAO model;
    public ObservableList<Voter> listVoters;



    public MainController() {
        model = DAO.getInstance();
        listVoters = FXCollections.observableArrayList(model.voters());
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
        }

        boolean flag = false;
        Voter voter = null;

        for(int i=0; i< listVoters.size(); i++)
        {
            System.out.println(listVoters.get(i));
            if(fieldUsername.getText().equals(listVoters.get(i).getEmail()) && fieldPassword.getText().equals(listVoters.get(i).getPassword())){
                flag=true;
                voter = listVoters.get(i);
                break;
            }

            if(fieldUsername.getText().equals(listVoters.get(i).getUsername()) && fieldPassword.getText().equals(listVoters.get(i).getPassword())){
                flag=true;
                voter = listVoters.get(i);
                break;
            }
        }

        if(flag==true){
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
        }


        Stage currentStage = (Stage) fieldUsername.getScene().getWindow();
        currentStage.close();

    }
}
