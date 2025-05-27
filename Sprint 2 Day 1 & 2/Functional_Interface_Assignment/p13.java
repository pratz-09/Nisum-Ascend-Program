public class p13 {

}

interface Demo {
    default void hello() {
        System.out.println("Hello from Demo");
    }
}

class Test implements Demo {
    @Override
    public void hello() {
        Demo.super.hello();
    }
}
