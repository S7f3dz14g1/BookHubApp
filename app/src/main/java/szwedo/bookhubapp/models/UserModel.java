package szwedo.bookhubapp.models;

import java.util.UUID;

public class UserModel {

    private UUID id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private int maxBooks;
    private int points;
    private int warnings;

    public UserModel(UUID id, String username, String email, String firstName, String lastName, String address, String phone, int maxBooks, int points, int warnings) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.maxBooks = maxBooks;
        this.points = points;
        this.warnings = warnings;
    }

    public UserModel(String firstName, String lastName, String address, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
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

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public int getMaxBooks() {
        return maxBooks;
    }

    public int getPoints() {
        return points;
    }

    public int getWarnings() {
        return warnings;
    }
}
