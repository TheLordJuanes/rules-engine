package com.bootcamp.prft.rulesEngine.mapper;

import com.bootcamp.prft.rulesEngine.dto.TableSimplifiedDTO;
import com.bootcamp.prft.rulesEngine.model.TableSimplified;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TableSimplifiedMapperDTO {

    TableSimplified fromDTO(TableSimplifiedDTO tableSimplifiedDTO);
    List<TableSimplifiedDTO> fromTableSimplified(List<TableSimplified> tableSimplified);
}