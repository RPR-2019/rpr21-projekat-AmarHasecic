package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;

import java.io.IOException;

public class CECController {


    boolean start;
    ObservableList candidates;
    public ListView listViewCandidates;
    public DAO model;
    public ToggleButton tglBtn;


    public CECController(boolean start) {

        model = DAO.getInstance();
        candidates = FXCollections.observableArrayList(model.candidatesObs());
        this.start=start;

    }

    public void initialize()
    {

        listViewCandidates.setItems(candidates);
        if(start==true) {
            tglBtn.setSelected(true);
            tglBtn.setStyle(" -fx-background-color: green;");
        }
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public void refreshAction(ActionEvent actionEvent){
        candidates = FXCollections.observableArrayList(model.getSortedCandidates());
        listViewCandidates.setItems(candidates);
    }

    public void printReportAction(ActionEvent actionEvent){
            try {
                new Report().showReport(model.getConn());
            } catch (JRException e1) {
                e1.printStackTrace();
            }

    }

    public void startStopAction(ActionEvent actionEvent){
          if(tglBtn.isSelected()){
              tglBtn.setStyle(" -fx-background-color: green;");
              start=true;
          }
          else{
              tglBtn.setStyle(" -fx-background-color: white;");
              start = false;
          }

    }

    public void exitAction(ActionEvent actionEvent){

        Stage stage = new Stage();
        Parent root = null;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
        MainController ctrl;
        ctrl = new MainController(start);
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
