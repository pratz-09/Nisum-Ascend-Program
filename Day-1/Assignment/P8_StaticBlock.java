package Assignment;

public class P8_StaticBlock {
    static int value;

    static {
        System.out.println("Static block executed");
        value = 100;
    }

    public static void main(String[] args) {
        System.out.println("Value: " + value);
    }
}
