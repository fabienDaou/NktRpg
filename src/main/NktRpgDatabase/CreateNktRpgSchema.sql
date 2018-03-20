CREATE DATABASE NktRpg;
USE NktRpg;

CREATE TABLE Session (id INT NOT NULL AUTO_INCREMENT, date DATE NOT NULL, title VARCHAR(50), PRIMARY KEY(id));

CREATE TABLE Event (
    id INT NOT NULL AUTO_INCREMENT,
    session_id INT NOT NULL,
    date DATE NOT NULL,
    location VARCHAR(20),
    description VARCHAR(500),
    PRIMARY KEY(id),
    FOREIGN KEY (session_id) REFERENCES Session(id)
);