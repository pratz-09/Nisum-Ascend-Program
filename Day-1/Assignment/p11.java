package Assignment;

import java.util.regex.*;

public class p11 {
    public static boolean isValidEmail(String username) {
        String emailRegex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        return Pattern.matches(emailRegex, username);
    }

    public static boolean isValidPassword(String password) {
        boolean hasLetter = password.matches(".*[A-Za-z].*");
        boolean hasDigit = password.matches(".*\\d.*");
        boolean hasSpecial = password.matches(".*[^A-Za-z0-9].*");
        return hasLetter && hasDigit && hasSpecial;
    }

    public static void main(String[] args) {
        String username = "test@example.com";
        String password = "Pass@123";
        System.out.println("Email valid: " + isValidEmail(username));
        System.out.println("Password valid: " + isValidPassword(password));
    }
}
