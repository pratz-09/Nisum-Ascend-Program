import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class p10 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Java", "C", "Python");
        list.sort(Comparator.comparingInt(String::length));

        // Print the sorted list
        for (String language : list) {
            System.out.println(language);
        }
    }
}
