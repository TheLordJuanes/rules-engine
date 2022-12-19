package com.bootcamp.prft.rulesEngine.service.impl;

import com.bootcamp.prft.rulesEngine.constant.UserErrorCode;
import com.bootcamp.prft.rulesEngine.error.exception.UserError;
import com.bootcamp.prft.rulesEngine.error.exception.UserException;
import com.bootcamp.prft.rulesEngine.model.User;
import com.bootcamp.prft.rulesEngine.repository.UserRepository;
import com.bootcamp.prft.rulesEngine.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getUser(UUID userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserException(HttpStatus.BAD_REQUEST, new UserError(UserErrorCode.CODE_01, UserErrorCode.CODE_01.getMessage())));
    }

    @Override
    public User createUser(User user) {
        validateUserExists(user.getEmail(), user.getPhoneNumber());
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(),false).collect(Collectors.toList());
    }

    private void validateUserExists(String email, String phoneNumber) {
        if (userRepository.findByEmail(email).isPresent() || userRepository.findByPhoneNumber(phoneNumber).isPresent())
            throw new UserException(HttpStatus.BAD_REQUEST, new UserError(UserErrorCode.CODE_05, UserErrorCode.CODE_05.getMessage()));
    }
}