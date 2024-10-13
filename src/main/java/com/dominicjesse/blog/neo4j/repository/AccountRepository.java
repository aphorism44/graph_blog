package com.dominicjesse.blog.neo4j.repository;


import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;

import com.dominicjesse.blog.neo4j.entity.Account;

import reactor.core.publisher.Mono;


public interface AccountRepository extends ReactiveNeo4jRepository<Account, Long> {
	
	Mono<Account> findByEmail(String email);
	Mono<Account> findByUserId(String userId);
}