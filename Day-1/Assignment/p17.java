package Assignment;

class Base {
    public int pubVar = 1;
    protected int protVar = 2;
    int defVar = 3;
    private int privVar = 4;

    static {
        System.out.println("Static block in Base");
    }

    public void show() {
        System.out.println("Base class method");
    }
}

class Derived extends Base {
    @Override
    public void show() {
        System.out.println("Derived class method");
    }
}

public class p17 {
    static {
        System.out.println("Static block in Main class");
    }

    public static void main(String[] args) {
        Derived d = new Derived();
        d.show();
        System.out.println("Public: " + d.pubVar);
        System.out.println("Protected: " + d.protVar);
        System.out.println("Default: " + d.defVar);
        // System.out.println("Private: " + d.privVar); // Not accessible
    }
}
