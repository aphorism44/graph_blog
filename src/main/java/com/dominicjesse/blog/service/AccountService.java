package com.dominicjesse.blog.service;

import java.util.List;
import java.util.Optional;

import com.dominicjesse.blog.enums.AccountType;
import com.dominicjesse.blog.neo4j.entity.Account;

import reactor.core.publisher.Mono;

public interface AccountService {
	
	List<Account> outputAllAccounts();
	Account getAccountByEmail(String email);
	Account createAccount(String email, AccountType type);
}
