package com.dominicjesse.blog.dto;

import java.util.Date;
import java.util.List;

import com.dominicjesse.blog.neo4j.entity.Entry;

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
    private Entry firstEntry;
    private List<EntryDto> entries;
    
    
}
