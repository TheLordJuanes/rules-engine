package com.bootcamp.prft.rulesEngine.mapper;

import com.bootcamp.prft.rulesEngine.dto.RowCellDTO;
import com.bootcamp.prft.rulesEngine.model.RowCell;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RowCellMapper {
    RowCell fromDTO(RowCellDTO rowCell);
    RowCellDTO fromRowCell(RowCell rowCell);
}
