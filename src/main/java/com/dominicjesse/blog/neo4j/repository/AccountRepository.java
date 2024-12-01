package com.dominicjesse.blog.neo4j.repository;


import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.dominicjesse.blog.neo4j.entity.Account;

public interface AccountRepository extends Neo4jRepository<Account, String> {
	
	List<Account> findByEmail(String email);
}