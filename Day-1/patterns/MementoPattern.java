package patterns;

class Memento {
    private String state;

    Memento(String s) {
        state = s;
    }

    String getState() {
        return state;
    }
}

class Originator {
    private String state;

    void setState(String s) {
        state = s;
    }

    Memento save() {
        return new Memento(state);
    }

    void restore(Memento m) {
        state = m.getState();
    }

    String getState() {
        return state;
    }
}

public class MementoPattern {
    public static void main(String[] args) {
        Originator o = new Originator();
        o.setState("A");
        Memento m = o.save();
        o.setState("B");
        o.restore(m);
        System.out.println(o.getState());
    }
}