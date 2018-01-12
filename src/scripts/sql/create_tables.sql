#CREATE DATABASE carrent;

CREATE TABLE car (
    id INT AUTO_INCREMENT,
    name varchar(30) NOT NULL,
    country varchar(15),
    color varchar(20),
    description TEXT,
    image_name TEXT,
    price DOUBLE NOT NULL,
    release_date DATE,
    created_at DATE NOT NULL,
    modified_at DATE NOT NULL,
    is_deleted INT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE user (
    id INT AUTO_INCREMENT,
    roles varchar(10) NOT NULL,
    nickname varchar(30) NOT NULL,
    password varchar(100) NOT NULL,
    first_name varchar(30) NOT NULL,
    second_name varchar(30) NOT NULL,
    last_name varchar(30),
    address TEXT NOT NULL,
    about_me TEXT,
    image_name TEXT,
    birthday DATE NOT NULL,
    is_deleted INT NOT NULL,
    created_at DATE NOT NULL,
    modified_at DATE NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (nickname)
);


CREATE TABLE carorder (
    id INT AUTO_INCREMENT,
    user_id INT NOT NULL,
    description TEXT NOT NULL,
    price DOUBLE NOT NULL,
    created_at DATE NOT NULL,
    modified_at DATE NOT NULL,
    starts_at DATE NOT NULL,
    expires_at DATE NOT NULL,
    status varchar(5) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(id),
    PRIMARY KEY (id)
);

CREATE TABLE carorder_car (
    carorder_id INT NOT NULL,
    car_id INT NOT NULL,
    FOREIGN KEY (carorder_id) REFERENCES carorder(id),
    FOREIGN KEY (car_id) REFERENCES car(id)
);