package Assignment;

class Outer {
    interface Nested {
        void display();
    }
}

class ImplementNested implements Outer.Nested {
    public void display() {
        System.out.println("Nested interface implemented");
    }
}

public class P25_NestedInterface {
    public static void main(String[] args) {
        Outer.Nested obj = new ImplementNested();
        obj.display();
    }
}
