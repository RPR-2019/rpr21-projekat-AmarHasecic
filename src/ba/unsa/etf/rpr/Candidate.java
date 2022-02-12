package ba.unsa.etf.rpr;

public class Candidate {

    private int id, numOfVotes, politicalPartyId;
    private String firstName, lastName;

    public Candidate(int id, int numOfVotes, String firstName, String lastName) {
        this.id = id;
        this.numOfVotes = numOfVotes;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Candidate() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumOfVotes() {
        return numOfVotes;
    }

    public void setNumOfVotes(int numOfVotes) {
        this.numOfVotes = numOfVotes;
    }

    public int getPoliticalPartyId() {
        return politicalPartyId;
    }

    public void setPoliticalPartyId(int politicalPartyId) {
        this.politicalPartyId = politicalPartyId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
