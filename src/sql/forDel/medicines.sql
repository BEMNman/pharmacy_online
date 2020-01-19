CREATE DATABASE IF NOT EXISTS pharmacy DEFAULT CHARACTER SET utf8;

DROP TABLE IF EXISTS pharmacy.medicines;

CREATE TABLE pharmacy.medicines
(
    `id`              int(11)                                             NOT NULL AUTO_INCREMENT,
    `name`            varchar(45)                                         NOT NULL,
    `form`            enum ('PILL', 'SOLUTION', 'POWDER', 'CREAM', 'GEL') NOT NULL,
    `dosage`          varchar(10),
    `recipe`          boolean DEFAULT '0',
    `amount`          int(11)    DEFAULT '0',
    `price`           decimal(10, 2),
    `quantityInStock` int(11)    DEFAULT '0',
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;

INSERT INTO pharmacy.medicines (name, form, dosage, recipe, amount, price, quantityInStock)
VALUES ('Acetaminophen', 'SOLUTION', '50ml', true, 20, 10.2, 1000),
       ('Ativan', 'SOLUTION', '50ml',  true, 10, 12.25, 20),
       ('Cyclobenzaprine', 'PILL', '50me', true, 5, 7.32, 30),
       ('Lexapro', 'POWDER', '2.5g',  false, 10, 8.95, 100),
       ('Meloxicam', 'PILL', '50ml',  false, 30, 2.15, 200),
       ('Xanax', 'PILL', '12500me',  true, 10, 1.05, 1000),
       ('Asperin', 'PILL', '1g',  false, 10, 5.5, 26),
       ('Ativan', 'POWDER', '1.25g',  false, 60, 4.74, 39),
       ('Lyrica', 'CREAM', '60ml',  false, 15, 8.25, 120);