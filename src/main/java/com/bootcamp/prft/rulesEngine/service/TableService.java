package com.bootcamp.prft.rulesEngine.service;

import com.bootcamp.prft.rulesEngine.model.Row;
import com.bootcamp.prft.rulesEngine.model.Table;
import com.bootcamp.prft.rulesEngine.model.TableSimplified;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface TableService {

   Table getRowsByRule(String tableName);
   void addRow(Row row, String tableName);
   List<TableSimplified> getTables();
}