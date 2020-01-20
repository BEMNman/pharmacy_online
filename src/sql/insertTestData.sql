INSERT INTO pharmacy.users (name, login, password, role, locked)
VALUES ('Ivan', 'ivanMan', MD5('111'), 'PACIENT', false),
       ('Sasha', 'sashaMan', MD5('222'), 'DOCTOR', false),
       ('Vasya', 'vasyaMan', MD5('333'), 'PHARMACIST', false);

INSERT INTO pharmacy.medicines (name, form, dosage, recipe, amountInPack, price, quantity)
VALUES ('Acetaminophen', 'SOLUTION', '50ml', true, 20, 10.2, 1000),
       ('Ativan', 'SOLUTION', '50ml', true, 10, 12.25, 20),
       ('Cyclobenzaprine', 'PILL', '50me', true, 5, 7.32, 30),
       ('Lexapro', 'POWDER', '2.5g', false, 10, 8.95, 100),
       ('Meloxicam', 'PILL', '50ml', false, 30, 2.15, 200),
       ('Xanax', 'PILL', '12500me', true, 10, 1.05, 1000),
       ('Asperin', 'PILL', '1g', false, 10, 5.5, 26),
       ('Ativan', 'POWDER', '1.25g', false, 60, 4.74, 39),
       ('Lyrica', 'CREAM', '60ml', false, 15, 8.25, 120);

INSERT INTO pharmacy.recipes (creationDate, expDate, medicamentId, amount, patientId, doctorId)
VALUES ('2020-02-19 22:54:38', '2020-02-19 22:54:38', 1, 1, 1, 3),
       ('2019-08-11 12:54:10', '2020-05-19 00:00:00', 6, 8, 1, 3),
       ('2018-06-10 14:42:10', '2021-01-01 00:00:00', 7, 3, 1, 3);