import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class p7 {
    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);
        nums = new ArrayList<>(nums);
        nums.removeIf(n -> n % 2 == 0);
        System.out.println(nums);
    }
}