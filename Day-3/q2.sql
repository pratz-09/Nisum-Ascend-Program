CREATE TABLE student (
    student_id INT PRIMARY KEY,
    first_name VARCHAR(35) NOT NULL,
    last_name VARCHAR(35),
    email VARCHAR(50) UNIQUE NOT NULL,
    date_of_birth DATE,
    course VARCHAR(35)
);