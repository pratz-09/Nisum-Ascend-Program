package classwork;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class streams5 {
    public static void main(String[] args) {
        List<String> fruits = new ArrayList<>(List.of("apple", "banana", "cherry", "date"));
        fruits.sort(Collections.reverseOrder());
        System.out.println(fruits); // Output: [date, cherry, banana, apple]
    }
}
