public class p11 {
    interface A {
        default void run() {
            System.out.println("A run");
        }
    }

    interface B {
        default void run() {
            System.out.println("B run");
        }
    }

    class MyClass implements A, B {
        @Override
        public void run() {
            A.super.run(); // or B.super.run();
        }
    }
}
