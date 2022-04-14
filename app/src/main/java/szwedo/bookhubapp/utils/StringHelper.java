package szwedo.bookhubapp.utils;

public class StringHelper {

    public static boolean validateNickLogin(String nick) {
        return nick.length() >= 3;
    }

    public static boolean validatePasswordLogin(String password) {
        return password.length() >= 8;
    }

    public static boolean validatePasswordRegistration(String password) {
        return password.matches("^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@!#$%^&+=().]).{8,25}$");
    }

    public static boolean validateTwoPasswordRegistration(String password1, String password2) {
        return password1.equals(password2);
    }

    public static boolean validateLoginRegistration(String login) {
        return login.matches("^[a-zA-Z0-9]{3,12}+$");
    }

    public static boolean validateEmailRegistration(String email) {
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
    }

    public static boolean name(String name) {
        return name.matches("^[\\p{L}'][ \\p{L}'-]*[\\p{L}]{3,12}$");
    }

    public static boolean address(String address) {
        return address.matches("^[#.0-9\\p{L}\\s\\/,-]+$");
    }

    public static boolean phone(String phone) {
        return phone.matches("^[+]?[0-9]{9,11}$");
    }
}
