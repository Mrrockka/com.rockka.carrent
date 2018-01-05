CREATE TABLE carorder_car (
    carorder_id INT NOT NULL,
    car_id INT NOT NULL,
    FOREIGN KEY (carorder_id) REFERENCES carorder(id),
    FOREIGN KEY (car_id) REFERENCES car(id)
);