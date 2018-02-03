#DATABASE carrent;

CREATE TABLE car (
    car_id INT AUTO_INCREMENT,
    name varchar(30) NOT NULL,
    country varchar(15),
    color varchar(20),
    description TEXT,
    price DOUBLE NOT NULL,
    release_date DATE,
    created_at TIMESTAMP NOT NULL,
    modified_at TIMESTAMP NOT NULL,
    status INT NOT NULL,
    PRIMARY KEY (car_id)
);

CREATE TABLE user (
    username varchar(30) NOT NULL,
    roles varchar(10) NOT NULL,
    password varchar(100) NOT NULL,
    first_name varchar(30) NOT NULL,
    second_name varchar(30) NOT NULL,
    last_name varchar(30),
    address TEXT NOT NULL,
    about_me TEXT,
    birthday DATE NOT NULL,
    status INT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    modified_at TIMESTAMP NOT NULL,
    PRIMARY KEY (username)
);


CREATE TABLE invoice (
    invoice_id INT AUTO_INCREMENT,
    username varchar(30) NOT NULL,
    car_id INT NOT NULL,
    description TEXT NOT NULL,
    price DOUBLE NOT NULL,
    created_at TIMESTAMP NOT NULL,
    modified_at TIMESTAMP NOT NULL,
    starts_at TIMESTAMP NOT NULL,
    expires_at TIMESTAMP NOT NULL,
    status INT NOT NULL,
    FOREIGN KEY (username) REFERENCES user(username),
    FOREIGN KEY (car_id) REFERENCES car(car_id),
    PRIMARY KEY (invoice_id)
);


CREATE TABLE invoice_invoice (
    first_id INT NOT NULL,
    second_id INT NOT NULL,
    FOREIGN KEY (first_id) REFERENCES invoice(invoice_id),
    FOREIGN KEY (second_id) REFERENCES invoice(invoice_id)
);