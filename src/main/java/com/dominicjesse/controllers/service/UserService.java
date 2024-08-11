package com.dominicjesse.controllers.service;

import java.util.List;

import com.dominicjesse.controllers.dto.UserDto;
import com.dominicjesse.controllers.entity.User;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}