package classwork;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class streams6 {
    public static void main(String[] args) {
        List<String> words = List.of("one", "two", "three", "four", "five");
        Map<Integer, List<String>> grouped = words.stream()
                .collect(Collectors.groupingBy(String::length));
        System.out.println(grouped);
        // Example output: {3=[one, two], 4=[four, five], 5=[three]}
    }
}
