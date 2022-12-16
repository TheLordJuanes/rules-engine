DROP TABLE IF EXISTS movie;
CREATE TABLE movie (
    id BIGSERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    in_Cinema boolean,
    unique (name)
);

insert into movie(name, in_Cinema) values('Movie1', false);
insert into movie(name, in_Cinema) values('Movie2', true);
insert into movie(name, in_Cinema) values('Movie3', false);
insert into movie(name, in_Cinema) values('Movie4', true);
insert into movie(name, in_Cinema) values('Movie5', true);
insert into movie(name, in_Cinema) values('Movie6', true);