package ba.unsa.etf.rpr;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class VoterController {

    @FXML
    public Label lblHello;
    private Voter voter;



    public VoterController(Voter voter) {
        this.voter=voter;

    }

    public void initialize(){
        this.lblHello.setText("Hello " + voter.getFirstName());
    }

}
