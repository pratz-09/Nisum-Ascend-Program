
class Counter {
    int count = 0;
    void increment() { count++; }
}

public class Q22_NoSyncCounter {
    public static void main(String[] args) throws InterruptedException {
        Counter c = new Counter();

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) c.increment();
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start(); t2.start();
        t1.join(); t2.join();

        System.out.println("Final count: " + c.count);
    }
}
