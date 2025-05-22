package patterns;

interface Target {
    void request();
}

class Adaptee {
    void specificRequest() {
        System.out.println("Specific request");
    }
}

class Adapter implements Target {
    private Adaptee adaptee = new Adaptee();

    public void request() {
        adaptee.specificRequest();
    }
}

public class AdapterPattern {
    public static void main(String[] args) {
        Target t = new Adapter();
        t.request();
    }
}