package com.bootcamp.prft.rulesEngine.mapper;

import com.bootcamp.prft.rulesEngine.model.ColumnInformation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
@SpringBootTest
public class ColumnInformationRowMapperTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testMapRow(){
        String sql = " SELECT table_name, column_name, data_type FROM information_schema.columns WHERE table_name = 'TATABROS'";
        List<ColumnInformation> columnInformationList = jdbcTemplate.query(sql, new ColumnInformationRowMapper());
        assertEquals(9, columnInformationList.size());
        assertEquals("TATABROS", columnInformationList.get(0).getTableName());
        assertEquals("ID", columnInformationList.get(0).getColumnName());
        assertEquals("UUID", columnInformationList.get(0).getDataType());

        assertEquals("TATABROS", columnInformationList.get(1).getTableName());
        assertEquals("NAME", columnInformationList.get(1).getColumnName());
        assertEquals("CHARACTER VARYING", columnInformationList.get(1).getDataType());

        assertEquals("TATABROS", columnInformationList.get(2).getTableName());
        assertEquals("SEX", columnInformationList.get(2).getColumnName());
        assertEquals("CHARACTER VARYING", columnInformationList.get(2).getDataType());

        assertEquals("TATABROS", columnInformationList.get(3).getTableName());
        assertEquals("WEIGHT", columnInformationList.get(3).getColumnName());
        assertEquals("DOUBLE PRECISION", columnInformationList.get(3).getDataType());

        assertEquals("TATABROS", columnInformationList.get(4).getTableName());
        assertEquals("AGE", columnInformationList.get(4).getColumnName());
        assertEquals("INTEGER", columnInformationList.get(4).getDataType());

        assertEquals("TATABROS", columnInformationList.get(5).getTableName());
        assertEquals("HEIGHT", columnInformationList.get(5).getColumnName());
        assertEquals("DOUBLE PRECISION", columnInformationList.get(5).getDataType());

        assertEquals("TATABROS", columnInformationList.get(6).getTableName());
        assertEquals("ARRIVAL_DATE", columnInformationList.get(6).getColumnName());
        assertEquals("TIMESTAMP", columnInformationList.get(6).getDataType());

        assertEquals("TATABROS", columnInformationList.get(7).getTableName());
        assertEquals("FATHER_ID", columnInformationList.get(7).getColumnName());
        assertEquals("UUID", columnInformationList.get(7).getDataType());

        assertEquals("TATABROS", columnInformationList.get(8).getTableName());
        assertEquals("MOTHER_ID", columnInformationList.get(8).getColumnName());
        assertEquals("UUID", columnInformationList.get(8).getDataType());
    }
}
