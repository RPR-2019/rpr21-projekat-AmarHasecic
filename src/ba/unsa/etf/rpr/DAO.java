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
    addCECQuery, editCECQuery, deleteCECQuery, newIdCECQuery,addVoterQuery, editVoterQuery, deleteVoterQuery,
    addCandidateQuery, editCandidateQuery, deleteCandidateQuery, newIdCandidateQuery, addPartyQuery, editPartyQuery, deletePartyQuery, newIdPartyQuery,
    addVoteQuery, deleteVoteQuery, allCandidatesFromParty, newVoteId, updateVoteQuerry;
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

            addVoterQuery = conn.prepareStatement("INSERT INTO Voters VALUES(?,?,?,?,?,?,?,?,?,?,?)");
            editVoterQuery = conn.prepareStatement("UPDATE Voters SET first_name=?, last_name=?, username=?, jmbg=?, date_of_birth=?, city=?, adress=?, email=?, phone=? WHERE id_pass=?");
            deleteVoterQuery = conn.prepareStatement("DELETE FROM Voters WHERE id_pass=?");

            addCandidateQuery = conn.prepareStatement("INSERT INTO Candidates VALUES(?,?,?,?,?)");
            editCandidateQuery = conn.prepareStatement("UPDATE Candidates SET first_name=?, last_name=?, numberOfVotes=?, political_party=? WHERE id=?");
            deleteCandidateQuery = conn.prepareStatement("DELETE FROM Candidates WHERE id=?");
            newIdCandidateQuery = conn.prepareStatement("SELECT MAX(id)+1 FROM Candidates");

            addPartyQuery = conn.prepareStatement("INSERT INTO PoliticalParties VALUES(?,?)");
            editPartyQuery = conn.prepareStatement("UPDATE PoliticalParties SET name=? WHERE id=?");
            deletePartyQuery = conn.prepareStatement("DELETE FROM PoliticalParties WHERE id=?");
            newIdPartyQuery = conn.prepareStatement("SELECT MAX(id)+1 FROM PoliticalParties");

            addVoteQuery = conn.prepareStatement("INSERT INTO voting_sheets VALUES(?,?,?)");
            deleteVoteQuery = conn.prepareStatement("DELETE FROM voting_sheets WHERE id=?");
            allCandidatesFromParty = conn.prepareStatement("SELECT * FROM Candidates WHERE political_party=?");
            newVoteId = conn.prepareStatement("SELECT MAX(id)+1 FROM voting_sheets");

            updateVoteQuerry = conn.prepareStatement("UPDATE Candidates SET numberOfVotes=? WHERE id=?");


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

    ObservableList<Candidate> getCandidatesFromParty(PoliticalParty party) {
        ObservableList<Candidate> output = FXCollections.observableArrayList();
        try {
            allCandidatesFromParty.setInt(1,party.getId());
            ResultSet rs = allCandidatesFromParty.executeQuery();

            while (rs.next()) {
               Candidate candidate = new Candidate(rs.getInt(1), rs.getInt(4),rs.getString(2), rs.getString(3));
                output.add(candidate);
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
                candidate.setPoliticalPartyId(rs.getInt(5));
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

    void addVoter(Voter voter){

  try{
      addVoterQuery.setString(1, voter.getPassword());
      addVoterQuery.setString(2, voter.getFirstName());
      addVoterQuery.setString(3, voter.getLastName());
      addVoterQuery.setString(4, voter.getUsername());
      addVoterQuery.setString(5, voter.getJmbg());
      addVoterQuery.setString(6, voter.getDate());
      addVoterQuery.setString(7, voter.getCity());
      addVoterQuery.setString(8, voter.getAdress());
      addVoterQuery.setString(9, voter.getEmail());
      addVoterQuery.setString(10, voter.getPhone());
      addVoterQuery.setString(11, voter.getBadge());

            addVoterQuery.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void updateVoter(Voter voter){

        try {

            editVoterQuery.setString(10, voter.getPassword());
            editVoterQuery.setString(1, voter.getFirstName());
            editVoterQuery.setString(2, voter.getLastName());
            editVoterQuery.setString(3, voter.getUsername());
            editVoterQuery.setString(4, voter.getJmbg());
            editVoterQuery.setString(5, voter.getDate());
            editVoterQuery.setString(6, voter.getCity());
            editVoterQuery.setString(7, voter.getAdress());
            editVoterQuery.setString(8, voter.getEmail());
            editVoterQuery.setString(9, voter.getPhone());

            editVoterQuery.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    void deleteVoter(String password){
        try {

            deleteVoterQuery.setString(1, password);
            deleteVoterQuery.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    void addCandidate(Candidate candidate){
        ResultSet rs = null;
        try {
            rs = newIdCandidateQuery.executeQuery();
            rs.next();
            int noviId = rs.getInt(1);

            addCandidateQuery.setInt(1, noviId);
            addCandidateQuery.setString(2, candidate.getFirstName());
            addCandidateQuery.setString(3, candidate.getLastName());
            addCandidateQuery.setInt(4, candidate.getNumOfVotes());
            addCandidateQuery.setInt(5, candidate.getPoliticalPartyId());
            addCandidateQuery.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void updateCandidate(Candidate candidate){

        try {
            editCandidateQuery.setString(1, candidate.getFirstName());
            editCandidateQuery.setString(2, candidate.getLastName());
            editCandidateQuery.setInt(3, candidate.getNumOfVotes());
            editCandidateQuery.setInt(4, candidate.getPoliticalPartyId());
            editCandidateQuery.setInt(5,candidate.getId());
            editCandidateQuery.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    void deleteCandidate(int id){
        try {

            deleteCandidateQuery.setInt(1, id);
            deleteCandidateQuery.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void addParty(PoliticalParty party){
        ResultSet rs = null;
        try {
            rs = newIdPartyQuery.executeQuery();
            rs.next();
            int noviId = rs.getInt(1);

            addPartyQuery.setInt(1, noviId);
            addPartyQuery.setString(2, party.getName());

            addPartyQuery.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void updateParty(PoliticalParty party){

        try {
            editPartyQuery.setString(1, party.getName());
            editPartyQuery.setInt(2,party.getId());
            editPartyQuery.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    void deleteParty(int id){
        try {

            deletePartyQuery.setInt(1, id);
            deletePartyQuery.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void addVote(VotingForm form)
    {
        ResultSet rs = null;
        for(int i=0; i<form.getCandidates().size();i++) {
            try {
                rs = newVoteId.executeQuery();
                rs.next();
                int noviId = rs.getInt(1);

                addVoteQuery.setInt(1, noviId);
                addVoteQuery.setString(2, form.getParty());
                addVoteQuery.setString(3, form.getCandidates().get(i).toString());
                addVoteQuery.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    void updateVote(Candidate candidate)
    {
        try {
            updateVoteQuerry.setInt(1, candidate.getNumOfVotes());
            updateVoteQuerry.setInt(2, candidate.getId());
            updateVoteQuerry.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
