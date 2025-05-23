-- If emp_id = 5 exists, it will be replaced
REPLACE INTO employee (emp_id, name, email, department, joining_date, designation)
VALUES (5, 'Updated Employee5', 'employee5_new@example.com', 'IT', '2022-01-15', 'Senior Developer');

INSERT INTO studentDetails (student_id, courseId, email, name, date_of_birth)
VALUES (10, 105, 'student10@example.com', 'Updated Student10', '2002-05-10')
ON DUPLICATE KEY UPDATE
    name = 'Updated Student10',
    email = 'student10_updated@example.com';

INSERT INTO salary (salary_id, emp_id, basic_salary, hra, allowance)
VALUES (10, 10, 60000, 9000, 2000)
ON DUPLICATE KEY UPDATE
    basic_salary = 60000,
    hra = 9000,
    allowance = 2000;