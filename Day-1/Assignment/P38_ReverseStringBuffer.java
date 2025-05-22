package Assignment;

public class P38_ReverseStringBuffer {
    public static void main(String[] args) {
        String str = "Hello World";
        StringBuffer sb = new StringBuffer(str);
        sb.reverse();
        System.out.println("Reversed String: " + sb.toString());
    }
}
