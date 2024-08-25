package com.dominicjesse.blog.service;

import java.util.List;

import com.dominicjesse.blog.dto.UserDto;
import com.dominicjesse.blog.mysql.entity.User;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}