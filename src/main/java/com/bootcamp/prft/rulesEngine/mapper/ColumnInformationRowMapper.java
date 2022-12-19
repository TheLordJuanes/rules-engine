package com.bootcamp.prft.rulesEngine.mapper;

import com.bootcamp.prft.rulesEngine.model.ColumnInformation;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ColumnInformationRowMapper implements RowMapper<ColumnInformation> {
    @Override
    public ColumnInformation mapRow(ResultSet resultSet, int i) throws SQLException {
        return new ColumnInformation(
                resultSet.getString("table_name"),
                resultSet.getString("column_name"),
                resultSet.getString("data_type")
        );
    }
}