package com.bootcamp.prft.rulesEngine.mapper;

import com.bootcamp.prft.rulesEngine.model.TableSimplified;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
@SpringBootTest
public class TableSimplifiedMapperSQLTest {
    @Qualifier("jdbcTemplate")
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testMapRow(){
        List<TableSimplified> tablesSimplified = jdbcTemplate.query("SHOW TABLES", new TableSimplifiedMapperSQL());
        assertEquals(true, existTable("DATABASECHANGELOG", tablesSimplified));
        assertEquals(true, existTable("DATABASECHANGELOGLOCK", tablesSimplified));
        assertEquals(true, existTable("TATABROS_TEST", tablesSimplified));
        assertEquals(true, existTable("MOVIE_TEST", tablesSimplified));
    }

    private boolean existTable(String tableName, List<TableSimplified> list){
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getTableName().equals(tableName))
                return true;
        }
        return false;
    }
}
