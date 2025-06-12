
class PrimeSum extends Thread {
    int start, end;
    int sum = 0;

    PrimeSum(int start, int end) {
        this.start = start; this.end = end;
    }

    boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i*i <= n; i++) if (n % i == 0) return false;
        return true;
    }

    public void run() {
        for (int i = start; i <= end; i++)
            if (isPrime(i)) sum += i;
    }
}

public class Q28_PrimeSum {
    public static void main(String[] args) throws InterruptedException {
        PrimeSum t1 = new PrimeSum(1, 5000);
        PrimeSum t2 = new PrimeSum(5001, 10000);
        t1.start(); t2.start();
        t1.join(); t2.join();
        System.out.println("Total Prime Sum: " + (t1.sum + t2.sum));
    }
}
