package patterns;

interface Expression {
    boolean interpret(String context);
}

class TerminalExpression implements Expression {
    private String data;

    TerminalExpression(String d) {
        data = d;
    }

    public boolean interpret(String context) {
        return context.contains(data);
    }
}

public class InterpreterPattern {
    public static void main(String[] args) {
        Expression expr = new TerminalExpression("Java");
        System.out.println(expr.interpret("Java is cool"));
    }
}