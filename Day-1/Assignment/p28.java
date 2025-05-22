package Assignment;

interface Parent {
    void parentMethod();
}

interface Child extends Parent {
    void childMethod();
}

class Impl implements Child {
    public void parentMethod() {
        System.out.println("Parent method");
    }

    public void childMethod() {
        System.out.println("Child method");
    }
}

public class p28 {
    public static void main(String[] args) {
        Impl obj = new Impl();
        obj.parentMethod();
        obj.childMethod();
    }
}
