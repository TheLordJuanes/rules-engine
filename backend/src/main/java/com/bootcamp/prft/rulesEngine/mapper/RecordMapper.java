package com.bootcamp.prft.rulesEngine.mapper;

import com.bootcamp.prft.rulesEngine.constant.TypeData;
import com.bootcamp.prft.rulesEngine.model.ColumnInformation;
import com.bootcamp.prft.rulesEngine.model.Row;
import com.bootcamp.prft.rulesEngine.model.RowCell;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

@AllArgsConstructor
public class RecordMapper implements RowMapper {

    private List<ColumnInformation> columnInformations;
    private final Dictionary<String, TypeData> dictionary;

    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        List<RowCell> cells = new ArrayList<>();
        for(ColumnInformation columnInformation : columnInformations){
            String dataTypeDB = columnInformation.getDataType();
            TypeData newDataType = dictionary.get(dataTypeDB);
            String columnName = columnInformation.getColumnName();
            switch (newDataType){
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
                    cells.add(booleanCell);;
                    break;
            }
        }
        return new Row(cells);
    }
}
