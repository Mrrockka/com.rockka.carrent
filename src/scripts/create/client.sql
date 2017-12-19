CREATE TABLE client (
    id INT AUTO_INCREMENT,
    first_name varchar(15),
    second_name varchar(15),
    last_name varchar(15),
    address varchar(20),
    about_me TEXT,
    image_name TEXT,
    birthday DATE NOT NULL,
    created_at DATE NOT NULL,
    modified_at DATE NOT NULL,
    isDeleted varchar(1) NOT NULL,
    isFree varchar(1) NOT NULL,
    PRIMARY KEY (id)
);