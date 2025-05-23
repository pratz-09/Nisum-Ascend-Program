package Assignment;

import java.util.*;
import java.util.Map.Entry;

class Event {
    String title, time, description;

    Event(String title, String time, String desc) {
        this.title = title;
        this.time = time;
        this.description = desc;
    }

    public String toString() {
        return time + " - " + title + ": " + description;
    }
}

public class p9 {
    public static void main(String[] args) {
        TreeMap<String, List<Event>> calendar = new TreeMap<>();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Add Event\n2. Remove Event\n3. View Events by Date\n4. List All Events\n5. Exit");
            System.out.print("Choose an option: ");
            int ch = sc.nextInt();
            sc.nextLine();
            if (ch == 1) {
                System.out.print("Date (YYYY-MM-DD): ");
                String date = sc.nextLine();
                System.out.print("Title: ");
                String title = sc.nextLine();
                System.out.print("Time: ");
                String time = sc.nextLine();
                System.out.print("Description: ");
                String desc = sc.nextLine();
                calendar.putIfAbsent(date, new ArrayList<>());
                calendar.get(date).add(new Event(title, time, desc));
            } else if (ch == 2) {
                System.out.print("Date (YYYY-MM-DD): ");
                String date = sc.nextLine();
                if (calendar.containsKey(date)) {
                    System.out.print("Title to remove: ");
                    String title = sc.nextLine();
                    calendar.get(date).removeIf(e -> e.title.equalsIgnoreCase(title));
                }
            } else if (ch == 3) {
                System.out.print("Date (YYYY-MM-DD): ");
                String date = sc.nextLine();
                if (calendar.containsKey(date)) {
                    for (Event e : calendar.get(date))
                        System.out.println(e);
                } else {
                    System.out.println("No events on this date.");
                }
            } else if (ch == 4) {
                for (Entry<String, List<Event>> entry : calendar.entrySet()) {
                    System.out.println(entry.getKey() + ":");
                    for (Event e : entry.getValue())
                        System.out.println("  " + e);
                }
            } else if (ch == 5) {
                break;
            } else {
                System.out.println("Invalid option.");
            }
        }
        sc.close();
    }
}
