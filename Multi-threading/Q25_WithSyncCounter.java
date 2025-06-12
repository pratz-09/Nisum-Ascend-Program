
class SyncCounter {
    int count = 0;

    synchronized void increment() {
        count++;
    }
}

public class Q25_WithSyncCounter {
    public static void main(String[] args) throws InterruptedException {
        SyncCounter c = new SyncCounter();

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++)
                c.increment();
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Final count: " + c.count);
    }
}
