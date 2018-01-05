CREATE TABLE car (
    id INT AUTO_INCREMENT,
    name varchar(30) NOT NULL,
    country varchar(15),
    color varchar(20),
    description TEXT,
    image_name TEXT,
    price DOUBLE NOT NULL,
    release_date YEAR,
    created_at DATE NOT NULL,
    modified_at DATE NOT NULL,
    is_deleted varchar(1) NOT NULL,
    PRIMARY KEY (id)
);