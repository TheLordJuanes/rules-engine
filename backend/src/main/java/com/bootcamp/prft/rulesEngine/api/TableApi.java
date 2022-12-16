package com.bootcamp.prft.rulesEngine.api;

import com.bootcamp.prft.rulesEngine.dto.RowDTO;
import com.bootcamp.prft.rulesEngine.dto.TableDTO;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path="api/v1/tables")
public interface TableApi {

    @GetMapping("/{tableName}")
    TableDTO getRowsByRule(@PathVariable String tableName);

    @PostMapping("/{tableName}")
    void addRow(@RequestBody RowDTO rowDTO, @PathVariable String tableName);

}
