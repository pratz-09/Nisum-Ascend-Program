CREATE TABLE employee (
    emp_id INT PRIMARY KEY,
    name VARCHAR(35) NOT NULL,
    email VARCHAR(52) UNIQUE,
    department VARCHAR(47),
    joining_date DATE
);

CREATE TABLE salary (
    salary_id INT PRIMARY KEY,
    emp_id INT,
    basic_salary DECIMAL(10,2),
    hra DECIMAL(10,2),
    allowance DECIMAL(10,2),
    FOREIGN KEY (emp_id) REFERENCES employee(emp_id)
);