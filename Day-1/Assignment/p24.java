package Assignment;

interface MyInterface {
    static void staticMethod() {
        System.out.println("Static method in interface");
    }
}

public class p24 {
    public static void main(String[] args) {
        MyInterface.staticMethod();
    }
}
