
import java.util.concurrent.locks.ReentrantLock;

class Account {
    int balance;
    ReentrantLock lock = new ReentrantLock();

    Account(int bal) { this.balance = bal; }

    void transfer(Account to, int amount) {
        if (this.lock.tryLock()) {
            try {
                if (to.lock.tryLock()) {
                    try {
                        if (balance >= amount) {
                            this.balance -= amount;
                            to.balance += amount;
                            System.out.println("Transferred " + amount);
                        }
                    } finally {
                        to.lock.unlock();
                    }
                }
            } finally {
                this.lock.unlock();
            }
        }
    }
}

public class Q24_AccountTransfer {
    public static void main(String[] args) {
        Account a1 = new Account(1000);
        Account a2 = new Account(1000);

        Runnable r1 = () -> a1.transfer(a2, 100);
        Runnable r2 = () -> a2.transfer(a1, 150);

        new Thread(r1).start();
        new Thread(r2).start();
    }
}
