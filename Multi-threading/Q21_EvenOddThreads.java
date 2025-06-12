
class EvenThread extends Thread {
    public void run() {
        for (int i = 2; i <= 10; i += 2) {
            System.out.println("Even: " + i);
            try { Thread.sleep(100); } catch (InterruptedException e) {}
        }
    }
}

class OddThread extends Thread {
    public void run() {
        for (int i = 1; i < 10; i += 2) {
            System.out.println("Odd: " + i);
            try { Thread.sleep(100); } catch (InterruptedException e) {}
        }
    }
}

public class Q21_EvenOddThreads {
    public static void main(String[] args) {
        new EvenThread().start();
        new OddThread().start();
    }
}
