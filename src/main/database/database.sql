-- Table for orders
CREATE TABLE order_table (
    id_order SERIAL PRIMARY KEY,
    table_number INT NOT NULL,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_price DECIMAL(7, 2) NOT NULL,
    FOREIGN KEY (id_table) REFERENCES table_order(id_table) ON DELETE CASCADE
);

-- Table for order details
CREATE TABLE order_detail (
    id_detail SERIAL PRIMARY KEY,
    id_order INT NOT NULL,
    product_name VARCHAR(100) NOT NULL,
    quantity INT NOT NULL,
    FOREIGN KEY (id_order) REFERENCES order_table(id_order) ON DELETE CASCADE
);
