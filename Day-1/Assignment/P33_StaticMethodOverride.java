package Assignment;

class Parent {
    static void display() {
        System.out.println("Static method in Parent");
    }
}

class Child extends Parent {
    static void display() {
        System.out.println("Static method in Child");
    }
}

public class P33_StaticMethodOverride {
    public static void main(String[] args) {
        Parent.display(); // Parent's static method
        Child.display();  // Child's static method
        Parent p = new Child();
        p.display();      // Parent's static method (not overridden)
    }
}
