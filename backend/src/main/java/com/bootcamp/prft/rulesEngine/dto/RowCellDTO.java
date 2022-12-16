package com.bootcamp.prft.rulesEngine.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RowCellDTO<T> {
    private T value;
    private String cellName;
}
