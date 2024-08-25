package com.dominicjesse.blog.neo4j.repository;


import java.util.Optional;

import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;

import com.dominicjesse.blog.neo4j.entity.Account;


public interface AccountRepository extends ReactiveNeo4jRepository<Account, Long> {
	
	Optional<Account> findByEmail(String email);
	Optional<Account> findByUserId(String userId);
}