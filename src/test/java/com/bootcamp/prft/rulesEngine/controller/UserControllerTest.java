package com.bootcamp.prft.rulesEngine.controller;

import com.bootcamp.prft.rulesEngine.constant.UserErrorCode;
import com.bootcamp.prft.rulesEngine.dto.UserDTO;
import com.bootcamp.prft.rulesEngine.error.exception.UserError;
import com.bootcamp.prft.rulesEngine.error.exception.UserException;
import com.bootcamp.prft.rulesEngine.mapper.UserMapper;
import com.bootcamp.prft.rulesEngine.model.User;
import com.bootcamp.prft.rulesEngine.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    private UserController userController;
    private UserService userService;
    private UserMapper userMapper;

    @BeforeEach
    public void init() {
        userService = mock(UserService.class);
        userMapper = mock(UserMapper.class);
        userController = new UserController(userService, userMapper);
    }

    @Test
    public void testControllerGetUser() {
        UUID id = UUID.randomUUID();
        when(userMapper.fromUser(any())).thenReturn(new UserDTO(id, "sam.4m225y4321@icesi.edu.co", "S@mmy1234", "+571231230123", "John", "Doe"));
        UserDTO userDTOCreate = userController.createUser(new UserDTO(null, "sam.4m225y4321@icesi.edu.co", "S@mmy1234", "+571231230123", "John", "Doe"));
        UserDTO userDTOGet = userController.getUser(userDTOCreate.getId());
        assertNotNull(userDTOGet);
        assertEquals(userDTOCreate, userDTOGet);
        verify(userService, times(1)).getUser(id);
    }

    @Test
    public void testControllerGetUserByIDNonExistent() {
        UUID id = UUID.randomUUID();
        when(userMapper.fromUser(any())).thenThrow(new UserException(HttpStatus.BAD_REQUEST, new UserError(UserErrorCode.CODE_01, UserErrorCode.CODE_01.getMessage())));
        assertThrows(UserException.class, () -> userController.getUser(id), "User ID doesn't exist.");
        verify(userService, times(1)).getUser(id);
    }

    @Test
    public void testControllerGetUsersEmpty() {
        when(userService.getUsers()).thenReturn(new ArrayList<>());
        assertEquals(0, userController.getUsers().size());
        verify(userService, times(1)).getUsers();
    }

    @Test
    public void testControllerGetUsersNonEmpty() {
        when(userMapper.fromUser(any())).thenReturn(new UserDTO(UUID.randomUUID(), "sam.4m225y4321@icesi.edu.co", "S@mmy1234", "+571231230123", "John", "Doe"));
        UserDTO userDTOCreate = userController.createUser(new UserDTO(null,  "sam.4m225y4321@icesi.edu.co", "S@mmy1234", "+571231230123", "John", "Doe"));
        List<User> users = userService.getUsers();
        users.add(userMapper.fromDTO(userDTOCreate));
        when(userService.getUsers()).thenReturn(users);
        assertEquals(1, userController.getUsers().size());
    }

    @Test
    public void testControllerCreateUser() {
        when(userMapper.fromUser(any())).thenReturn(new UserDTO(UUID.randomUUID(), "sam.4m225y4321@icesi.edu.co", "S@mmy1234", "+571231230123", "John", "Doe"));
        assertNotNull(userController.createUser(new UserDTO(null, "sam.4m225y4321@icesi.edu.co", "S@mmy1234", "+571231230123", "John", "Doe")));
        verify(userService, times(1)).createUser(any());
    }
}