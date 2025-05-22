package patterns;

class Mediator {
    private Colleague c1, c2;

    void setColleagues(Colleague c1, Colleague c2) {
        this.c1 = c1;
        this.c2 = c2;
    }

    void send(String msg, Colleague sender) {
        if (sender == c1)
            c2.notify(msg);
        else
            c1.notify(msg);
    }
}

abstract class Colleague {
    protected Mediator mediator;

    Colleague(Mediator m) {
        mediator = m;
    }

    abstract void notify(String msg);
}

class ConcreteColleague extends Colleague {
    ConcreteColleague(Mediator m) {
        super(m);
    }

    void notify(String msg) {
        System.out.println("Received: " + msg);
    }
}

public class MediatorPattern {
    public static void main(String[] args) {
        Mediator m = new Mediator();
        ConcreteColleague c1 = new ConcreteColleague(m);
        ConcreteColleague c2 = new ConcreteColleague(m);
        m.setColleagues(c1, c2);
        m.send("Hello", c1);
    }
}