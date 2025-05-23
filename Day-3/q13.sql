-- 1. Update single record in employee
UPDATE employee
SET designation = 'Senior Developer'
WHERE emp_id = 5;

-- 2. Update multiple records in employee
UPDATE employee
SET department = 'HR'
WHERE designation = 'Manager';

-- 3. Update single record in studentDetails
UPDATE studentDetails
SET email = 'newemail10@example.com'
WHERE student_id = 10 AND courseId = 102;

-- 4. Update multiple records in studentDetails
UPDATE studentDetails
SET courseId = 105
WHERE name = 'Student15';

-- 5. Update single record in salary
UPDATE salary
SET basic_salary = basic_salary + 5000
WHERE salary_id = 3;

-- 6. Update multiple records in salary (join with employee)
UPDATE salary s
JOIN employee e ON s.emp_id = e.emp_id
SET s.allowance = s.allowance + 1000
WHERE e.department = 'IT';

-- 7. Update single record in course
UPDATE course
SET credits = 4
WHERE courseId = 102;

-- 8. Update multiple records in course
UPDATE course
SET credits = 3
WHERE course_name LIKE '%Data%';