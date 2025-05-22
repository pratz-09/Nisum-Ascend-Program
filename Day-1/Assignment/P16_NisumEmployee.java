package Assignment;

class Nisum {
    String company = "Nisum";
    int code = 101;
}

class Employee extends Nisum {
    void showDetails() {
        System.out.println("Company: " + company);
        System.out.println("Code: " + code);
    }
}

public class P16_NisumEmployee {
    public static void main(String[] args) {
        Employee emp = new Employee();
        emp.showDetails();
    }
}
