package ba.unsa.etf.rpr;

import com.sun.scenario.effect.Effect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class VoterController {

    @FXML
    public Label lblHello;
    private Voter voter;
    public ImageView imgViewSticker;
    public Button btnStart;



    public VoterController(Voter voter) {
        this.voter=voter;

    }

    public void initialize(){

        this.lblHello.setText("Hello " + voter.getFirstName());
        if(voter.getBadge()!=null)
        {

            Image image = new Image("/jpg/IVotedSticker.jpg");
            imgViewSticker.setImage(image);

            btnStart.setDisable(true);
        }
    }

    public void startVotingAction(ActionEvent actionEvent){
        Stage stage = new Stage();
        Parent root = null;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/voting-page1.fxml"));
        VotingPage1Controller ctrl;
        ctrl = new VotingPage1Controller(voter);

        loader.setController(ctrl);
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Choose Political Party");
        stage.show();

        Stage currentStage = (Stage) lblHello.getScene().getWindow();
        currentStage.close();
          
    }

    public void cancelAction(ActionEvent actionEvent){
        Stage stage = new Stage();
        Parent root = null;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
        MainController ctrl;
        ctrl = new MainController();

        loader.setController(ctrl);
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Vote!");
        stage.show();

        Stage currentStage = (Stage) lblHello.getScene().getWindow();
        currentStage.close();

    }

}
