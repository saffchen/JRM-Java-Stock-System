INSERT INTO store(name, description)
VALUES ('North Shore', 'test description'),
       ('Burnie', 'test description');

INSERT INTO product(category, count, price, name, store_id, description)
VALUES ('Car', 33, 678, 'Daihatsu', 1, 'test description'),
       ('Car', 35, 700, 'Plymouth', 1, 'test description'),
       ('Car', 37, 702, 'Intrepid', 1, 'test description'),
       ('Car', 42, 3977, 'Dodge', 2, 'test description'),
       ('Car', 21, 568, 'Lincoln', 2, 'test description'),
       ('Car', 25, 5685, 'Lancia', 2, 'test description');

insert into product_tags (product_id, tags)
values (1, 'car'),
       (2, 'car'),
       (3, 'car'),
       (4, 'car'),
       (5, 'car'),
       (6, 'car');

INSERT INTO "public".person(id, description, email, "active", "name", "password", "role", "username")
VALUES  (1, 'TEST USER DESCRIPTION', 'user@email.ru', false, 'test user', '$2a$10$izr6UC4bg8.Df6fuAeK.UexR0XChhEVTPuHkb0/WdE2K8Uefo5.5S', 'ROLE_USER', 'user'),
        (2, 'TEST ADMIN DESCRIPTION', 'admin@email.ru', false, 'test admin', '$2a$10$jGjXJj0rigt8l3maMMjQDevNFcJ/hj4WAqFJFEGNXvA3exGkXMk8e', 'ROLE_ADMIN', 'admin'),
        (3, 'TEST TEST DESCRIPTION', 'testUser@email.ru', true, 'testUser testUser', '$2a$10$QWBT9uHUtyysganKk1Vpku1t2zPS1py50Fhha2I/.g.QdlYLGXDAW', 'ROLE_USER', 'testuser'),
        (4, 'TEST TEST DESCRIPTION', 'testAdmin@email.ru', true, 'testAdmin testAdmin', '$2a$10$3qmGRufTczbq9NwCF0FOCOHFuu.ZpxVNOfm99lcp/iaauM1wnZKjW', 'ROLE_ADMIN', 'testadmin');
