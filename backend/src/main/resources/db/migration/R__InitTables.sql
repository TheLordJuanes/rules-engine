DROP TABLE IF EXISTS movie;
CREATE TABLE movie (
    id BIGSERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    description VARCHAR(20) NOT NULL,
    in_Cinema boolean,
    unique (name)
);

DROP TABLE IF EXISTS cinema;
CREATE TABLE cinema (
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