CREATE TABLE user (
    id INT AUTO_INCREMENT,
    user_role varchar(10) NOT NULL,
    nickname varchar(30) NOT NULL,
    password varchar(100) NOT NULL,
    is_deleted varchar(1) NOT NULL,
    created_at DATE NOT NULL,
    modified_at DATE NOT NULL,
    PRIMARY KEY (id)
);