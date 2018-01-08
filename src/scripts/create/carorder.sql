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