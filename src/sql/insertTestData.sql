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
       ('Aevit', 'CAPSULE', '50mg', false, 20, 2.12, 51, false),
       ('Azikar', 'CAPSULE', '250mg', true, 6, 21.50, 24, false),
       ('Bioselac', 'CAPSULE', ' ', false, 10, 12.50, 5, false),
       ('Azopt', 'SOLUTION', '1%', false, 1, 11.00, 26, false),
       ('Brinzopt', 'SOLUTION', '1%', false, 1, 11.98, 14, false),
       ('Cyclobenzaprine', 'PILL', '50me', true, 5, 7.32, 30, false),
       ('Lexapro', 'POWDER', '2.5g', false, 10, 8.95, 100, false),
       ('Badyaga', 'POWDER', '5g', false, 1, 2.49, 6, false),
       ('Liofilisat', 'POWDER', '2mg', true, 1, 373.70, 2, false),
       ('Liofilisat', 'POWDER', '3.5mg', true, 1, 324.20, 1, false),
       ('Meloxicam', 'PILL', '50ml', false, 30, 2.15, 200, false),
       ('Xanax', 'PILL', '12500me', true, 10, 1.05, 1000, true),
       ('Asperin', 'PILL', '1g', true, 10, 5.5, 26, false),
       ('Ativan', 'POWDER', '1.25g', false, 60, 4.74, 39, false),
       ('Bsaa', 'CAPSULE', ' ', false, 60, 20.40, 12, false),
       ('Bsaa', 'CAPSULE', ' ', false, 90, 27.68, 8, false),
       ('Validol', 'CAPSULE', '100mg', false, 50, 1.18, 100, false),
       ('Validol', 'PILL', '60mg', false, 10, 0.33, 251, false),
       ('Vendiol', 'PILL', ' ', true, 28, 14.77, 25, false),
       ('Vetom 4', 'CAPSULE', ' ', false, 50, 35.90, 65, false),
       ('Vetom 4', 'POWDER', '500g', false, 1, 35.90, 9, false),
       ('Vitality', 'PILL', ' ', false, 30, 10.24, 13, false),
       ('Vitality', 'PILL', ' ', false, 60, 15.40, 5, false),
       ('Supervit', 'PILL', ' ', false, 30, 5.88, 27, false),
       ('Gastrigel', 'GEL', '10ml', false, 30, 61.20, 20, false),
       ('Gedeliks', 'SOLUTION', '100ml', false, 1, 8.01, 21, false),
       ('Avaksim', 'SOLUTION', '5ml', true, 10, 5.00, 100, false),
       ('Avaksim', 'SOLUTION', '0.5ml', true, 1, 0.60, 200, false),
       ('Barboval', 'SOLUTION', '25ml', true, 1, 2.23, 102, false),
       ('Avirol', 'PILL', '1g', false, 90, 23.6, 54, false),
       ('Becarbon', 'PILL', ' ', false, 20, 1.59, 89, false),
       ('Amizon', 'PILL', '125mg', false, 10, 3.71, 102, false),
       ('Amizon', 'PILL', '125mg', false, 20, 3.71, 102, false),
       ('Citalopram', 'SOLUTION', '10mg', true, 20, 17.35, 125, false),
       ('FLUDEX', 'PILL', '1.5mg', false, 30, 12.37, 1000, false),
       ('Bishofit', 'GEL', '75ml', false, 1, 40.60, 61, false),
       ('Lyrica', 'CREAM', '60ml', false, 15, 8.25, 120, false);

INSERT INTO pharmacy.recipes (creationDate, expDate, medicamentId, amount, patientId, doctorId)
VALUES ('2020-02-19', '2020-02-19', 1, 1, 1, 3),
       ('2019-08-11', '2020-05-19', 6, 8, 2, 4),
       ('2018-06-10', '2021-01-01', 7, 3, 1, 4);