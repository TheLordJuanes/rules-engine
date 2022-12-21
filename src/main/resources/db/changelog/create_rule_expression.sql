CREATE TABLE IF NOT EXISTS rule_expression(
    rule_id UUID,
    expression_id UUID,
    PRIMARY KEY (rule_id, expression_id),
    FOREIGN KEY (rule_id) REFERENCES springjdbc.public.rules(rule_id),
    FOREIGN KEY (expression_id) REFERENCES springjdbc.public.expressions(expression_id)
);