package Assignment;

import java.util.*;

public class p41 {
    public static void main(String[] args) {
        String str = "Super Man Bat Man Spider Man";
        Map<Character, Integer> countMap = new HashMap<>();
        for (char c : str.toCharArray()) {
            if (c != ' ') {
                countMap.put(c, countMap.getOrDefault(c, 0) + 1);
            }
        }
        for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
