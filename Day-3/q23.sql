DROP TABLE IF EXISTS Enrollments;
DROP TABLE IF EXISTS Students;
DROP TABLE IF EXISTS Courses;

CREATE TABLE Students (
    StudentID INT PRIMARY KEY,
    FirstName VARCHAR(50),
    LastName VARCHAR(50),
    Email VARCHAR(100),
    DateOfBirth DATE
);

CREATE TABLE Courses (
    CourseID INT PRIMARY KEY,
    CourseName VARCHAR(100),
    Credits INT
);

CREATE TABLE Enrollments (
    EnrollmentID INT PRIMARY KEY,
    StudentID INT,
    CourseID INT,
    EnrollmentDate DATE,
    FOREIGN KEY (StudentID) REFERENCES Students(StudentID),
    FOREIGN KEY (CourseID) REFERENCES Courses(CourseID)
);

-- Insert data into Students
INSERT INTO Students (StudentID, FirstName, LastName, Email, DateOfBirth) VALUES
(1, 'John', 'Doe', 'john.doe@example.com', '2001-05-15'),
(2, 'Jane', 'Smith', 'jane.smith@example.com', '1999-08-22'),
(3, 'Alice', 'Brown', 'alice.brown@example.com', '2002-12-10'),
(4, 'Bob', 'Johnson', 'bob.johnson@example.com', '2000-03-05'),
(5, 'Charlie', 'Williams', 'charlie.williams@example.com', '2003-07-30'),
(6, 'David', 'Miller', 'david.miller@example.com', '2004-01-01'),
(7, 'Emma', 'Jones', 'emma.jones@example.com', '2002-09-15'),
(8, 'Frank', 'Garcia', 'frank.garcia@example.com', '2001-11-11'),
(9, 'Grace', 'Martinez', 'grace.martinez@example.com', '2003-02-28'),
(10, 'Hannah', 'Anderson', 'hannah.anderson@example.com', '2000-12-12'),
(11, 'Ivy', 'Thomas', 'ivy.thomas@example.com', '2001-04-20'),
(12, 'Jack', 'White', 'jack.white@example.com', '2002-09-10'),
(13, 'Kara', 'Lopez', 'kara.lopez@example.com', '2000-07-07'),
(14, 'Leo', 'Harris', 'leo.harris@example.com', '2003-01-19'),
(15, 'Mia', 'Clark', 'mia.clark@example.com', '2001-06-06'),
(16, 'Nina', 'Lewis', 'nina.lewis@example.com', '2004-10-01'),
(17, 'Oscar', 'Young', 'oscar.young@example.com', '2002-02-02'),
(18, 'Paul', 'Hall', 'paul.hall@example.com', '2003-03-03'),
(19, 'Quinn', 'Allen', 'quinn.allen@example.com', '1999-11-30'),
(20, 'Rose', 'Scott', 'rose.scott@example.com', '2000-12-25');

-- Insert data into Courses
INSERT INTO Courses (CourseID, CourseName, Credits) VALUES
(101, 'Database Systems', 4),
(102, 'Data Structures', 4),
(103, 'Web Development', 3),
(104, 'Operating Systems', 3),
(105, 'Machine Learning', 4);

-- Insert data into Enrollments
INSERT INTO Enrollments (EnrollmentID, StudentID, CourseID, EnrollmentDate) VALUES
(1, 1, 101, '2024-06-10'),
(2, 2, 101, '2024-06-11'),
(3, 3, 102, '2024-06-12'),
(4, 4, 103, '2024-06-13'),
(5, 5, 101, '2024-06-14'),
(6, 1, 102, '2024-06-15'),
(7, 6, 104, '2024-06-16'),
(8, 7, 104, '2024-06-17'),
(9, 8, 105, '2024-06-18'),
(10, 9, 102, '2024-06-19'),
(11, 10, 101, '2024-06-20'),
(12, 3, 105, '2024-06-21'),
(13, 5, 102, '2024-06-22'),
(14, 4, 101, '2024-06-23'),
(15, 7, 102, '2024-06-24'),
(16, 8, 103, '2024-06-25'),
(17, 2, 103, '2024-06-26'),
(18, 6, 105, '2024-06-27'),
(19, 9, 105, '2024-06-28'),
(20, 10, 104, '2024-06-29');