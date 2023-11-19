CREATE TABLE book
(
    id          SERIAL PRIMARY KEY,
    isbn        VARCHAR(255),
    title       VARCHAR(255),
    author      VARCHAR(255),
    description VARCHAR(255),
    user_id     VARCHAR(255),
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user (username)
);