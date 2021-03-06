package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController{

    public TextField fieldUsername;
    public TextField fieldPassword;
    public DAO model;
    public ObservableList<Voter> listVoters;
    public ObservableList<CECMember> cecMembers;
    private boolean start;
    public Button btnGithub;
    public Label lblMessage;

    public MainController(boolean start) {
        model = DAO.getInstance();
        listVoters = FXCollections.observableArrayList(model.voters());
        cecMembers = FXCollections.observableArrayList(model.cecMembersObs());
        this.start=start;
    }

    public void initialize() {

        ImageView imageView = new ImageView(getClass().getResource("/img/github.png").toExternalForm());
        btnGithub.setGraphic(imageView);
    }


    public void loginAction(ActionEvent actionEvent) {

     boolean flagAdmin = false;
            if (fieldUsername.getText().equals("admin") && fieldPassword.getText().equals("admin")) {
                flagAdmin = true;
                if(start==false) {
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
                stage.setTitle("Administrator");
                stage.show();

                Stage currentStage = (Stage) fieldUsername.getScene().getWindow();
                currentStage.close();
            }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Can't enter now.");
                    alert.setHeaderText("Sorry...");
                    alert.setContentText("Elections started. Admin entry denied.");
                    alert.showAndWait();
                }
        }


            boolean flagVoter = false;
            Voter voter = null;

            for (int i = 0; i < listVoters.size(); i++) {

                if (fieldUsername.getText().equals(listVoters.get(i).getEmail()) && fieldPassword.getText().equals(listVoters.get(i).getPassword())) {
                    flagVoter = true;
                    voter = listVoters.get(i);
                    break;
                }

                if (fieldUsername.getText().equals(listVoters.get(i).getUsername()) && fieldPassword.getText().equals(listVoters.get(i).getPassword())) {
                    flagVoter = true;
                    voter = listVoters.get(i);
                    break;
                }
            }

            if (flagVoter == true) {

                if(start==true) {
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
                stage.setResizable(false);
                stage.show();

                Stage currentStage = (Stage) fieldUsername.getScene().getWindow();
                currentStage.close();
            }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Can't vote now.");
                    alert.setHeaderText("Sorry...");
                    alert.setContentText("You will be able to vote when elections start ");
                    alert.showAndWait();
                }
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
            CECController ctrl = new CECController(start);
            loader.setController(ctrl);
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Statistics");
            stage.setResizable(false);
            stage.show();

            stage.setOnHiding( event -> {
               this.start = ctrl.isStart();
            } );

            Stage currentStage = (Stage) fieldUsername.getScene().getWindow();
            currentStage.close();
        }

        if(!flagVoter && !flagCec && !flagAdmin){
            lblMessage.setText("The username or password you\n entered doesn't belong to\n an account.");
        }

    }

    public static void openWebpage(String url) {
        try {
            new ProcessBuilder("x-www-browser", url).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void githubAction(ActionEvent actionEvent){
        openWebpage("https://github.com/RPR-2019/rpr21-projekat-AmarHasecic");
    }

}
