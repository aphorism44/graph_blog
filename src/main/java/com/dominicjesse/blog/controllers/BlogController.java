package com.dominicjesse.blog.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import com.dominicjesse.blog.service.AccountService;
import com.dominicjesse.blog.service.EntryService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import reactor.core.publisher.Mono;

import com.dominicjesse.blog.dto.AccountDto;
import com.dominicjesse.blog.neo4j.entity.Account;


@CrossOrigin(origins = "http://localhost:8080") 
@Controller
public class BlogController {

	@Autowired
	private AccountService accountService;
	
	 @Autowired
	 private EntryService entryService;
	 	
	@GetMapping("/home")
	 public String home(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        Account account = accountService.getAccountByEmail(email);
        AccountDto accountDto = new AccountDto(account);
        model.addAttribute("account", accountDto);
		return "home";
	 }
}
