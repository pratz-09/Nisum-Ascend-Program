import java.util.Arrays;
import java.util.List;

public class p17 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Java", "C", "Python");
        list.sort((s1, s2) -> Integer.compare(s1.length(), s2.length()));

        // Print the sorted list
        list.forEach(System.out::println);
    }
}
