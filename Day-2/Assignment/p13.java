package Assignment;

import java.util.*;

public class p13 {
    public static <T> List<T> commonElements(List<T> a, List<T> b) {
        Set<T> set = new HashSet<>(a);
        set.retainAll(b);
        return new ArrayList<>(set);
    }
    public static void main(String[] args) {
        List<Integer> l1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> l2 = Arrays.asList(3, 4, 5, 6, 7);
        System.out.println(commonElements(l1, l2));
    }
}
