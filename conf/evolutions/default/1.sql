# Admin table

# --- !Ups
CREATE TABLE admins(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255),
    hashed_password VARCHAR(255)
);

CREATE UNIQUE INDEX admins_uniq_email ON admins(email);

# --- !Downs

DROP TABLE admin;

