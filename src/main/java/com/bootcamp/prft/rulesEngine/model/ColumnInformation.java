package com.bootcamp.prft.rulesEngine.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ColumnInformation {

    private String tableName;

    private String columnName;

    private String dataType;
}