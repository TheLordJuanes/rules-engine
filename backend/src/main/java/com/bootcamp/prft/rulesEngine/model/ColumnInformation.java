package com.bootcamp.prft.rulesEngine.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ColumnInformation{
    private String tableName;
    private String columnName;
    private String dataType;
}
