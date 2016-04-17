# Users table

# --- !Ups

CREATE TABLE users(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    role INT NOT NULL, -- 1 for patient, 2 for doctor
    phone_number VARCHAR(255)
);

CREATE UNIQUE INDEX users_uniq_email ON users(email);

# --- !Downs

DROP TABLE users;
