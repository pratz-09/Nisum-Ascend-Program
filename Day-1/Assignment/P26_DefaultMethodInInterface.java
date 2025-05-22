package Assignment;

interface DefaultDemo {
    default void show() {
        System.out.println("Default method in interface");
    }
}

class DemoImpl implements DefaultDemo {}

public class P26_DefaultMethodInInterface {
    public static void main(String[] args) {
        DemoImpl obj = new DemoImpl();
        obj.show();
    }
}
