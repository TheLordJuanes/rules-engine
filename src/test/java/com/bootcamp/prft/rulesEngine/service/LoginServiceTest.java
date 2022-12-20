package com.bootcamp.prft.rulesEngine.service;

import com.bootcamp.prft.rulesEngine.constant.UserErrorCode;
import com.bootcamp.prft.rulesEngine.dto.LoginDTO;
import com.bootcamp.prft.rulesEngine.dto.TokenDTO;
import com.bootcamp.prft.rulesEngine.error.exception.UserException;
import com.bootcamp.prft.rulesEngine.model.User;
import com.bootcamp.prft.rulesEngine.repository.UserRepository;
import com.bootcamp.prft.rulesEngine.service.impl.LoginServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import java.util.Collections;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LoginServiceTest {

    private LoginService loginService;
    private UserRepository userRepository;
    private final UUID testUserUUID = UUID.fromString("1887fcda-c227-400b-ad7c-541802a92d74");
    private User testUser;
    private LoginDTO testLoginDTO;

    @BeforeEach
    void init() {
        userRepository = mock(UserRepository.class);
        loginService = new LoginServiceImpl(userRepository);
        testUser = new User(testUserUUID,"test@icesi.edu.co","Qwerty123#","C14","+573161234567",null);
        testLoginDTO = new LoginDTO();
    }

    @Test
    void testLoginPhone() {
        testLoginDTO.setEmailPhone(testUser.getPhoneNumber());
        testLoginDTO.setPassword(testUser.getPassword());
        when(userRepository.findAll()).thenReturn(Collections.singletonList(testUser));
        TokenDTO tokenDTO = loginService.login(testLoginDTO);
        verify(userRepository,times(1)).findAll();
        assertNotNull(tokenDTO);
    }

    @Test
    void testLoginPhoneWrongPassword() {
        testLoginDTO.setEmailPhone(testUser.getPhoneNumber());
        testLoginDTO.setPassword(testUser.getPassword()+"46654");
        when(userRepository.findAll()).thenReturn(Collections.singletonList(testUser));
        UserException thrown = assertThrows(UserException.class, () -> loginService.login(testLoginDTO), "User Exception expected");
        assertEquals(UserErrorCode.CODE_03.getMessage(),thrown.getError().getMessage());
        assertEquals(HttpStatus.BAD_REQUEST,thrown.getHttpStatus());
        verify(userRepository,times(1)).findAll();
    }

    @Test
    void testLoginEmail() {
        testLoginDTO.setEmailPhone(testUser.getEmail());
        testLoginDTO.setPassword(testUser.getPassword());
        when(userRepository.findAll()).thenReturn(Collections.singletonList(testUser));
        TokenDTO tokenDTO = loginService.login(testLoginDTO);
        verify(userRepository,times(1)).findAll();
        assertNotNull(tokenDTO);
    }

    @Test
    void testLoginEmailWrongPassword() {
        testLoginDTO.setEmailPhone(testUser.getPhoneNumber());
        testLoginDTO.setPassword(testUser.getPassword()+"46654");
        when(userRepository.findAll()).thenReturn(Collections.singletonList(testUser));
        UserException thrown = assertThrows(UserException.class, () -> loginService.login(testLoginDTO), "User Exception expected");
        assertEquals(UserErrorCode.CODE_03.getMessage(),thrown.getError().getMessage());
        assertEquals(HttpStatus.BAD_REQUEST,thrown.getHttpStatus());
        verify(userRepository,times(1)).findAll();
    }

    @Test
    void testLoginUserNotFound() {
        testLoginDTO.setEmailPhone("test." + testUser.getEmail());
        testLoginDTO.setPassword(testUser.getPassword());
        when(userRepository.findAll()).thenReturn(Collections.emptyList());
        UserException thrown = assertThrows(UserException.class, () -> loginService.login(testLoginDTO), "User Exception expected");
        assertEquals(UserErrorCode.CODE_03.getMessage(),thrown.getError().getMessage());
        assertEquals(HttpStatus.BAD_REQUEST,thrown.getHttpStatus());
        verify(userRepository,times(1)).findAll();
    }
}