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
    private PreparedStatement allVotersQuery;

    public DAO() {
        String url = "jdbc:sqlite:baza.db";
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            allVotersQuery = conn.prepareStatement("SELECT * FROM Voters");
        }
        catch (SQLException e) {
            createDatabase();

            try {
                allVotersQuery = conn.prepareStatement("SELECT * FROM Voters");

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
            ulaz = new Scanner(new FileInputStream("baza.sql"));
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
            stmt.executeUpdate("DELETE FROM grad");
            stmt.executeUpdate("DELETE FROM drzava");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        createDatabase();
    }

    public Connection getConn() {
        return conn;
    }

}
