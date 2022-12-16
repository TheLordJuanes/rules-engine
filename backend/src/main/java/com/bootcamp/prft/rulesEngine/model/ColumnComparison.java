package com.bootcamp.prft.rulesEngine.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@JsonDeserialize(as = ColumnComparison.class)
public class ColumnComparison extends Comparison{
    private String column;
    private String column1;
    private String logicOperator;

    @Override
    public String convertToSQL(List<ColumnInformation> columnsInformation, String currentDataBase) {
        return null;
    }
}
