CREATE TABLE user
(
    username VARCHAR(255) PRIMARY KEY,
    full_name VARCHAR(255),
    password VARCHAR(255),
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMP,
    CONSTRAINT unique_username UNIQUE (username)
);
