package com.bootcamp.prft.rulesEngine.controller;


import com.bootcamp.prft.rulesEngine.api.TableApi;
import com.bootcamp.prft.rulesEngine.dto.RowDTO;
import com.bootcamp.prft.rulesEngine.dto.TableDTO;
import com.bootcamp.prft.rulesEngine.dto.TableSimplifiedDTO;
import com.bootcamp.prft.rulesEngine.mapper.RowMapper;
import com.bootcamp.prft.rulesEngine.mapper.TableMapper;
import com.bootcamp.prft.rulesEngine.mapper.TableSimplifiedMapperDTO;
import com.bootcamp.prft.rulesEngine.model.Rule;
import com.bootcamp.prft.rulesEngine.service.TableService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class TableController implements TableApi {

    private final TableService tableService;
    private final TableMapper tableMapper;

    private final RowMapper rowMapper;

    private final TableSimplifiedMapperDTO tableSimplifiedMapperDTO;

    @Override
    public TableDTO getRowsByRule(Rule rule) {
        //TO DO
        return null;
//        return tableMapper.fromTable(tableService.getRowsByRule(tableName));
    }

    @Override
    public void addRow(RowDTO rowDTO, String tableName) {
        tableService.addRow(rowMapper.fromDTO(rowDTO), tableName);
    }

    @Override
    public List<TableSimplifiedDTO> getTables() {
        return tableSimplifiedMapperDTO.fromTableSimplified(tableService.getTables());
    }


}
