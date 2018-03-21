CREATE DATABASE NktRpg;
USE NktRpg;

CREATE TABLE Sessions (id SERIAL PRIMARY KEY, date TIMESTAMP NOT NULL, title VARCHAR(50));

CREATE TABLE Events (
    id SERIAL PRIMARY KEY,
    session_id INT NOT NULL,
    date TIMESTAMP NOT NULL,
    location VARCHAR(20),
    description VARCHAR(500),
    FOREIGN KEY (session_id) REFERENCES Sessions(id)
);