package Assignment;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

class ProductInventory {
    private HashMap<String, Integer> inventory = new HashMap<>();

    public void addProduct(String name, int quantity) {
        inventory.put(name, inventory.getOrDefault(name, 0) + quantity);
    }

    public void updateProduct(String name, int quantity) {
        if (inventory.containsKey(name)) {
            inventory.put(name, quantity);
        } else {
            System.out.println("Product not found.");
        }
    }

    public void removeProduct(String name) {
        inventory.remove(name);
    }

    public boolean isInStock(String name) {
        return inventory.containsKey(name) && inventory.get(name) > 0;
    }

    public void displayInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }
        System.out.println("Product\tQuantity");
        for (Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + "\t" + entry.getValue());
        }
    }
}

public class p4 {
    public static void main(String[] args) {
        ProductInventory inventory = new ProductInventory();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println(
                    "\n1. Add Product\n2. Update Product\n3. Remove Product\n4. Check Stock\n5. Display Inventory\n6. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter product name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter quantity: ");
                    int qty = sc.nextInt();
                    inventory.addProduct(name, qty);
                    break;
                case 2:
                    System.out.print("Enter product name: ");
                    String upName = sc.nextLine();
                    System.out.print("Enter new quantity: ");
                    int upQty = sc.nextInt();
                    inventory.updateProduct(upName, upQty);
                    break;
                case 3:
                    System.out.print("Enter product name to remove: ");
                    String remName = sc.nextLine();
                    inventory.removeProduct(remName);
                    break;
                case 4:
                    System.out.print("Enter product name to check: ");
                    String checkName = sc.nextLine();
                    if (inventory.isInStock(checkName)) {
                        System.out.println(checkName + " is in stock.");
                    } else {
                        System.out.println(checkName + " is not in stock.");
                    }
                    break;
                case 5:
                    inventory.displayInventory();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
