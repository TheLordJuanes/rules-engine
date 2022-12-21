package com.bootcamp.prft.rulesEngine.service;

import com.bootcamp.prft.rulesEngine.model.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface TableService {

   Table getRowsByRule(Rule rule);
   void addRow(Row row, String tableName);
   List<TableSimplified> getTables();
   List<ColumnInformation> getColumns(String tableName);
}