package com.bootcamp.prft.rulesEngine.controller;

import com.bootcamp.prft.rulesEngine.api.LoginAPI;
import com.bootcamp.prft.rulesEngine.dto.LoginDTO;
import com.bootcamp.prft.rulesEngine.dto.TokenDTO;
import com.bootcamp.prft.rulesEngine.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class LoginController implements LoginAPI {

    private final LoginService loginService;

    @Override
    public TokenDTO login(LoginDTO loginDTO) {
        return loginService.login(loginDTO);
    }
}