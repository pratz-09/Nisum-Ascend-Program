
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentCoursesRetriever {

    public static List<CourseInfo> getStudentCourses(int studentId) {
        List<CourseInfo> courses = new ArrayList<>();

        String jdbcURL = "jdbc:mysql://localhost:3306/jdbc";
        String dbUser = "root";
        String dbPassword = "prateek1234";

        String query = "SELECT c.course_code, c.course_name, c.credits, " +
                "CONCAT(i.first_name, ' ', i.last_name) AS instructor_name, e.grade " +
                "FROM students s " +
                "JOIN enrollments e ON s.student_id = e.student_id " +
                "JOIN courses c ON e.course_id = c.course_id " +
                "JOIN instructors i ON c.instructor_id = i.instructor_id " +
                "WHERE s.student_id = ? " +
                "ORDER BY c.course_name";

        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, studentId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String courseCode = resultSet.getString("course_code");
                    String courseName = resultSet.getString("course_name");
                    int credits = resultSet.getInt("credits");
                    String instructorName = resultSet.getString("instructor_name");
                    String grade = resultSet.getString("grade");

                    courses.add(new CourseInfo(courseCode, courseName, credits, instructorName, grade));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courses;
    }

    public static void main(String[] args) {
        int studentId = 1;
        List<CourseInfo> studentCourses = getStudentCourses(studentId);

        String studentName = getStudentName(studentId);
        System.out.println("Courses taken by " + studentName + ":\n");

        System.out.println("------------------------------------------------------------------");
        System.out.printf("%-10s %-30s %-20s %-5s\n", "CODE", "COURSE NAME", "INSTRUCTOR", "GRADE");
        System.out.println("------------------------------------------------------------------");

        for (CourseInfo course : studentCourses) {
            System.out.printf("%-10s %-30s %-20s %-5s\n",
                    course.getCourseCode(),
                    course.getCourseName(),
                    course.getInstructorName(),
                    course.getGrade());
        }
        System.out.println("------------------------------------------------------------------");
    }

    private static String getStudentName(int studentId) {
        String jdbcURL = "jdbc:mysql://localhost:3306/jdbc";
        String dbUser = "root";
        String dbPassword = "PiXeL2.O";
        String name = "Unknown Student";

        String query = "SELECT CONCAT(first_name, ' ', last_name) AS full_name FROM students WHERE student_id = ?";

        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, studentId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    name = resultSet.getString("full_name");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return name;
    }

    public static class CourseInfo {
        private final String courseCode;
        private final String courseName;
        private final int credits;
        private final String instructorName;
        private final String grade;

        public CourseInfo(String courseCode, String courseName, int credits, String instructorName, String grade) {
            this.courseCode = courseCode;
            this.courseName = courseName;
            this.credits = credits;
            this.instructorName = instructorName;
            this.grade = grade;
        }

        public String getCourseCode() {
            return courseCode;
        }

        public String getCourseName() {
            return courseName;
        }

        public int getCredits() {
            return credits;
        }

        public String getInstructorName() {
            return instructorName;
        }

        public String getGrade() {
            return grade;
        }
    }
}