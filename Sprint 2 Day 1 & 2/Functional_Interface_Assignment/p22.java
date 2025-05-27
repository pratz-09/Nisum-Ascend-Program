import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class p22 {
    public static void main(String[] args) {
        Consumer<String> print = System.out::println;
        List<String> items = Arrays.asList("a", "b", "c");
        items.forEach(print);
    }
}
