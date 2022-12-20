package com.bootcamp.prft.rulesEngine.mapper;

import com.bootcamp.prft.rulesEngine.model.TableSimplified;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableSimplifiedMapperSQL implements RowMapper<TableSimplified>{

    @Override
    public TableSimplified mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new TableSimplified(
                rs.getString("table_name")
        );
    }
}