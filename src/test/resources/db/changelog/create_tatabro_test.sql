CREATE TABLE IF NOT EXISTS tatabros_test(
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    sex VARCHAR(255) NOT NULL,
    weight DOUBLE PRECISION NOT NULL,
    age INTEGER NOT NULL,
    height DOUBLE PRECISION NOT NULL,
    arrival_date TIMESTAMP NOT NULL,
    father_id UUID,
    mother_id UUID
);

INSERT INTO tatabros_test (id, name, sex, weight, age, height, arrival_date, father_id, mother_id) VALUES ('b55d9d91-2d6f-48f6-9442-8f654a0aba47', 'Tommy', 'M', '24.0', '18', '53.0', '2018-12-31T10:10:10', null, null);
INSERT INTO tatabros_test (id, name, sex, weight, age, height, arrival_date, father_id, mother_id) VALUES ('5631cbd3-cf53-415f-bd06-4e995ee3c322', 'Dory', 'F', '25.0', '17', '55.0', '2019-12-19T11:18:10', null, null);
INSERT INTO tatabros_test (id, name, sex, weight, age, height, arrival_date, father_id, mother_id) VALUES ('2f7908f1-c6c5-4fd9-bca9-07332d2c60e6', 'Piggy', 'M', '20.0', '15', '50.0', '2021-12-13T11:13:19', 'b55d9d91-2d6f-48f6-9442-8f654a0aba47', '5631cbd3-cf53-415f-bd06-4e995ee3c322');
