package com.bootcamp.prft.rulesEngine.repository;

import com.bootcamp.prft.rulesEngine.model.*;

import java.util.List;

public interface TableRepository {

    List<ColumnInformation> getInfoOfTableColumns(String tableName);
    Table getRowsByRule(Rule rule);
    void saveRow(Row row, String tableName);
    List<TableSimplified> getTablesOfDataBase();
}