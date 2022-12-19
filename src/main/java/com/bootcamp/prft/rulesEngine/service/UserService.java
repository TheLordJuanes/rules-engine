package com.bootcamp.prft.rulesEngine.service;

import com.bootcamp.prft.rulesEngine.model.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import java.util.UUID;

public interface UserService {

    User getUser(@PathVariable UUID userId);
    User createUser(@RequestBody User user);
    List<User> getUsers();
}