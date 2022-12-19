package com.bootcamp.prft.rulesEngine.service;

import com.bootcamp.prft.rulesEngine.constant.UserErrorCode;
import com.bootcamp.prft.rulesEngine.error.exception.UserError;
import com.bootcamp.prft.rulesEngine.error.exception.UserException;
import com.bootcamp.prft.rulesEngine.model.User;
import com.bootcamp.prft.rulesEngine.repository.UserRepository;
import com.bootcamp.prft.rulesEngine.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private UserRepository userRepository;
    private UserService userService;
    private List<User> users;

    @BeforeEach
    public void init() {
        userRepository = mock(UserRepository.class);
        userService = new UserServiceImpl(userRepository);
        users = new ArrayList<>();
    }

    public void stage1() {
        User user = new User(null, "sam.4m225y4321@icesi.edu.co", "S@mmy1234", "+571231230123", "John", "Doe");
        userService.createUser(user);
        users.add(user);
    }

    @Test
    public void testGetUser() {
        UUID id = UUID.randomUUID();
        User user = new User(id, "sam.4m225y4321@icesi.edu.co", "S@mmy1234", "+571231230123", "John", "Doe");
        when(userRepository.save(any())).thenReturn(new User(id, "sam.4m225y4321@icesi.edu.co", "S@mmy1234", "+571231230123", "John", "Doe"));
        when(userRepository.findById(any())).thenReturn(Optional.of(new User(id, "sam.4m225y4321@icesi.edu.co", "S@mmy1234", "+571231230123", "John", "Doe")));
        User userCreate = userService.createUser(user);
        User userGet = userService.getUser(id);
        assertNotNull(userGet);
        assertEquals(userCreate, userGet);
    }

    @Test
    public void testGetUserNonExistent() {
        UUID id = UUID.randomUUID();
        when(userRepository.findById(any())).thenThrow(new UserException(HttpStatus.BAD_REQUEST, new UserError(UserErrorCode.CODE_01, UserErrorCode.CODE_01.getMessage())));
        assertThrows(UserException.class, () -> userService.getUser(id), "User ID doesn't exist.");
        verify(userRepository, times(1)).findById(id);
    }

    @Test
    public void testGetUsersEmpty() {
        assertEquals(0, userService.getUsers().size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testGetUsersNonEmpty() {
        when(userRepository.save(any())).thenReturn(new User(UUID.randomUUID(), "sam.4m225y4321@icesi.edu.co", "S@mmy1234", "+571231230123", "John", "Doe"));
        User userCreate = userService.createUser(new User(null, "sam.4m225y4321@icesi.edu.co", "S@mmy1234", "+571231230123", "John", "Doe"));
        List<User> users = new ArrayList<>();
        users.add(userCreate);
        when(userService.getUsers()).thenReturn(users);
        assertEquals(1, userService.getUsers().size());
    }

    @Test
    public void testCreateUser() {
        assertDoesNotThrow(() -> userService.createUser(new User(null, "sam.4m225y4321@icesi.edu.co", "S@mmy1234", "+571231230123", "John", "Doe")));
        verify(userRepository, times(1)).save(any());
    }

    @Test
    public void testCreateUserWithRepeatedEmail() {
        stage1();
        UUID id = UUID.randomUUID();
        when(userRepository.findByEmail(any())).thenReturn(Optional.of(new User()));
        assertThrows(UserException.class, () -> userService.createUser(new User(id, "sam.4m225y4321@icesi.edu.co", "S@mmy1234", "+579876543210", "John", "Doe")), "The email and/or phone number is already registered.");
        verify(userRepository, times(1)).save(any());
    }

    @Test
    public void testCreateUserWithRepeatedPhoneNumber() {
        stage1();
        UUID id = UUID.randomUUID();
        when(userRepository.findByPhoneNumber(any())).thenReturn(Optional.of(new User()));
        assertThrows(UserException.class, () -> userService.createUser(new User(id, "sam@icesi.edu.co", "S@mmy1234", "+571231230123", "John", "Doe")), "The email and/or phone number is already registered.");
        verify(userRepository, times(1)).save(any());
    }

    @Test
    public void testCreateUserWithRepeatedEmailAndPhoneNumber() {
        stage1();
        UUID id = UUID.randomUUID();
        when(userRepository.findByPhoneNumber(any())).thenReturn(Optional.of(new User()));
        assertThrows(UserException.class, () -> userService.createUser(new User(id, "sam.4m225y4321@icesi.edu.co", "S@mmy1234", "+571231230123", "Sam", "Wing")), "The email and/or phone number is already registered.");
        verify(userRepository, times(1)).save(any());
    }
}
