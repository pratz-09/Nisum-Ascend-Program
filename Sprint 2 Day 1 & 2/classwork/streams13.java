package classwork;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class streams13 {
    public static void main(String[] args) {
        List<Integer> nums = List.of(1, 2, 3, 2, 3, 4, 5, 3);

        Map<Integer, Long> duplicates = nums.stream()
                .collect(Collectors.groupingBy(n -> n, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        System.out.println(duplicates); // Output: {2=2, 3=3}
    }
}
