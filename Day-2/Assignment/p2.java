package Assignment;

import java.util.ArrayList;
import java.util.Scanner;

class Student {
    int id;
    String name;
    double grade;

    Student(int id, String name, double grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }
}

class StudentManager {
    private ArrayList<Student> students = new ArrayList<>();

    public void addStudent(int id, String name, double grade) {
        students.add(new Student(id, name, grade));
    }

    public void removeStudent(int id) {
        students.removeIf(student -> student.id == id);
    }

    public Student searchStudent(int id) {
        for (Student student : students) {
            if (student.id == id) {
                return student;
            }
        }
        return null;
    }

    public void displayAll() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        System.out.println("ID\tName\tGrade");
        for (Student student : students) {
            System.out.printf("%d\t%s\t%.2f\n", student.id, student.name, student.grade);
        }
    }
}

public class p2 {
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Add Student\n2. Remove Student\n3. Search Student by ID\n4. Display All Students\n5. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline
            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter grade: ");
                    double grade = sc.nextDouble();
                    manager.addStudent(id, name, grade);
                    break;
                case 2:
                    System.out.print("Enter ID to remove: ");
                    int removeId = sc.nextInt();
                    manager.removeStudent(removeId);
                    break;
                case 3:
                    System.out.print("Enter ID to search: ");
                    int searchId = sc.nextInt();
                    Student found = manager.searchStudent(searchId);
                    if (found != null) {
                        System.out.printf("Found: %d %s %.2f\n", found.id, found.name, found.grade);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 4:
                    manager.displayAll();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
