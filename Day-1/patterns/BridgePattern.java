package patterns;

interface DrawAPI {
    void drawCircle(int r, int x, int y);
}

class RedCircle implements DrawAPI {
    public void drawCircle(int r, int x, int y) {
        System.out.println("Red circle");
    }
}

class GreenCircle implements DrawAPI {
    public void drawCircle(int r, int x, int y) {
        System.out.println("Green circle");
    }
}

abstract class Shape {
    protected DrawAPI drawAPI;

    Shape(DrawAPI d) {
        this.drawAPI = d;
    }

    abstract void draw();
}

class Circle extends Shape {
    Circle(DrawAPI d) {
        super(d);
    }

    void draw() {
        drawAPI.drawCircle(1, 2, 3);
    }
}

public class BridgePattern {
    public static void main(String[] args) {
        Shape s = new Circle(new RedCircle());
        s.draw();
    }
}