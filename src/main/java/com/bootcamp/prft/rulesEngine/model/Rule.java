package com.bootcamp.prft.rulesEngine.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class Rule {

    private String tableName;

    private String encoded;

    private List<Expression> expressions;

    private String decoded;
}