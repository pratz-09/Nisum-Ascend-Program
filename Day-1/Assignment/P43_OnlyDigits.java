package Assignment;

public class P43_OnlyDigits {
    public static void main(String[] args) {
        String str = "123456";
        boolean onlyDigits = str.matches("\\d+");
        System.out.println("Contains only digits: " + onlyDigits);
    }
}
