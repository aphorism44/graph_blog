package com.dominicjesse.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dominicjesse.blog.enums.AccountType;
import com.dominicjesse.blog.neo4j.entity.Account;
import com.dominicjesse.blog.neo4j.entity.Entry;
import com.dominicjesse.blog.neo4j.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	AccountRepository accountRepo;

	@Override
	public List<Entry> getAllAccountEntries(Account a) {
		return a.getEntries();
	}

	@Override
	public Account getAccountByEmail(String email) {
		List<Account> accountList = accountRepo.findByEmail(email);
		return accountList.size() > 0 ? accountList.get(0) : null;
	}
	
	@Override
	public Account createAccount(String email, AccountType type) {
		Account account = new Account(email, type);
		accountRepo.save(account);
		return account;
	}


}
