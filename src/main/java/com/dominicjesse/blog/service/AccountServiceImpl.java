package com.dominicjesse.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dominicjesse.blog.neo4j.entity.Account;
import com.dominicjesse.blog.neo4j.repository.AccountRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	AccountRepository accountRepo;

	@Override
	public Flux<Account> outputAllAccounts() {
		return accountRepo.findAll();
	}

	@Override
	public Mono<Account> getAccountByEmail(String email) {
		return accountRepo.findByEmail(email);
	}

	@Override
	public Mono<Account> getAccountById(Long id) {
		return accountRepo.findById(id);
	}

	@Override
	public Mono<Account> getAccountByUserId(String id) {
		return accountRepo.findByUserId(id);
	}


}
