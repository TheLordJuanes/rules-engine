package com.bootcamp.prft.rulesEngine.controller;

import com.bootcamp.prft.rulesEngine.api.TableAPI;
import com.bootcamp.prft.rulesEngine.dto.*;
import com.bootcamp.prft.rulesEngine.mapper.*;
import com.bootcamp.prft.rulesEngine.service.TableService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@AllArgsConstructor
public class TableController implements TableAPI {

    private final TableService tableService;
    private final TableMapper tableMapper;
    private final RowMapper rowMapper;
    private final TableSimplifiedMapperDTO tableSimplifiedMapperDTO;
    private final RuleMapper ruleMapper;

    private final ColumnInformationMapper columnInformationMapper;

    @Override
    public TableDTO getRowsByRule(RuleDTO ruleDTO) {
        return tableMapper.fromTable(tableService.getRowsByRule(ruleMapper.fromDTO(ruleDTO)));
    }

    @Override
    public void addRow(RowDTO rowDTO, TableSimplifiedDTO tableSimplifiedDTO) {
        tableService.addRow(rowMapper.fromDTO(rowDTO), tableSimplifiedDTO.getTableName());
    }

    @Override
    public List<TableSimplifiedDTO> getTables() {
        return tableSimplifiedMapperDTO.fromTableSimplified(tableService.getTables());
    }

    @Override
    public List<ColumnInformationDTO> getColumns(String tableName) {
        return columnInformationMapper.fromColumnInformation(tableService.getColumns(tableName));
    }
}