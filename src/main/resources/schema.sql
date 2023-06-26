CREATE TABLE customer
(
    id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name VARCHAR(255),
    PRIMARY KEY (id)
);
CREATE TABLE transaction
(
    id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    customer_id INTEGER,
    transaction_value DOUBLE PRECISION,
    transaction_date DATETIME,
    PRIMARY KEY (id),
    FOREIGN KEY (customer_id) REFERENCES customer(id)
);