package com.bootcamp.prft.rulesEngine.service.impl;

import com.bootcamp.prft.rulesEngine.constant.UserErrorCode;
import com.bootcamp.prft.rulesEngine.dto.LoginDTO;
import com.bootcamp.prft.rulesEngine.dto.TokenDTO;
import com.bootcamp.prft.rulesEngine.error.exception.UserError;
import com.bootcamp.prft.rulesEngine.error.exception.UserException;
import com.bootcamp.prft.rulesEngine.model.User;
import com.bootcamp.prft.rulesEngine.repository.UserRepository;
import com.bootcamp.prft.rulesEngine.service.LoginService;
import com.bootcamp.prft.rulesEngine.utils.JWTParser;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Service
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;

    @Override
    public TokenDTO login(LoginDTO loginDTO) {
        User user = StreamSupport.stream(userRepository.findAll().spliterator(),false).filter(user1 -> user1.getEmail().equals(loginDTO.getEmailPhone()) || user1.getPhoneNumber().equals((loginDTO.getEmailPhone()))).findFirst().orElseThrow(() -> new UserException(HttpStatus.BAD_REQUEST, new UserError(UserErrorCode.CODE_03, UserErrorCode.CODE_03.getMessage())));
        if (user.getPassword().equals(loginDTO.getPassword())) {
            Map<String, String> claims = new HashMap<>();
            claims.put("userId", user.getId().toString());
            return new TokenDTO(JWTParser.createJWT(user.getId().toString(), user.getEmail(), user.getEmail(), claims,3000000000000000000L));
        }
        throw new UserException(HttpStatus.BAD_REQUEST, new UserError(UserErrorCode.CODE_03, UserErrorCode.CODE_03.getMessage()));
    }
}