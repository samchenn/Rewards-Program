// insert 10 transaction for each customer with random value between 0 and 1000 and random date between 2020-04-01 and 2023-06-01
INSERT INTO customer (id, name) VALUES (1, 'user1');
INSERT INTO transaction (id, transaction_value, transaction_date, customer_id) VALUES (1, 100, TIMESTAMP '2023-06-01 18:32:09.12', 1);
INSERT INTO transaction (id, transaction_value, transaction_date, customer_id) VALUES (2, 200, TIMESTAMP '2023-06-01 18:32:09.12', 1);
INSERT INTO transaction (id, transaction_value, transaction_date, customer_id) VALUES (3, 50, TIMESTAMP '2023-04-01 18:32:09.12', 1);
INSERT INTO transaction (id, transaction_value, transaction_date, customer_id) VALUES (4, 30, TIMESTAMP '2023-04-01 18:32:09.12', 1);
INSERT INTO transaction (id, transaction_value, transaction_date, customer_id) VALUES (5, 0, TIMESTAMP '2023-05-01 18:32:09.12', 1);
INSERT INTO transaction (id, transaction_value, transaction_date, customer_id) VALUES (6, 1, TIMESTAMP '2023-05-01 18:32:09.12', 1);
INSERT INTO transaction (id, transaction_value, transaction_date, customer_id) VALUES (7, 999, TIMESTAMP '2023-05-01 18:32:09.12', 1);
INSERT INTO transaction (id, transaction_value, transaction_date, customer_id) VALUES (8, 2.33, TIMESTAMP '2023-06-01 18:32:09.12', 1);
INSERT INTO transaction (id, transaction_value, transaction_date, customer_id) VALUES (9, 101, TIMESTAMP '2023-04-01 18:32:09.12', 1);
INSERT INTO transaction (id, transaction_value, transaction_date, customer_id) VALUES (10, 56.78, TIMESTAMP '2023-05-01 18:32:09.12', 1);


