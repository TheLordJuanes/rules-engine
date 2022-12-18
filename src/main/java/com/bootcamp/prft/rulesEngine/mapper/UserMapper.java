package com.bootcamp.prft.rulesEngine.mapper;

import com.bootcamp.prft.rulesEngine.dto.LoggedUserDTO;
import com.bootcamp.prft.rulesEngine.dto.UserDTO;
import com.bootcamp.prft.rulesEngine.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User fromDTO(UserDTO userDTO);

    UserDTO fromUser(User user);

    LoggedUserDTO loggedUserFromUser(User user);

    User fromLoggedUserDTO(LoggedUserDTO loggedUserDTO);
}