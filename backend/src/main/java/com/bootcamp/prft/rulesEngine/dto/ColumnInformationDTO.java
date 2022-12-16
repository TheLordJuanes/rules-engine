package com.bootcamp.prft.rulesEngine.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColumnInformationDTO {
    private String tableName;
    private String columnName;
    private String dataType;
}
