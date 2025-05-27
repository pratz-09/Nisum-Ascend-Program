package classwork;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class streams14 {
    public static void main(String[] args) {
        Map<String, List<List<Integer>>> map = Map.of(
                "a", List.of(List.of(1, 2), List.of(3)),
                "b", List.of(List.of(4), List.of(5, 6)));

        List<Integer> flatList = map.values().stream()
                .flatMap(List::stream)
                .flatMap(List::stream)
                .collect(Collectors.toList());

        System.out.println(flatList); // Output: [1, 2, 3, 4, 5, 6]
    }
}
