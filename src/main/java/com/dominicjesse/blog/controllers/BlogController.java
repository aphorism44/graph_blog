package com.dominicjesse.blog.controllers;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dominicjesse.blog.dto.AccountDto;
import com.dominicjesse.blog.dto.EntryDto;
import com.dominicjesse.blog.dto.NavigationRequest;
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

	private final AccountService accountService;
	
	private final EntryService entryService;
	 
	private final HttpSession session;
	
	
	public BlogController(AccountService accountService, EntryService entryService, HttpSession session) {
        this.accountService = accountService;
		this.entryService = entryService;
		this.session = session;
    }
	
	 	
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
		List<Entry> entries = currentAccount.getEntries();
		List<EntryDto> entryDtos = entries.stream()
                .map(EntityMapper::toEntryDto)
                .collect(Collectors.toList());
		model.addAttribute("entries", entryDtos);
		return "entries";
	}
	
	@GetMapping("/edit")
	public String edit(Model model, HttpServletRequest request, HttpSession session) {
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
		EntryDto entryDto = EntityMapper.toEntryDto(currentEntry);
		model.addAttribute("entry", entryDto);
		return "edit";
	}
	
	@PostMapping("/entries/navigate")
	public String navigateEntry(@RequestBody NavigationRequest navRequest, HttpSession session) {
	    Entry currentEntry = (Entry) session.getAttribute("currentEntry");
	    Entry navEntry = null;
	    switch(navRequest.getDirection()) {
	    	case "next":
		    	navEntry = entryService.getNextEntry(currentEntry);
		      	break;
		    case "previous":
		    	navEntry = entryService.getPreviousEntry(currentEntry);
		    	break;
		    default:
		    	navEntry = currentEntry;
	    }
		session.setAttribute("currentEntry", EntityMapper.toEntryDto(navEntry));
		return "redirect:/edit";
	}
	
	@PostMapping("/entries/save")
    public String saveEntry(@ModelAttribute EntryDto entry, RedirectAttributes redirectAttributes) {
		Entry savedEntry = entryService.saveEntry(EntityMapper.toEntryEntity(entry));
	    session.setAttribute("currentEntry", savedEntry);
	    redirectAttributes.addFlashAttribute("message", "Entry saved successfully!");
	    return "redirect:/edit";
    }
	
	@PostMapping("/entries/create")
	public String createNewEntry(HttpSession session) {
		Entry currentEntry = (Entry) session.getAttribute("currentEntry");
	    Account currentAccount = (Account) session.getAttribute("account");
	    Entry newEntry = entryService.createNewEntry("", "", currentAccount, currentEntry);
	    session.setAttribute("currentEntry", newEntry);
	    return "redirect:/edit";
	}
	 
}
