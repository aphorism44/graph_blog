package com.dominicjesse.blog.service;

import java.util.Optional;

import com.dominicjesse.blog.neo4j.entity.Account;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountService {
	
	Flux<Account> outputAllAccounts();
	Optional<Account> getAccountByEmail(String email);
	Mono<Account> getAccountById(Long id);
	Optional<Account> getAccountByUserId(String id);
}
