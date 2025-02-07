
-- БАЗА ДАНИХ
-- Можливість створення БД з метою уникнення некваліфікованих
-- дій, краще залишити за розробником.
-- Тому такий функціонал у додатку не прописуємо.
CREATE DATABASE lesson39;


-- ТАБЛИЦІ
-- Можливість створення таблиць БД, з метою уникнення некваліфікованих
-- дій, краще залишити за розробником.
-- Тому такий функціонал у додатку не прописуємо.
-- Попередньо, необхідно спроектувати таблиці та їх зв'язки,
-- на основі сутностей реального світу.

CREATE TABLE IF NOT EXISTS users
( id SERIAL PRIMARY KEY,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS products
( id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  measure VARCHAR(255) NOT NULL,
  quota DECIMAL(5,2) NOT NULL,
  price DECIMAL(6,2) NOT NULL
);





