package ba.unsa.etf.rpr;

import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Collections;

public class CECController {


    boolean start;
    ObservableList candidates;
    public ListView listViewCandidates;

    public CECController(ObservableList candidates) {
        start = false;
        this.candidates = candidates;
    }

    public void initialize()
    {
        listViewCandidates.setItems(candidates);
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public void refreshAction(ActionEvent actionEvent){

    }

    public void printReportAction(ActionEvent actionEvent){

    }

    public void startStopAction(ActionEvent actionEvent){

    }

    public void exitAction(ActionEvent actionEvent){

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

        Stage currentStage = (Stage) listViewCandidates.getScene().getWindow();
        currentStage.close();
    }
}
