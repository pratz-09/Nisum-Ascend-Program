package Assignment;

public class P12_StaticMethodInstanceVar {
    int x = 10;

    public static void show() {
        // System.out.println(x); // Error: Cannot make a static reference to the non-static field x
        System.out.println("Static methods cannot access instance variables directly.");
    }

    public static void main(String[] args) {
        show();
    }
}
