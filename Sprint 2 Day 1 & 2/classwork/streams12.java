package classwork;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Arrays;

public class streams12 {
    public static void main(String[] args) {
        List<String> sentences = List.of("Java is fun", "Streams are powerful", "Java is powerful");

        Map<String, Long> wordFrequency = sentences.stream()
                .flatMap(sentence -> Arrays.stream(sentence.split("\\s+")))
                .map(word -> word.toLowerCase())
                .collect(Collectors.groupingBy(word -> word, Collectors.counting()));

        System.out.println(wordFrequency);
        // Example output: {java=2, is=2, fun=1, streams=1, are=1, powerful=2}
    }
}
