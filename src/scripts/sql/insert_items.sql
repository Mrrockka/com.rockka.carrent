
#USER
INSERT INTO user (roles, nickname, password,first_name, second_name, address, about_me, birthday, is_deleted,
created_at, modified_at)
VALUES ('ROLE_USER', 'somebody', '12345','some', 'body', 'Belarus, Hrodno', 'somebody that I used to know', '1990-10-02', 0, curdate(), curdate());

INSERT INTO user (roles, nickname, password,first_name, second_name, address, about_me, birthday, is_deleted, created_at, modified_at)
VALUES ('ROLE_ADMIN', 'admin', 'admin','admin', 'admin', 'Belarus, Hrodno', 'some admin', '1900-01-20', 0, curdate(),
curdate());

#CAR
INSERT INTO car (name, country, color, description, release_date, price, created_at, modified_at, is_deleted)
VALUES ('Nissan Subzero', 'Japan', 'Gray', 'Very good car for riding', '2007-10-01', 200.00, CURDATE(), CURDATE(), 0);

INSERT INTO car (name, country, color, description, release_date, price, created_at, modified_at, is_deleted)
VALUES ('Nissan Cashcai', 'Japan', 'Blue', 'Perfect car for riding', '2017-10-01', 500.00, CURDATE(), CURDATE(), 0);

#CARORDER
INSERT INTO carorder (car_id, user_id, description, price, created_at, modified_at, starts_at, expires_at, status)
VALUES (1, 1, 'first order', 2000.00, curdate(), curdate(), '2017-12-21', '2017-12-31', 'act');