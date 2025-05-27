import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class p20 {
    public static void main(String[] args) {
        Predicate<Integer> by2 = n -> n % 2 == 0;
        Predicate<Integer> by3 = n -> n % 3 == 0;
        List<Integer> nums = Arrays.asList(2, 3, 4, 6, 9, 12);
        nums.stream().filter(by2.and(by3)).forEach(System.out::println); // both
        nums.stream().filter(by2.or(by3)).forEach(System.out::println); // either
    }
}
