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
    year NUMERIC,
    description VARCHAR(500) NOT NULL,
    in_Cinema boolean,
    unique (name)
);

CREATE TABLE cinema(
   id BIGSERIAL PRIMARY KEY,
   name TEXT NOT NULL,
   opening_hours VARCHAR(20) NOT NULL
);

insert into movie(name, year, description, in_Cinema) values('La La Land: ciudad de sueños', 2016, 'Sebastian, un pianista de jazz, y Mia, una aspirante a actriz, se enamoran locamente; pero la ambición desmedida que tienen por triunfar en sus respectivas carreras, en una ciudad como Los Ángeles, repleta de competencia y carente de piedad, pone en peligro su amor', false);
insert into movie(name, year, description, in_Cinema) values('Black Panther: Wakanda Forever', 2022, 'Una secuela que seguirá explorando el incomparable mundo de Wakanda y todos los ricos y variados personajes presentados en la película de 2018.', true);
insert into movie(name, year, description, in_Cinema) values('Los pingüinos de Madagascar', 2015, 'Los valientes pingüinos Skipper, Kowalski, Rico y Cabo unen fuerzas con un espía experto para frustrar los planes de un villano con tentáculos que desea apoderarse del mundo', false);
insert into movie(name, year, description, in_Cinema) values('Kung Fu Panda 2', 2011, 'No description', false);
insert into movie(name, year, description, in_Cinema) values('Shrek', 2001,'Un ogro llamado Shrek vive en su pantano, pero su preciada soledad se ve súbitamente interrumpida por la invasión de los ruidosos personajes de los cuentos de hadas', false);
insert into movie(name, year, description, in_Cinema) values('El Hobbit: la batalla de los cinco ejércitos', 2014, 'No description', false);