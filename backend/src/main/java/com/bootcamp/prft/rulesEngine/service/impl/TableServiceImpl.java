package com.bootcamp.prft.rulesEngine.service.impl;

import com.bootcamp.prft.rulesEngine.constant.DictionaryDB;
import com.bootcamp.prft.rulesEngine.constant.TypeData;
import com.bootcamp.prft.rulesEngine.model.ColumnInformation;
import com.bootcamp.prft.rulesEngine.model.Row;
import com.bootcamp.prft.rulesEngine.model.Table;
import com.bootcamp.prft.rulesEngine.model.TableSimplified;
import com.bootcamp.prft.rulesEngine.repository.TableRepository;
import com.bootcamp.prft.rulesEngine.repository.impl.TableRepositoryPostgresImpl;
import com.bootcamp.prft.rulesEngine.service.TableService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Dictionary;
import java.util.List;

@Service
public class TableServiceImpl implements TableService {

    private final String CURRENT_DATA_BASE = "postgresql";
    private final JdbcTemplate jdbcTemplate;

    private TableRepository tableRepository;

    private Dictionary<String, TypeData> dictionary;

    public TableServiceImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
        DictionaryDB tempDictionaries = new DictionaryDB();
        dictionary = null;
        tableRepository = null;
        switch (CURRENT_DATA_BASE){
            case "postgresql":
                dictionary = tempDictionaries.getPostgresDictionay();
                tableRepository = new TableRepositoryPostgresImpl(jdbcTemplate, dictionary);
                break;
        }
    }

    @Override
    public Table getRowsByRule(String tableName) {
        return tableRepository.getRowsByRule(tableName);
    }

    @Override
    public void addRow(Row row, String tableName) {
        tableRepository.saveRow(row, tableName);
    }

    @Override
    public List<TableSimplified> getTables() {
        return tableRepository.getTablesOfDataBase();
    }
}
