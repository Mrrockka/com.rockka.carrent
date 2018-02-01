
#USER
INSERT INTO user (roles, username, password,first_name, second_name, address, about_me, birthday, status,
created_at, modified_at)
VALUES ('ROLE_USER', 'somebody', '12345','some', 'body', 'Belarus, Hrodno', 'somebody that I used to know', '1990-10-02', 1, now(), now());

INSERT INTO user (roles, username, password,first_name, second_name, address, about_me, birthday, status, created_at, modified_at)
VALUES ('ROLE_ADMIN', 'admin', 'admin','admin', 'admin', 'Belarus, Hrodno', 'some admin', '1900-01-20', 1, now(), now());

INSERT INTO user (roles, username, password, first_name, second_name, address, about_me, birthday, status, created_at, modified_at)
VALUES ('ROLE_USER', 'Zmei', '12345','Zmei', 'Gorinich', 'Russia, Moscow', 'Zavoevatel', '1800-11-27', 1, now(), now());

#CAR
INSERT INTO car (name, country, color, description, release_date, price, created_at, modified_at, status)
VALUES ('Nissan Subzero', 'Japan', 'Gray', 'Very good car for riding', '2007-10-01', 200.00, now(), now(), 1);

INSERT INTO car (name, country, color, description, release_date, price, created_at, modified_at, status)
VALUES ('Nissan Cashcai', 'Japan', 'Blue', 'Perfect car for riding', '2017-10-01', 500.00, now(), now(), 1);

#INVOICE
INSERT INTO invoice (car_id, username, description, price, created_at, modified_at, starts_at, expires_at, status)
VALUES (1, 'somebody', 'first order', 2000.00, now(), now(), '2017-12-21', '2017-12-31', 3);

INSERT INTO invoice (car_id, username, description, price, created_at, modified_at, starts_at, expires_at, status)
VALUES (2, 'admin', 'second order', 100.00, now(), now(), '2018-02-21', '2018-02-25', 2);
#with debt and many-to-one relations
INSERT INTO invoice (car_id, username, description, price, created_at, modified_at, starts_at, expires_at, status)
VALUES (2, 'somebody', 'third order', 300.00, now(), now(), '2017-02-21', '2017-02-25', 4);
#with debt and many-to-one relations
INSERT INTO invoice (car_id, username, description, price, created_at, modified_at, starts_at, expires_at, status)
VALUES (2, 'somebody', 'Repair car order', 100.00, now(), now(), '2017-02-25', '2018-02-25', 5);

INSERT INTO invoice (car_id, username, description, price, created_at, modified_at, starts_at, expires_at, status)
VALUES (2, 'Zmei', 'zmei order', 1020.00, now(), now(), '2010-02-21', '2015-02-25', 3);

#INVOICE-INVOICE many-to-one
INSERT INTO invoice_invoice (first_id, second_id)
VALUES (3, 4);
