package Assignment;

public class P46_SplitWithDelimiter {
    public static void main(String[] args) {
        String str = "apple,banana,orange";
        String[] parts = str.split(",");
        for (String part : parts) {
            System.out.println(part);
        }
    }
}
