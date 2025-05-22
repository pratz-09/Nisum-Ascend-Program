package Assignment;

public class P10_StaticMethodOverloading {
    public static void display(int a) {
        System.out.println("Display with int: " + a);
    }

    public static void display(String s) {
        System.out.println("Display with String: " + s);
    }

    public static void display(int a, String s) {
        System.out.println("Display with int and String: " + a + ", " + s);
    }

    public static void main(String[] args) {
        display(10);
        display("Hello");
        display(20, "World");
    }
}
