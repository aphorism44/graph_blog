package com.dominicjesse.blog.service;

import com.dominicjesse.blog.neo4j.entity.Account;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountService {
	
	Flux<Account> outputAllAccounts();
	Mono<Account> getAccountByEmail(String email);
	Mono<Account> getAccountById(Long id);
	Mono<Account> getAccountByUserId(String id);
}
