-- a) Retrieve all students
SELECT * FROM Students;

-- b) Display FirstName, Email
SELECT FirstName, Email FROM Students;

-- c) List 4-credit courses
SELECT * FROM Courses WHERE Credits = 4;

-- d) Students born after Jan 1, 2000
SELECT * FROM Students WHERE DateOfBirth > '2000-01-01';

-- e) First name starts with "J"
SELECT * FROM Students WHERE FirstName LIKE 'J%';

-- f) Count students per course
SELECT CourseID, COUNT(StudentID) AS StudentCount
FROM Enrollments
GROUP BY CourseID;

-- g) Students & enrollment dates
SELECT s.StudentID, s.FirstName, s.LastName, e.EnrollmentDate
FROM Students s
JOIN Enrollments e ON s.StudentID = e.StudentID;

-- h) Unique Course IDs
SELECT DISTINCT CourseID FROM Enrollments;

-- i) Students in CourseID 102
SELECT s.StudentID, s.FirstName, s.LastName
FROM Students s
JOIN Enrollments e ON s.StudentID = e.StudentID
WHERE e.CourseID = 102;

-- j) Order students by LastName
SELECT * FROM Students ORDER BY LastName;

-- k) Students not enrolled
SELECT * FROM Students
WHERE StudentID NOT IN (SELECT DISTINCT StudentID FROM Enrollments);

-- l) Average course credits
SELECT AVG(Credits) AS AverageCredits FROM Courses;

-- m) Top 3 most recent enrollments
SELECT * FROM Enrollments ORDER BY EnrollmentDate DESC LIMIT 3;

-- n) Courses with 'Data' in name
SELECT * FROM Courses WHERE CourseName LIKE '%Data%';

-- o) Youngest student
SELECT * FROM Students ORDER BY DateOfBirth DESC LIMIT 1;

-- p) Count same last names
SELECT LastName, COUNT(*) AS NameCount
FROM Students
GROUP BY LastName
HAVING COUNT(*) > 1;

-- q) Courses with student count (desc)
SELECT c.CourseName, COUNT(e.StudentID) AS EnrolledStudents
FROM Courses c
LEFT JOIN Enrollments e ON c.CourseID = e.CourseID
GROUP BY c.CourseID, c.CourseName
ORDER BY EnrolledStudents DESC;

-- r) Students with same first name
SELECT FirstName, COUNT(*) AS CountWithSameName
FROM Students
GROUP BY FirstName
HAVING COUNT(*) > 1;

-- s) Enrollments in current year (2024)
SELECT * FROM Enrollments
WHERE YEAR(EnrollmentDate) = 2024;

-- t) Students and course names
SELECT s.FirstName, s.LastName, c.CourseName
FROM Students s
JOIN Enrollments e ON s.StudentID = e.StudentID
JOIN Courses c ON e.CourseID = c.CourseID;