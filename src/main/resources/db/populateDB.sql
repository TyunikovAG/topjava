DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals (dateTime, description, calories, user_id)
VALUES
('2015-JUNE-1 14:0'      , 'Админ ланч',                  510,    100001),
('2015-JUNE-1 21:0'      , 'Админ ужин',                  1500,   100001),
('2020-JANUARY-31 0:0'   , 'Еда на граничное значение',   100,    100000),
('2020-JANUARY-30 10:0'  , 'Завтрак',                     500,    100000),
('2020-JANUARY-31 13:0'  , 'Обед',                        500,    100000),
('2020-JANUARY-30 20:0'  , 'Ужин',                        500,    100000),
('2020-JANUARY-31 10:0'  , 'Завтрак',                     1000,   100000),
('2020-JANUARY-30 13:0'  , 'Обед',                        1000,   100000),
('2020-JANUARY-31 20:0'  , 'Ужин',                        41,     100000);