package Assignment;

import java.util.*;

class Cart {
    String itemName;
    double itemValue;
    int itemId;

    Cart(String itemName, double itemValue, int itemId) {
        if (itemName == null || itemName.isEmpty())
            throw new IllegalArgumentException("Invalid item name");
        if (itemValue <= 0)
            throw new IllegalArgumentException("Invalid item value");
        if (itemId <= 0)
            throw new IllegalArgumentException("Invalid item id");
        this.itemName = itemName;
        this.itemValue = itemValue;
        this.itemId = itemId;
    }
}

public class p4 {
    public static void main(String[] args) {
        List<Cart> cartList = new ArrayList<>();
        cartList.add(new Cart("Book", 250.0, 1));
        cartList.add(new Cart("Pen", 20.0, 2));
        cartList.add(new Cart("Bag", 500.0, 3));

        int itemsCount = cartList.size();
        double orderTotal = 0;
        for (Cart c : cartList) {
            orderTotal += c.itemValue;
        }

        System.out.println("Order Summary:");
        System.out.println("Items Count: " + itemsCount);
        System.out.println("Order Total: " + orderTotal);
    }
}
