INSERT INTO satellite(name, description)
VALUES ('North Shore', 'test description'),
       ('Burnie', 'test description'),
       ('Lasbela', 'test description'),
       ('Charlottetown', 'test description'),
       ('Sechura', 'test description'),
       ('Aizwal', 'test description'),
       ('Dumaguete', 'test description'),
       ('Scorrano', 'test description'),
       ('Kuruman', 'test description'),
       ('Yeongcheon', 'test description'),
       ('Gansu', 'test description'),
       ('Brodick', 'test description'),
       ('León', 'test description'),
       ('Tucapel', 'test description'),
       ('Patos', 'test description'),
       ('Murcia', 'test description'),
       ('Introd', 'test description'),
       ('Gumi', 'test description'),
       ('Ledbury', 'test description'),
       ('Nurallao', 'test description'),
       ('Bragança', 'test description'),
       ('Motueka', 'test description'),
       ('Cañas', 'test description'),
       ('Picton', 'test description'),
       ('Seshego', 'test description'),
       ('Jaén', 'test description'),
       ('Lagos', 'test description'),
       ('Norrköping', 'test description'),
       ('Wolfurt', 'test description'),
       ('Jinju', 'test description');

/*SELECT setval('satellite_id_seq', 30, true);*/


INSERT INTO product(category, count, price, name, satellite_id, description)
VALUES ('Car', 51, 1546, 'Renault', 17, 'test description'),
       ('Car', 35, 1873, 'Porsche', 17, 'test description'),
       ('Car', 54, 2085, 'Volkswagen', 3, 'test description'),
       ('Car', 8, 2207, 'Acura', 6, 'test description'),
       ('Car', 22, 1801, 'Peugeot', 12, 'test description'),
       ('Car', 13, 1163, 'Renault', 13, 'test description'),
       ('Car', 19, 1529, 'Subaru', 19, 'test description'),
       ('Car', 49, 2290, 'Volkswagen', 9, 'test description'),
       ('Car', 40, 663, 'Infiniti', 15, 'test description'),
       ('Car', 24, 715, 'Lincoln', 7, 'test description'),
       ('Car', 40, 4122, 'Nissan', 27, 'test description'),
       ('Car', 34, 1552, 'Daimler', 27, 'test description'),
       ('Car', 40, 4527, 'Peugeot', 11, 'test description'),
       ('Car', 21, 568, 'Lincoln', 2, 'test description'),
       ('Car', 24, 2224, 'MINI', 26, 'test description'),
       ('Car', 47, 1833, 'Kenworth', 5, 'test description'),
       ('Car', 2, 1191, 'Kia Motors', 25, 'test description'),
       ('Car', 36, 3254, 'MINI', 8, 'test description'),
       ('Car', 32, 26, 'GMC', 25, 'test description'),
       ('Car', 47, 2342, 'MINI', 3, 'test description'),
       ('Car', 42, 3977, 'Dodge', 2, 'test description'),
       ('Car', 33, 678, 'Daihatsu', 1, 'test description'),
       ('Car', 21, 4333, 'Ford', 6, 'test description'),
       ('Car', 43, 881, 'Hyundai Motors', 14, 'test description'),
       ('Car', 54, 3369, 'Daimler', 6, 'test description'),
       ('Car', 12, 1288, 'Ford', 10, 'test description'),
       ('Car', 32, 194, 'Mitsubishi Motors', 19, 'test description'),
       ('Car', 45, 3382, 'Mazda', 9, 'test description'),
       ('Car', 55, 72, 'Peugeot', 4, 'test description'),
       ('Car', 17, 74, 'Kia Motors', 21, 'test description'),
       ('Car', 42, 481, 'Tata Motors', 26, 'test description'),
       ('Car', 6, 4835, 'Kenworth', 9, 'test description'),
       ('Car', 52, 3872, 'Toyota', 20, 'test description'),
       ('Car', 25, 3716, 'Mazda', 10, 'test description'),
       ('Car', 51, 3679, 'MINI', 7, 'test description'),
       ('Car', 46, 3529, 'Volvo', 8, 'test description'),
       ('Car', 26, 1624, 'Renault', 11, 'test description'),
       ('Car', 46, 226, 'Kia Motors', 8, 'test description'),
       ('Car', 37, 2259, 'Mahindra and Mahindra', 30, 'test description'),
       ('Car', 9, 3014, 'Suzuki', 24, 'test description'),
       ('Car', 42, 603, 'Lincoln', 8, 'test description'),
       ('Car', 53, 358, 'Mazda', 2, 'test description'),
       ('Car', 53, 2485, 'JLR', 7, 'test description'),
       ('Car', 19, 4297, 'Suzuki', 29, 'test description'),
       ('Car', 9, 234, 'Infiniti', 12, 'test description'),
       ('Car', 6, 3614, 'Mahindra and Mahindra', 18, 'test description'),
       ('Car', 11, 1590, 'Mitsubishi Motors', 7, 'test description'),
       ('Car', 22, 1291, 'Smart', 5, 'test description'),
       ('Car', 21, 250, 'JLR', 24, 'test description'),
       ('Car', 41, 4264, 'Subaru', 5, 'test description');

/*SELECT setval('product_id_seq', 50, true);*/

insert into product_tags (product_id, tags)
values (1, 'car'),
       (2, 'car'),
       (3, 'car'),
       (4, 'car'),
       (5, 'car'),
       (6, 'car'),
       (7, 'car'),
       (8, 'car'),
       (9, 'car'),
       (10, 'car'),
       (11, 'car'),
       (12, 'car'),
       (13, 'car'),
       (14, 'car'),
       (15, 'car'),
       (16, 'car'),
       (17, 'car'),
       (18, 'car'),
       (19, 'car'),
       (20, 'car'),
       (21, 'car'),
       (22, 'car'),
       (23, 'car'),
       (24, 'car'),
       (25, 'car'),
       (26, 'car'),
       (27, 'car'),
       (28, 'car'),
       (29, 'car'),
       (30, 'car'),
       (31, 'car'),
       (32, 'car'),
       (33, 'car'),
       (34, 'car'),
       (35, 'car'),
       (36, 'car'),
       (37, 'car'),
       (38, 'car'),
       (39, 'car'),
       (40, 'car'),
       (41, 'car'),
       (42, 'car'),
       (43, 'car'),
       (44, 'car'),
       (45, 'car'),
       (46, 'car'),
       (47, 'car'),
       (48, 'car'),
       (49, 'car'),
       (50, 'car');
