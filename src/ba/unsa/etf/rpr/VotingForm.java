package ba.unsa.etf.rpr;

import java.util.List;

public class VotingForm {

    private int id;
    private String party;
    private List<Candidate>  candidates;

    public VotingForm(int id, String party, List<Candidate> candidates) {
        this.id = id;
        this.party = party;
        this.candidates = candidates;
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

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }
}
