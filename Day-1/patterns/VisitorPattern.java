interface Visitor {
    void visit(Element e);
}

interface Element {
    void accept(Visitor v);
}

class ConcreteElement implements Element {
    public void accept(Visitor v) {
        v.visit(this);
    }
}

class ConcreteVisitor implements Visitor {
    public void visit(Element e) {
        System.out.println("Visited element");
    }
}

public class VisitorPattern {
    public static void main(String[] args) {
        Element e = new ConcreteElement();
        Visitor v = new ConcreteVisitor();
        e.accept(v);
    }
}