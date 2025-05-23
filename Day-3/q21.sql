-- 1. Get names of students enrolled in more than one course
SELECT name
FROM Students
WHERE student_id IN (
    SELECT student_id
    FROM Enrollments
    GROUP BY student_id
    HAVING COUNT(course_id) > 1
);

-- 2. Get course names where credits are greater than the average credit value
SELECT course_name
FROM Courses
WHERE credits > (
    SELECT AVG(credits)
    FROM Courses
);

-- 3. Get departments with more than 1 student
SELECT dept_name
FROM Departments
WHERE dept_id IN (
    SELECT dept_id
    FROM Students
    GROUP BY dept_id
    HAVING COUNT(student_id) > 1
);

-- 4. Get student details who are enrolled in 'Data Structures'
SELECT *
FROM Students
WHERE student_id IN (
    SELECT student_id
    FROM Enrollments
    WHERE course_id = (
        SELECT course_id
        FROM Courses
        WHERE course_name = 'Data Structures'
    )
);

-- 5. Get students whose enrollment date is the latest
SELECT *
FROM Students
WHERE student_id IN (
    SELECT student_id
    FROM Enrollments
    WHERE enrollment_date = (
        SELECT MAX(enrollment_date)
        FROM Enrollments
    )
);