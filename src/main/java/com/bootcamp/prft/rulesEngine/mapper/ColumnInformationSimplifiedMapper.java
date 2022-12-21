package com.bootcamp.prft.rulesEngine.mapper;

import com.bootcamp.prft.rulesEngine.constant.RuleErrorCode;
import com.bootcamp.prft.rulesEngine.constant.TypeData;
import com.bootcamp.prft.rulesEngine.error.exception.RuleError;
import com.bootcamp.prft.rulesEngine.error.exception.RuleException;
import com.bootcamp.prft.rulesEngine.model.ColumnInformation;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Dictionary;
import java.util.Optional;

@AllArgsConstructor
public class ColumnInformationSimplifiedMapper implements RowMapper<ColumnInformation> {

    private final Dictionary<String, TypeData> dictionary;

    @Override
    public ColumnInformation mapRow(ResultSet rs, int rowNum) throws SQLException {
        String tableName = rs.getString("table_name");
        String simpleDataType = convertDataType(rs.getString("data_type"));
        return new ColumnInformation(
                tableName,
                rs.getString("column_name"),
                simpleDataType
        );
    }

    private String convertDataType(String dataType){
        TypeData newDataType = Optional.ofNullable(dictionary.get(dataType))
                .orElseThrow( () -> new RuleException(HttpStatus.BAD_REQUEST, new RuleError(RuleErrorCode.CODE_04, "The data type " + dataType + " is not supported")));
        switch (newDataType){
            case String:
                return "String";
            case Boolean:
                return "Boolean";
            default:
                return "Number";
        }
    }
}
