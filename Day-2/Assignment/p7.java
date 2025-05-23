package Assignment;

import java.util.*;
import java.util.Map.Entry;

public class p7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter text:");
        String text = sc.nextLine().toLowerCase().replaceAll("[^a-z0-9 ]", " ");
        String[] words = text.split("\\s+");
        HashMap<String, Integer> freq = new HashMap<>();
        for (String w : words) {
            if (!w.isEmpty())
                freq.put(w, freq.getOrDefault(w, 0) + 1);
        }
        List<Map.Entry<String, Integer>> list = new ArrayList<>(freq.entrySet());
        list.sort((a, b) -> b.getValue() - a.getValue());
        System.out.println("Word\tFrequency");
        for (Entry<String, Integer> e : list)
            System.out.println(e.getKey() + "\t" + e.getValue());
        sc.close();
    }
}
