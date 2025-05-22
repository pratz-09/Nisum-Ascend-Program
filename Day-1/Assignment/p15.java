package Assignment;

class Student {
    private int math;
    private int science;
    private int english;
    public String name;

    public Student(String name, int math, int science, int english) {
        this.name = name;
        this.math = math;
        this.science = science;
        this.english = english;
    }

    public void displayMarks() {
        System.out.println("Math: " + math + ", Science: " + science + ", English: " + english);
    }
}

class College {
    String collegeName;
    Student student;

    public College(String collegeName, Student student) {
        this.collegeName = collegeName;
        this.student = student;
    }

    public void displayInfo() {
        System.out.println("College: " + collegeName);
        System.out.println("Student: " + student.name);
        student.displayMarks();
    }
}

public class p15 {
    public static void main(String[] args) {
        Student s = new Student("Alice", 90, 85, 88);
        College c = new College("ABC College", s);
        c.displayInfo();
    }
}
