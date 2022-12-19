package com.bootcamp.prft.rulesEngine.model;

import com.bootcamp.prft.rulesEngine.constant.TypeData;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Dictionary;
import java.util.List;

@Data
@AllArgsConstructor
@JsonDeserialize(as = ColumnComparison.class)
public class ColumnComparison extends Comparison {

    private String column;

    private String column1;

    private String logicalOperator;

    @Override
    public String convertToSQL(List<ColumnInformation> columnsInformation, Dictionary<String, TypeData> dictionary) {
        return column + " " + logicalOperator + " " + column1;
    }

    private void checkDataTypeOfColumns(List<ColumnInformation> columnsInformation){
        ColumnInformation columnInfo = searchColumnInformationByName(columnsInformation, column);
        ColumnInformation columnInfo1 = searchColumnInformationByName(columnsInformation, column1);

    }

    private ColumnInformation searchColumnInformationByName(List<ColumnInformation> columnsInformation, String columnName){
        for (int i = 0; i < columnsInformation.size(); i++) {
            if(columnsInformation.get(i).getColumnName().equals(columnName)){
                return columnsInformation.get(i);
            }
        }
        return null;
    }
}