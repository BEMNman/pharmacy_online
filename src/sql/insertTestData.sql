INSERT INTO pharmacy.users (name, login, password, role)
VALUES ('Patient First', 'patientF', MD5('p111'), 'PATIENT'),
       ('Patient Second', 'patientS', MD5('p222'), 'PATIENT'),
       ('Patient Empty', 'patientE', MD5('pe333'), 'PATIENT'),
       ('Doctor First', 'doctorF', MD5('d111'), 'DOCTOR'),
       ('Doctor Second', 'doctorS', MD5('d222'), 'DOCTOR'),
       ('Doctor Empty', 'doctorE', MD5('de333'), 'DOCTOR'),
       ('Pharmacist First', 'pharmacistF', MD5('ph111'), 'PHARMACIST');

INSERT INTO pharmacy.medicines (name, form, dosage, recipe, amountInPack, price, quantity, archive)
VALUES ('Acetaminophen', 'SOLUTION', '50ml', true, 20, 10.2, 1000, false),
       ('Ativan', 'SOLUTION', '50ml', true, 10, 12.25, 20, false),
       ('Cyclobenzaprine', 'PILL', '50me', true, 5, 7.32, 30, false),
       ('Lexapro', 'POWDER', '2.5g', false, 10, 8.95, 100, false),
       ('Meloxicam', 'PILL', '50ml', false, 30, 2.15, 200, false),
       ('Xanax', 'PILL', '12500me', true, 10, 1.05, 1000, true),
       ('Asperin', 'PILL', '1g', true, 10, 5.5, 26, false),
       ('Ativan', 'POWDER', '1.25g', false, 60, 4.74, 39, false),
       ('Avaksim', 'SOLUTION', '5ml', true, 10, 5.00, 100, false),
       ('Avaksim', 'SOLUTION', '0.5ml', true, 1, 0.60, 200, false),
       ('Avirol', 'PILL', '1g', false, 90, 23.6, 54, false),
       ('Citalopram', 'SOLUTION', '10mg', true, 20, 17.35, 125, false),
       ('FLUDEX', 'PILL', '1.5mg', false, 30, 12.37, 1000, false),
       ('Lyrica', 'CREAM', '60ml', false, 15, 8.25, 120, false);

INSERT INTO pharmacy.recipes (creationDate, expDate, medicamentId, amount, patientId, doctorId)
VALUES ('2020-02-19', '2020-02-19', 1, 1, 1, 3),
       ('2019-08-11', '2020-05-19', 6, 8, 2, 4),
       ('2018-06-10', '2021-01-01', 7, 3, 1, 4);