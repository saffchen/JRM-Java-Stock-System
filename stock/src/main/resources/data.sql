INSERT INTO satellite(id, name, description)
VALUES (1, 'North Shore', 'test description'),
       (2, 'Burnie', 'test description'),
       (3, 'Lasbela', 'test description'),
       (4, 'Charlottetown', 'test description'),
       (5, 'Sechura', 'test description'),
       (6, 'Aizwal', 'test description'),
       (7, 'Dumaguete', 'test description'),
       (8, 'Scorrano', 'test description'),
       (9, 'Kuruman', 'test description'),
       (10, 'Yeongcheon', 'test description'),
       (11, 'Gansu', 'test description'),
       (12, 'Brodick', 'test description'),
       (13, 'León', 'test description'),
       (14, 'Tucapel', 'test description'),
       (15, 'Patos', 'test description'),
       (16, 'Murcia', 'test description'),
       (17, 'Introd', 'test description'),
       (18, 'Gumi', 'test description'),
       (19, 'Ledbury', 'test description'),
       (20, 'Nurallao', 'test description'),
       (21, 'Bragança', 'test description'),
       (22, 'Motueka', 'test description'),
       (23, 'Cañas', 'test description'),
       (24, 'Picton', 'test description'),
       (25, 'Seshego', 'test description'),
       (26, 'Jaén', 'test description'),
       (27, 'Lagos', 'test description'),
       (28, 'Norrköping', 'test description'),
       (29, 'Wolfurt', 'test description'),
       (30, 'Jinju', 'test description');
SELECT setval('satellite_id_seq', 30, true);


INSERT INTO product(id, category, count, price, title, satellite_id, description)
VALUES (1, 'Car', 51, 1546, 'Renault', 17, 'test description'),
       (2, 'Car', 35, 1873, 'Porsche', 17, 'test description'),
       (3, 'Car', 54, 2085, 'Volkswagen', 3, 'test description'),
       (4, 'Car', 8, 2207, 'Acura', 6, 'test description'),
       (5, 'Car', 22, 1801, 'Peugeot', 12, 'test description'),
       (6, 'Car', 13, 1163, 'Renault', 13, 'test description'),
       (7, 'Car', 19, 1529, 'Subaru', 19, 'test description'),
       (8, 'Car', 49, 2290, 'Volkswagen', 9, 'test description'),
       (9, 'Car', 40, 663, 'Infiniti', 15, 'test description'),
       (10, 'Car', 24, 715, 'Lincoln', 7, 'test description'),
       (11, 'Car', 40, 4122, 'Nissan', 27, 'test description'),
       (12, 'Car', 34, 1552, 'Daimler', 27, 'test description'),
       (13, 'Car', 40, 4527, 'Peugeot', 11, 'test description'),
       (14, 'Car', 21, 568, 'Lincoln', 2, 'test description'),
       (15, 'Car', 24, 2224, 'MINI', 26, 'test description'),
       (16, 'Car', 47, 1833, 'Kenworth', 5, 'test description'),
       (17, 'Car', 2, 1191, 'Kia Motors', 25, 'test description'),
       (18, 'Car', 36, 3254, 'MINI', 8, 'test description'),
       (19, 'Car', 32, 26, 'GMC', 25, 'test description'),
       (20, 'Car', 47, 2342, 'MINI', 3, 'test description'),
       (21, 'Car', 42, 3977, 'Dodge', 2, 'test description'),
       (22, 'Car', 33, 678, 'Daihatsu', 1, 'test description'),
       (23, 'Car', 21, 4333, 'Ford', 6, 'test description'),
       (24, 'Car', 43, 881, 'Hyundai Motors', 14, 'test description'),
       (25, 'Car', 54, 3369, 'Daimler', 6, 'test description'),
       (26, 'Car', 12, 1288, 'Ford', 10, 'test description'),
       (27, 'Car', 32, 194, 'Mitsubishi Motors', 19, 'test description'),
       (28, 'Car', 45, 3382, 'Mazda', 9, 'test description'),
       (29, 'Car', 55, 72, 'Peugeot', 4, 'test description'),
       (30, 'Car', 17, 74, 'Kia Motors', 21, 'test description'),
       (31, 'Car', 42, 481, 'Tata Motors', 26, 'test description'),
       (32, 'Car', 6, 4835, 'Kenworth', 9, 'test description'),
       (33, 'Car', 52, 3872, 'Toyota', 20, 'test description'),
       (34, 'Car', 25, 3716, 'Mazda', 10, 'test description'),
       (35, 'Car', 51, 3679, 'MINI', 7, 'test description'),
       (36, 'Car', 46, 3529, 'Volvo', 8, 'test description'),
       (37, 'Car', 26, 1624, 'Renault', 11, 'test description'),
       (38, 'Car', 46, 226, 'Kia Motors', 8, 'test description'),
       (39, 'Car', 37, 2259, 'Mahindra and Mahindra', 30, 'test description'),
       (40, 'Car', 9, 3014, 'Suzuki', 24, 'test description'),
       (41, 'Car', 42, 603, 'Lincoln', 8, 'test description'),
       (42, 'Car', 53, 358, 'Mazda', 2, 'test description'),
       (43, 'Car', 53, 2485, 'JLR', 7, 'test description'),
       (44, 'Car', 19, 4297, 'Suzuki', 29, 'test description'),
       (45, 'Car', 9, 234, 'Infiniti', 12, 'test description'),
       (46, 'Car', 6, 3614, 'Mahindra and Mahindra', 18, 'test description'),
       (47, 'Car', 11, 1590, 'Mitsubishi Motors', 7, 'test description'),
       (48, 'Car', 22, 1291, 'Smart', 5, 'test description'),
       (49, 'Car', 21, 250, 'JLR', 24, 'test description'),
       (50, 'Car', 41, 4264, 'Subaru', 5, 'test description');  
       SELECT setval('product_id_seq', 50, true);

insert into product_entity_tags (product_entity_id, tags)
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

INSERT INTO "public".person(id, description, email, "active", "name", "password", "role", "username")
VALUES  (1, 'TEST USER DESCRIPTION', 'user@email.ru', false, 'test user', '$2a$10$izr6UC4bg8.Df6fuAeK.UexR0XChhEVTPuHkb0/WdE2K8Uefo5.5S', 'ROLE_USER', 'user'),
        (2, 'TEST ADMIN DESCRIPTION', 'admin@email.ru', false, 'test admin', '$2a$10$jGjXJj0rigt8l3maMMjQDevNFcJ/hj4WAqFJFEGNXvA3exGkXMk8e', 'ROLE_ADMIN', 'admin'),
        (3, 'TEST TEST DESCRIPTION', 'testUser@email.ru', true, 'testUser testUser', '$2a$10$QWBT9uHUtyysganKk1Vpku1t2zPS1py50Fhha2I/.g.QdlYLGXDAW', 'ROLE_USER', 'testuser'),
        (4, 'TEST TEST DESCRIPTION', 'testAdmin@email.ru', true, 'testAdmin testAdmin', '$2a$10$3qmGRufTczbq9NwCF0FOCOHFuu.ZpxVNOfm99lcp/iaauM1wnZKjW', 'ROLE_ADMIN', 'testadmin');
