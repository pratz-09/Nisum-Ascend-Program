import java.util.function.Function;

public class p18 {
    public static void main(String[] args) {
        Function<String, String> toUpper = String::toUpperCase;
        System.out.println(toUpper.apply("hello"));
    }
}
