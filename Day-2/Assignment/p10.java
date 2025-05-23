package Assignment;

import java.util.*;

class Movie {
    String title, director, genre;
    int year;
    double rating;
    Movie(String t, String d, String g, int y, double r) {
        title = t; director = d; genre = g; year = y; rating = r;
    }
    public String toString() {
        return String.format("%-15s %-10s %-10s %-5d %-4.1f", title, director, genre, year, rating);
    }
}

public class p10 {
    public static void main(String[] args) {
        ArrayList<Movie> movies = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Add Movie\n2. Remove Movie\n3. Filter\n4. Sort\n5. Display\n6. Exit");
            System.out.print("Choose an option: ");
            int ch = sc.nextInt(); sc.nextLine();
            if (ch == 1) {
                System.out.print("Title: "); String t = sc.nextLine();
                System.out.print("Director: "); String d = sc.nextLine();
                System.out.print("Genre: "); String g = sc.nextLine();
                System.out.print("Year: "); int y = sc.nextInt();
                System.out.print("Rating: "); double r = sc.nextDouble();
                movies.add(new Movie(t, d, g, y, r));
            } else if (ch == 2) {
                System.out.print("Title to remove: "); String t = sc.nextLine();
                movies.removeIf(m -> m.title.equalsIgnoreCase(t));
            } else if (ch == 3) {
                System.out.println("Filter by: 1.Genre 2.Director 3.Year");
                int f = sc.nextInt(); sc.nextLine();
                if (f == 1) {
                    System.out.print("Genre: "); String g = sc.nextLine();
                    movies.stream().filter(m -> m.genre.equalsIgnoreCase(g)).forEach(System.out::println);
                } else if (f == 2) {
                    System.out.print("Director: "); String d = sc.nextLine();
                    movies.stream().filter(m -> m.director.equalsIgnoreCase(d)).forEach(System.out::println);
                } else if (f == 3) {
                    System.out.print("Year: "); int y = sc.nextInt();
                    movies.stream().filter(m -> m.year == y).forEach(System.out::println);
                }
            } else if (ch == 4) {
                System.out.println("Sort by: 1.Title 2.Year 3.Rating");
                int s = sc.nextInt();
                if (s == 1) movies.sort(Comparator.comparing(m -> m.title));
                else if (s == 2) movies.sort(Comparator.comparingInt(m -> m.year));
                else if (s == 3) movies.sort((a, b) -> Double.compare(b.rating, a.rating));
            } else if (ch == 5) {
                System.out.printf("%-15s %-10s %-10s %-5s %-5s\n", "Title", "Director", "Genre", "Year", "Rate");
                for (Movie m : movies) System.out.println(m);
            } else if (ch == 6) {
                break;
            } else {
                System.out.println("Invalid option.");
            }
        }
        sc.close();
    }
}
