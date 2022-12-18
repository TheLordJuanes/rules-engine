package com.bootcamp.prft.rulesEngine.api;

import com.bootcamp.prft.rulesEngine.dto.UserDTO;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RequestMapping("/users")
public interface UserAPI {

    @GetMapping("/{userId}")
    UserDTO getUser(@PathVariable() UUID userId);

    @PostMapping()
    UserDTO createUser(@RequestBody UserDTO userDTO) throws Exception;

    @GetMapping
    List<UserDTO> getUsers();
}