package Assignment;

class SuperClass {
    void superMethod() {
        System.out.println("SuperClass method");
    }
}

class SubClass extends SuperClass {
    void subMethod() {
        System.out.println("SubClass method");
    }
}

public class p22 {
    public static void main(String[] args) {
        SuperClass obj = new SubClass();
        obj.superMethod();
        // obj.subMethod(); // Compile-time error: cannot find symbol
    }
}
