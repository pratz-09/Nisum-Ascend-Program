package Assignment;

class StaticDemo {
    static int a = initializeA();
    static {
        System.out.println("Static block executed");
    }
    static int b = initializeB();

    static int initializeA() {
        System.out.println("Static variable a initialized");
        return 10;
    }

    static int initializeB() {
        System.out.println("Static variable b initialized");
        return 20;
    }
}

public class p7 {
    public static void main(String[] args) {
        System.out.println("Main method started");
        System.out.println("a = " + StaticDemo.a);
        System.out.println("b = " + StaticDemo.b);
    }
}
