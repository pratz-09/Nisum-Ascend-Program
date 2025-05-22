package Assignment;

final class Constants {
    public static final double PI = 3.14159;
    public void show() {
        System.out.println("Final class method");
    }
}

// class SubClass extends Constants {} // Error: cannot inherit from final class

public class P32_FinalClass {
    public static void main(String[] args) {
        Constants c = new Constants();
        c.show();
        System.out.println("PI: " + Constants.PI);
    }
}
