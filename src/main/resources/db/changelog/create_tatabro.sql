CREATE TABLE IF NOT EXISTS tatabros(
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