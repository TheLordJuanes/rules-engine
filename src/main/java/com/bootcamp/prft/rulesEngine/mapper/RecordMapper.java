package com.bootcamp.prft.rulesEngine.mapper;

import com.bootcamp.prft.rulesEngine.constant.RuleErrorCode;
import com.bootcamp.prft.rulesEngine.constant.TypeData;
import com.bootcamp.prft.rulesEngine.error.exception.RuleError;
import com.bootcamp.prft.rulesEngine.error.exception.RuleException;
import com.bootcamp.prft.rulesEngine.model.ColumnInformation;
import com.bootcamp.prft.rulesEngine.model.Row;
import com.bootcamp.prft.rulesEngine.model.RowCell;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class RecordMapper implements RowMapper<Row> {

    private List<ColumnInformation> columnInformations;
    private final Dictionary<String, TypeData> dictionary;

    @Override
    public Row mapRow(ResultSet resultSet, int i) throws SQLException {
        List<RowCell> cells = new ArrayList<>();
        for(ColumnInformation columnInformation : columnInformations) {
            String dataTypeDB = columnInformation.getDataType().toLowerCase();
            TypeData newDataType = Optional.ofNullable(dictionary.get(dataTypeDB))
                    .orElseThrow( () -> new RuleException(HttpStatus.BAD_REQUEST, new RuleError(RuleErrorCode.CODE_04, "The data type " + dataTypeDB + " is not supported")));
            String columnName = columnInformation.getColumnName();
            switch (newDataType) {
                case Integer:
                    RowCell<Integer> integerCell = new RowCell<>(
                            resultSet.getInt(columnName),
                            columnName);
                    cells.add(integerCell);
                    break;
                case String:
                    RowCell<String> stringCell = new RowCell<>(
                            resultSet.getString(columnName),
                            columnName);
                    cells.add(stringCell);
                    break;
                case Boolean:
                    RowCell<Boolean> booleanCell = new RowCell<>(
                            resultSet.getBoolean(columnName),
                            columnName);
                    cells.add(booleanCell);
                    break;
                case Double:
                    RowCell<Double> doubleCell = new RowCell<>(
                            resultSet.getDouble(columnName),
                            columnName);
                    cells.add(doubleCell);
                    break;
            }
        }
        return new Row(cells);
    }
}