package Assignment;

import java.util.Scanner;
import java.util.Stack;

public class p5 {
    public static void main(String[] args) {
        Stack<String> history = new Stack<>();
        Scanner sc = new Scanner(System.in);
        String current = null;

        while (true) {
            System.out.println("\n1. Visit Website\n2. Back\n3. Current Page\n4. View History\n5. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter URL: ");
                    String url = sc.nextLine();
                    if (current != null) history.push(current);
                    current = url;
                    System.out.println("Visited: " + current);
                    break;
                case 2:
                    if (!history.isEmpty()) {
                        current = history.pop();
                        System.out.println("Went back to: " + current);
                    } else {
                        System.out.println("No previous page.");
                    }
                    break;
                case 3:
                    System.out.println("Current page: " + (current != null ? current : "None"));
                    break;
                case 4:
                    System.out.println("History:");
                    for (String h : history) System.out.println(h);
                    break;
                case 5:
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
