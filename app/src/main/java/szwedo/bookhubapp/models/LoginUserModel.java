package szwedo.bookhubapp.models;

public class LoginUserModel {

    private String nick;
    private String email;
    private String password;

    public LoginUserModel(String nick, String email, String password) {
        this.nick = nick;
        this.email = email;
        this.password = password;
    }

    public LoginUserModel() {

    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
