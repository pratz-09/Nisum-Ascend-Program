package Assignment;

@FunctionalInterface
interface MyFunc {
    void execute();
}

public class P27_FunctionalInterface {
    public static void main(String[] args) {
        MyFunc func = () -> System.out.println("Functional interface executed");
        func.execute();
    }
}
