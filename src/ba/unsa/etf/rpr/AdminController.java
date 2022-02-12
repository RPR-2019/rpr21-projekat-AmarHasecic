package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.text.TabExpander;
import java.io.IOException;
import java.util.Optional;

public class AdminController {


    public DAO model;
    public TableView tableViewVoters;
    public TableView tblCEC;
    public TableView tblParties;
    public TableView tblCandidates;

    public TableColumn password;
    public TableColumn firstName;
    public TableColumn lastName;
    public TableColumn username;
    public TableColumn jmbg;
    public TableColumn birth;
    public TableColumn city;
    public TableColumn adress;
    public TableColumn email;
    public TableColumn phone;
    public TableColumn badge;

    public TableColumn code;
    public TableColumn firstNameCEC;
    public TableColumn lastNameCEC;
    public TableColumn usernameCEC;

    public TableColumn partyName;
    public TableColumn partyId;

    public TableColumn candId;
    public TableColumn candName;
    public TableColumn candLast;
    public TableColumn numOfVotes;
    public TableColumn pp;

    public ObservableList listCECMembers;
    public ObservableList listVoters;
    public ObservableList listPrties;
    public ObservableList listCandidates;



    public AdminController() {
        model = DAO.getInstance();
        this.listCECMembers = FXCollections.observableArrayList(model.cecMembersObs());
        this.listVoters = FXCollections.observableArrayList(model.voters());
        this.listPrties = FXCollections.observableArrayList(model.partiesObs());
        this.listCandidates = FXCollections.observableArrayList(model.candidatesObs());


    }

    public void initialize(){


        tableViewVoters.setItems(listVoters);
        tblCEC.setItems(listCECMembers);
        tblParties.setItems(listPrties);
        tblCandidates.setItems(listCandidates);


        password.setCellValueFactory(new PropertyValueFactory("password"));
        firstName.setCellValueFactory(new PropertyValueFactory("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory("lastName"));
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        jmbg.setCellValueFactory(new PropertyValueFactory("jmbg"));
        birth.setCellValueFactory(new PropertyValueFactory("date"));
        city.setCellValueFactory(new PropertyValueFactory("city"));
        adress.setCellValueFactory(new PropertyValueFactory<>("adress"));
        email.setCellValueFactory(new PropertyValueFactory("email"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        if(badge!=null)
        badge.setCellValueFactory(new PropertyValueFactory<>("badge"));

        code.setCellValueFactory(new PropertyValueFactory<>("code"));
        firstNameCEC.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCEC.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        usernameCEC.setCellValueFactory(new PropertyValueFactory<>("username"));

        partyName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partyId.setCellValueFactory(new PropertyValueFactory<>("id"));

        candId.setCellValueFactory(new PropertyValueFactory("id"));
        candName.setCellValueFactory(new PropertyValueFactory("firstName"));
        candLast.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        numOfVotes.setCellValueFactory(new PropertyValueFactory("numOfVotes"));
        pp.setCellValueFactory(new PropertyValueFactory<>("PoliticalPartyId"));

    }

    public void addCecAction(ActionEvent actionEvent) {

        Stage stage = new Stage();
        Parent root = null;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/cec-update.fxml"));
        CECUpdateController ctrl = new CECUpdateController( model.cecMembersObs(), null);
        loader.setController(ctrl);
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        stage.setTitle("Add CEC Member");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        stage.setOnHiding( event -> {
            CECMember member = ctrl.getMember();
            if (member != null) {
                model.addCec(member);
                listCECMembers.setAll(model.cecMembersObs());
            }

        } );

    }
    public void editCecAction(ActionEvent actionEvent) {
        CECMember member = (CECMember) tblCEC.getSelectionModel().getSelectedItem();
        if (member == null) return;

        Stage stage = new Stage();
        Parent root = null;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/cec-update.fxml"));
        CECUpdateController ctrl = new CECUpdateController( model.cecMembersObs(), member);
        loader.setController(ctrl);
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        stage.setTitle("Add CEC Member");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        stage.setOnHiding( event -> {
            CECMember m = ctrl.getMember();
            if (m != null) {
                model.updateCec(m);
                listCECMembers.setAll(model.cecMembersObs());
            }

        } );

    }
    public void deleteCecAction(ActionEvent actionEvent) {

        CECMember member = (CECMember) tblCEC.getSelectionModel().getSelectedItem();
        if (member == null) return;

        Alert upozorenje = new Alert(Alert.AlertType.CONFIRMATION);
        upozorenje.setTitle("Confirmation");
        upozorenje.setHeaderText("Delete?");
        Optional<ButtonType> result = upozorenje.showAndWait();

        if(result.get() == ButtonType.OK)
        {
            model.deleteCec(member.getCode());
            listCECMembers.setAll(model.cecMembersObs());
        }

    }




    public void addCandidateAction(ActionEvent actionEvent) {
    }

    public void addPartyAction(ActionEvent actionEvent) {
    }

    public void addVoterAction(ActionEvent actionEvent) {
    }

    public void deleteCandidateAction(ActionEvent actionEvent) {
    }

    public void deletePArtyAction(ActionEvent actionEvent) {
    }

    public void deleteVoterAction(ActionEvent actionEvent) {
    }

    public void editCandidateAction(ActionEvent actionEvent) {
    }

    public void editPartyAction(ActionEvent actionEvent) {
    }

    public void editVoterAction(ActionEvent actionEvent) {
    }
    public void exitAction(ActionEvent actionEvent) {

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

        Stage currentStage = (Stage) tableViewVoters.getScene().getWindow();
        currentStage.close();
    }

}
