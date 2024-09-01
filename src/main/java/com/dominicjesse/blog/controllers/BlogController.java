package com.dominicjesse.blog.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Optional;

import com.dominicjesse.blog.service.AccountService;
import com.dominicjesse.blog.service.EntryService;
import com.dominicjesse.blog.dto.AccountDto;
import com.dominicjesse.blog.neo4j.entity.Account;



@Controller
public class BlogController {

	@Autowired
	private AccountService accountService;
	
	 @Autowired
	 private EntryService entryService;
	 	
	@GetMapping("/home")
	 public String home(Model model, Principal principal) {
		String email = principal.getName();
		Optional<Account> account = accountService.getAccountByEmail(email);
		AccountDto aDto = new AccountDto(account.get());
		model.addAttribute("account", aDto);
		return "home";
	 }
}
