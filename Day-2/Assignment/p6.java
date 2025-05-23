package Assignment;

import java.util.Scanner;
import java.util.TreeSet;

class Employee implements Comparable<Employee> {
    int id;
    String name, department;
    double salary;

    Employee(int id, String name, String department, double salary) {
        this.id = id; this.name = name; this.department = department; this.salary = salary;
    }

    public int compareTo(Employee o) {
        return this.name.compareToIgnoreCase(o.name);
    }

    public String toString() {
        return id + "\t" + name + "\t" + department + "\t" + salary;
    }
}

public class p6 {
    public static void main(String[] args) {
        TreeSet<Employee> employees = new TreeSet<>();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Add Employee\n2. View All\n3. Exit");
            System.out.print("Choose an option: ");
            int ch = sc.nextInt();
            sc.nextLine();
            if (ch == 1) {
                System.out.print("ID: "); int id = sc.nextInt(); sc.nextLine();
                System.out.print("Name: "); String name = sc.nextLine();
                System.out.print("Department: "); String dept = sc.nextLine();
                System.out.print("Salary: "); double sal = sc.nextDouble();
                employees.add(new Employee(id, name, dept, sal));
            } else if (ch == 2) {
                System.out.println("ID\tName\tDepartment\tSalary");
                for (Employee e : employees) System.out.println(e);
            } else if (ch == 3) {
                break;
            } else {
                System.out.println("Invalid option.");
            }
        }
        sc.close();
    }
}
