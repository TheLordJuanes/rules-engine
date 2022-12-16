package com.bootcamp.prft.rulesEngine.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Expression {
    @JsonProperty("number")
    private int number;
    @JsonProperty("comparison")
    @JsonAlias({"value", "column"})
    private Comparison comparison;
}
