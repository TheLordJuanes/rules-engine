package com.bootcamp.prft.rulesEngine.api;

import com.bootcamp.prft.rulesEngine.dto.RowDTO;
import com.bootcamp.prft.rulesEngine.dto.RuleDTO;
import com.bootcamp.prft.rulesEngine.dto.TableDTO;
import com.bootcamp.prft.rulesEngine.dto.TableSimplifiedDTO;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping(path="api/v1/tables")
public interface TableAPI {

    @PostMapping
    TableDTO getRowsByRule(@RequestBody RuleDTO ruleDTO);

    @PostMapping("/{tableName}") //remove query string parameter
    void addRow(@RequestBody RowDTO rowDTO, @PathVariable String tableName);

    @GetMapping
    List<TableSimplifiedDTO> getTables();
}