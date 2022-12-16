package com.bootcamp.prft.rulesEngine.repository.impl;

import com.bootcamp.prft.rulesEngine.constant.TypeData;
import com.bootcamp.prft.rulesEngine.mapper.ColumnInformationRowMapper;
import com.bootcamp.prft.rulesEngine.mapper.RecordMapper;
import com.bootcamp.prft.rulesEngine.model.ColumnInformation;
import com.bootcamp.prft.rulesEngine.model.Row;
import com.bootcamp.prft.rulesEngine.model.RowCell;
import com.bootcamp.prft.rulesEngine.model.Table;
import com.bootcamp.prft.rulesEngine.repository.TableRepository;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Dictionary;
import java.util.List;

@AllArgsConstructor
public class TableRepositoryImpl implements TableRepository {

    private final JdbcTemplate jdbcTemplate;
    private Dictionary<String, TypeData> dictionary;

    @Override
    public List<ColumnInformation> getInfoOfTableColumns(String tableName) {
        String sql = " SELECT table_name, column_name, data_type FROM information_schema.columns WHERE table_name = ?;";
        return jdbcTemplate.query(sql, new ColumnInformationRowMapper(), tableName);
    }

    @Override
    public Table getRowsByRule(String tableName) {
        List<ColumnInformation> columnsInformation = getInfoOfTableColumns(tableName);
        String sql = "select " + getColumnsNamesForSelect(columnsInformation)+" from " + tableName;

        List<Row> rows = jdbcTemplate.query(sql, new RecordMapper(columnsInformation, dictionary));
        return new Table(rows);
    }

    private String getColumnsNamesForSelect(List<ColumnInformation> columnInformation){
        String columnsNames = "";
        for (int i = 0; i < columnInformation.size()-1; i++) {
            columnsNames += columnInformation.get(i).getColumnName()+", ";
        }
        columnsNames += columnInformation.get(columnInformation.size()-1).getColumnName();
        return columnsNames;
    }

    @Override
    public void saveRow(Row row, String tableName) {
        String sql = "INSERT INTO " + getTableForInsert(row, tableName) + " VALUES " + getValuesForInsert(row);
        jdbcTemplate.update(sql);
    }

    private String getTableForInsert(Row row, String tableName){
        String tableWithColumns = tableName+"(";
        List<RowCell> cells = row.getRow();
        for (int i = 0; i < cells.size()-1; i++) {
            tableWithColumns += cells.get(i).getCellName() + ", ";
        }
        tableWithColumns += cells.get(cells.size()-1).getCellName()+")";
        return tableWithColumns;
    }

    private String getValuesForInsert(Row row){
        String values = "(";
        List<RowCell> cells = row.getRow();
        for (int i = 0; i < cells.size()-1; i++) {
            String value = getValue(cells, i);
            values += value + ", ";
        }
        String value = getValue(cells, cells.size()-1);
        values += value +")";
        return values;
    }

    private String getValue(List<RowCell> cells ,int i){
        String value = cells.get(i).getValue().toString();
        if(cells.get(i).getValue() instanceof String){
            value = "'"+value+"'";
        }
        return value;
    }

}
