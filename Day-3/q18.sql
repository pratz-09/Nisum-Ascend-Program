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
(2, 'Aleeva', 'aleeva@example.com', 2),
(3, 'Chris', 'chris@example.com', 3),
(4, 'Deepak', 'deepak@example.com', 1),
(5, 'Daksh', 'daksh@example.com', 2);

INSERT INTO Enrollments (student_id, course_id, enrollment_date) VALUES
(1, 101, '2024-07-01'),
(1, 104, '2024-07-15'),
(2, 102, '2024-07-05'),
(3, 103, '2024-07-08'),
(4, 101, '2024-07-10'),
(5, 102, '2024-07-12');

INSERT INTO Students (student_id, name, email, dept_id) VALUES
(6, 'Farah', 'farah@cs.com', 1),
(7, 'George', 'george@mech.com', 2),
(8, 'Helen', 'helen@ee.com', 3),
(9, 'Irene', 'irene@cs.com', 1),
(10, 'John', 'john@mech.com', 2);

INSERT INTO Courses (course_id, course_name, credits, dept_id) VALUES
(106, 'Digital Logic', 3, 3),
(107, 'CAD Design', 4, 2);

INSERT INTO Enrollments (student_id, course_id, enrollment_date) VALUES
(6, 104, '2024-07-20'),
(7, 105, '2024-07-22'),
(8, 106, '2024-07-23'),
(9, 101, '2024-07-24'),
(10, 107, '2024-07-25');


-- 1. Single column
SELECT name FROM Students;

-- 2. Multiple columns
SELECT name, email FROM Students;

-- 3. All columns
SELECT * FROM Students;

-- 4. WHERE condition
SELECT * FROM Courses WHERE credits = 4;

-- 5. ORDER BY (ascending)
SELECT * FROM Students ORDER BY name ASC;

-- 6. ORDER BY (descending)
SELECT * FROM Courses ORDER BY credits DESC;

-- 7. LIMIT / FETCH
-- MySQL
SELECT * FROM Students LIMIT 3;

-- 8. BETWEEN (numeric)
SELECT * FROM Courses WHERE credits BETWEEN 3 AND 4;

-- 9. BETWEEN (non-numeric, e.g., date range)
SELECT * FROM Enrollments 
WHERE enrollment_date BETWEEN '2024-07-10' AND '2024-07-20';

-- 10. IN
SELECT * FROM Students WHERE dept_id IN (1, 3);

-- 11. LIKE (pattern matching)
SELECT * FROM Students WHERE email LIKE '%@cs.com';