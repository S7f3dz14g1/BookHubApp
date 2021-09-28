package szwedo.bookhubapp.models;

public class EditUserModel {

    private final String email;
    private final String newPassword;
    private final String oldPassword;
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String phone;

    public EditUserModel(String email, String newPassword, String oldPassword, String firstName, String lastName, String address, String phone) {
        this.email = email;
        this.newPassword = newPassword;
        this.oldPassword = oldPassword;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }
}
