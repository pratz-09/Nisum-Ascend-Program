CREATE TABLE studentDetails (
    student_id INT,
    courseId INT NOT NULL,
    email VARCHAR(52) UNIQUE,
    name VARCHAR(35),
    date_of_birth DATE,
    PRIMARY KEY (student_id, courseId)
);