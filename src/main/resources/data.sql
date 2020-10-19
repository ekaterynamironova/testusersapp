INSERT INTO users_schema.addresses (id, country, city, street, home_number)
    VALUES (1, 'Ukraine', 'Kiev', 'Main Street', '4');
INSERT INTO users_schema.addresses (id, country, city, street, home_number)
    VALUES (2, 'Ukraine', 'Lviv', 'Some Street', '15');

INSERT INTO users_schema.users (id, first_name, last_name, birthday, login,
    password, description, address_id) VALUES (1, 'First', 'User', '08-Jan-1998',
    'first@gmail.com', '12345', 'First User Description', 1);
INSERT INTO users_schema.users (id, first_name, last_name, birthday, login,
    password, description, address_id) VALUES (2, 'Second', 'User', '31-July-1999',
    'second@gmail.com', '54321', 'Second User Description', 2);

ALTER SEQUENCE users_schema.users_id_seq RESTART WITH 3;
ALTER SEQUENCE users_schema.addresses_id_seq RESTART WITH 3;