package com.bootcamp.prft.rulesEngine.mapper;

import com.bootcamp.prft.rulesEngine.dto.ColumnInformationDTO;
import com.bootcamp.prft.rulesEngine.model.ColumnInformation;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ColumnInformationMapper {

    ColumnInformation fromDTO(ColumnInformationDTO columnInformationDTO);
    List<ColumnInformationDTO> fromColumnInformation(List<ColumnInformation> columnInformation);
    ColumnInformationDTO fromColumnInformation(ColumnInformation columnInformation);
}