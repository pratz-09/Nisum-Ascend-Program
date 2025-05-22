package Assignment;

class Student {
    String name;
    String address;
    String section;
    String className;
    static String college;
    static int rollNo;

    static {
        college = "ABC College";
        rollNo = 1001;
    }

    Student(String name, String address, String section, String className) {
        this.name = name;
        this.address = address;
        this.section = section;
        this.className = className;
    }

    void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Address: " + address);
        System.out.println("Section: " + section);
        System.out.println("Class: " + className);
        System.out.println("College: " + college);
        System.out.println("Roll No: " + rollNo);
    }
}

public class p9 {
    public static void main(String[] args) {
        Student s1 = new Student("John", "Delhi", "A", "10th");
        s1.displayInfo();
    }
}
