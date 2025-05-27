package classwork;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class streams8 {
    public static void main(String[] args) {
        java.util.List<String> names = java.util.List.of("alice", "arnold", "bob", "charlie", "andrew");
        long count = names.stream()
                .filter(name -> name.startsWith("A") || name.startsWith("a"))
                .count();
        System.out.println(count); // Output: 3
    }
}
