INSERT INTO pharmacy.users (name, login,  password, role, locked)
VALUES ('Ivan', 'ivanMan', MD5('111'), 'PACIENT', false),
       ('Sasha', 'sashaMan', MD5('222'), 'DOCTOR', false),
       ('Vasya', 'vasyaMan', MD5('333'), 'PHARMACIST', false);

INSERT INTO pharmacy.medicines (name, form, dosage, recipe, amountInPack, price, quantity)
VALUES ('Acetaminophen', 'SOLUTION', '50ml', true, 20, 10.2, 1000),
       ('Ativan', 'SOLUTION', '50ml',  true, 10, 12.25, 20),
       ('Cyclobenzaprine', 'PILL', '50me', true, 5, 7.32, 30),
       ('Lexapro', 'POWDER', '2.5g',  false, 10, 8.95, 100),
       ('Meloxicam', 'PILL', '50ml',  false, 30, 2.15, 200),
       ('Xanax', 'PILL', '12500me',  true, 10, 1.05, 1000),
       ('Asperin', 'PILL', '1g',  false, 10, 5.5, 26),
       ('Ativan', 'POWDER', '1.25g',  false, 60, 4.74, 39),
       ('Lyrica', 'CREAM', '60ml',  false, 15, 8.25, 120);