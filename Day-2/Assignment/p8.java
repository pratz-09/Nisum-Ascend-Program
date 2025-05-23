package Assignment;

import java.util.*;

class MenuItem {
    String name, description;
    double price;

    MenuItem(String name, String desc, double price) {
        this.name = name;
        this.description = desc;
        this.price = price;
    }

    public String toString() {
        return name + ": " + description + " ($" + price + ")";
    }
}

public class p8 {
    public static void main(String[] args) {
        LinkedHashMap<String, HashMap<String, MenuItem>> menu = new LinkedHashMap<>();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Add Item\n2. Remove Item\n3. Update Item\n4. Display Menu\n5. Exit");
            System.out.print("Choose an option: ");
            int ch = sc.nextInt();
            sc.nextLine();
            if (ch == 1) {
                System.out.print("Category: ");
                String cat = sc.nextLine();
                System.out.print("Name: ");
                String name = sc.nextLine();
                System.out.print("Description: ");
                String desc = sc.nextLine();
                System.out.print("Price: ");
                double price = sc.nextDouble();
                menu.putIfAbsent(cat, new HashMap<>());
                menu.get(cat).put(name, new MenuItem(name, desc, price));
            } else if (ch == 2) {
                System.out.print("Category: ");
                String cat = sc.nextLine();
                System.out.print("Name: ");
                String name = sc.nextLine();
                if (menu.containsKey(cat))
                    menu.get(cat).remove(name);
            } else if (ch == 3) {
                System.out.print("Category: ");
                String cat = sc.nextLine();
                System.out.print("Name: ");
                String name = sc.nextLine();
                if (menu.containsKey(cat) && menu.get(cat).containsKey(name)) {
                    System.out.print("New Description: ");
                    String desc = sc.nextLine();
                    System.out.print("New Price: ");
                    double price = sc.nextDouble();
                    menu.get(cat).put(name, new MenuItem(name, desc, price));
                }
            } else if (ch == 4) {
                for (String cat : menu.keySet()) {
                    System.out.println("\n" + cat + ":");
                    for (MenuItem item : menu.get(cat).values())
                        System.out.println("  " + item);
                }
            } else if (ch == 5) {
                break;
            } else {
                System.out.println("Invalid option.");
            }
        }
        sc.close();
    }
}
