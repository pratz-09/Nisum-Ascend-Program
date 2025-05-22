package patterns;

interface GUIFactory {
    Button createButton();
}

interface Button {
    void paint();
}

class WinButton implements Button {
    public void paint() {
        System.out.println("Windows Button");
    }
}

class MacButton implements Button {
    public void paint() {
        System.out.println("Mac Button");
    }
}

class WinFactory implements GUIFactory {
    public Button createButton() {
        return new WinButton();
    }
}

class MacFactory implements GUIFactory {
    public Button createButton() {
        return new MacButton();
    }
}

public class AbstractFactoryPattern {
    public static void main(String[] args) {
        GUIFactory factory = new WinFactory();
        Button btn = factory.createButton();
        btn.paint();
    }
}