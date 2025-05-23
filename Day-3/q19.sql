-- 1. COUNT: Number of students in each department
SELECT dept_id, COUNT(*) AS student_count
FROM Students
GROUP BY dept_id;

-- 2. AVG: Average number of credits per department
SELECT dept_id, AVG(credits) AS avg_credits
FROM Courses
GROUP BY dept_id;

-- 3. MAX: Most recent enrollment per course
SELECT course_id, MAX(enrollment_date) AS latest_enrollment
FROM Enrollments
GROUP BY course_id;

-- 4. MIN: Earliest enrollment per student
SELECT student_id, MIN(enrollment_date) AS first_enrollment
FROM Enrollments
GROUP BY student_id;

-- 5. SUM: Total credits by department
SELECT dept_id, SUM(credits) AS total_credits
FROM Courses
GROUP BY dept_id;

-- 6. GROUP BY with JOIN: Number of enrollments per department
SELECT d.dept_name, COUNT(e.course_id) AS total_enrollments
FROM Enrollments e
JOIN Courses c ON e.course_id = c.course_id
JOIN Departments d ON c.dept_id = d.dept_id
GROUP BY d.dept_name;

-- 7. HAVING: Departments with more than 2 students
SELECT dept_id, COUNT(*) AS student_count
FROM Students
GROUP BY dept_id
HAVING COUNT(*) > 2;

-- 8. HAVING: Courses with average enrollment date after 2024-07-20 (not typical but for demo)
SELECT course_id, AVG(DATEDIFF(enrollment_date, '2024-07-01')) AS avg_enroll_day_offset
FROM Enrollments
GROUP BY course_id
HAVING AVG(DATEDIFF(enrollment_date, '2024-07-01')) > 20;