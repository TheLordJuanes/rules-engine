package com.bootcamp.prft.rulesEngine.dto;

import com.bootcamp.prft.rulesEngine.model.Comparison;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpressionDTO {
    private int number;
    private ComparisonDTO comparison;
}
