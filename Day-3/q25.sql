-- a) Students and their courses (INNER JOIN)
SELECT s.StudentID, s.FirstName, s.LastName, c.CourseName
FROM Students s
JOIN Enrollments e ON s.StudentID = e.StudentID
JOIN Courses c ON e.CourseID = c.CourseID;

-- b) Courses and students (include courses with no enrollments) - LEFT JOIN
SELECT c.CourseID, c.CourseName, s.FirstName, s.LastName
FROM Courses c
LEFT JOIN Enrollments e ON c.CourseID = e.CourseID
LEFT JOIN Students s ON e.StudentID = s.StudentID;

-- c) Students not enrolled in any course
SELECT s.*
FROM Students s
LEFT JOIN Enrollments e ON s.StudentID = e.StudentID
WHERE e.StudentID IS NULL;

-- d) Course names and count of students
SELECT c.CourseName, COUNT(e.StudentID) AS StudentCount
FROM Courses c
LEFT JOIN Enrollments e ON c.CourseID = e.CourseID
GROUP BY c.CourseID, c.CourseName;

-- e) Full name and course names
SELECT CONCAT(s.FirstName, ' ', s.LastName) AS FullName, c.CourseName
FROM Students s
JOIN Enrollments e ON s.StudentID = e.StudentID
JOIN Courses c ON e.CourseID = c.CourseID;

-- f) INNER JOIN on enrollments
SELECT e.EnrollmentID, s.FirstName, c.CourseName, e.EnrollmentDate
FROM Enrollments e
INNER JOIN Students s ON e.StudentID = s.StudentID
INNER JOIN Courses c ON e.CourseID = c.CourseID;

-- g) LEFT JOIN for courses with student names (students may be NULL if not enrolled)
SELECT c.CourseName, s.FirstName, s.LastName
FROM Courses c
LEFT JOIN Enrollments e ON c.CourseID = e.CourseID
LEFT JOIN Students s ON e.StudentID = s.StudentID;

-- h) RIGHT JOIN for students and course names (all students shown, even if not enrolled)
SELECT s.FirstName, s.LastName, c.CourseName
FROM Students s
RIGHT JOIN Enrollments e ON s.StudentID = e.StudentID
RIGHT JOIN Courses c ON e.CourseID = c.CourseID;

-- i) FULL OUTER JOIN (simulate using UNION of LEFT and RIGHT joins if not supported)
SELECT s.StudentID, s.FirstName, c.CourseID, c.CourseName
FROM Students s
LEFT JOIN Enrollments e ON s.StudentID = e.StudentID
LEFT JOIN Courses c ON e.CourseID = c.CourseID

UNION

SELECT s.StudentID, s.FirstName, c.CourseID, c.CourseName
FROM Students s
RIGHT JOIN Enrollments e ON s.StudentID = e.StudentID
RIGHT JOIN Courses c ON e.CourseID = c.CourseID;

-- j) Students in both 'Database Systems' and 'Data Structures'
SELECT s.StudentID, s.FirstName, s.LastName
FROM Students s
JOIN Enrollments e1 ON s.StudentID = e1.StudentID
JOIN Courses c1 ON e1.CourseID = c1.CourseID AND c1.CourseName = 'Database Systems'
JOIN Enrollments e2 ON s.StudentID = e2.StudentID
JOIN Courses c2 ON e2.CourseID = c2.CourseID AND c2.CourseName = 'Data Structures';