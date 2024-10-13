package com.dominicjesse.blog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dominicjesse.blog.neo4j.entity.Account;
import com.dominicjesse.blog.neo4j.repository.AccountRepository;

import reactor.core.publisher.Mono;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	AccountRepository accountRepo;

	@Override
	@Transactional
	public List<Account> outputAllAccounts() {
		return accountRepo.findAll();
	}

	@Override
	public Account getAccountByEmail(String email) {
		List<Account> accountList = accountRepo.findAllByEmail(email);
		return accountList.get(0);
	}

	@Override
	public Optional<Account> getAccountById(String id) {
		return accountRepo.findById(id);
	}

	@Override
	public Optional<Account> getAccountByUserId(String id) {
		//return accountRepo.findByUserId(id);
		return null;
	}


}
