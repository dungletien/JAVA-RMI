CREATE DATABASE student_management_RMI;

USE student_management_RMI;

CREATE TABLE students (
    id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    birthday DATE NOT NULL,
    email VARCHAR(100) NOT NULL
);
