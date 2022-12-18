package com.bootcamp.prft.rulesEngine.service;

import com.bootcamp.prft.rulesEngine.dto.LoginDTO;
import com.bootcamp.prft.rulesEngine.dto.TokenDTO;
import org.springframework.web.bind.annotation.RequestBody;

public interface LoginService {

    TokenDTO login(@RequestBody LoginDTO loginDTO);
}