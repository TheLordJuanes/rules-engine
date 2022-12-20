package com.bootcamp.prft.rulesEngine.mapper;

import com.bootcamp.prft.rulesEngine.constant.DictionaryDB;
import com.bootcamp.prft.rulesEngine.constant.TypeData;
import com.bootcamp.prft.rulesEngine.dto.RowCellDTO;
import com.bootcamp.prft.rulesEngine.error.exception.RuleException;
import com.bootcamp.prft.rulesEngine.model.ColumnInformation;
import com.bootcamp.prft.rulesEngine.model.Row;
import com.bootcamp.prft.rulesEngine.model.RowCell;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
@SpringBootTest
public class RecordMapperTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private Dictionary<String, TypeData> dictionary;
    private List<ColumnInformation> columnInformationList;

    private List<Row> predefinedRows;

    public void stage1Postgresql(){
        DictionaryDB dictionaryDB = new DictionaryDB();
        dictionary = dictionaryDB.getPostgresqlDictionary();
        columnInformationList = new ArrayList<>();
        columnInformationList.add(new ColumnInformation("TATABROS", "ID", "UUID"));
        columnInformationList.add(new ColumnInformation("TATABROS", "NAME", "CHARACTER VARYING"));
        columnInformationList.add(new ColumnInformation("TATABROS", "SEX", "CHARACTER VARYING"));
        columnInformationList.add(new ColumnInformation("TATABROS", "WEIGHT", "DOUBLE PRECISION"));
        columnInformationList.add(new ColumnInformation("TATABROS", "AGE", "INTEGER"));
        columnInformationList.add(new ColumnInformation("TATABROS", "HEIGHT", "DOUBLE PRECISION"));
        columnInformationList.add(new ColumnInformation("TATABROS", "ARRIVAL_DATE", "TIMESTAMP"));
        columnInformationList.add(new ColumnInformation("TATABROS", "FATHER_ID", "UUID"));
        columnInformationList.add(new ColumnInformation("TATABROS", "MOTHER_ID", "UUID"));
        predefinedRows = new ArrayList<>();
        List<RowCell> cells = new ArrayList<>();
        cells.add(new RowCell<>("b55d9d91-2d6f-48f6-9442-8f654a0aba47", "ID"));
        cells.add(new RowCell<>("Tommy", "NAME"));
        cells.add(new RowCell<>("M", "SEX"));
        cells.add(new RowCell<>(24.0, "WEIGHT"));
        cells.add(new RowCell<>(18, "AGE"));
        cells.add(new RowCell<>(53.0, "HEIGHT"));
        cells.add(new RowCell<>("2018-12-31 10:10:10", "ARRIVAL_DATE"));
        cells.add(new RowCell<>(null, "FATHER_ID"));
        cells.add(new RowCell<>(null, "MOTHER_ID"));
        predefinedRows.add(new Row(cells));
    }

    public void stage2Postgresql(){
        DictionaryDB dictionaryDB = new DictionaryDB();
        dictionary = dictionaryDB.getPostgresqlDictionary();
        columnInformationList = new ArrayList<>();
        columnInformationList.add(new ColumnInformation("TATABROS", "ID", "UUID"));
        columnInformationList.add(new ColumnInformation("TATABROS", "NAME", "CHARACTER VARYING"));
        columnInformationList.add(new ColumnInformation("TATABROS", "SEX", "CHARACTER VARYING"));
        columnInformationList.add(new ColumnInformation("TATABROS", "WEIGHT", "DOUBLE PRECISION"));
        columnInformationList.add(new ColumnInformation("TATABROS", "AGE", "INTEGER"));
        columnInformationList.add(new ColumnInformation("TATABROS", "HEIGHT", "DOUBLE PRECISION"));
        columnInformationList.add(new ColumnInformation("TATABROS", "ARRIVAL_DATE", "TIMESTAMP"));
        columnInformationList.add(new ColumnInformation("TATABROS", "FATHER_ID", "UUIDD"));
        columnInformationList.add(new ColumnInformation("TATABROS", "MOTHER_ID", "UUID"));
    }

    @Test
    public void testMapRow(){
        stage1Postgresql();
        String sql = "SELECT * FROM TATABROS WHERE NAME = 'Tommy'";
        List<Row> rows = jdbcTemplate.query(sql, new RecordMapper(columnInformationList, dictionary));
        assertEquals(1, rows.size());
        assertEquals(rows.get(0), predefinedRows.get(0));
    }

    @Test
    public void testMapRowWithException(){
        stage2Postgresql();
        String sql = "SELECT * FROM TATABROS WHERE NAME = 'Tommy'";
        assertThrows(RuleException.class, () -> jdbcTemplate.query(sql, new RecordMapper(columnInformationList, dictionary)), "The data type uuidd is not supported");
    }
}
