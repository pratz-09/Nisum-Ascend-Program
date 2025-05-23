-- DROP tables if they exist
DROP TABLE IF EXISTS Enrollments;
DROP TABLE IF EXISTS Students;
DROP TABLE IF EXISTS Courses;
DROP TABLE IF EXISTS Departments;

-- Create Departments table
CREATE TABLE Departments (
    dept_id INT PRIMARY KEY,
    dept_name VARCHAR(100)
);

-- Create Courses table
CREATE TABLE Courses (
    course_id INT PRIMARY KEY,
    course_name VARCHAR(100),
    credits INT,
    dept_id INT,
    FOREIGN KEY (dept_id) REFERENCES Departments(dept_id)
);

-- Create Students table
CREATE TABLE Students (
    student_id INT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100),
    dept_id INT,
    FOREIGN KEY (dept_id) REFERENCES Departments(dept_id)
);

-- Create Enrollments table (associative table for many-to-many)
CREATE TABLE Enrollments (
    enroll_id INT PRIMARY KEY,
    student_id INT,
    course_id INT,
    FOREIGN KEY (student_id) REFERENCES Students(student_id),
    FOREIGN KEY (course_id) REFERENCES Courses(course_id)
);

-- Insert Departments
INSERT INTO Departments VALUES 
(1, 'Computer Science'),
(2, 'Mechanical'),
(3, 'Electrical');

-- Insert Courses
INSERT INTO Courses VALUES 
(101, 'Data Structures', 4, 1),
(102, 'Thermodynamics', 3, 2),
(103, 'Circuits', 3, 3),
(104, 'Algorithms', 4, 1);

-- Insert Students
INSERT INTO Students VALUES 
(1, 'Ayusman', 'ayusman@example.com', 1),
(2, 'Bhusan', 'bhusan@example.com', 2),
(3, 'Chris', 'chris@example.com', 1),
(4, 'Dravid', 'dravid@example.com', 3),
(5, 'Eva', 'eva@example.com', NULL);  -- no department

-- Insert Enrollments
INSERT INTO Enrollments VALUES 
(1, 1, 101),
(2, 1, 104),
(3, 2, 102),
(4, 4, 103);

-- INNER JOIN: Students and Courses via Enrollments
SELECT s.name AS student_name, c.course_name
FROM Students s
INNER JOIN Enrollments e ON s.student_id = e.student_id
INNER JOIN Courses c ON e.course_id = c.course_id;

-- LEFT JOIN: All students even if not enrolled
SELECT s.name AS student_name, c.course_name
FROM Students s
LEFT JOIN Enrollments e ON s.student_id = e.student_id
LEFT JOIN Courses c ON e.course_id = c.course_id;

-- RIGHT JOIN: All courses even if not enrolled (Note: MySQL supports RIGHT JOIN)
SELECT s.name AS student_name, c.course_name
FROM Enrollments e
RIGHT JOIN Students s ON s.student_id = e.student_id
RIGHT JOIN Courses c ON e.course_id = c.course_id;

-- FULL OUTER JOIN (Simulated using UNION)
SELECT s.name AS student_name, c.course_name
FROM Students s
LEFT JOIN Enrollments e ON s.student_id = e.student_id
LEFT JOIN Courses c ON e.course_id = c.course_id

UNION

SELECT s.name AS student_name, c.course_name
FROM Students s
RIGHT JOIN Enrollments e ON s.student_id = e.student_id
RIGHT JOIN Courses c ON e.course_id = c.course_id;

-- CROSS JOIN: Cartesian product
SELECT s.name AS student_name, c.course_name
FROM Students s
CROSS JOIN Courses c;