CREATE DATABASE NktRpg;
USE NktRpg;

CREATE TABLE Sessions (id INT NOT NULL AUTO_INCREMENT, date DATETIME NOT NULL, title VARCHAR(50), PRIMARY KEY(id));

CREATE TABLE Events (
    id INT NOT NULL AUTO_INCREMENT,
    session_id INT NOT NULL,
    date DATETIME NOT NULL,
    location VARCHAR(20),
    description VARCHAR(500),
    PRIMARY KEY(id),
    FOREIGN KEY (session_id) REFERENCES Sessions(id)
);

CREATE TABLE Comments (
    id INT NOT NULL AUTO_INCREMENT,
    event_id INT NOT NULL,
    date DATETIME NOT NULL,
    username VARCHAR(20),
    content VARCHAR(500),
    PRIMARY KEY(id),
    FOREIGN KEY (event_id) REFERENCES Events(id)
);