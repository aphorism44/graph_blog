package com.dominicjesse.blog.dto;

import java.util.Date;

import com.dominicjesse.blog.neo4j.entity.Account;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountDto {
	private String id;
    @Email
    private String email;
    private String accountType;
    private Date createdOn;
    private Date lastUpdated;
    
    public AccountDto(Account a) {
    	this.id = a.getId();
    	this.email = a.getEmail();
    	this.accountType = a.getAccountType().toString();
    	this.createdOn = a.getCreatedOn();
    	this.lastUpdated = a.getLastUpdated();
    }
    
}
