package patterns;

abstract class Handler {
    protected Handler next;

    public void setNext(Handler n) {
        next = n;
    }

    public abstract void handle(int req);
}

class ConcreteHandler1 extends Handler {
    public void handle(int req) {
        if (req < 10)
            System.out.println("Handler1 handled " + req);
        else if (next != null)
            next.handle(req);
    }
}

class ConcreteHandler2 extends Handler {
    public void handle(int req) {
        if (req >= 10)
            System.out.println("Handler2 handled " + req);
        else if (next != null)
            next.handle(req);
    }
}

public class ChainOfResponsibilityPattern {
    public static void main(String[] args) {
        Handler h1 = new ConcreteHandler1();
        Handler h2 = new ConcreteHandler2();
        h1.setNext(h2);
        h1.handle(5);
        h1.handle(15);
    }
}