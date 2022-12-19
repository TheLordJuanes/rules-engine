package com.bootcamp.prft.rulesEngine.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class RuleException extends RuntimeException{
    private HttpStatus httpStatus;
    private RuleError error;
}