import java.util.*;
import java.util.stream.*;

public class EmployeeUtils {

    // 1. Get full name of first employee
    public static String getFirstEmployeeFullName(List<Employee> employees) {
        return employees.isEmpty() ? "" : employees.get(0).getFirstName() + employees.get(0).getLastName();
    }

    // 2. Map<department, count>
    public static Map<String, Long> countByDepartment(List<Employee> employees) {
        return employees.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
    }

    // 3. Search employees by name in Map<String, List<Employee>>
    public static List<Employee> searchEmployees(Map<String, List<Employee>> map, String search) {
        String s = search.toLowerCase();
        return map.values().stream()
            .flatMap(List::stream)
            .filter(e -> e.getFirstName().toLowerCase().contains(s) || e.getLastName().toLowerCase().contains(s))
            .collect(Collectors.toList());
    }

    // 4. Pad storeId with leading zeros
    public static String padStoreId(String storeId) {
        return String.format("%4s", storeId).replace(' ', '0');
    }

    // 5. Employees NOT in given department
    public static List<Employee> excludeDepartment(List<Employee> employees, String dept) {
        return employees.stream().filter(e -> !e.getDepartment().equals(dept)).collect(Collectors.toList());
    }

    // 6. Sort by first name
    public static List<Employee> sortByFirstName(List<Employee> employees) {
        return employees.stream().sorted(Comparator.comparing(Employee::getFirstName)).collect(Collectors.toList());
    }

    // 7. Employee with highest empId
    public static Optional<Employee> getMaxEmpId(List<Employee> employees) {
        return employees.stream().max(Comparator.comparing(Employee::getEmpId));
    }

    // 8. Concatenate all full names with pipe
    public static String concatFullNames(List<Employee> employees) {
        return employees.stream()
            .map(e -> e.getFirstName() + e.getLastName())
            .collect(Collectors.joining("|"));
    }

    // 9. Get 8th employee's full name and department
    public static String getEighthEmployeeDetails(List<Employee> employees) {
        if (employees.size() < 8) return "";
        Employee e = employees.get(7);
        return e.getFirstName() + e.getLastName() + " - " + e.getDepartment();
    }

    // 10. Get Employee objects by IDs
    public static List<Employee> getEmployeesByIds(List<Integer> ids, List<Employee> all) {
        Set<Integer> idSet = new HashSet<>(ids);
        return all.stream().filter(e -> idSet.contains(e.getEmpId())).collect(Collectors.toList());
    }

    // 11. Group by gender and count
    public static Map<String, Long> countByGender(List<Employee> employees) {
        return employees.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
    }

    // 12. Separate by gender, return formatted string
    public static String groupByGenderString(List<Employee> employees) {
        Map<String, List<String>> map = employees.stream()
            .collect(Collectors.groupingBy(Employee::getGender,
                Collectors.mapping(e -> e.getFirstName(), Collectors.toList())));
        return "MALE: " + map.getOrDefault("MALE", Collections.emptyList()) +
               ", FEMALE: " + map.getOrDefault("FEMALE", Collections.emptyList());
    }

    // 13. Sort by salary ascending
    public static List<Employee> sortBySalary(List<Employee> employees) {
        return employees.stream().sorted(Comparator.comparing(Employee::getSalary)).collect(Collectors.toList());
    }

    // 14. Use Optional in Employee and return details
    public static String getEmployeeDetails(Employee e) {
        return "Name: " + e.getFirstName() + " " + e.getLastName() +
               ", Email: " + e.getEmail().orElse("N/A");
    }

    // 15. Return default address if missing
    public static Address getEmployeeAddress(Employee e) {
        return e.getAddress().orElse(new Address("Default St", "Default City", "Default Country"));
    }

    // 16. Increase IT salary using Optional.ifPresent
    public static void increaseITSalary(List<Employee> employees, double increment) {
        employees.forEach(e -> {
            Optional<String> dept = Optional.ofNullable(e.getDepartment());
            dept.filter(d -> d.equals("IT")).ifPresent(d -> e.setSalary(e.getSalary() + increment));
        });
    }

    // 17. Sort addresses by city, then country
    public static List<Address> sortAddresses(List<Address> addresses) {
        return addresses.stream()
            .sorted(Comparator.comparing(Address::getCity).thenComparing(Address::getCountry))
            .collect(Collectors.toList());
    }

    // 18. Map<fullName, address>
    public static Map<String, Address> mapFullNameToAddress(List<Employee> employees) {
        return employees.stream()
            .collect(Collectors.toMap(
                e -> e.getFirstName() + e.getLastName(),
                e -> e.getAddress().orElse(null)
            ));
    }

    // 19. findAny and findFirst
    public static Optional<Employee> findAnyEmployee(List<Employee> employees) {
        return employees.stream().findAny();
    }
    public static Optional<Employee> findFirstEmployee(List<Employee> employees) {
        return employees.stream().findFirst();
    }

    // 20. anyMatch, allMatch, noneMatch
    public static boolean anyFromHR(List<Employee> employees) {
        return employees.stream().anyMatch(e -> "HR".equals(e.getDepartment()));
    }
    public static boolean allHaveEmail(List<Employee> employees) {
        return employees.stream().allMatch(e -> e.getEmail().isPresent());
    }
    public static boolean noneHasNullName(List<Employee> employees) {
        return employees.stream().noneMatch(e -> e.getFirstName() == null || e.getLastName() == null);
    }
}

// Minimal Employee class for compilation
class Employee {
    private String firstName;
    private String lastName;
    private int empId;
    private String department;
    private String gender;
    private double salary;
    private Optional<String> email;
    private Optional<Address> address;

    public Employee(String firstName, String lastName, int empId, String department, String gender, double salary, Optional<String> email, Optional<Address> address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.empId = empId;
        this.department = department;
        this.gender = gender;
        this.salary = salary;
        this.email = email;
        this.address = address;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public int getEmpId() { return empId; }
    public String getDepartment() { return department; }
    public String getGender() { return gender; }
    public double getSalary() { return salary; }
    public Optional<String> getEmail() { return email; }
    public Optional<Address> getAddress() { return address; }
    public void setSalary(double salary) { this.salary = salary; }
}

// Minimal Address class for compilation
class Address {
    private String street;
    private String city;
    private String country;

    public Address(String street, String city, String country) {
        this.street = street;
        this.city = city;
        this.country = country;
    }

    public String getStreet() { return street; }
    public String getCity() { return city; }
    public String getCountry() { return country; }
}