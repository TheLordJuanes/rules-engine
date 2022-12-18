package com.bootcamp.prft.rulesEngine.dto;

import com.bootcamp.prft.rulesEngine.model.Expression;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RuleDTO {

    private String tableName;
    private String encoded;
    private List<Expression> expressions;
    private String decoded;
}