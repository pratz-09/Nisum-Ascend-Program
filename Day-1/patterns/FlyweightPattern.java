package patterns;

import java.util.*;

class Circle {
    private String color;

    Circle(String color) {
        this.color = color;
    }

    public void draw() {
        System.out.println("Circle color: " + color);
    }
}

class CircleFactory {
    private static final Map<String, Circle> map = new HashMap<>();

    public static Circle getCircle(String color) {
        map.putIfAbsent(color, new Circle(color));
        return map.get(color);
    }
}

public class FlyweightPattern {
    public static void main(String[] args) {
        Circle c1 = CircleFactory.getCircle("Red");
        Circle c2 = CircleFactory.getCircle("Red");
        System.out.println(c1 == c2); // true
    }
}