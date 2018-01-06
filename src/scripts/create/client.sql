CREATE TABLE client (
    user_id INT NOT NULL,
    first_name varchar(15),
    second_name varchar(15),
    last_name varchar(15),
    address varchar(20),
    about_me TEXT,
    image_name TEXT,
    birthday DATE NOT NULL,
    created_at DATE NOT NULL,
    modified_at DATE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(id),
    PRIMARY KEY (user_id)
);