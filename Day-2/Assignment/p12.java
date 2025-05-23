package Assignment;

import java.util.*;

public class p12 {
    public static Set<Integer> sortedUnique(int[] arr) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int n : arr) set.add(n);
        return set;
    }
    public static void main(String[] args) {
        int[] arr = {5, 3, 8, 3, 1, 5, 2};
        System.out.println(sortedUnique(arr));
    }
}
