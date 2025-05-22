package patterns;

import java.util.*;

interface Component {
    void show();
}

class Leaf implements Component {
    String name;

    Leaf(String n) {
        name = n;
    }

    public void show() {
        System.out.println(name);
    }
}

class Composite implements Component {
    List<Component> children = new ArrayList<>();

    public void add(Component c) {
        children.add(c);
    }

    public void show() {
        for (Component c : children)
            c.show();
    }
}

public class CompositePattern {
    public static void main(String[] args) {
        Composite root = new Composite();
        root.add(new Leaf("Leaf1"));
        root.add(new Leaf("Leaf2"));
        root.show();
    }
}