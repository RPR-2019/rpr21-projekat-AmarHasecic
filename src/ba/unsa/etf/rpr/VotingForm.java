package ba.unsa.etf.rpr;

public class VotingForm {

    private int id;
    private String party;
    private String candidate;

    public VotingForm(int id, String party, String candidate) {
        this.id = id;
        this.party = party;
        this.candidate = candidate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getCandidate() {
        return candidate;
    }

    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }
}
