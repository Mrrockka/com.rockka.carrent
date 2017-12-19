CREATE TABLE car (
    id INT AUTO_INCREMENT,
    mark varchar(30),
    model varchar(30),
    country varchar(15),
    color varchar(20),
    description TEXT,
    image_name TEXT,
    release_date YEAR,
    price DOUBLE NOT NULL,
    created_at DATE NOT NULL,
    modified_at DATE NOT NULL,
    is_deleted varchar(1) NOT NULL,
    is_free varchar(1) NOT NULL,
    PRIMARY KEY (id)
);