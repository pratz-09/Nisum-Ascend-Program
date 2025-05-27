package classwork;

import java.util.List;
import java.util.stream.Collectors;

public class streams10 {
    public static void main(String[] args) {
        List<List<String>> nestedList = List.of(
                List.of("a", "b"),
                List.of("c", "d"),
                List.of("e", "f"));
        List<String> flatList = nestedList.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
        System.out.println(flatList); // Output: [a, b, c, d, e, f]
    }
}
