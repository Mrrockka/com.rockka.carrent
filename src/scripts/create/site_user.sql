CREATE TABLE site_user (
    id INT AUTO_INCREMENT,
    client_id INT,
    user_role varchar(10) NOT NULL,
    nickname varchar(30) NOT NULL,
    password varchar(100) NOT NULL,
    FOREIGN KEY (client_id) REFERENCES client(id),
    PRIMARY KEY (id)
);