package com.dominicjesse.blog.mysql.service;

import java.util.List;

import com.dominicjesse.blog.mysql.entity.User;
import com.dominicjesse.blog.mysql.dto.UserDto;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}