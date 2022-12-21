package com.bootcamp.prft.rulesEngine.api;

import com.bootcamp.prft.rulesEngine.dto.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping(path="api/v1/table")
public interface TableAPI {

    @PostMapping
    TableDTO getRowsByRule(@RequestBody RuleDTO ruleDTO);

    @PostMapping("/row")
    void addRow(@RequestBody RowDTO rowDTO, @RequestBody TableSimplifiedDTO tableSimplifiedDTO);

    @GetMapping
    List<TableSimplifiedDTO> getTables();

    @GetMapping("/{tableName}")
    List<ColumnInformationDTO> getColumns(@PathVariable String tableName);
}