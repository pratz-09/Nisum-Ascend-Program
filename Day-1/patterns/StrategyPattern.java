interface Strategy {
    int execute(int a, int b);
}

class Add implements Strategy {
    public int execute(int a, int b) {
        return a + b;
    }
}

class Subtract implements Strategy {
    public int execute(int a, int b) {
        return a - b;
    }
}

class Context {
    private Strategy strategy;

    Context(Strategy s) {
        strategy = s;
    }

    int execute(int a, int b) {
        return strategy.execute(a, b);
    }
}

public class StrategyPattern {
    public static void main(String[] args) {
        Context c = new Context(new Add());
        System.out.println(c.execute(5, 3));
        c = new Context(new Subtract());
        System.out.println(c.execute(5, 3));
    }
}