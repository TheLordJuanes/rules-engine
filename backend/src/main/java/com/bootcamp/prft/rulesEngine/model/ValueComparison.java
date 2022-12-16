package com.bootcamp.prft.rulesEngine.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@JsonDeserialize(as = ValueComparison.class)
public class ValueComparison extends Comparison{
    private String column;
    private String value;
    private String logicOperator;

    @Override
    public String convertToSQL(List<ColumnInformation> columnsInformation, String currentDataBase) {
        return null;
    }
}
