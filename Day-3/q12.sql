DROP TABLE IF EXISTS salary;
DROP TABLE IF EXISTS studentDetails;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS course;

CREATE TABLE employee (
    emp_id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE,
    department VARCHAR(50),
    joining_date DATE,
    designation VARCHAR(50)
);student_id

CREATE TABLE studentDetails (
    student_id INT,
    courseId INT NOT NULL,
    email VARCHAR(100) UNIQUE,
    name VARCHAR(100),
    date_of_birth DATE,
    PRIMARY KEY (student_id, courseId)
);

CREATE TABLE salary (
    salary_id INT PRIMARY KEY,
    emp_id INT,
    basic_salary DECIMAL(10,2),
    hra DECIMAL(10,2),
    allowance DECIMAL(10,2),
    FOREIGN KEY (emp_id) REFERENCES employee(emp_id)
);

CREATE TABLE course (
    courseId INT PRIMARY KEY,
    course_name VARCHAR(100),
    credits INT
);

INSERT INTO employee (emp_id, name, email, department, joining_date, designation)
SELECT
    seq AS emp_id,
    CONCAT('Employee', seq) AS name,
    CONCAT('employee', seq, '@example.com') AS email,
    ELT(FLOOR(1 + RAND() * 4), 'HR', 'IT', 'Finance', 'Marketing') AS department,
    DATE_ADD('2015-01-01', INTERVAL FLOOR(RAND() * 2000) DAY) AS joining_date,
    ELT(FLOOR(1 + RAND() * 3), 'Manager', 'Developer', 'Analyst') AS designation
FROM (
    SELECT @row := @row + 1 AS seq FROM 
        (SELECT 0 UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) t1,
        (SELECT 0 UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) t2,
        (SELECT @row := 0) t3
    LIMIT 40
) numbers;

INSERT INTO salary (salary_id, emp_id, basic_salary, hra, allowance)
SELECT
    seq AS salary_id,
    seq AS emp_id,
    FLOOR(30000 + RAND() * 40000) AS basic_salary,
    FLOOR(5000 + RAND() * 10000) AS hra,
    FLOOR(1000 + RAND() * 5000) AS allowance
FROM (
    SELECT @row2 := @row2 + 1 AS seq FROM 
        (SELECT 0 UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) t1,
        (SELECT 0 UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) t2,
        (SELECT @row2 := 0) t3
    LIMIT 40
) numbers;

INSERT INTO studentDetails (student_id, courseId, email, name, date_of_birth)
SELECT
    seq AS student_id,
    FLOOR(100 + RAND() * 10) AS courseId,
    CONCAT('student', seq, '@example.com') AS email,
    CONCAT('Student', seq) AS name,
    DATE_SUB(CURDATE(), INTERVAL FLOOR(RAND() * 10000) DAY) AS date_of_birth
FROM (
    SELECT @row3 := @row3 + 1 AS seq FROM 
        (SELECT 0 UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) t1,
        (SELECT 0 UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) t2,
        (SELECT @row3 := 0) t3
    LIMIT 40
) numbers;

INSERT INTO course (courseId, course_name, credits)
SELECT
    seq + 100 AS courseId,
    CONCAT('Course', seq) AS course_name,
    FLOOR(1 + RAND() * 5) AS credits
FROM (
    SELECT @row4 := @row4 + 1 AS seq FROM 
        (SELECT 0 UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) t1,
        (SELECT 0 UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) t2,
        (SELECT @row4 := 0) t3
    LIMIT 40
) numbers;

SELECT * FROM employee;
SELECT * FROM salary;
SELECT * FROM studentDetails;
SELECT * FROM course;