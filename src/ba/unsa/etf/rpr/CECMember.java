package ba.unsa.etf.rpr;

public class CECMember {

    private String code, firstName, lastName, username;

    public CECMember(String code, String firstName, String lastName, String username) {
        this.code = code;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
