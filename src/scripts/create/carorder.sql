CREATE TABLE carorder (
    id INT AUTO_INCREMENT,
    client_id INT NOT NULL,
    description TEXT NOT NULL,
    price DOUBLE NOT NULL,
    created_at DATE NOT NULL,
    modified_at DATE NOT NULL,
    starts_at DATE NOT NULL,
    expires_at DATE NOT NULL,
    status varchar(5) NOT NULL,
    FOREIGN KEY (client_id) REFERENCES client(id),
    FOREIGN KEY (car_id) REFERENCES car(id),
    PRIMARY KEY (id)
);