package Assignment;

class User {
    private static User instance;
    public String name;
    public String email;

    private User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public static User getInstance(String name, String email) {
        if (instance == null) {
            instance = new User(name, email);
        }
        return instance;
    }

    public void display() {
        System.out.println("Name: " + name + ", Email: " + email);
    }
}

public class p18 {
    public static void main(String[] args) {
        User user = User.getInstance("Bob", "bob@example.com");
        user.display();
    }
}
