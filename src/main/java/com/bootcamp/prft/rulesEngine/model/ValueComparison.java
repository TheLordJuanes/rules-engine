package com.bootcamp.prft.rulesEngine.model;

import com.bootcamp.prft.rulesEngine.constant.RuleErrorCode;
import com.bootcamp.prft.rulesEngine.constant.TypeData;
import com.bootcamp.prft.rulesEngine.error.exception.RuleError;
import com.bootcamp.prft.rulesEngine.error.exception.RuleException;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Dictionary;
import java.util.List;

@Data
@AllArgsConstructor
@JsonDeserialize(as = ValueComparison.class)
public class ValueComparison extends Comparison {

    private String column;

    private String value;

    private String logicalOperator;

    @Override
    public String convertToSQL(List<ColumnInformation> columnsInformation, Dictionary<String, TypeData> dictionary) {
        ColumnInformation columnInfo = searchColumnInformationByName(columnsInformation, column);
        if(columnInfo == null){
            throw new RuleException(HttpStatus.BAD_REQUEST, new RuleError(RuleErrorCode.CODE_02, "The column " + column + " does not exist in the table"));
        }
        String dataTypeDB = columnInfo.getDataType();
        if (dictionary.get(dataTypeDB) == TypeData.String) {
            return column + " " + logicalOperator + " " + "'" + value + "'";
        }
        return column + " " + logicalOperator + " " + value;
    }

    private ColumnInformation searchColumnInformationByName(List<ColumnInformation> columnsInformation, String columnName){
        for (ColumnInformation columnInformation : columnsInformation) {
            if (columnInformation.getColumnName().equals(columnName)) {
                return columnInformation;
            }
        }
        return null;
    }
}