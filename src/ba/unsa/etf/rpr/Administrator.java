package ba.unsa.etf.rpr;

public class Administrator {

    private String username;
    private String password;

    public Administrator(String username, String password) {
        this.username = "admin";
        this.password = "admin";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
