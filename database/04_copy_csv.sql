\c iii;
\copy Offices FROM '/docker-entrypoint-initdb.d/Offices.csv' DELIMITER ',' CSV
\copy Employees FROM '/docker-entrypoint-initdb.d/Employees.csv' DELIMITER ',' CSV
\copy Customers FROM '/docker-entrypoint-initdb.d/Customers.csv' DELIMITER ',' CSV
\copy Payments FROM '/docker-entrypoint-initdb.d/Payments.csv' DELIMITER ',' CSV
\copy Orders FROM '/docker-entrypoint-initdb.d/Orders.csv' DELIMITER ',' CSV
\copy Products FROM '/docker-entrypoint-initdb.d/Products.csv' DELIMITER ',' CSV
\copy ProductLines FROM '/docker-entrypoint-initdb.d/ProductLines.csv' DELIMITER ',' CSV
\copy OrderDetails FROM '/docker-entrypoint-initdb.d/OrderDetails.csv' DELIMITER ',' CSV
