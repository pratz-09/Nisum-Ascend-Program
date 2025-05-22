package patterns;

class Prototype implements Cloneable {
    int x;

    Prototype(int x) {
        this.x = x;
    }

    public Prototype clone() throws CloneNotSupportedException {
        return (Prototype) super.clone();
    }
}

public class PrototypePattern {
    public static void main(String[] args) throws Exception {
        Prototype p1 = new Prototype(5);
        Prototype p2 = p1.clone();
        System.out.println(p1.x + " " + p2.x);
    }
}