-- 1. DELETE single record from employee
DELETE FROM employee
WHERE emp_id = 1;

-- 2. DELETE multiple records from employee
DELETE FROM employee
WHERE department = 'HR';

-- 3. DELETE single record from studentDetails
DELETE FROM studentDetails
WHERE student_id = 2 AND courseId = 101;

-- 4. DELETE multiple records from studentDetails
DELETE FROM studentDetails
WHERE courseId = 105;

-- 5. DELETE single record from salary
DELETE FROM salary
WHERE salary_id = 3;

-- 6. DELETE multiple records from salary (based on HRA condition)
DELETE FROM salary
WHERE hra < 6000;

-- 7. DELETE single record from course
DELETE FROM course
WHERE courseId = 104;

-- 8. DELETE multiple records from course
DELETE FROM course
WHERE credits = 2;