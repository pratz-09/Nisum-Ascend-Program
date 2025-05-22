package Assignment;

public class P13_StaticBlockVsMethod {
    static {
        System.out.println("Static block executed");
    }

    public static void staticMethod() {
        System.out.println("Static method executed");
    }

    public static void main(String[] args) {
        staticMethod();
    }
}
