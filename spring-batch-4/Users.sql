CREATE TABLE Users (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    dob DATE,
    phone VARCHAR(20),
    address VARCHAR(255)
);