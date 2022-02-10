package ba.unsa.etf.rpr;

import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminController {


    public DAO model;
    public TableView tableViewVoters;
    public TableView tblCEC;

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



    public AdminController() {
        model = DAO.getInstance();

    }

    public void initialize(){

        tableViewVoters.setItems(model.votersObs());
        tblCEC.setItems(model.cecMembersObs());


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




    }
}
