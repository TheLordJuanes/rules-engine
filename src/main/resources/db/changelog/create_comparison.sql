CREATE TABLE IF NOT EXISTS comparisons(
    comparison_id UUID PRIMARY KEY,
    type VARCHAR(255) NOT NULL,
    valuee VARCHAR(255) NOT NULL,
    value1 VARCHAR(255) NOT NULL,
    logical_operator VARCHAR(255) NOT NULL
);