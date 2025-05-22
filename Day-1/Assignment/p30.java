package Assignment;

abstract class Shape {
    String color;

    Shape(String color) {
        this.color = color;
        System.out.println("Shape constructor called");
    }

    abstract double area();

    void displayColor() {
        System.out.println("Color: " + color);
    }
}

class Circle extends Shape {
    double radius;

    Circle(String color, double radius) {
        super(color);
        this.radius = radius;
    }

    double area() {
        return Math.PI * radius * radius;
    }
}

public class p30 {
    public static void main(String[] args) {
        Shape s = new Circle("Red", 5.0);
        s.displayColor();
        System.out.println("Area: " + s.area());
    }
}
