package ba.unsa.etf.rpr;

public class CECMember {

    private int code;
    private String username, firstName, lastName;

    public CECMember(int code, String username, String firstName, String lastName) {
        this.code = code;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
