package com.bootcamp.prft.rulesEngine.repository;

import com.bootcamp.prft.rulesEngine.model.ColumnInformation;
import com.bootcamp.prft.rulesEngine.model.Row;
import com.bootcamp.prft.rulesEngine.model.Table;
import com.bootcamp.prft.rulesEngine.model.TableSimplified;

import java.util.List;

public interface TableRepository {

    List<ColumnInformation> getInfoOfTableColumns(String tableName);
    Table getRowsByRule(String tableName);

    void saveRow(Row row, String tableName);

    List<TableSimplified> getTablesOfDataBase();

}
