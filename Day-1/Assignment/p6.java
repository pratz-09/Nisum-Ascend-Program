package Assignment;

class Parent {
    static void showMessage() {
        System.out.println("Static method in Parent");
    }
}

class Child extends Parent {
    void display() {
        // Accessing static method from parent class
        Parent.showMessage();
    }
}

public class p6 {
    public static void main(String[] args) {
        Child c = new Child();
        c.display();
    }
}
