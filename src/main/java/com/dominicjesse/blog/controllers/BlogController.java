package com.dominicjesse.blog.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.dominicjesse.blog.dto.AccountDto;
import com.dominicjesse.blog.dto.EntryDto;
import com.dominicjesse.blog.enums.AccountType;
import com.dominicjesse.blog.neo4j.entity.Account;
import com.dominicjesse.blog.neo4j.entity.Entry;
import com.dominicjesse.blog.service.AccountService;
import com.dominicjesse.blog.service.EntryService;
import com.dominicjesse.blog.utilities.EntityMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


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
        if (account == null)
        	account = accountService.createAccount(email, AccountType.FREE);
        session.setAttribute("account", account);
        AccountDto accountDto = EntityMapper.toAccountDto(account);
        model.addAttribute("account", accountDto);
		return "home";
	 }
	
	@GetMapping("/entries")
	public String entries(Model model) {
		Account currentAccount = (Account) session.getAttribute("account");
		AccountDto currentAccountDto = EntityMapper.toAccountDto(currentAccount);
		List<EntryDto> entryDtos = currentAccountDto.getEntries();
		model.addAttribute("entries", entryDtos);
		return "entries";
	}
	
	@GetMapping("/edit")
	public String edit(Model model) {
		REDO TINY MCE PLUGIN USING FREE MODEL
		Entry currentEntry = (Entry) session.getAttribute("currentEntry");
		if (currentEntry == null) {
			Account currentAccount = (Account) session.getAttribute("account");
			Entry latestEntry = entryService.getLatestEntry(currentAccount);
			if (latestEntry == null) {
				currentEntry = entryService.createFirstEntry(currentAccount);
			}
			currentEntry = latestEntry;
			session.setAttribute("currentEntry", currentEntry);
		}
		model.addAttribute("entry", EntityMapper.toEntryDto(currentEntry));
		return "edit";
	}
	
	@PostMapping("/entries/save")
    public ResponseEntity<String> saveEntry(@RequestBody EntryDto entry) {
        entryService.saveEntry(EntityMapper.toEntryEntity(entry));
        return new ResponseEntity<>("Entry saved successfully", HttpStatus.OK);
    }
	 
}
