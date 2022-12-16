package com.bootcamp.prft.rulesEngine.service;

import com.bootcamp.prft.rulesEngine.model.Row;
import com.bootcamp.prft.rulesEngine.model.Table;
import org.springframework.stereotype.Service;

@Service
public interface TableService {
   Table getRowsByRule(String tableName);
   void addRow(Row row, String tableName);
}
