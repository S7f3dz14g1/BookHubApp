package szwedo.bookhubapp.models;

public class RegistrationApiUserModel {

    private String username;
    private String email;
    private String password;

    public RegistrationApiUserModel(String nick, String email, String password) {
        this.username = nick;
        this.email = email;
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
