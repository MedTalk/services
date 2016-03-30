# Users table

# --- !Ups
CREATE TABLE roles(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE UNIQUE INDEX roles_uniq_name ON roles(name);

CREATE TABLE users(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    role_id INT NOT NULL REFERENCES roles(id),
    phone_number VARCHAR(255)
);

CREATE UNIQUE INDEX users_uniq_email ON users(email);

# --- !Downs

DROP TABLE users;

DROP TABLE roles;