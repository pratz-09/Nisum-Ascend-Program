public class p16 {
    interface MathOperation {
        int operate(int a, int b);
    }

    MathOperation add = (a, b) -> a + b;
    MathOperation sub = (a, b) -> a - b;
    MathOperation mul = (a, b) -> a * b;
}
