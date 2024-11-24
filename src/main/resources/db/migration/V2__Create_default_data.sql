SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
INSERT INTO roles (role_id, role_name) VALUES (1, 'Administrator');
INSERT INTO status (status_name) VALUES ('Nowe'),
                                                      ('Do wydania'),
                                                      ('Odebrane');
INSERT INTO order_types (type_name) VALUES ('Normalne'), ('Pilne');
    INSERT INTO categories (category_name) VALUES ('Laptop'), ('Komputer'), ('Drukarka'),('Inne')
    ,('Smartphone'), ('Tablet');
INSERT INTO employees (employee_id, first_name, block, last_name, mail, password, phone, username, role_id) VALUES (NULL, 'Admin', false, 'Admin', 'admin@fastserwis.pl', 'password', NULL, 'Administrator', '1');
COMMIT;