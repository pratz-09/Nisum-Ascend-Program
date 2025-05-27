package classwork;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Arrays;

public class streams9 {
    public static void main(String[] args) {
        List<String> words = List.of("listen", "silent", "enlist", "rat", "tar", "art");
        Map<String, List<String>> anagrams = words.stream()
                .collect(Collectors.groupingBy(
                        word -> {
                            char[] chars = word.toCharArray();
                            Arrays.sort(chars);
                            return new String(chars);
                        }));
        System.out.println(anagrams);

    }
}
