CREATE TABLE IF NOT EXISTS expressions(
    expression_id UUID PRIMARY KEY,
    number INT UNIQUE NOT NULL,
    comparison_id UUID,
    FOREIGN KEY (comparison_id) REFERENCES springjdbc.public.comparisons(comparison_id)
);