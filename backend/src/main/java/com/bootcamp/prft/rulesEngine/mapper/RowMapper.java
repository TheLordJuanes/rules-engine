package com.bootcamp.prft.rulesEngine.mapper;

import com.bootcamp.prft.rulesEngine.dto.RowDTO;
import com.bootcamp.prft.rulesEngine.model.Row;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RowMapper {
    Row fromDTO(RowDTO rowDTO);
    RowDTO fromRow(Row row);
}
