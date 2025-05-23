CREATE TABLE demo_table (
    id INT PRIMARY KEY,
    name VARCHAR(100)
);

INSERT INTO demo_table VALUES (1, 'Harish Sir'), (2, 'Muneer Sir');

SELECT * FROM demo_table;

TRUNCATE TABLE demo_table;

SELECT * FROM demo_table;

DROP TABLE demo_table;