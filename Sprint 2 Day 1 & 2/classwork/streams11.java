package classwork;

import java.util.List;
import java.util.stream.Collectors;

public class streams11 {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        List<String> evenOrOdd = numbers.stream()
                .map(n -> n % 2 == 0 ? "even" : "odd")
                .collect(Collectors.toList());
        System.out.println(evenOrOdd); // Output: [odd, even, odd, even, odd]
    }
}
