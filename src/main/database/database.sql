CREATE TABLE Product (
    id_product SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(10, 2) NOT NULL
);

CREATE TABLE Order (
    id_order SERIAL PRIMARY KEY,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20) NOT NULL,
    id_table INT,
    FOREIGN KEY (id_table) REFERENCES Order(id_order)
);

CREATE TABLE OrderDetail (
    id_detail SERIAL PRIMARY KEY,
    id_order INT NOT NULL,
    id_product INT NOT NULL,
    quantity INT NOT NULL,
    FOREIGN KEY (id_order) REFERENCES Order(id_order),
    FOREIGN KEY (id_product) REFERENCES Product(id_product)
);
