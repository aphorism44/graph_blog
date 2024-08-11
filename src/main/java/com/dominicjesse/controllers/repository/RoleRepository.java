package com.dominicjesse.controllers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dominicjesse.controllers.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}