package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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

    public void addVoterAction(ActionEvent actionEvent) {

        Stage stage = new Stage();
        Parent root = null;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/voter-update.fxml"));
        VoterUpdateController ctrl = new VoterUpdateController( model.votersObs(), null);
        loader.setController(ctrl);
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        stage.setTitle("Add Voter");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        stage.setOnHiding( event -> {
            Voter voter = ctrl.getVoter();
            if (voter != null) {
                model.addVoter(voter);
                listVoters.setAll(model.voters());
            }

        } );

    }

    public void deleteAction(ActionEvent actionEvent){

        if(tblCandidates.getSelectionModel().getSelectedItem()!=null){
            Candidate candidate = (Candidate) tblCandidates.getSelectionModel().getSelectedItem();
            if (candidate == null)
            {
                Alert upozorenje = new Alert(Alert.AlertType.CONFIRMATION);
                upozorenje.setTitle("Error");
                upozorenje.setHeaderText("You have to select item before performing this action!");
                Optional<ButtonType> result = upozorenje.showAndWait();
                return;
            }


            Alert upozorenje = new Alert(Alert.AlertType.CONFIRMATION);
            upozorenje.setTitle("Confirmation");
            upozorenje.setHeaderText("Delete?");
            Optional<ButtonType> result = upozorenje.showAndWait();

            if(result.get() == ButtonType.OK)
            {
                model.deleteCandidate(candidate.getId());
                listCandidates.setAll(model.candidatesObs());
            }
        }
        if(tblParties.getSelectionModel().getSelectedItem()!=null){
            PoliticalParty party = (PoliticalParty) tblParties.getSelectionModel().getSelectedItem();
            if (party == null)
            {
                Alert upozorenje = new Alert(Alert.AlertType.CONFIRMATION);
                upozorenje.setTitle("Error");
                upozorenje.setHeaderText("You have to select item before performing this action!");
                Optional<ButtonType> result = upozorenje.showAndWait();
                return;
            }


            Alert upozorenje = new Alert(Alert.AlertType.CONFIRMATION);
            upozorenje.setTitle("Confirmation");
            upozorenje.setHeaderText("Delete?");
            Optional<ButtonType> result = upozorenje.showAndWait();

            if(result.get() == ButtonType.OK)
            {
                model.deleteParty(party.getId());
                listPrties.setAll(model.partiesObs());
            }
        }

        if(tableViewVoters.getSelectionModel().getSelectedItem()!=null){
            Voter voter = (Voter) tableViewVoters.getSelectionModel().getSelectedItem();
            if (voter == null)
            {
                Alert upozorenje = new Alert(Alert.AlertType.CONFIRMATION);
                upozorenje.setTitle("Error");
                upozorenje.setHeaderText("You have to select item before performing this action!");
                Optional<ButtonType> result = upozorenje.showAndWait();
                return;
            }

            Alert upozorenje = new Alert(Alert.AlertType.CONFIRMATION);
            upozorenje.setTitle("Confirmation");
            upozorenje.setHeaderText("Delete?");
            Optional<ButtonType> result = upozorenje.showAndWait();

            if(result.get() == ButtonType.OK)
            {
                model.deleteVoter(voter.getPassword());
                listVoters.setAll(model.votersObs());
            }
        }

        if(tblCEC.getSelectionModel().getSelectedItem()!=null){
            CECMember member = (CECMember) tblCEC.getSelectionModel().getSelectedItem();

            if(member==null)
            {
                Alert upozorenje = new Alert(Alert.AlertType.CONFIRMATION);
                upozorenje.setTitle("Error");
                upozorenje.setHeaderText("You have to select item before performing this action!");
                Optional<ButtonType> result = upozorenje.showAndWait();
                return;
            }


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


    }

    public void editAction(ActionEvent actionEvent){

        if(tblCandidates.getSelectionModel().getSelectedItem()!=null){
            Candidate candidate = (Candidate) tblCandidates.getSelectionModel().getSelectedItem();
            if (candidate == null)
            {
                Alert upozorenje = new Alert(Alert.AlertType.CONFIRMATION);
                upozorenje.setTitle("Error");
                upozorenje.setHeaderText("You have to select item before performing this action!");
                Optional<ButtonType> result = upozorenje.showAndWait();
                return;
            }

            Stage stage = new Stage();
            Parent root = null;

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/candidate-update.fxml"));
            CandidateUpdateController ctrl = new CandidateUpdateController( model.candidatesObs(), candidate);
            loader.setController(ctrl);
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene scene = new Scene(root);
            stage.setTitle("Edit Candidate");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

            stage.setOnHiding( event -> {
                Candidate c = ctrl.getCandidate();
                if (c != null) {
                    model.updateCandidate(c);
                    listCandidates.setAll(model.candidatesObs());
                }

            } );
        }
        if(tblParties.getSelectionModel().getSelectedItem()!=null){
            PoliticalParty party = (PoliticalParty) tblParties.getSelectionModel().getSelectedItem();
            if (party == null)
            {
                Alert upozorenje = new Alert(Alert.AlertType.CONFIRMATION);
                upozorenje.setTitle("Error");
                upozorenje.setHeaderText("You have to select item before performing this action!");
                Optional<ButtonType> result = upozorenje.showAndWait();
                return;
            }

            Stage stage = new Stage();
            Parent root = null;

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/party-update.fxml"));
            PartyUpdateController ctrl = new PartyUpdateController( model.candidatesObs(), party);
            loader.setController(ctrl);
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene scene = new Scene(root);
            stage.setTitle("Edit Political Party");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

            stage.setOnHiding( event -> {
                PoliticalParty p = ctrl.getParty();
                if (p != null) {
                    model.updateParty(p);
                    listPrties.setAll(model.partiesObs());
                }

            } );
        }
        if(tableViewVoters.getSelectionModel().getSelectedItem()!=null){
            Voter voter = (Voter) tableViewVoters.getSelectionModel().getSelectedItem();
            if (voter == null)
            {
                Alert upozorenje = new Alert(Alert.AlertType.CONFIRMATION);
                upozorenje.setTitle("Error");
                upozorenje.setHeaderText("You have to select item before performing this action!");
                Optional<ButtonType> result = upozorenje.showAndWait();
                return;
            }

            Stage stage = new Stage();
            Parent root = null;

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/voter-update.fxml"));
            VoterUpdateController ctrl = new VoterUpdateController( model.votersObs(), voter);
            loader.setController(ctrl);
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene scene = new Scene(root);
            stage.setTitle("Edit Voter");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

            stage.setOnHiding( event -> {
                Voter v = ctrl.getVoter();
                if (v != null) {
                    model.updateVoter(v);
                    listVoters.setAll(model.votersObs());
                }

            } );
        }

        if(tblCEC.getSelectionModel().getSelectedItem()!=null){
            CECMember member = (CECMember) tblCEC.getSelectionModel().getSelectedItem();
            if (member == null)
            {
                Alert upozorenje = new Alert(Alert.AlertType.CONFIRMATION);
                upozorenje.setTitle("Error");
                upozorenje.setHeaderText("You have to select item before performing this action!");
                Optional<ButtonType> result = upozorenje.showAndWait();
                return;
            }

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
            stage.setTitle("Edit CEC Member");
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

    }


    public void addCandidateAction(ActionEvent actionEvent) {
        Stage stage = new Stage();
        Parent root = null;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/candidate-update.fxml"));
        CandidateUpdateController ctrl = new CandidateUpdateController( model.candidatesObs(), null);
        loader.setController(ctrl);
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        stage.setTitle("Add Candidate");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        stage.setOnHiding( event -> {
            Candidate candidate = ctrl.getCandidate();
            if (candidate != null) {
                candidate.setNumOfVotes(0);
                model.addCandidate(candidate);
                listCandidates.setAll(model.candidatesObs());
            }

        } );

    }


    public void addPartyAction(ActionEvent actionEvent) {
        Stage stage = new Stage();
        Parent root = null;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/party-update.fxml"));
        PartyUpdateController ctrl = new PartyUpdateController( model.partiesObs(), null);
        loader.setController(ctrl);
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        stage.setTitle("Add Political Party");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        stage.setOnHiding( event -> {
            PoliticalParty party= ctrl.getParty();
            if (party != null) {
                model.addParty(party);
                listPrties.setAll(model.partiesObs());
            }

        } );

    }


    public void exitAction(ActionEvent actionEvent) {

        Stage stage = new Stage();
        Parent root = null;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
        MainController ctrl;
        ctrl = new MainController(false);

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

    public void tutAction(ActionEvent actionEvent){

        Stage stage = new Stage();
        Parent root = null;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/admin-tutorial.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Tutorial");
        stage.show();

    }

    public void factoryActions(ActionEvent actionEvent){

    }

}
