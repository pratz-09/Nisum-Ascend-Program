DROP TABLE IF EXISTS Enrollments;
DROP TABLE IF EXISTS Students;
DROP TABLE IF EXISTS Courses;
DROP TABLE IF EXISTS Departments;

CREATE TABLE Departments (
    dept_id INT PRIMARY KEY,
    dept_name VARCHAR(100) NOT NULL
);

CREATE TABLE Courses (
    course_id INT PRIMARY KEY,
    course_name VARCHAR(100) NOT NULL,
    credits INT,
    dept_id INT,
    FOREIGN KEY (dept_id) REFERENCES Departments(dept_id)
);

CREATE TABLE Students (
    student_id INT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    dept_id INT,
    FOREIGN KEY (dept_id) REFERENCES Departments(dept_id)
);

CREATE TABLE Enrollments (
    student_id INT,
    course_id INT,
    enrollment_date DATE,
    PRIMARY KEY (student_id, course_id),
    FOREIGN KEY (student_id) REFERENCES Students(student_id),
    FOREIGN KEY (course_id) REFERENCES Courses(course_id)
);

INSERT INTO Departments (dept_id, dept_name) VALUES
(1, 'Computer Science'),
(2, 'Mechanical'),
(3, 'Electrical');

INSERT INTO Courses (course_id, course_name, credits, dept_id) VALUES
(101, 'Data Structures', 4, 1),
(102, 'Thermodynamics', 3, 2),
(103, 'Circuits', 3, 3),
(104, 'Algorithms', 4, 1),
(105, 'Mechanics', 3, 2);

INSERT INTO Students (student_id, name, email, dept_id) VALUES
(1, 'Prateek', 'prateek@example.com', 1),
(2, 'Om', 'om@example.com', 2),
(3, 'Aleeva', 'aleeva@example.com', 3),
(4, 'Abhipsha', 'abhipsha@example.com', 1),
(5, 'Praneet', 'praneet@example.com', 2);

INSERT INTO Enrollments (student_id, course_id, enrollment_date) VALUES
(1, 101, '2024-07-01'),
(1, 104, '2024-07-15'),
(2, 102, '2024-07-05'),
(3, 103, '2024-07-08'),
(4, 101, '2024-07-10'),
(5, 102, '2024-07-12');

SELECT * FROM Departments;
SELECT * FROM Courses;
SELECT * FROM Students;
SELECT * FROM Enrollments;