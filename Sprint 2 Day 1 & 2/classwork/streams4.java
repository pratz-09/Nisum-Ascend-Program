package classwork;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Arrays;

public class streams4 {
    public static void main(String[] args) {
        java.util.List<Integer> numbers = java.util.List.of(1, 2, 3, 4);
        int sumOfSquares = numbers.stream()
                .map(n -> n * n)
                .reduce(0, Integer::sum);
        System.out.println(sumOfSquares); // Output: 30
    }
}
