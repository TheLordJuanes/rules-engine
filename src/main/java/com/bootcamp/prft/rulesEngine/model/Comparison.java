package com.bootcamp.prft.rulesEngine.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.List;

@JsonDeserialize(using = ComparisonDeserializer.class)
public abstract class Comparison {

    public abstract String convertToSQL(List<ColumnInformation> columnsInformation, String currentDataBase);
}