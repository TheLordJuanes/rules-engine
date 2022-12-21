package com.bootcamp.prft.rulesEngine.model;

import com.bootcamp.prft.rulesEngine.constant.RuleErrorCode;
import com.bootcamp.prft.rulesEngine.constant.TypeData;
import com.bootcamp.prft.rulesEngine.error.exception.RuleError;
import com.bootcamp.prft.rulesEngine.error.exception.RuleException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.http.HttpStatus;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.Dictionary;
import java.util.List;
import java.util.UUID;

@Data
@Table(name = "comparisons")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comparison {

    @Id
    @Type(type="org.hibernate.type.PostgresUUIDType")
    @Column(name = "comparison_id")
    private UUID id;
    private String type;
    private String value;
    private String value1;
    @Column(name = "logical_operator")
    private String logicalOperator;

    @PrePersist
    public void generateId(){
        this.id = UUID.randomUUID();
    }

    public String convertToSQL(List<ColumnInformation> columnsInformation, Dictionary<String, TypeData> dictionary){
        switch (type){
            case "value":
                return convertToSQLForTypeValue(columnsInformation, dictionary);
            case "column":
                return value + " " + logicalOperator + " " + value1;
            default:
                throw new RuleException(HttpStatus.BAD_REQUEST, new RuleError(RuleErrorCode.CODE_03, RuleErrorCode.CODE_03.getMessage()));
        }
    }

    public String convertToSQLForTypeValue(List<ColumnInformation> columnsInformation, Dictionary<String, TypeData> dictionary) {
        ColumnInformation columnInfo = searchColumnInformationByName(columnsInformation, value);
        if(columnInfo == null){
            throw new RuleException(HttpStatus.BAD_REQUEST, new RuleError(RuleErrorCode.CODE_02, "The column " + value + " does not exist in the table"));
        }
        String dataTypeDB = columnInfo.getDataType();
        if (dictionary.get(dataTypeDB) == TypeData.String) {
            return value + " " + logicalOperator + " " + "'" + value1 + "'";
        }
        return value + " " + logicalOperator + " " + value1;
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