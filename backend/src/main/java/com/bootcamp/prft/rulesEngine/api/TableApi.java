package com.bootcamp.prft.rulesEngine.api;

import com.bootcamp.prft.rulesEngine.dto.RowDTO;
import com.bootcamp.prft.rulesEngine.dto.TableDTO;
import com.bootcamp.prft.rulesEngine.dto.TableSimplifiedDTO;
import com.bootcamp.prft.rulesEngine.model.Rule;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path="api/v1/tables")
public interface TableApi {

    @PostMapping
    TableDTO getRowsByRule(@RequestBody Rule rule);

    @PostMapping("/{tableName}") //remove query string parameter
    void addRow(@RequestBody RowDTO rowDTO, @PathVariable String tableName);

    @GetMapping
    List<TableSimplifiedDTO> getTables();
}
