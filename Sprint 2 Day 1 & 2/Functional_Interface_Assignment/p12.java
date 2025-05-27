public class p12 {

}

interface X {
    default void show() {
        System.out.println("X show");
    }
}

class Y implements X {
    @Override
    public void show() {
        System.out.println("Y show");
        X.super.show();
    }
}
