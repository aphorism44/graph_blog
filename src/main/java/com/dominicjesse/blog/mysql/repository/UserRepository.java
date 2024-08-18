package com.dominicjesse.blog.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dominicjesse.blog.mysql.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}