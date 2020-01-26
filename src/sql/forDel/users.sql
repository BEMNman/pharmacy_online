CREATE DATABASE IF NOT EXISTS pharmacy DEFAULT CHARACTER SET utf8;

DROP table if exists pharmacy.users;

CREATE TABLE pharmacy.users
(
    `id`       int(11)                                  NOT NULL AUTO_INCREMENT,
    `name`     varchar(45)                              NOT NULL,
    `login`    varchar(45)                              NOT NULL,
    `password` varchar(45)                              NOT NULL,
    `role`     enum ('PATIENT', 'DOCTOR', 'PHARMACIST') NOT NULL,
    `locked`   boolean DEFAULT '0',
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;

INSERT INTO pharmacy.users (name, login,  password, role, locked)
VALUES ('Ivan', 'ivanMan', MD5('111'), 'PATIENT', false),
       ('Sasha', 'sashaMan', MD5('222'), 'DOCTOR', false),
       ('Vasya', 'vasyaMan', MD5('333'), 'PHARMACIST', false);