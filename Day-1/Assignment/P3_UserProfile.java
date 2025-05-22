import java.util.Scanner;

class UserProfile {
    String name;
    int age;
    String email;

    UserProfile(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    void printProfile() {
        System.out.println("User Profile:");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Email: " + email);
    }
}

public class P3_UserProfile {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Age: ");
        int age = sc.nextInt();
        sc.nextLine(); // consume newline
        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        UserProfile user = new UserProfile(name, age, email);
        user.printProfile();
    }
}