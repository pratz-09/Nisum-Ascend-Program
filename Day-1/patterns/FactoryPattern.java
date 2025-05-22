package patterns;

interface Animal {
    void speak();
}

class Dog implements Animal {
    public void speak() {
        System.out.println("Woof");
    }
}

class Cat implements Animal {
    public void speak() {
        System.out.println("Meow");
    }
}

class AnimalFactory {
    public static Animal getAnimal(String type) {
        if ("dog".equalsIgnoreCase(type))
            return new Dog();
        else if ("cat".equalsIgnoreCase(type))
            return new Cat();
        return null;
    }
}

public class FactoryPattern {
    public static void main(String[] args) {
        Animal a = AnimalFactory.getAnimal("dog");
        a.speak();
    }
}