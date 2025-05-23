package Assignment;

import java.util.HashSet;
import java.util.Scanner;

public class p3 {
    public static void main(String[] args) {
        HashSet<String> emails = new HashSet<>();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter email addresses (type 'done' to finish):");
        while (true) {
            String email = sc.nextLine().trim();
            if (email.equalsIgnoreCase("done")) break;
            emails.add(email);
        }

        System.out.println("\nUnique email addresses:");
        for (String email : emails) {
            System.out.println(email);
        }
        sc.close();
    }
}
