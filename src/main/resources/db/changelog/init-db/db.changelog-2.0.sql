--liquibase formatted sql

--changeset polikarpov:1
INSERT INTO subscriptions (name)
VALUES ('YouTube Premium');
--rollback DELETE FROM subscriptions WHERE name = 'YouTube Premium'

--changeset polikarpov:2
INSERT INTO subscriptions (name)
VALUES ('VK Музыка');
--rollback DELETE FROM subscriptions WHERE name = 'VK Музыка'

--changeset polikarpov:3
INSERT INTO subscriptions (name)
VALUES ('Яндекс.Плюс');
--rollback DELETE FROM subscriptions WHERE name = 'Яндекс.Плюс'

--changeset polikarpov:4
INSERT INTO subscriptions (name)
VALUES ('Netflix');
--rollback DELETE FROM subscriptions WHERE name = 'Netflix'