package com.bootcamp.prft.rulesEngine.mapper;

import com.bootcamp.prft.rulesEngine.dto.TableDTO;
import com.bootcamp.prft.rulesEngine.model.Table;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TableMapper {
    Table fromDTO(TableDTO table);
    TableDTO fromTable(Table table);
}
