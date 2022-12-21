package com.bootcamp.prft.rulesEngine.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RuleErrorCode {
    CODE_01("The rule format is invalid"),
    CODE_02("An entered column does not exist in the table"),
    CODE_03("The type of the comparison is invalid"),
    CODE_04("There is one data type of column that is not supported"),
    CODE_05("Missing permission to access the table");

    private final String message;
}