CREATE TABLE user (
    id INT AUTO_INCREMENT,
    user_role varchar(10) NOT NULL,
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