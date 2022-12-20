package com.bootcamp.prft.rulesEngine.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComparisonDTO {
    private String type;
    private String value;
    private String value1;
    private String logicalOperator;
}
