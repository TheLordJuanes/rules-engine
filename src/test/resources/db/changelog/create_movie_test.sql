CREATE TABLE IF NOT EXISTS movie_test(
      id UUID PRIMARY KEY,
      name TEXT NOT NULL,
      year_publication NUMERIC NOT NULL,
      description VARCHAR(500) NOT NULL,
      in_Cinema boolean NOT NULL,
      unique (name)
);


insert into movie_test(id, name, year_publication, description, in_Cinema) values('f7e9087f-8b3c-4d86-a4f7-4d0fcdab891a', 'La La Land: ciudad de sueños', 2016, 'Sebastian, un pianista de jazz, y Mia, una aspirante a actriz, se enamoran locamente; pero la ambición desmedida que tienen por triunfar en sus respectivas carreras, en una ciudad como Los Ángeles, repleta de competencia y carente de piedad, pone en peligro su amor', false);
insert into movie_test(id, name, year_publication, description, in_Cinema) values('36211885-abc3-4100-802c-da9dd4868612', 'Black Panther: Wakanda Forever', 2022, 'Una secuela que seguirá explorando el incomparable mundo de Wakanda y todos los ricos y variados personajes presentados en la película de 2018.', true);
insert into movie_test(id, name, year_publication, description, in_Cinema) values('ea5befa5-3771-4882-8abc-355fdda4a8c1', 'Los pingüinos de Madagascar', 2015, 'Los valientes pingüinos Skipper, Kowalski, Rico y Cabo unen fuerzas con un espía experto para frustrar los planes de un villano con tentáculos que desea apoderarse del mundo', false);
insert into movie_test(id, name, year_publication, description, in_Cinema) values('09a6d9c3-1095-4920-9f6d-ab5c9bf286ba', 'Kung Fu Panda 2', 2011, 'No description', false);
insert into movie_test(id, name, year_publication, description, in_Cinema) values('b02bb49e-6986-4e6d-9374-034c6fa7bd05', 'Shrek', 2001,'Un ogro llamado Shrek vive en su pantano, pero su preciada soledad se ve súbitamente interrumpida por la invasión de los ruidosos personajes de los cuentos de hadas', false);
insert into movie_test(id, name, year_publication, description, in_Cinema) values('1e069707-f6e0-4711-8e59-710a7a3a303a', 'El Hobbit: la batalla de los cinco ejércitos', 2014, 'No description', false);