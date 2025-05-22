package patterns;

interface Coffee {
    String getDescription();
}

class SimpleCoffee implements Coffee {
    public String getDescription() {
        return "Simple Coffee";
    }
}

class MilkDecorator implements Coffee {
    private Coffee coffee;

    MilkDecorator(Coffee c) {
        coffee = c;
    }

    public String getDescription() {
        return coffee.getDescription() + ", Milk";
    }
}

public class DecoratorPattern {
    public static void main(String[] args) {
        Coffee c = new MilkDecorator(new SimpleCoffee());
        System.out.println(c.getDescription());
    }
}