import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class p23 {
    public static void main(String[] args) {
        Consumer<Integer> doubler = n -> System.out.println(n * 2);
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        numbers.forEach(doubler);
    }
}
