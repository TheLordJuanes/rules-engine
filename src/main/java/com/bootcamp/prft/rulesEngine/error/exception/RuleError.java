package com.bootcamp.prft.rulesEngine.error.exception;

import com.bootcamp.prft.rulesEngine.constant.RuleErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RuleError {
    private RuleErrorCode code;
    private String message;
}