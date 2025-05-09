--liquibase formatted sql

--changeset polikarpov:1
CREATE TABLE IF NOT EXISTS users
(
    id    BIGSERIAL PRIMARY KEY,
    email VARCHAR(124) NOT NULL UNIQUE,
    firstname VARCHAR(124),
    lastname VARCHAR(124)
);
--rollback DROP TABLE users

--changeset polikarpov:2
CREATE TABLE IF NOT EXISTS subscriptions
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(124) NOT NULL
);
--rollback DROP TABLE subscriptions

--changeset polikarpov:3
CREATE TABLE IF NOT EXISTS users_sub
(
    user_id BIGINT NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    sub_id  INT    NOT NULL REFERENCES subscriptions (id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, sub_id)
);
--rollback DROP TABLE users_sub