package patterns;

interface Command {
    void execute();
}

class Light {
    void on() {
        System.out.println("Light ON");
    }
}

class LightOnCommand implements Command {
    private Light light;

    LightOnCommand(Light l) {
        light = l;
    }

    public void execute() {
        light.on();
    }
}

public class CommandPattern {
    public static void main(String[] args) {
        Light light = new Light();
        Command cmd = new LightOnCommand(light);
        cmd.execute();
    }
}