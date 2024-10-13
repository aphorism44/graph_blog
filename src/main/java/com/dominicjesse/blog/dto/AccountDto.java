package com.dominicjesse.blog.dto;

import java.time.LocalDateTime;

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
	private Long id;
    @Email
    private String email;
    private String accountType;
    private LocalDateTime createdOn;
    private LocalDateTime lastUpdated;
    private String userId;
    
    public AccountDto(Account a) {
    	this.id = a.getId();
    	this.email = a.getEmail();
    	this.accountType = a.getAccountType().toString();
    	this.createdOn = a.getCreatedOn().toLocalDateTime();
    	this.lastUpdated = a.getLastUpdated().toLocalDateTime();
    	this.userId = a.getUserId();
    }
    
}
