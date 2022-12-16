package com.bootcamp.prft.rulesEngine.mapper;


import com.bootcamp.prft.rulesEngine.dto.ColumnInformationDTO;
import com.bootcamp.prft.rulesEngine.model.ColumnInformation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ColumnInformationMapper {
    ColumnInformation fromDTO(ColumnInformationDTO columnInformationDTO);
    ColumnInformationDTO fromColumnInformation(ColumnInformation columnInformation);
}
