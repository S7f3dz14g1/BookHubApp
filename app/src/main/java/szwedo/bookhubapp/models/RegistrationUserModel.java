package szwedo.bookhubapp.models;

public class RegistrationUserModel {

    private String nick;
    private String passwordFirst;
    private String passwordSecond;
    private String email;

    public RegistrationUserModel(String nick, String passwordFirst, String passwordSecond, String email) {
        this.nick = nick;
        this.passwordFirst = passwordFirst;
        this.passwordSecond = passwordSecond;
        this.email = email;
    }

    public RegistrationUserModel(String passwordFirst, String passwordSecond, String email) {
        this.passwordFirst = passwordFirst;
        this.passwordSecond = passwordSecond;
        this.email = email;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPasswordFirst() {
        return passwordFirst;
    }

    public void setPasswordFirst(String passwordFirst) {
        this.passwordFirst = passwordFirst;
    }

    public String getPasswordSecond() {
        return passwordSecond;
    }

    public void setPasswordSecond(String passwordSecond) {
        this.passwordSecond = passwordSecond;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
