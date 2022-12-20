package com.bootcamp.prft.rulesEngine.api;

import com.bootcamp.prft.rulesEngine.dto.RowDTO;
import com.bootcamp.prft.rulesEngine.dto.RuleDTO;
import com.bootcamp.prft.rulesEngine.dto.TableDTO;
import com.bootcamp.prft.rulesEngine.dto.TableSimplifiedDTO;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping(path="api/v1/table")
public interface TableAPI {

    @PostMapping
    TableDTO getRowsByRule(@RequestBody RuleDTO ruleDTO);

    @PostMapping("row")
    void addRow(@RequestBody RowDTO rowDTO, @RequestBody TableSimplifiedDTO tableSimplifiedDTO);

    @GetMapping
    List<TableSimplifiedDTO> getTables();
}