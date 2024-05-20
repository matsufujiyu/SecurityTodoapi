CREATE TABLE TODOLIST (
id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
title VARCHAR(256) NOT NULL,
status VARCHAR(256),
details VARCHAR(256)
);

create table users (
userId varchar(50) not null primary key,
email varchar(500) not null,
password varchar(500) not null,
authority enum('ADMIN','USER') not null
);
