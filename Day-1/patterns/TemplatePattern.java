package patterns;

abstract class Game {
    abstract void start();

    abstract void end();

    public final void play() {
        start();
        end();
    }
}

class Football extends Game {
    void start() {
        System.out.println("Football started");
    }

    void end() {
        System.out.println("Football ended");
    }
}

public class TemplatePattern {
    public static void main(String[] args) {
        Game g = new Football();
        g.play();
    }
}