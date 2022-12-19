package com.bootcamp.prft.rulesEngine.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RuleErrorCode {
    CODE_01("The rule format is invalid"),
    CODE_02("An entered column does not exist in the table");

    private final String message;
}