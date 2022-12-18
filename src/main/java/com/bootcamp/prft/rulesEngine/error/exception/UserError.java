package com.bootcamp.prft.rulesEngine.error.exception;

import com.bootcamp.prft.rulesEngine.constant.UserErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserError {

    private UserErrorCode code;
    private String message;
}