CREATE DATABASE IF NOT EXISTS todo;

USE todo;

DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id INT NOT NULL auto_increment PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    pwd VARCHAR(32) NOT NULL,
    phone VARCHAR(100) NULL
);
