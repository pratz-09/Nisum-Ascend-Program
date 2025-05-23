package Assignment;

import java.util.ArrayList;
import java.util.Scanner;

class Item {
    String name;
    double price;
    int quantity;

    Item(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}

class ShoppingCart {
    private ArrayList<Item> cart = new ArrayList<>();

    public void addItem(String name, double price, int quantity) {
        cart.add(new Item(name, price, quantity));
    }

    public void removeItem(String name) {
        cart.removeIf(item -> item.name.equalsIgnoreCase(name));
    }

    public void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }
        System.out.println("Items in cart:");
        for (Item item : cart) {
            System.out.printf("%s - $%.2f x %d\n", item.name, item.price, item.quantity);
        }
    }

    public double calculateTotal() {
        double total = 0;
        for (Item item : cart) {
            total += item.price * item.quantity;
        }
        return total;
    }
}

public class p1 {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Add Item\n2. Remove Item\n3. View Cart\n4. Calculate Total\n5. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline
            switch (choice) {
                case 1:
                    System.out.print("Enter item name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter price: ");
                    double price = sc.nextDouble();
                    System.out.print("Enter quantity: ");
                    int qty = sc.nextInt();
                    cart.addItem(name, price, qty);
                    break;
                case 2:
                    System.out.print("Enter item name to remove: ");
                    String removeName = sc.nextLine();
                    cart.removeItem(removeName);
                    break;
                case 3:
                    cart.viewCart();
                    break;
                case 4:
                    System.out.printf("Total Price: $%.2f\n", cart.calculateTotal());
                    break;
                case 5:
                    System.out.println("Thank you for shopping!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}