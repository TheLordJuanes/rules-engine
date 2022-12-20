package com.bootcamp.prft.rulesEngine.controller;

import com.bootcamp.prft.rulesEngine.api.TableAPI;
import com.bootcamp.prft.rulesEngine.dto.RowDTO;
import com.bootcamp.prft.rulesEngine.dto.RuleDTO;
import com.bootcamp.prft.rulesEngine.dto.TableDTO;
import com.bootcamp.prft.rulesEngine.dto.TableSimplifiedDTO;
import com.bootcamp.prft.rulesEngine.mapper.RowMapper;
import com.bootcamp.prft.rulesEngine.mapper.RuleMapper;
import com.bootcamp.prft.rulesEngine.mapper.TableMapper;
import com.bootcamp.prft.rulesEngine.mapper.TableSimplifiedMapperDTO;
import com.bootcamp.prft.rulesEngine.service.TableService;
import lombok.AllArgsConstructor;
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
}