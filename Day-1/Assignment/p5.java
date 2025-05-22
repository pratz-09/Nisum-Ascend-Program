package Assignment;

class UserDetails {
    public String name;
    public int id;
    public String email;
    private String creditCard;
    private double creditCardBalance;
    private String password;

    public UserDetails(String name, int id, String email, String password, String creditCard,
            double creditCardBalance) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.password = password;
        this.creditCard = creditCard;
        this.creditCardBalance = creditCardBalance;
    }

    // Optionally, getters for private fields if needed (not public)
    protected String getCreditCard() {
        return creditCard;
    }

    protected double getCreditCardBalance() {
        return creditCardBalance;
    }
}

public class p5 {
    public static void main(String[] args) {
        UserDetails user = new UserDetails("Alice", 101, "alice@example.com", "pass123", "1234-5678-9012-3456", 5000.0);
        System.out.println("Name: " + user.name);
        System.out.println("ID: " + user.id);
        System.out.println("Email: " + user.email);
        // Cannot access creditCard or creditCardBalance directly here
    }
}
