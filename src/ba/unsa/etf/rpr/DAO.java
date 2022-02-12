package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DAO {

    private Connection conn;
    private static DAO instance;
    private PreparedStatement allVotersQuery, allCecMembersQuery, allPoliticalParties, allCandidatesQuery,
    addCECQuery, editCECQuery, deleteCECQuery, newIdCECQuery;
            ;

    public DAO() {
        String url = "jdbc:sqlite:baza.db";
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            allVotersQuery = conn.prepareStatement("SELECT * FROM Voters");
            allCecMembersQuery = conn.prepareStatement("SELECT * FROM CECMembers");
            allPoliticalParties = conn.prepareStatement("SELECT * FROM PoliticalParties");
            allCandidatesQuery = conn.prepareStatement("SELECT * FROM Candidates");

            addCECQuery = conn.prepareStatement("INSERT INTO CECMembers VALUES(?,?,?,?)");
            editCECQuery = conn.prepareStatement("UPDATE CECMembers SET username=?, first_name=?, last_name=? WHERE code=?");
            deleteCECQuery = conn.prepareStatement("DELETE FROM CECMembers WHERE code=?");
            newIdCECQuery = conn.prepareStatement("SELECT MAX(code)+1 FROM CECMembers");




        }
        catch (SQLException e) {
            createDatabase();

            try {
                allVotersQuery = conn.prepareStatement("SELECT * FROM Voters");
                allCecMembersQuery = conn.prepareStatement("SELECT * FROM CECMembers");
                allCandidatesQuery = conn.prepareStatement("SELECT * FROM Candidates");

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }


    }

    ArrayList<Voter> voters() {
        ArrayList<Voter> output = new ArrayList<Voter>();
        try {
            ResultSet rs = allVotersQuery.executeQuery();

            while (rs.next()) {
                Voter voter = new Voter(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
                output.add(voter);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return output;
    }
    ObservableList<Voter> votersObs() {
        ObservableList<Voter> output = FXCollections.observableArrayList();
        try {
            ResultSet rs = allVotersQuery.executeQuery();

            while (rs.next()) {
                Voter voter = new Voter(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));

                output.add(voter);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return output;
    }

    ObservableList<CECMember> cecMembersObs() {
        ObservableList<CECMember> output = FXCollections.observableArrayList();
        try {
            ResultSet rs = allCecMembersQuery.executeQuery();

            while (rs.next()) {
                CECMember member = new CECMember(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4));
                output.add(member);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return output;
    }

    public ObservableList<PoliticalParty> partiesObs() {

        ObservableList<PoliticalParty> output = FXCollections.observableArrayList();
        try {
            ResultSet rs = allPoliticalParties.executeQuery();

            while (rs.next()) {
                PoliticalParty party = new PoliticalParty(rs.getInt(1), rs.getString(2));
                output.add(party);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return output;
    }
    public ObservableList<Candidate> candidatesObs() {
        ObservableList<Candidate> output = FXCollections.observableArrayList();
        try {
            ResultSet rs = allCandidatesQuery.executeQuery();

            while (rs.next()) {
                Candidate candidate = new Candidate(rs.getInt(1), rs.getInt(4),rs.getString(2), rs.getString(3));
                output.add(candidate);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return output;
    }


    

    public static DAO getInstance() {
        if (instance == null) instance = new DAO();
        return instance;
    }

    public static void removeInstance() {
        if (instance == null) return;
        instance.close();
        instance = null;
    }

    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createDatabase() {
        Scanner ulaz = null;
        try {
            ulaz = new Scanner(new FileInputStream("baza.db.sql"));
            String sqlUpit = "";
            while (ulaz.hasNext()) {
                sqlUpit += ulaz.nextLine();
                if (sqlUpit.charAt(sqlUpit.length() - 1) == ';') {
                    try {
                        Statement stmt = conn.createStatement();
                        stmt.execute(sqlUpit);
                        sqlUpit = "";
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            ulaz.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    public void setDefaultDatabase() {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stmt.executeUpdate("DELETE FROM voters");
            stmt.executeUpdate("DELETE FROM cecmembers");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        createDatabase();
    }

    public Connection getConn() {
        return conn;
    }


    void addCec(CECMember member){
        ResultSet rs = null;
        try {
            rs = newIdCECQuery.executeQuery();
            rs.next();
            int noviId = rs.getInt(1);

            addCECQuery.setInt(1, noviId);
            addCECQuery.setString(2, member.getUsername());
            addCECQuery.setString(3, member.getFirstName());
            addCECQuery.setString(4, member.getLastName());
            addCECQuery.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void updateCec(CECMember member){

        try {
            editCECQuery.setString(1, member.getUsername());
            editCECQuery.setString(2, member.getFirstName());
            editCECQuery.setString(3, member.getLastName());
            editCECQuery.setInt(4, member.getCode());
            editCECQuery.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    void deleteCec(int code){
        try {

            deleteCECQuery.setInt(1, code);
            deleteCECQuery.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
