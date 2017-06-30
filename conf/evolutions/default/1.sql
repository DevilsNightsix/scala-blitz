# Users schema

# --- !Ups

CREATE TABLE users (
    id SERIAL NOT NULL,
    email varchar(255) NOT NULL,
    username varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

# --- !Downs

DROP TABLE users;

