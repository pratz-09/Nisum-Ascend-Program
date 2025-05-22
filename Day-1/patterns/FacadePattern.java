package patterns;

class CPU {
    void start() {
        System.out.println("CPU started");
    }
}

class Memory {
    void load() {
        System.out.println("Memory loaded");
    }
}

class ComputerFacade {
    private CPU cpu = new CPU();
    private Memory mem = new Memory();

    void start() {
        cpu.start();
        mem.load();
    }
}

public class FacadePattern {
    public static void main(String[] args) {
        ComputerFacade comp = new ComputerFacade();
        comp.start();
    }
}