package com.dominicjesse.blog.service;

import java.util.List;

import com.dominicjesse.blog.enums.AccountType;
import com.dominicjesse.blog.neo4j.entity.Account;
import com.dominicjesse.blog.neo4j.entity.Entry;


public interface AccountService {
	
	List<Entry> getAllAccountEntries(Account a);
	Account getAccountByEmail(String email);
	Account createAccount(String email, AccountType type);
}
