package com.dominicjesse.blog.controllers;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import com.dominicjesse.blog.service.AccountService;
import com.dominicjesse.blog.service.EntryService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.dominicjesse.blog.dto.AccountDto;
import com.dominicjesse.blog.enums.AccountType;
import com.dominicjesse.blog.neo4j.entity.Account;
import com.dominicjesse.blog.neo4j.entity.Entry;


@CrossOrigin(origins = "http://localhost:8080") 
@Controller
public class BlogController {

	@Autowired
	private AccountService accountService;
	
	 @Autowired
	 private EntryService entryService;
	 
	 @Autowired
	 private HttpSession session;
	 	
	@GetMapping("/home")
	 public String home(HttpServletRequest request, Model model) {
        String email = (String) session.getAttribute("email");
        Account account = accountService.getAccountByEmail(email);
        session.setAttribute("account", account);
        String username = null;
        if (account == null) {
        	account = accountService.createAccount(email, AccountType.FREE);
        }        
        AccountDto accountDto = new AccountDto(account);
        model.addAttribute("account", accountDto);
		return "home";
	 }
	
	@GetMapping("/entries")
	public String entries(Model model) {
		Account currentAccount = (Account) session.getAttribute("account");
		List<Entry> allEntries = entryService.getAllEntries(currentAccount);
		model.addAttribute("entries", allEntries);
		return "entries";
	}
	 
}
