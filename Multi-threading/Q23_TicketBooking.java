
class TicketBooking {
    int tickets = 5;

    synchronized void bookTicket(String name, int qty) {
        if (qty <= tickets) {
            System.out.println(name + " booked " + qty + " ticket(s).");
            tickets -= qty;
        } else {
            System.out.println(name + " tried to book " + qty + " ticket(s) but not enough available.");
        }
    }
}

public class Q23_TicketBooking {
    public static void main(String[] args) {
        TicketBooking booking = new TicketBooking();

        Runnable r1 = () -> booking.bookTicket("Alice", 3);
        Runnable r2 = () -> booking.bookTicket("Bob", 4);

        new Thread(r1).start();
        new Thread(r2).start();
    }
}
