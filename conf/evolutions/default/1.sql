# Admins table

# --- !Ups
CREATE TABLE admins(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255)
);

CREATE UNIQUE INDEX admins_uniq_email ON admins(email);

# --- !Downs

DROP TABLE admins;
