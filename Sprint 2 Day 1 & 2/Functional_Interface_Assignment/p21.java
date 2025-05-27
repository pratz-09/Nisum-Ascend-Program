import java.util.function.Supplier;

public class p21 {
    public static void main(String[] args) {
        Supplier<Double> random = Math::random;
        for (int i = 0; i < 5; i++)
            System.out.println(random.get());
    }
}
