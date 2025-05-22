package Assignment;

class Sample {
    public void test() {}
    public int add(int a, int b) { return a + b; }
}

public class P34_GetClassMetadata {
    public static void main(String[] args) {
        Sample obj = new Sample();
        Class<?> cls = obj.getClass();

        System.out.println("Class Name: " + cls.getName());
        System.out.println("Simple Name: " + cls.getSimpleName());
        System.out.println("Is Interface: " + cls.isInterface());
        System.out.println("Methods:");
        for (var method : cls.getDeclaredMethods()) {
            System.out.println(" - " + method.getName());
        }
    }
}
