
class Buffer {
    int data;
    boolean hasData = false;

    synchronized void produce(int value) throws InterruptedException {
        while (hasData) wait();
        data = value;
        hasData = true;
        System.out.println("Produced: " + value);
        notify();
    }

    synchronized void consume() throws InterruptedException {
        while (!hasData) wait();
        System.out.println("Consumed: " + data);
        hasData = false;
        notify();
    }
}

public class Q29_ProducerConsumer {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();

        Thread producer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                try { buffer.produce(i); Thread.sleep(100); } catch (InterruptedException e) {}
            }
        });

        Thread consumer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                try { buffer.consume(); Thread.sleep(150); } catch (InterruptedException e) {}
            }
        });

        producer.start();
        consumer.start();
    }
}
