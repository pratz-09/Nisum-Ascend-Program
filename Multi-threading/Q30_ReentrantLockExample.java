
import java.util.concurrent.locks.ReentrantLock;

class SharedData {
    int value = 0;
    ReentrantLock lock = new ReentrantLock();

    void increment() {
        lock.lock();
        try {
            value++;
        } finally {
            lock.unlock();
        }
    }
}

public class Q30_ReentrantLockExample {
    public static void main(String[] args) throws InterruptedException {
        SharedData data = new SharedData();

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++)
                data.increment();
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Final value: " + data.value);
    }
}
