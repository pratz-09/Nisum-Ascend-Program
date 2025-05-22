package Assignment;

class Base {
    public final void show() {
        System.out.println("Final method in Base");
    }
}

class Derived extends Base {
    // Cannot override final method
    // public void show() { ... } // Compile-time error
    public void display() {
        System.out.println("Derived class display");
    }
}

public class P29_FinalMethod {
    public static void main(String[] args) {
        Derived d = new Derived();
        d.show();
        d.display();
    }
}
