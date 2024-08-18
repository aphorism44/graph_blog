package com.dominicjesse.blog.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dominicjesse.blog.mysql.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}