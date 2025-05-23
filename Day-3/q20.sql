-- 1. Get all distinct department IDs from Students
SELECT DISTINCT dept_id
FROM Students;

-- 2. Get all distinct course IDs from Enrollments
SELECT DISTINCT course_id
FROM Enrollments;

-- 3. Get all distinct student names
SELECT DISTINCT name
FROM Students;

-- 4. Get all distinct enrollment dates
SELECT DISTINCT enrollment_date
FROM Enrollments;

-- 5. Get all distinct department names from Departments table
SELECT DISTINCT dept_name
FROM Departments;

-- 6. Get all distinct student-course combinations (composite uniqueness)
SELECT DISTINCT student_id, course_id
FROM Enrollments;

-- 7. Get all distinct credit values from Courses
SELECT DISTINCT credits
FROM Courses;