package Assignment;

public class P19_MethodOverloading {
    public void show(int a) {
        System.out.println("int: " + a);
    }

    public void show(String s) {
        System.out.println("String: " + s);
    }

    public void show(int a, String s) {
        System.out.println("int and String: " + a + ", " + s);
    }

    public static void main(String[] args) {
        P19_MethodOverloading obj = new P19_MethodOverloading();
        obj.show(5);
        obj.show("Hello");
        obj.show(10, "World");
    }
}
