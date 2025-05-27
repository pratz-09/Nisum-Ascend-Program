package classwork;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class streams7 {
    public static void main(String[] args) {
        java.util.List<Integer> numbers = java.util.List.of(10, 20, 5, 80, 30);
        int max = numbers.stream()
                .max(Integer::compareTo)
                .orElseThrow();
        System.out.println(max); // Output: 80
    }
}
