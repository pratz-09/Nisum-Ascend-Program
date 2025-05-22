package Assignment;

public class P45_RemoveMultipleSpaces {
    public static void main(String[] args) {
        String str = "Java   is   awesome";
        String result = str.replaceAll("\\s+", " ");
        System.out.println(result);
    }
}
