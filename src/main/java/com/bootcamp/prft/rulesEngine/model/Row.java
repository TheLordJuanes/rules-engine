package com.bootcamp.prft.rulesEngine.model;

import lombok.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Row {

    private List<RowCell> row;
}