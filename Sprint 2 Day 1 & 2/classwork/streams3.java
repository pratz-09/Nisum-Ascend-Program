package classwork;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Arrays;

public class streams3 {
    public static void main(String[] args) {
        List<String> names = List.of("Alice", "bob", "charlie", "david");
        Optional<String> firstC = names.stream()
                .filter(name -> name.startsWith("C") || name.startsWith("c"))
                .findFirst();

        firstC.ifPresent(System.out::println); // Output: charlie
    }
}
