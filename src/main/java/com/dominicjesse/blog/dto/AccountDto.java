package com.dominicjesse.blog.dto;

import java.util.Date;

import com.dominicjesse.blog.neo4j.entity.Account;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    	this.accountType = a.getAccount_type().toString();
    	this.createdOn = a.getCreated_on();
    	this.lastUpdated = a.getLast_updated();
    }
    
}
