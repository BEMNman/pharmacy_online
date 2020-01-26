INSERT INTO pharmacy.users (name, login, password, role, locked)
VALUES ('Ivan', 'ivanMan', MD5('111'), 'PATIENT', false),
       ('Sasha', 'sashaMan', MD5('222'), 'DOCTOR', false),
       ('Vasya', 'vasyaMan', MD5('333'), 'PHARMACIST', false);

INSERT INTO pharmacy.medicines (name, form, dosage, recipe, amountInPack, price, quantity, archive)
VALUES ('Acetaminophen', 'SOLUTION', '50ml', true, 20, 10.2, 1000, 0),
       ('Ativan', 'SOLUTION', '50ml', true, 10, 12.25, 20, 0),
       ('Cyclobenzaprine', 'PILL', '50me', true, 5, 7.32, 30, 0),
       ('Lexapro', 'POWDER', '2.5g', false, 10, 8.95, 100, 0),
       ('Meloxicam', 'PILL', '50ml', false, 30, 2.15, 200, 0),
       ('Xanax', 'PILL', '12500me', true, 10, 1.05, 1000, 1),
       ('Asperin', 'PILL', '1g', false, 10, 5.5, 26, 0),
       ('Ativan', 'POWDER', '1.25g', false, 60, 4.74, 39, 0),
       ('Lyrica', 'CREAM', '60ml', false, 15, 8.25, 120, 0);

INSERT INTO pharmacy.recipes (creationDate, expDate, medicamentId, amount, patientId, doctorId)
VALUES ('2020-02-19', '2020-02-19', 1, 1, 1, 3),
       ('2019-08-11', '2020-05-19', 6, 8, 1, 3),
       ('2018-06-10', '2021-01-01', 7, 3, 1, 3);