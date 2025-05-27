import java.util.Arrays;
import java.util.List;

public class p19 {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Alex");
        names.stream().filter(s -> s.startsWith("A")).forEach(System.out::println);
    }
}
