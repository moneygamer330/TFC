-- Table for products
CREATE TABLE product (
    id_product SERIAL PRIMARY KEY,
    product_name VARCHAR(100) NOT NULL,
    price DECIMAL(10, 2) NOT NULL
);

-- Table for tables
CREATE TABLE table_order (
    id_table SERIAL PRIMARY KEY,
    table_number INT NOT NULL
);

-- Table for orders
CREATE TABLE order_table (
    id_order SERIAL PRIMARY KEY,
    id_table INT NOT NULL,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_table) REFERENCES table_order(id_table) ON DELETE CASCADE
);

-- Table for order details
CREATE TABLE order_detail (
    id_detail SERIAL PRIMARY KEY,
    id_order INT NOT NULL,
    id_product INT NOT NULL,
    quantity INT NOT NULL,
    FOREIGN KEY (id_order) REFERENCES order_table(id_order) ON DELETE CASCADE,
    FOREIGN KEY (id_product) REFERENCES product(id_product)
);
