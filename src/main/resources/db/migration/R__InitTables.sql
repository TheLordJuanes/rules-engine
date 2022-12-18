DROP TABLE IF EXISTS movie;
DROP TABLE IF EXISTS cinema;
DROP TABLE IF EXISTS users;

CREATE TABLE users(
    id UUID PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255) UNIQUE NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL
);

CREATE TABLE movie(
    id BIGSERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    description VARCHAR(20) NOT NULL,
    in_Cinema boolean,
    unique (name)
);

CREATE TABLE cinema(
   id BIGSERIAL PRIMARY KEY,
   name TEXT NOT NULL,
   opening_hours VARCHAR(20) NOT NULL
);

insert into movie(name, description, in_Cinema) values('Movie1', 'Description 1', false);
insert into movie(name, description, in_Cinema) values('Movie2', 'Description 2', true);
insert into movie(name, description, in_Cinema) values('Movie3', 'Description 3', false);
insert into movie(name, description, in_Cinema) values('Movie4', 'Description 4', true);
insert into movie(name, description, in_Cinema) values('Movie5', 'Description 5', true);
insert into movie(name, description, in_Cinema) values('Movie6', 'Description 6', true);