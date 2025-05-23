CREATE TABLE student (
    student_id INT,
    course_id INT,
    first_name VARCHAR(37),
    last_name VARCHAR(37),
    email VARCHAR(52),
    PRIMARY KEY (student_id, course_id)
);